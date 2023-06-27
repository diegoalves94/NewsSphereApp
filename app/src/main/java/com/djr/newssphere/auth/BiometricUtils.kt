package com.djr.newssphere.auth

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.djr.newssphere.R

object BiometricUtils {
    private const val TAG = "BiometricUtils"
//    fun createBiometricPrompt(
//        activity: AppCompatActivity,
//        processSuccess: (BiometricPrompt.AuthenticationResult) -> Unit
//    ): BiometricPrompt {
//        val executor = ContextCompat.getMainExecutor(activity)
//
//        val callback = object : BiometricPrompt.AuthenticationCallback() {
//
//            override fun onAuthenticationError(errCode: Int, errString: CharSequence) {
//                super.onAuthenticationError(errCode, errString)
//                Log.d(TAG, "errCode is $errCode and errString is: $errString")
//            }
//
//            override fun onAuthenticationFailed() {
//                super.onAuthenticationFailed()
//                Log.d(TAG, "Biometric authentication failed for unknown reason.")
//            }
//
//            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
//                super.onAuthenticationSucceeded(result)
//                Log.d(TAG, "Authentication was successful")
//                processSuccess(result)
//            }
//        }
//        return BiometricPrompt(activity, executor, callback)
//    }
//
//    fun createBiometricPromptInfo(activity: AppCompatActivity): BiometricPrompt.PromptInfo =
//        BiometricPrompt.PromptInfo.Builder().apply {
//            setTitle(activity.getString(R.string.prompt_title))
//            setSubtitle(activity.getString(R.string.prompt_subtitle))
//            setDescription(activity.getString(R.string.prompt_description))
//            setNegativeButtonText("Cancel")
//        }.build()

    fun showBiometricPrompt(activity: AppCompatActivity) {
        isBiometricSupported(activity).let {
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Cancel")
                .build()

            val biometricPrompt = BiometricPrompt(activity, ContextCompat.getMainExecutor(activity),
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        // Handle authentication error
                        showMessage(activity, "Authentication error: $errString")
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        // Handle authentication success
                        showMessage(activity,"Authentication succeeded!")
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        // Handle authentication failure
                        showMessage(activity, "Authentication failed.")
                    }
                })

            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun showMessage(activity: AppCompatActivity, message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun isBiometricSupported(activity: AppCompatActivity): Boolean {
        val biometricManager = BiometricManager.from(activity)
        val canAuthenticate = biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
        when (canAuthenticate) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                // The user can authenticate with biometrics, continue with the authentication process
                return true
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE, BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE, BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // Handle the error cases as needed in your app
                return false
            }

            else -> {
                // Biometric status unknown or another error occurred
                return false
            }
        }
    }
}