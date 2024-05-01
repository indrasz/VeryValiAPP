package com.example.veryvali.ui.screen.response

import com.example.veryvali.ui.components.CustomButton
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.veryvali.R
import com.example.veryvali.data.model.Recipient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponseScreen(navController: NavHostController, recipient: Recipient)  {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Tanggapan",
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
        ResponseContent(innerPadding, navController, recipient)
    }
}

@Composable
fun ResponseContent(innerPadding: PaddingValues, navController: NavHostController, recipient: Recipient) {

    var text by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, start = 16.dp, top = 72.dp),
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
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Berikan Tanggapan Kelayakan",
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Nama : ",
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Light,
                            style = TextStyle(fontSize = 14.sp)
                        )

                        Text(
                            text = recipient.nama,
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Light,
                            style = TextStyle(fontSize = 14.sp)
                        )
                    }

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Kecamatan : ",
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Light,
                            style = TextStyle(fontSize = 14.sp)
                        )

                        Text(
                            text = recipient.kecamatan,
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Light,
                            style = TextStyle(fontSize = 14.sp)
                        )
                    }

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Kelurahan/Desa : ",
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            fontWeight = FontWeight.Light,
                            style = TextStyle(fontSize = 14.sp)
                        )

                        Text(
                            text = recipient.desa,
                            fontWeight = FontWeight.Light,
                            style = TextStyle(fontSize = 14.sp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Berikan Tanggapan",
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.img_sad),
                        contentDescription = "Hero Image",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Tidak layak sebagai penerima manfaat",
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Light,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
//                    horizontalAlignment = Alignment.Start,
                ) {
                    Column {
                        Text(
                            text = "Alasan",
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
                            text = "Catatan",
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

                    Text(
                        text = "Lampiran Foto (Data Pendukung)",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 2.dp)
                            ) {
                                Text(
                                    text = "Foto 1",
                                    modifier = Modifier
                                        .padding(bottom = 8.dp),
                                    fontWeight = FontWeight.Light,
                                    style = TextStyle(fontSize = 14.sp)
                                )

                                Box(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(color = Color(0xFFFFFFFF))
                                        .border(
                                            width = 1.dp,
                                            color = Color.Black,
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Add Icon",
                                        tint = Color.Black,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 2.dp)
                            ) {
                                Text(
                                    text = "Foto 2",
                                    modifier = Modifier
                                        .padding(bottom = 8.dp),
                                    fontWeight = FontWeight.Light,
                                    style = TextStyle(fontSize = 14.sp)
                                )

                                Box(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(color = Color(0xFFFFFFFF))
                                        .border(
                                            width = 1.dp,
                                            color = Color.Black,
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Add Icon",
                                        tint = Color.Black,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomButton(
                    text = "Kirim Tanggapan",
                    fullWidth = false,
                    onClick = {
                        navController.navigate("success")
                    }
                )
            }
        }
    }
}

private const val IMAGE_REQUEST_CODE = 123

@Composable
fun ImageInputBox(
    modifier: Modifier = Modifier,
    initialImage: ImageBitmap? = null,
    onImageSelected: (ImageBitmap) -> Unit
) {
    var imageBitmap by remember { mutableStateOf(initialImage) }
    val context = LocalContext.current

    Box(
        modifier = modifier
            .size(56.dp)
            .background(color = Color.White)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                // Handle image selection
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                val activity = context as? Activity
                activity?.startActivityForResult(intent, IMAGE_REQUEST_CODE)
            }
    ) {
        if (imageBitmap != null) {
            Image(
                bitmap = imageBitmap!!,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        } else {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    // Handle result of image selection
    val activityResultLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                intent?.data?.let { uri ->
                    val bitmap = uri.toBitmap(context)
                    imageBitmap = bitmap
                    onImageSelected(bitmap)
                }
            }
        }

    DisposableEffect(Unit) {
        onDispose {
            activityResultLauncher.unregister()
        }
    }
}

private fun Uri.toBitmap(context: Context): ImageBitmap {
    val inputStream = context.contentResolver.openInputStream(this)
    return BitmapFactory.decodeStream(inputStream).asImageBitmap()
}
