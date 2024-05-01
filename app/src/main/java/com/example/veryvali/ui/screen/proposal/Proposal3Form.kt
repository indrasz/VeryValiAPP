package com.example.veryvali.ui.screen.proposal

import android.widget.Toast
import com.example.veryvali.ui.components.CustomButton
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.veryvali.data.model.Individual
import com.example.veryvali.data.model.SurveyType
import com.example.veryvali.data.repository.IndividualRepository
import com.example.veryvali.data.repository.SurveyRepository

@Composable
fun Proposal3Form(
    innerPadding: PaddingValues,
    proposal3Data: String,
    surveyRepository: SurveyRepository,
    onNext: (String) -> Unit
) {

    val options = listOf("YA", "TIDAK")
    var question1Expanded by remember { mutableStateOf(false) }
    var question2Expanded by remember { mutableStateOf(false) }
    var question3Expanded by remember { mutableStateOf(false) }
    var question4Expanded by remember { mutableStateOf(false) }
    var question5Expanded by remember { mutableStateOf(false) }
    var question6Expanded by remember { mutableStateOf(false) }
    var question7Expanded by remember { mutableStateOf(false) }
    var question8Expanded by remember { mutableStateOf(false) }
    var question9Expanded by remember { mutableStateOf(false) }
    var question10Expanded by remember { mutableStateOf(false) }

    var question1 by remember { mutableStateOf(options[0]) }
    var question2 by remember { mutableStateOf(options[0]) }
    var question3 by remember { mutableStateOf(options[0]) }
    var question4 by remember { mutableStateOf(options[0]) }
    var question5 by remember { mutableStateOf(options[0]) }
    var question6 by remember { mutableStateOf(options[0]) }
    var question7 by remember { mutableStateOf(options[0]) }
    var question8 by remember { mutableStateOf(options[0]) }
    var question9 by remember { mutableStateOf(options[0]) }
    var question10 by remember { mutableStateOf(options[0]) }

    val ctx = LocalContext.current
    val isLoading by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column {
                Text(
                    text = "Apakah memiliki tempat berteduh tetap sehari hari?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 14.sp)
                )
                Box(
                    modifier = Modifier.clickable(onClick = { question1Expanded = !question1Expanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = question1,
                        onValueChange = { question1 = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.Black,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { question1Expanded = !question1Expanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { question1Expanded = !question1Expanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (question1Expanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    question1 = selectionOption
                                    question1Expanded = false
                                }
                            )
                        }
                    }
                }
                Text(
                    text = "Kemensos RI Nomor 262/HUK/2022",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle(1),
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Column {
                Text(
                    text = "Apakah kepala keluarga atau pengurus keluarga masih berkerja?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 14.sp)
                )
                Box(
                    modifier = Modifier.clickable(onClick = { question2Expanded = !question2Expanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = question2,
                        onValueChange = { question2 = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.Black,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { question2Expanded = !question2Expanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { question2Expanded = !question2Expanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (question2Expanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    question2 = selectionOption
                                    question2Expanded = false
                                }
                            )
                        }
                    }
                }
                Text(
                    text = "Kemensos RI Nomor 262/HUK/2022",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle(1),
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Column {
                Text(
                    text = "Apakah pengeluaran pangan lebih besar (> 70%) dari total pengeluaran?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 14.sp)
                )
                Box(
                    modifier = Modifier.clickable(onClick = { question3Expanded = !question3Expanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = question3,
                        onValueChange = { question3 = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.Black,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { question2Expanded = !question2Expanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { question3Expanded = !question3Expanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (question3Expanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    question3 = selectionOption
                                    question3Expanded = false
                                }
                            )
                        }
                    }
                }
                Text(
                    text = "Kemensos RI Nomor 262/HUK/2022",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle(1),
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Column {
                Text(
                    text = "Apakah tempat tinggal sebagian besar berlantai tanah dan/atau plesteran?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 14.sp)
                )
                Box(
                    modifier = Modifier.clickable(onClick = { question4Expanded = !question4Expanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = question4,
                        onValueChange = { question4 = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.Black,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { question4Expanded = !question4Expanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { question4Expanded = !question4Expanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (question4Expanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    question4 = selectionOption
                                    question4Expanded = false
                                }
                            )
                        }
                    }
                }
                Text(
                    text = "Kemensos RI Nomor 262/HUK/2022",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle(1),
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Column {
                Text(
                    text = "Apakah tempat tinggal memiliki fasilitas buang air kecil / besar sendiri?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 14.sp)
                )
                Box(
                    modifier = Modifier.clickable(onClick = { question5Expanded = !question5Expanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = question5,
                        onValueChange = { question5 = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.Black,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { question5Expanded = !question5Expanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { question5Expanded = !question5Expanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (question5Expanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    question5 = selectionOption
                                    question5Expanded = false
                                }
                            )
                        }
                    }
                }
                Text(
                    text = "Kemensos RI Nomor 262/HUK/2022",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle(1),
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Column {
                Text(
                    text = "Apakah target survey tinggal bersama anggota keluarga yang lain?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 14.sp)
                )
                Box(
                    modifier = Modifier.clickable(onClick = { question6Expanded = !question6Expanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = question6,
                        onValueChange = { question6 = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.Black,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { question6Expanded = !question6Expanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { question6Expanded = !question6Expanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (question6Expanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    question6 = selectionOption
                                    question6Expanded = false
                                }
                            )
                        }
                    }
                }
                Text(
                    text = "Kemensos RI Nomor 262/HUK/2022",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle(1),
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Column {
                Text(
                    text = "Apakah pernah khawatir atau pernah tidak makan dalam setahun terakhir?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 14.sp)
                )
                Box(
                    modifier = Modifier.clickable(onClick = { question7Expanded = !question7Expanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = question7,
                        onValueChange = { question7 = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.Black,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { question7Expanded = !question7Expanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { question7Expanded = !question7Expanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (question7Expanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    question7 = selectionOption
                                    question7Expanded = false
                                }
                            )
                        }
                    }
                }
                Text(
                    text = "Kemensos RI Nomor 262/HUK/2022",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle(1),
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Column {
                Text(
                    text = "Apakah ada pengeluaran untuk pakaian selama 1 (satu) tahun terakhir?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 14.sp)
                )
                Box(
                    modifier = Modifier.clickable(onClick = { question8Expanded = !question8Expanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = question8,
                        onValueChange = { question8 = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.Black,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { question8Expanded = !question8Expanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { question8Expanded = !question8Expanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (question8Expanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    question8 = selectionOption
                                    question8Expanded = false
                                }
                            )
                        }
                    }
                }
                Text(
                    text = "Kemensos RI Nomor 262/HUK/2022",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle(1),
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Column {
                Text(
                    text = "Apakah tempat tinggal sebagian besar berdinding bambu / kawat / kayu?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 14.sp)
                )
                Box(
                    modifier = Modifier.clickable(onClick = { question9Expanded = !question9Expanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = question9,
                        onValueChange = { question9 = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.Black,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { question9Expanded = !question9Expanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { question9Expanded = !question9Expanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (question9Expanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    question9 = selectionOption
                                    question9Expanded = false
                                }
                            )
                        }
                    }
                }
                Text(
                    text = "Kemensos RI Nomor 262/HUK/2022",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle(1),
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Column {
                Text(
                    text = "Apakah sumber penerangan berasal dari listrik dengan daya 450V atau bukan listrik?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 14.sp)
                )
                Box(
                    modifier = Modifier.clickable(onClick = { question10Expanded = !question10Expanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = question10,
                        onValueChange = { question10 = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.Black,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { question10Expanded = !question10Expanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { question10Expanded = !question10Expanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (question10Expanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    question10 = selectionOption
                                    question10Expanded = false
                                }
                            )
                        }
                    }
                }
                Text(
                    text = "Kemensos RI Nomor 262/HUK/2022",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle(1),
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator() // Loading indicator
            } else {
                CustomButton(
                    text = "Selanjutnya",
                    fullWidth = false,
                    onClick = {
                        val surveyTypeData = SurveyType(
                            question1 = question1,
                            question2 = question2,
                            question3 = question3,
                            question4 = question4,
                            question5 = question5,
                            question6 = question6,
                            question7 = question7,
                            question8 = question8,
                            question9 = question9,
                            question10 = question10,
                        )
                        addSurveyType(
                            surveyTypeData = surveyTypeData,
                            onNext = onNext,
                            surveyRepository = surveyRepository,
                            onError = { errorMessage ->
                                Toast.makeText(ctx, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                )
            }
        }
    }
}

private fun addSurveyType(
    surveyTypeData: SurveyType,
    onNext: (String) -> Unit,
    surveyRepository: SurveyRepository,
    onError: (String) -> Unit
) {
    surveyRepository.createSurveyType(
        surveyTypeData,
        onSuccess = {
            onNext("Data individu berhasil ditambahkan")
        },
        onFailure = { errorMessage ->
            onError(errorMessage ?: "Gagal menambahkan data individu")
        }
    )
}