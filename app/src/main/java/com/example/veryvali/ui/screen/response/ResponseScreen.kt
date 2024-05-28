package com.example.veryvali.ui.screen.response

import android.annotation.SuppressLint
import com.example.veryvali.ui.components.CustomButton
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
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
import androidx.core.content.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.veryvali.R
import com.example.veryvali.data.model.Proposal
import com.example.veryvali.data.model.Recipient
import com.example.veryvali.data.model.Response
import com.example.veryvali.data.model.User
import com.example.veryvali.di.ResponseViewModel
import com.example.veryvali.di.SurveyViewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponseScreen(navController: NavHostController, recipient: Recipient, user: User?)  {

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
        ResponseContent(innerPadding, navController, recipient, user)
    }
}

@Composable
fun ResponseContent(innerPadding: PaddingValues, navController: NavHostController, recipient: Recipient, user: User?) {

//    var text by remember { mutableStateOf("") }

    val responseViewModel: ResponseViewModel = viewModel()
    var statusKelayakan by remember { mutableStateOf(false) }
    var alasan by remember { mutableStateOf("") }
    var catatan by remember { mutableStateOf("") }
    val isLoading by responseViewModel.loadingState.collectAsState()

    var selectedFirstImageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var selectedSecondImageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF0E7CDA), shape = RoundedCornerShape(24.dp))
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
                    Box(
                        modifier = Modifier
                            .background(color = if (statusKelayakan) Color.Green else Color.Red, shape = RoundedCornerShape(50))
                            .padding(16.dp)
                    ) {
                        IconButton(
                            onClick = { statusKelayakan = !statusKelayakan },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            if (statusKelayakan) {
                                Icon(
                                    Icons.Filled.Check,
                                    contentDescription = "Checked Icon",
                                    tint = Color.White,
                                    modifier = Modifier.size(80.dp)
                                )
                            } else {
                                Icon(
                                    Icons.Filled.Close,
                                    contentDescription = "Close Icon",
                                    tint = Color.White,
                                    modifier = Modifier.size(80.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text =( if (statusKelayakan) "Layak sebagai penerima manfaat" else "Tidak layak sebagai penerima manfaat"),
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
                            value = alasan,
                            onValueChange = { alasan = it },
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
                            value = catatan,
                            onValueChange = { catatan = it },
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
                                ImageInputBox(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(color = Color(0xFFFFFFFF))
                                        .border(
                                            width = 1.dp,
                                            color = Color.Black,
                                            shape = RoundedCornerShape(16.dp)
                                        ),
                                    initialImage = selectedFirstImageBitmap,
                                    onImageSelected = { imageBitmap ->
                                        selectedFirstImageBitmap = imageBitmap
                                    }
                                )

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
                                ImageInputBox(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(color = Color(0xFFFFFFFF))
                                        .border(
                                            width = 1.dp,
                                            color = Color.Black,
                                            shape = RoundedCornerShape(16.dp)
                                        ),
                                    initialImage = selectedSecondImageBitmap,
                                    onImageSelected = { imageBitmap ->
                                        selectedSecondImageBitmap = imageBitmap
                                    }
                                )

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
                if (isLoading) {
                    CircularProgressIndicator() // Loading indicator
                } else {
                    CustomButton(
                        text = "Kirim Tanggapan",
                        fullWidth = false,
                        onClick = {
                            selectedFirstImageBitmap?.let { firstBitmap ->
                                selectedSecondImageBitmap?.let { secondBitmap ->
                                    user?.let{ userData ->
                                        val response = Response(
                                            statusKelayakan = statusKelayakan,
                                            alasan = alasan,
                                            catatan = catatan,
                                            idRecipient = recipient.nik,
                                            idUser = userData.userId
                                        )

                                        responseViewModel.createResponseWithRecipientId(
                                            response,
                                            recipient.nik,
                                            firstBitmap.asAndroidBitmap(),
                                            secondBitmap.asAndroidBitmap()
                                        )
                                        navController.navigate("success")
                                    }




                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

//private fun ImageBitmap.asAndroidBitmap(): Bitmap {
//    // Convert ImageBitmap to Android Bitmap
//    val androidBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
//    androidBitmap.copyPixelsFromBuffer((this as androidx.compose.ui.graphics.AndroidImageBitmap).buffer)
//    return androidBitmap
//}

//private const val IMAGE_REQUEST_CODE = 123

@Composable
fun ImageInputBox(
    modifier: Modifier = Modifier,
    initialImage: ImageBitmap? = null,
    onImageSelected: (ImageBitmap) -> Unit
) {
    var imageBitmap by remember { mutableStateOf(initialImage) }
    val context = LocalContext.current

    val imageUri = remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            imageUri.value?.let { uri ->
                val bitmap = uri.toBitmap(context)
                imageBitmap = bitmap
                onImageSelected(bitmap)
            }
        }
    }

    Box(
        modifier = modifier
            .size(120.dp)
            .background(color = Color.White)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                val uri = createImageUri(context)
                imageUri.value = uri
                launcher.launch(uri)
            }
    ) {
        if (imageBitmap != null) {
            Image(
                bitmap = imageBitmap!!,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
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
}

private fun createImageUri(context: Context): Uri {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "temp_image_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    }
    return context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        ?: throw IllegalStateException("Failed to create new MediaStore record.")
}

private fun Uri.toBitmap(context: Context): ImageBitmap {
    val inputStream = context.contentResolver.openInputStream(this)
    return BitmapFactory.decodeStream(inputStream)?.asImageBitmap()
        ?: throw IllegalArgumentException("Failed to load bitmap from URI: $this")
}