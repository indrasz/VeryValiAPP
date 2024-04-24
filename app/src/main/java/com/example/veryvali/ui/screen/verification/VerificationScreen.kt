package com.example.veryvali.ui.screen.verification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(navController: NavHostController)  {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Verifikasi",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {  navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        ScrollContent(innerPadding, navController)
    }
}

@Composable
fun ScrollContent(innerPadding: PaddingValues, navController: NavHostController) {

    var search by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp, start = 24.dp, end = 24.dp), // 56.dp adalah tinggi dari top bar (atau sesuaikan dengan kebutuhan Anda)
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                label = { Text("Cari") },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFA6D4FD), shape = RoundedCornerShape(24.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Column (modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 2.dp)){
                        Text(
                            text = "Kecamatan",
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
                                .background(
                                    color = Color(0xFFFFFFFF),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                        )
                    }
                    Column(modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 2.dp)) {
                        Text(
                            text = "Kelurahan",
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
                                .background(
                                    color = Color(0xFFFFFFFF),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFA6D4FD), shape = RoundedCornerShape(24.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Column (modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 2.dp)){
                        Text(
                            text = "Nama & Umur",
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
                                .background(
                                    color = Color(0xFFFFFFFF),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                        )
                    }
                    Column(modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 2.dp))
                    {
                        Text(
                            text = "Tanggapan Kelayakan",
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
                                .background(
                                    color = Color(0xFFFFFFFF),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
                        )

                    }

                }

                CollapseItem(
                    itemName = "Daftar bantuan sosial",
                    icon = Icons.Default.ArrowDropDown
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFFA6D4FD),
                                shape = RoundedCornerShape(24.dp)
                            )
                            .background(Color.White, RoundedCornerShape(16.dp))

                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 6.dp),
                            horizontalArrangement = Arrangement.Center

                        ) {
                            Column (modifier = Modifier.weight(1f)){
                                Text(
                                    text = "BPNPT",
                                    modifier = Modifier
                                        .padding(bottom = 6.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                                Row {
                                    Text(
                                        text = "Status:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Periode:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }

                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "PBI-JK",
                                    modifier = Modifier
                                        .padding(bottom = 8.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                                Row {
                                    Text(
                                        text = "Status:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Periode:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 6.dp),
                            horizontalArrangement = Arrangement.Center

                        ) {
                            Column (modifier = Modifier.weight(1f)){
                                Text(
                                    text = "BST",
                                    modifier = Modifier
                                        .padding(bottom = 6.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                                Row {
                                    Text(
                                        text = "Status:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Periode:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }

                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "PKH",
                                    modifier = Modifier
                                        .padding(bottom = 8.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                                Row {
                                    Text(
                                        text = "Status:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Periode:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 6.dp),
                            horizontalArrangement = Arrangement.Center

                        ) {
                            Column (modifier = Modifier.weight(1f)){
                                Text(
                                    text = "BLT-BBM",
                                    modifier = Modifier
                                        .padding(bottom = 6.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                                Row {
                                    Text(
                                        text = "Status:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Periode:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }

                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Bantuan Yatim Piatu",
                                    modifier = Modifier
                                        .padding(bottom = 8.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                                Row {
                                    Text(
                                        text = "Status:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Periode:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 6.dp),
                            horizontalArrangement = Arrangement.Center

                        ) {
                            Column (modifier = Modifier.weight(1f)){
                                Text(
                                    text = "Rumah Sejahtera Terpadu (RST)",
                                    modifier = Modifier
                                        .padding(bottom = 6.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                                Row {
                                    Text(
                                        text = "Status:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Periode:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }

                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Pemakanan",
                                    modifier = Modifier
                                        .padding(bottom = 8.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                                Row {
                                    Text(
                                        text = "Status:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Periode:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 6.dp),
                            horizontalArrangement = Arrangement.Center

                        ) {
                            Column (modifier = Modifier.weight(1f)){
                                Text(
                                    text = "Sembako Adaptif",
                                    modifier = Modifier
                                        .padding(bottom = 6.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                                Row {
                                    Text(
                                        text = "Status:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Periode:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }

                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "PENA",
                                    modifier = Modifier
                                        .padding(bottom = 8.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                                Row {
                                    Text(
                                        text = "Status:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Periode:",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Text(
                                        text = "-",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp),
                                        fontWeight = FontWeight.Light,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                }
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .pointerInput(Unit){
                        detectTapGestures { navController.navigate("response") }
                    }
                        .fillMaxSize()
                        .padding(0.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Klik untuk memberi tanggapan",
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Light,
                        style = TextStyle(fontSize = 12.sp)
                    )
                }

            }
        }
    }
}

@Composable
fun CollapseItem(
    itemName: String,
    icon: ImageVector,
    expandedContent: @Composable (() -> Unit)? = null
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .clickable { expanded = !expanded }
    ) {
        Text(
            text = itemName,
            modifier = Modifier.padding(top = 12.dp),
            style = TextStyle(fontSize = 14.sp)
        )
        IconButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.padding(0.dp)

        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Expand / Collapse Icon"
            )
        }
    }

    if (expanded) {
        expandedContent?.invoke()
    }
}

