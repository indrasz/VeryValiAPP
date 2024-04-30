package com.example.veryvali.ui.screen.proposal

import android.widget.Toast
import com.example.veryvali.ui.components.CustomButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.veryvali.data.repository.RecipientRepository

@Composable
fun Proposal1Form(
    innerPadding: PaddingValues,
    proposal1Data: String,
    recipientRepository: RecipientRepository,
    onNext: (String) -> Unit,
) {
    var nik by remember { mutableStateOf(proposal1Data) }
    val ctx = LocalContext.current
    var isLoading by remember { mutableStateOf(false) } // State untuk menentukan apakah sedang loading atau tidak

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFA6D4FD), shape = RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "NIK",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 14.sp)
            )

            OutlinedTextField(
                value = nik,
                onValueChange = { nik = it },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    focusedBorderColor = Color.White,
                ),
                textStyle = TextStyle(color = Color.Black),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(16.dp)),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    cekDataNIK(nik, onNext, recipientRepository) { errorMessage ->
                        // Panggil Toast untuk menampilkan pesan kesalahan
                        Toast.makeText(ctx, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                })
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            // Tampilkan loading indicator jika isLoading true
            if (isLoading) {
                CircularProgressIndicator() // Loading indicator
            } else {
                CustomButton(
                    text = "Selanjutnya",
                    fullWidth = false,
                    onClick = {
                        isLoading = true // Set isLoading true saat tombol ditekan untuk menampilkan loading indicator
                        cekDataNIK(nik, onNext, recipientRepository) { errorMessage ->
                            // Panggil Toast untuk menampilkan pesan kesalahan
                            Toast.makeText(ctx, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }
}


// Fungsi untuk mengecek data NIK
private fun cekDataNIK(
    nik: String,
    onNext: (String) -> Unit,
    recipientRepository: RecipientRepository,
    onError: (String) -> Unit // Tambahkan parameter onError untuk menampilkan pesan kesalahan
//    ctx: androidx.compose.ui.platform.LocalContext.current
) {

    recipientRepository.checkNIK(
        nik,
        onSuccess = {
            // Jika data NIK ditemukan, lanjutkan ke halaman selanjutnya
            onNext(nik)
        },
        onFailure = {
            // Jika data NIK tidak ditemukan, panggil callback onError dengan pesan kesalahan
            onError("Data dengan NIK $nik tidak ditemukan")
        }
    )
}
