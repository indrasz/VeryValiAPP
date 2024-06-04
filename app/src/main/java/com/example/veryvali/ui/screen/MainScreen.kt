package com.example.veryvali.ui.screen

import com.example.veryvali.ui.components.CustomButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.veryvali.R


@Composable
fun MainScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "Hero Image",
            modifier = Modifier
                .size(250.dp)
                .padding(top = 90.dp)
        )

        Column(
            modifier = Modifier
                .padding(top = 0.dp)
                .height(340.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF0E7CDA).copy(alpha = 0.05f),
                        shape = RoundedCornerShape(
                            topStart = 180.dp,
                            topEnd = 180.dp
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            color = Color(0xFF0E7CDA).copy(alpha = 0.1f),
                            shape = RoundedCornerShape(
                                topStart = 180.dp,
                                topEnd = 180.dp
                            )
                        )
//                        .padding(16.dp) // Menambahkan padding agar button tidak terlalu dekat dengan tepi
                        .height(280.dp) // Mengatur tinggi Column
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                ) {
                    Column(
                        modifier = Modifier
//                            .fillMaxSize()
                            .background(
                                color = Color(0xFF0E7CDA),
                                shape = RoundedCornerShape(
                                    topStart = 180.dp,
                                    topEnd = 180.dp
                                )
                            )
                            .padding(16.dp) // Menambahkan padding agar button tidak terlalu dekat dengan tepi
                            .height(180.dp) // Mengatur tinggi Column
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { navController.navigate("login") },
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .height(62.dp)
                                .width(180.dp),
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF23317D))
                        ) {
                            Text(
                                text =  "Masuk",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        }
                        Button(
                            onClick = { navController.navigate("register") },
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .height(62.dp)
                                .width(180.dp)
                                .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(24.dp)),
                            shape = RoundedCornerShape(24.dp),
                        ) {
                            Text(
                                text =  "Daftar",
                                color = Color.White,
                                fontSize = 24.sp
                            )
                        }
                    }
                }
            }
        }
    }
}