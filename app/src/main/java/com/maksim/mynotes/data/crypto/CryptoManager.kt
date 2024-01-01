package com.maksim.mynotes.data.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.io.InputStream
import java.io.OutputStream
import java.lang.IllegalStateException
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class CryptoManager {

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private val encryptCipher = Cipher.getInstance(TRANSFORMATION).apply {
        init(Cipher.ENCRYPT_MODE, getKey())
    }

    private fun getDecryptCipher(iv: ByteArray): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        }
    }

    companion object {
        private const val ALG = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALG/$BLOCK_MODE/$PADDING"
        private const val DEFAULT_KEY_ALIAS = "default_key"
    }

    private fun getKey(): SecretKey {
        return (keyStore.getEntry(DEFAULT_KEY_ALIAS, null) as? KeyStore.SecretKeyEntry)?.secretKey
            ?: createKey(DEFAULT_KEY_ALIAS)
    }

    private fun createKey(alias: String): SecretKey {
        return KeyGenerator.getInstance(ALG).apply {
            init(
                KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                ).setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
        }.generateKey()
    }


    fun encrypt(data: String): String {
        val encryptedBytes = encryptCipher.doFinal(data.toByteArray())

        val encrypted = Base64.encode(encryptedBytes, Base64.DEFAULT).decodeToString()
        val iv = Base64.encode(encryptCipher.iv, Base64.DEFAULT).decodeToString()
        val separator = "|"

        val result = StringBuilder().append(iv).append(separator).append(encrypted)

        return result.toString()
    }

    fun decrypt(data: String): String {
        val parts = data.split("|")
        if (parts.size == 2) {
            val iv = Base64.decode(parts.first(), Base64.DEFAULT)
            val encryptedData = Base64.decode(parts.last(), Base64.DEFAULT)
            return getDecryptCipher(iv).doFinal(encryptedData).decodeToString()
        } else {
            throw IllegalStateException("The data is corrupted")
        }
    }

    fun encrypt(bytes: ByteArray, outputStream: OutputStream): ByteArray {
        val encrypted = encryptCipher.doFinal(bytes)
        outputStream.use {
            it.write(encryptCipher.iv.size)
            it.write(encryptCipher.iv)
            it.write(encrypted.size)
            it.write(encrypted)
        }
        return encrypted
    }

    fun decrypt(inputStream: InputStream): ByteArray {
        return inputStream.use {
            val ivSize = it.read()
            val iv = ByteArray(ivSize)
            it.read(iv)
            val dataSize = it.read()
            val data = ByteArray(dataSize)
            it.read(data)
            getDecryptCipher(iv).doFinal(data)
        }
    }

}