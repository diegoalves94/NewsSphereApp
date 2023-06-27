package com.djr.newssphere.auth

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat

object BiometricUtils {
    private const val TAG = "BiometricUtils"

    fun showBiometricPrompt(activity: AppCompatActivity) {
        isBiometricSupported(activity).let {
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Use your biometric credential")
                .setNegativeButtonText("Cancel")
                .build()

            val biometricPrompt = BiometricPrompt(activity, ContextCompat.getMainExecutor(activity),
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        Log.d("Authentication:", errString.toString())
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        Log.d("Authentication:", "Succeeded!")
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Log.d("Authentication:", "Failed!")
                    }
                })

            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun isBiometricSupported(activity: AppCompatActivity): Boolean {
        val biometricManager = BiometricManager.from(activity)
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                true
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE, BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE, BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                false
            }

            else -> {
                // Biometric status unknown or another error occurred
                false
            }
        }
    }
}