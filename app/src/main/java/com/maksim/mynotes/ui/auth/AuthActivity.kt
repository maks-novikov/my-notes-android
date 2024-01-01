package com.maksim.mynotes.ui.auth

import android.os.Bundle
import android.util.Log
import com.maksim.mynotes.data.crypto.CryptoManager
import com.maksim.mynotes.databinding.ActivityAuthBinding
import com.maksim.mynotes.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


    private fun encrypt() {
        val cryptoManager = CryptoManager()

        val message =  "maksim novikov, born in 1993 currently lived in Israel, works at Upnlugged company."
        val encrypted = cryptoManager.encrypt(message)

       /* val file = File(filesDir, "secret.txt")
        if (!file.exists())
            file.createNewFile()

        val fos = FileOutputStream(file)

        val encryptResult = cryptoManager.encrypt(
            "maksim novikov, born in 1993 currently lived in Israel, works at Upnlugged company.".toByteArray(),
            fos
        ).decodeToString()*/

        Log.d("CryptoManager", "encryptResult -> $encrypted")
    }

    private fun decrypt(data: String){
        val cryptoManager = CryptoManager()

        val decryptResult = cryptoManager.decrypt(data)

       /* val file = File(filesDir, "secret.txt")
        if (!file.exists())
            return

        val fos = FileInputStream(file)
        val decryptedResult = cryptoManager.decrypt(fos).decodeToString()*/
        Log.d("CryptoManager", "decryptedResult -> $decryptResult")

    }
}