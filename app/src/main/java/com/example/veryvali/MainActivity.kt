package com.example.veryvali

import android.annotation.SuppressLint
import android.content.pm.PackageManager
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    @OptIn(ExperimentalPermissionsApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            var mapsLatitude by remember { mutableStateOf("") }
            var mapsLongitude by remember { mutableStateOf("") }

            // Permission state for multiple permissions
            val locationPermissionState = rememberMultiplePermissionsState(
                permissions = listOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )

            // Check permissions
            LaunchedEffect(locationPermissionState.allPermissionsGranted) {
                if (locationPermissionState.allPermissionsGranted) {
                    getLocation(fusedLocationClient) { latitude, longitude ->
                        mapsLatitude = latitude.toString()
                        mapsLongitude = longitude.toString()
                    }
                } else {
                    locationPermissionState.launchMultiplePermissionRequest()
                }
            }


            VeryValiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFFFFF),
                ) {
                    ComposeNavigation(mapsLatitude, mapsLongitude)
                }
            }
        }
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun ComposeNavigation(mapsLatitude: String, mapsLongitude: String) {
        val navController = rememberNavController()
        val viewModel: BansosViewModel = viewModel()

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
                ProposalScreen(navController = navController, mapsLatitude, mapsLongitude)
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

    private fun getLocation(client: FusedLocationProviderClient, onLocationReceived: (Double, Double) -> Unit) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            client.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    onLocationReceived(it.latitude, it.longitude)
                }
            }
        }
    }
}

