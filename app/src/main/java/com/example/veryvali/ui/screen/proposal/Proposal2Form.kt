package com.example.veryvali.ui.screen.proposal

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.veryvali.ui.components.CustomButton
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.veryvali.data.model.Individual
import com.example.veryvali.data.repository.IndividualRepository

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Proposal2Form(
    innerPadding: PaddingValues,
    proposal2Data: String,
    individualRepository: IndividualRepository,
    onNext: (String) -> Unit
) {
    var noKK by remember { mutableStateOf(proposal2Data) }
    var nik by remember { mutableStateOf(proposal2Data) }
    var namaLengkap by remember { mutableStateOf(proposal2Data) }
    var ibuKandung by remember { mutableStateOf(proposal2Data) }
    var tanggalLahir by remember { mutableStateOf(proposal2Data) }
    var tempatLahir by remember { mutableStateOf(proposal2Data) }
    var alamat by remember { mutableStateOf(proposal2Data) }
    var kecamatan by remember { mutableStateOf(proposal2Data) }
    var kelurahan by remember { mutableStateOf(proposal2Data) }
    var lingkungan by remember { mutableStateOf(proposal2Data) }

    val jenisPekerjaanOptions = listOf("Pekerjaan 1", "Pekerjaan 2", "Pekerjaan 3", "Pekerjaan 4", "Pekerjaan 5")
    var jenisPekerjaanExpanded by remember { mutableStateOf(false) }
    var jenisPekerjaan by remember { mutableStateOf(jenisPekerjaanOptions[0]) }

    val jenisKelaminOptions = listOf("Laki-laki", "Perempuan")
    var jenisKelaminExpanded by remember { mutableStateOf(false) }
    var jenisKelamin by remember { mutableStateOf(jenisKelaminOptions[0]) }

    val statusPerkawinanOptions = listOf("Sudah Menikah", "Belum Menikah")
    var statusPerkawinanExpanded by remember { mutableStateOf(false) }
    var statusPerkawinan by remember { mutableStateOf(statusPerkawinanOptions[0]) }

    val hubunganKeluargaOptions = listOf("Hubungan 1", "Hubungan 2")
    var hubunganKeluargaExpanded by remember { mutableStateOf(false) }
    var hubunganKeluarga by remember { mutableStateOf(hubunganKeluargaOptions[0]) }

    val pendidikanTerakhirOptions = listOf("SD", "SMP", "SMA", "Kuliah")
    var pendidikanTerakhirExpanded by remember { mutableStateOf(false) }
    var pendidikanTerakhir by remember { mutableStateOf(pendidikanTerakhirOptions[0]) }


//    var selectedDate by remember { mutableStateOf(proposal2Data) }
    val ctx = LocalContext.current
    val isLoading by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        initialDisplayedMonthMillis = System.currentTimeMillis(),
        yearRange = 2000..2024
    )
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Select Date") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false
                        // Handle confirm button click if needed
                    }
                ) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog.value = false }
                ) {
                    Text(text = "Cancel")
                }
            },
            text = {
                DatePicker(state = datePickerState)
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        )
    {
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
                    value = noKK,
                    onValueChange = { noKK = it },
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
                        )
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
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(16.dp)
                        ),
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
                    value = namaLengkap,
                    onValueChange = { namaLengkap = it },
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
                    value = ibuKandung,
                    onValueChange = { ibuKandung = it },
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
                )
            }
            Column  {
                Text(
                    text = "Jenis Pekerjaan",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 14.sp)
                )

                Box(
                    modifier = Modifier.clickable(onClick = { jenisPekerjaanExpanded = !jenisPekerjaanExpanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = jenisPekerjaan,
                        onValueChange = { jenisPekerjaan = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.White,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { jenisPekerjaanExpanded = !jenisPekerjaanExpanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { jenisPekerjaanExpanded = !jenisPekerjaanExpanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (jenisPekerjaanExpanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        jenisPekerjaanOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    jenisPekerjaan = selectionOption
                                    jenisPekerjaanExpanded = false
                                }
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Column (modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 2.dp)){
                    Text(
                        text = "Tanggal Lahir",
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = tanggalLahir,
                        onValueChange = { tanggalLahir = it },
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
                            )
                    )
//                        DatePicker(
//                            state = datePickerState,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 8.dp)
//                                .background(
//                                    color = Color(0xFFFFFFFF),
//                                    shape = RoundedCornerShape(16.dp)
//                                )
//                        )
                }
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 2.dp)) {
                    Text(
                        text = "Tempat Lahir",
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = tempatLahir,
                        onValueChange = { tempatLahir = it },
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
                            )
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Column (modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 2.dp)){
                    Text(
                        text = "Jenis Kelamin",
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    Box(
                        modifier = Modifier.clickable(onClick = { jenisKelaminExpanded = !jenisKelaminExpanded })
                    ) {
                        OutlinedTextField(
                            readOnly = true,
                            value = jenisKelamin,
                            onValueChange = { jenisKelamin = it },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedTextColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                focusedTextColor = Color.White,
                                focusedBorderColor = Color.White,
                            ),
                            textStyle = TextStyle(color = Color.Black),
                            singleLine = true,
                            shape = RoundedCornerShape(16.dp),
                            trailingIcon = {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Dropdown Icon",
                                    modifier = Modifier.clickable(onClick = { jenisKelaminExpanded = !jenisKelaminExpanded })
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .clickable(onClick = { jenisKelaminExpanded = !jenisKelaminExpanded })
                                .background(
                                    color = Color(0xFFFFFFFF),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                        )
                    }
                    if (jenisKelaminExpanded) {
                        Column(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(24.dp))
                                .fillMaxWidth()
                                .heightIn(max = 200.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .padding(4.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            jenisKelaminOptions.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(text = selectionOption) },
                                    onClick = {
                                        jenisKelamin = selectionOption
                                        jenisKelaminExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 2.dp)) {
                    Text(
                        text = "Status Perkawinan",
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    Box(
                        modifier = Modifier.clickable(onClick = { statusPerkawinanExpanded = !statusPerkawinanExpanded })
                    ) {
                        OutlinedTextField(
                            readOnly = true,
                            value = statusPerkawinan,
                            onValueChange = { statusPerkawinan = it },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedTextColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                focusedTextColor = Color.White,
                                focusedBorderColor = Color.White,
                            ),
                            textStyle = TextStyle(color = Color.Black),
                            singleLine = true,
                            shape = RoundedCornerShape(16.dp),
                            trailingIcon = {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Dropdown Icon",
                                    modifier = Modifier.clickable(onClick = { statusPerkawinanExpanded = !statusPerkawinanExpanded })
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .clickable(onClick = { statusPerkawinanExpanded = !statusPerkawinanExpanded })
                                .background(
                                    color = Color(0xFFFFFFFF),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                        )
                    }
                    if (statusPerkawinanExpanded) {
                        Column(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(24.dp))
                                .fillMaxWidth()
                                .heightIn(max = 200.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .padding(4.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            statusPerkawinanOptions.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(text = selectionOption) },
                                    onClick = {
                                        statusPerkawinan = selectionOption
                                        statusPerkawinanExpanded = false
                                    }
                                )
                            }
                        }
                    }
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

                Box(
                    modifier = Modifier.clickable(onClick = { hubunganKeluargaExpanded = !hubunganKeluargaExpanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = hubunganKeluarga,
                        onValueChange = { hubunganKeluarga = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.White,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { hubunganKeluargaExpanded = !hubunganKeluargaExpanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { hubunganKeluargaExpanded = !hubunganKeluargaExpanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (hubunganKeluargaExpanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        hubunganKeluargaOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    hubunganKeluarga = selectionOption
                                    hubunganKeluargaExpanded = false
                                }
                            )
                        }
                    }
                }
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

                Box(
                    modifier = Modifier.clickable(onClick = { pendidikanTerakhirExpanded = !pendidikanTerakhirExpanded })
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = pendidikanTerakhir,
                        onValueChange = { pendidikanTerakhir = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedTextColor = Color.White,
                            focusedBorderColor = Color.White,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable(onClick = { pendidikanTerakhirExpanded = !pendidikanTerakhirExpanded })
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(onClick = { pendidikanTerakhirExpanded = !pendidikanTerakhirExpanded })
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                }
                if (pendidikanTerakhirExpanded) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(4.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        pendidikanTerakhirOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption) },
                                onClick = {
                                    pendidikanTerakhir = selectionOption
                                    pendidikanTerakhirExpanded = false
                                }
                            )
                        }
                    }
                }
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
                    value = alamat,
                    onValueChange = { alamat = it },
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
                    value = kecamatan,
                    onValueChange = { kecamatan = it },
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
                    value = kelurahan,
                    onValueChange = { kelurahan = it },
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
                        )

                )
            }
            Column {
                Text(
                    text = "Lingkungan",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 14.sp)
                )

                OutlinedTextField(
                    value = lingkungan,
                    onValueChange = { lingkungan = it },
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
                        )
                )
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
                    text = "Selanjutnya",
                    fullWidth = false,
                    onClick = {
                        val individualData = Individual(
                            noKK = noKK,
                            nik = nik,
                            namaLengkap = namaLengkap,
                            ibuKandung = ibuKandung,
                            jenisPekerjaan = jenisPekerjaan,
                            tanggalLahir = tanggalLahir,
                            tempatLahir = tempatLahir,
                            jenisKelamin = jenisKelamin,
                            statusPerkawinan = statusPerkawinan,
                            hubunganKeluarga = hubunganKeluarga,
                            pendidikanTerakhir = pendidikanTerakhir,
                            alamat = alamat,
                            kecamatan = kecamatan,
                            kelurahan = kelurahan,
                            lingkungan = lingkungan
                        )
                        addDataIndividual(
                            individualData = individualData,
                            onNext = onNext,
                            individualRepository = individualRepository,
                            onError = { errorMessage ->
                                // Panggil Toast untuk menampilkan pesan kesalahan
                                Toast.makeText(ctx, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                )
            }
        }
    }
}


private fun addDataIndividual(
    individualData: Individual,
    onNext: (String) -> Unit,
    individualRepository: IndividualRepository,
    onError: (String) -> Unit
) {
    individualRepository.createIndividual(
        individualData,
        onSuccess = {
            onNext("Data individu berhasil ditambahkan")
        },
        onFailure = { errorMessage ->
            onError(errorMessage ?: "Gagal menambahkan data individu")
        }
    )
}