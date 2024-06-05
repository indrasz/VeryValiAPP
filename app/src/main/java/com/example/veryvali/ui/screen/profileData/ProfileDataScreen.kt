package com.example.veryvali.ui.screen.profileData

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.veryvali.R
import com.example.veryvali.data.model.User
import com.example.veryvali.data.repository.AuthRepository
import com.example.veryvali.di.UserViewModel
import com.example.veryvali.ui.components.CustomButton
import com.example.veryvali.ui.screen.home.HomeContent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDataScreen(navController: NavHostController, user: User?, userViewModel: UserViewModel)  {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Data Diri",
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
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFFFFFF), // Change this to your desired color
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                )
            )
        },
    ) { innerPadding ->
        ScrollContent(innerPadding, user, userViewModel)
    }
}

@Composable
fun ScrollContent(innerPadding: PaddingValues, user: User?, userViewModel: UserViewModel) {

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFFFFF))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(fontSize = 24.sp),
                    text = "Verivali menggunakan informasi ini untuk memverifikasi identitas Anda dan untuk menjaga keamanan data diri Anda"
                )
                user?.let {
                    Column {
                        Text(
                            text = "Nama Pengguna",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 18.sp)
                        )
                        OutlinedTextField(
                            readOnly = true,
                            value = it.username,
                            onValueChange = { },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedTextColor = Color.Black,
                                unfocusedBorderColor = Color.Black,
                                focusedTextColor = Color.Black,
                                focusedBorderColor = Color.Black,
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
                            text = "Email",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 18.sp)
                        )
                        OutlinedTextField(
                            readOnly = true,
                            value = it.email,
                            onValueChange = { },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedTextColor = Color.Black,
                                unfocusedBorderColor = Color.Black,
                                focusedTextColor = Color.Black,
                                focusedBorderColor = Color.Black,
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
                            text = "NIP",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 18.sp)
                        )
                        OutlinedTextField(
                            readOnly = true,
                            value = it.nip,
                            onValueChange = { },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedTextColor = Color.Black,
                                unfocusedBorderColor = Color.Black,
                                focusedTextColor = Color.Black,
                                focusedBorderColor = Color.Black,
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
                            text = "Nomor Whatsapp",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 18.sp)
                        )
                        OutlinedTextField(
                            readOnly = true,
                            value = it.whatsappNumber,
                            onValueChange = { },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedTextColor = Color.Black,
                                unfocusedBorderColor = Color.Black,
                                focusedTextColor = Color.Black,
                                focusedBorderColor = Color.Black,
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
        }
    }
}
