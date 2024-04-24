package com.example.veryvali.ui.screen.login

import com.example.veryvali.ui.components.CustomButton
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.veryvali.R
import com.example.veryvali.data.repository.AuthRepository

//import com.example.veryvali.di.login.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val ctx = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        TopAppBar(
            title = { Text(
                text = "Masuk",
//                textAlign = TextAlign.Center,
//                modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp)
            )},
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
        )
        Image(
            painter = painterResource(id = R.drawable.img_hero2),
            contentDescription = "Hero Image",
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "Masuk",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            CustomButton(
                text = "Masuk",
                fullWidth = false,
                onClick = {
                    // Panggil fungsi login dari AuthRepository saat tombol "Masuk" ditekan
                    AuthRepository().userLogin(email, password,
                        onSuccess = {
                            // Login berhasil, navigasi ke layar beranda
                            navController.navigate("home")
                        },
                        onFailure = { errorMessage ->
                            // Login gagal, tampilkan pesan kesalahan kepada pengguna
                            // Misalnya dengan menampilkan pesan toast atau dialog
                            Toast.makeText(ctx, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            )
        }
    }
}
