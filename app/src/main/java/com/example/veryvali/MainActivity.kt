package com.example.veryvali

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.veryvali.ui.screen.login.LoginScreen
import com.example.veryvali.ui.screen.MainScreen
import com.example.veryvali.ui.screen.home.HomeScreen
import com.example.veryvali.ui.screen.profile.ProfileScreen
import com.example.veryvali.ui.screen.proposal.ProposalScreen
import com.example.veryvali.ui.screen.register.RegisterScreen
import com.example.veryvali.ui.screen.response.ResponseScreen
import com.example.veryvali.ui.screen.success.SuccessScreen
import com.example.veryvali.ui.screen.verification.VerificationScreen
import com.example.veryvali.ui.theme.VeryValiTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VeryValiTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    ComposeNavigation()
                }
            }
        }
    }

    @Composable
    fun ComposeNavigation() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "main"){
            composable("main"){
                MainScreen(navController = navController)
            }
            composable("login"){
                LoginScreen(navController = navController)
            }
            composable("register"){
                RegisterScreen(navController = navController)
            }
            composable("home"){
                HomeScreen(navController = navController)
            }
            composable("profile"){
                ProfileScreen(navController = navController)
            }
            composable("proposal"){
                ProposalScreen(navController = navController)
            }
            composable("verification"){
                VerificationScreen(navController = navController)
            }
            composable("response"){
                ResponseScreen(navController = navController)
            }
            composable("success"){
                SuccessScreen(navController = navController)
            }
        }

    }
}

