package com.djr.newssphere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.djr.newssphere.auth.BiometricUtils

class MainActivity : AppCompatActivity(){

    private lateinit var biometricPrompt: BiometricPrompt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        if (isBiometricSupported()) {
//            showBiometricPrompt()
            BiometricUtils.showBiometricPrompt(this)
//        } else {
//            // Handle the case when biometric authentication is not supported
//        }
    }


}