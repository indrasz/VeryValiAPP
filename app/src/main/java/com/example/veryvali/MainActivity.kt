package com.example.veryvali

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.veryvali.di.BansosViewModel
import com.example.veryvali.di.RecipientsViewModel
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

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VeryValiTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color.White) {
                    ComposeNavigation()
                }
            }
        }
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun ComposeNavigation() {
        val navController = rememberNavController()
        val viewModel: BansosViewModel = viewModel()
//        val recipientsState by viewModel.recipientsState.collectAsState()

        NavHost(navController = navController, startDestination = "home"){
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
            composable("verification") {
                VerificationScreen(navController = navController)
            }
            composable("response/{recipientId}",
                arguments = listOf(navArgument("recipientId") { type = NavType.StringType })) { backStackEntry ->
                val recipientId = backStackEntry.arguments?.getString("recipientId")
                val recipient = viewModel.recipientsState.value.let { state ->
                    if (state is BansosViewModel.BansosState.Success) {
                        state.recipients.find { it.id == recipientId }
                    } else {
                        null
                    }
                }
                if (recipient != null) {
                    ResponseScreen(navController = navController, recipient = recipient)
                }
            }
            composable("success"){
                SuccessScreen(navController = navController)
            }
        }
    }
}

