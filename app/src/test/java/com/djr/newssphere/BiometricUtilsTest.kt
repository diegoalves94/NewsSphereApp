package com.djr.newssphere

import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import com.djr.newssphere.auth.BiometricUtils
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class BiometricUtilsTest {

    @Test
    fun showBiometricPrompt_callsBiometricPromptAuthenticate() {
        // Mock the dependencies
        val mockActivity: AppCompatActivity = mock()
        val mockBiometricPrompt: BiometricPrompt = mock()

        // Set the mock BiometricPrompt instance
        BiometricUtils.biometricPromptInstance = mockBiometricPrompt

        // Call the showBiometricPrompt() method
        BiometricUtils.showBiometricPrompt(mockActivity)

        // Verify that BiometricPrompt.authenticate() is called
        verify(mockBiometricPrompt).authenticate(any())
    }

    @Test
    fun isBiometricSupported_returnsTrueWhenCanAuthenticateSuccess() {
        // Mock the dependencies
        val mockActivity: AppCompatActivity = mock()
        val mockBiometricManager: BiometricManager = mock()

        // Mock the BiometricManager.canAuthenticate() method
        whenever(mockBiometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK))
            .thenReturn(BiometricManager.BIOMETRIC_SUCCESS)

        // Set the mock BiometricManager instance
        BiometricUtils.biometricManagerInstance = mockBiometricManager

        // Call the isBiometricSupported() method
        val result = BiometricUtils.isBiometricSupported(mockActivity)

        // Verify that the result is true
        assert(result)
    }

    @Test
    fun isBiometricSupported_returnsFalseWhenCannotAuthenticate() {
        // Mock the dependencies
        val mockActivity: AppCompatActivity = mock()
        val mockBiometricManager: BiometricManager = mock()

        // Mock the BiometricManager.canAuthenticate() method
        whenever(mockBiometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK))
            .thenReturn(BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE)

        // Set the mock BiometricManager instance
        BiometricUtils.biometricManagerInstance = mockBiometricManager

        // Call the isBiometricSupported() method
        val result = BiometricUtils.isBiometricSupported(mockActivity)

        // Verify that the result is false
        assert(!result)
    }
}

