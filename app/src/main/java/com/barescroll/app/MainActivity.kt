package com.barescroll.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.barescroll.app.onboarding.OnboardingScreen
import com.barescroll.app.ui.theme.BareScrollTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BareScrollTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    // First run only. Once onboarding completes, the app opens
                    // on the Today tab (SCREENS_AND_FLOWS.md).
                    OnboardingScreen(
                        onFinished = {
                            // TODO: persist onboarding-done in DataStore and
                            // navigate to the Today tab when it exists.
                        },
                    )
                }
            }
        }
    }
}
