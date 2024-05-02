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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.veryvali.data.model.Proposal
import com.example.veryvali.data.model.Recipient
import com.example.veryvali.data.model.SurveyType
import com.example.veryvali.data.repository.ProposalRepository
import com.example.veryvali.data.repository.SurveyRepository
import com.example.veryvali.di.ProposalViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Proposal4Form(
    innerPadding: PaddingValues,
    proposalViewModel: ProposalViewModel,
    recipientData: Recipient?,
    onNextStepWithData: (Proposal) -> Unit
) {
//    var text by remember { mutableStateOf(proposal4Data) }

    val programBansosOptions = listOf("BNPT", "BST", "BLT-BBM", "RUMAH SEJAHTERA TERPADU (RST)", "SEMBAKO ADAPTIF", "PBI-JK", "BANTUAN YATIM PIATU", "PEMAKANAN", "PENA")
    var programBansosExpanded by remember { mutableStateOf(false) }
    var programBansos by remember { mutableStateOf(programBansosOptions[0]) }

    val disabilitasOptions = listOf("YA", "TIDAK")
    var disabilitasExpanded by remember { mutableStateOf(false) }
    var disabilitas by remember { mutableStateOf(disabilitasOptions[0]) }

    val statusOrangTuaOptions = listOf("ORANG TUA LENGKAP", "YATIM", "PIATU", "YATIM PIATU")
    var statusOrangTuaExpanded by remember { mutableStateOf(false) }
    var statusOrangTua by remember { mutableStateOf(statusOrangTuaOptions[0]) }


    var mapsLatitude by remember { mutableStateOf("") }
    var mapsLongitude by remember { mutableStateOf("") }
    var tanggalHamil by remember { mutableStateOf("") }

    val date = remember { mutableStateOf(LocalDate.now())}
    val isOpen = remember { mutableStateOf(false)}

    val ctx = LocalContext.current
    val isLoading by proposalViewModel.loadingState.collectAsState()

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
                    .background(color = Color(0xFF0E7CDA), shape = RoundedCornerShape(24.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Column {
                    Text(
                        text = "Program Bansos",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    Box(
                        modifier = Modifier.clickable(onClick = { programBansosExpanded = !programBansosExpanded })
                    ) {
                        OutlinedTextField(
                            readOnly = true,
                            value = programBansos,
                            onValueChange = { programBansos = it },
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
                                    modifier = Modifier.clickable(onClick = { programBansosExpanded = !programBansosExpanded })
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .clickable(onClick = { programBansosExpanded = !programBansosExpanded })
                                .background(
                                    color = Color(0xFFFFFFFF),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                        )
                    }
                    if (programBansosExpanded) {
                        Column(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(24.dp))
                                .fillMaxWidth()
                                .heightIn(max = 200.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .padding(4.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            programBansosOptions.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(text = selectionOption) },
                                    onClick = {
                                        programBansos = selectionOption
                                        programBansosExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Column {
                    Text(
                        text = "Disabilitas",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    Box(
                        modifier = Modifier.clickable(onClick = { disabilitasExpanded = !disabilitasExpanded })
                    ) {
                        OutlinedTextField(
                            readOnly = true,
                            value = disabilitas,
                            onValueChange = { disabilitas = it },
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
                                    modifier = Modifier.clickable(onClick = { disabilitasExpanded = !disabilitasExpanded })
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .clickable(onClick = { disabilitasExpanded = !disabilitasExpanded })
                                .background(
                                    color = Color(0xFFFFFFFF),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                        )
                    }
                    if (disabilitasExpanded) {
                        Column(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(24.dp))
                                .fillMaxWidth()
                                .heightIn(max = 200.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .padding(4.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            disabilitasOptions.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(text = selectionOption) },
                                    onClick = {
                                        disabilitas = selectionOption
                                        disabilitasExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Column {
                    Text(
                        text = "Tanggal Hamil",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        readOnly = true,
                        value = date.value.format(DateTimeFormatter.ISO_DATE),
                        onValueChange = { tanggalHamil = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedTextColor = Color.White,
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
                        trailingIcon = {
                            Icon(
                                Icons.Default.DateRange,
                                contentDescription = "Calendar",
                                modifier = Modifier.clickable(onClick = { isOpen.value = true })
                            )
                        },
                    )

                    if (isOpen.value) {
                        CustomDatePickerDialog(
                            onAccept = {
                                isOpen.value = false // close dialog
                            },
                            onCancel = {
                                isOpen.value = false //close dialog
                            }
                        )
                    }
                }

                Column {
                    Text(
                        text = "Disabilitas",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    Box(
                        modifier = Modifier.clickable(onClick = { statusOrangTuaExpanded = !statusOrangTuaExpanded })
                    ) {
                        OutlinedTextField(
                            readOnly = true,
                            value = statusOrangTua,
                            onValueChange = { statusOrangTua = it },
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
                                    modifier = Modifier.clickable(onClick = { statusOrangTuaExpanded = !statusOrangTuaExpanded })
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .clickable(onClick = { statusOrangTuaExpanded = !statusOrangTuaExpanded })
                                .background(
                                    color = Color(0xFFFFFFFF),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                        )
                    }
                    if (statusOrangTuaExpanded) {
                        Column(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(24.dp))
                                .fillMaxWidth()
                                .heightIn(max = 200.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .padding(4.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            statusOrangTuaOptions.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(text = selectionOption) },
                                    onClick = {
                                        statusOrangTua = selectionOption
                                        statusOrangTuaExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Column {
                    Text(
                        text = "Maps Latitude",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = mapsLatitude,
                        onValueChange = { mapsLatitude = it },
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
                        text = "Maps Longitude",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 14.sp)
                    )

                    OutlinedTextField(
                        value = mapsLongitude,
                        onValueChange = { mapsLongitude = it },
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
                            val proposal = recipientData?.let {
                                Proposal(
                                    programBansos = programBansos,
                                    disabilitas = disabilitas,
                                    tanggalHamil = tanggalHamil,
                                    statusOrangTua = statusOrangTua,
                                    mapsLatitude = mapsLatitude,
                                    mapsLongitude = mapsLongitude,
                                    idRecipient = it.id
                                )
                            }
                            proposalViewModel.createProposal(proposal!!, recipientData.id) { proposal ->
                                onNextStepWithData(proposal)
                            }
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    onAccept: (Long?) -> Unit,
    onCancel: () -> Unit
) {
    val state = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = { },
        confirmButton = {
            Button(onClick = { onAccept(state.selectedDateMillis) }) {
                Text("Accept")
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = state)
    }
}