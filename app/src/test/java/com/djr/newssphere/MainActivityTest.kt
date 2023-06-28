package com.djr.newssphere

import androidx.navigation.NavController
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.djr.newssphere.auth.BiometricUtils
import com.djr.newssphere.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun onSupportNavigateUp_navigatesUp() {
        // Mock the NavController
        val mockNavController: NavController = mock()

        // Launch the activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Set the mock NavController
        activityScenario.onActivity { activity ->
            activity.navController = mockNavController
        }

        // Perform onSupportNavigateUp() and verify the navigation
        activityScenario.onActivity { activity ->
            activity.onSupportNavigateUp()
        }

        // Verify that NavController.navigateUp() is called
        verify(mockNavController).navigateUp()
    }

    @Test
    fun onSupportNavigateUp_returnsSuperCallWhenNavigationFails() {
        // Mock the NavController to return false for navigateUp()
        val mockNavController: NavController = mock()
        whenever(mockNavController.navigateUp()).thenReturn(false)

        // Launch the activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Set the mock NavController
        activityScenario.onActivity { activity ->
            activity.navController = mockNavController
        }

        // Perform onSupportNavigateUp() and verify the super call
        activityScenario.onActivity { activity ->
            val result = activity.onSupportNavigateUp()
            assert(result) // Verify that super.onSupportNavigateUp() is called
        }
    }

}
