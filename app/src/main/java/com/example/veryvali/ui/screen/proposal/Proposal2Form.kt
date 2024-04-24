package com.example.veryvali.ui.screen.proposal

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Proposal2Form(innerPadding: PaddingValues, proposal2Data: String, onNext: (String) -> Unit) {
    var text by remember { mutableStateOf(proposal2Data) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        )
    {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFA6D4FD), shape = RoundedCornerShape(24.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Column {
                    Text(
                        text = "Nomor Kartu Keluarga",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
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
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                    )
                }
                Column {
                    Text(
                        text = "NIK",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
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
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                    )
                }
                Column {
                    Text(
                        text = "Nama lengkap sesuai KTP",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
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
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                    )
                }
                Column {
                    Text(
                        text = "Ibu Kandung",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
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
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                    )
                }
                Column {
                    Text(
                        text = "Jenis Pekerjaan",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
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
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Column (modifier = Modifier.weight(1f).padding(horizontal = 2.dp)){
                        Text(
                            text = "Tanggal Lahir",
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 14.sp)
                        )

                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
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
                            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                        )
                    }
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 2.dp)) {
                        Text(
                            text = "Tempat Lahir",
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 14.sp)
                        )

                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
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
                            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Column (modifier = Modifier.weight(1f).padding(horizontal = 2.dp)){
                        Text(
                            text = "Jenis Kelamin",
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 14.sp)
                        )

                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
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
                            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                        )
                    }
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 2.dp)) {
                        Text(
                            text = "Status Perkawinan",
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 14.sp)
                        )

                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
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
                            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                        )
                    }
                }
                Column {
                    Text(
                        text = "Hubungan Keluarga",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
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
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                    )
                }
                Column {
                    Text(
                        text = "Pendidikan Terakhir",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
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
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                    )
                }
                Column {
                    Text(
                        text = "Alamat",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
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
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                    )
                }
                Column {
                    Text(
                        text = "Kecamatan",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
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
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                    )
                }
                Column {
                    Text(
                        text = "Kelurahan",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
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
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Column (modifier = Modifier.weight(1f).padding(horizontal = 2.dp)){
                        Text(
                            text = "Rt",
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 14.sp)
                        )

                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
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
                            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                        )
                    }
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 2.dp)) {
                        Text(
                            text = "Rw",
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 14.sp)
                        )

                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
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
                            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomButton(
                    text = "Selanjutnya",
                    fullWidth = false,
                    onClick = { onNext(text) }
                )
            }
        }
    }
}
