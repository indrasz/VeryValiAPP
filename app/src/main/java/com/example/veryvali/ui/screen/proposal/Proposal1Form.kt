package com.example.veryvali.ui.screen.proposal

import android.util.Log
import android.widget.Toast
import com.example.veryvali.ui.components.CustomButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.veryvali.data.model.Recipient
import com.example.veryvali.di.ProposalViewModel


@Composable
fun Proposal1Form(
    proposalViewModel: ProposalViewModel,
    onNextStepWithData: (Recipient) -> Unit,
//    onFailure: (String) -> Unit
) {
    var nik by remember { mutableStateOf("") }
    val loadingState by proposalViewModel.loadingState.collectAsState()
    val ctx = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Column {
            Text(
                text = "NIK",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 14.sp)
            )
            Box {
                OutlinedTextField(
                    value = nik,
                    onValueChange = { nik = it },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color.White,
                        unfocusedBorderColor = Color(0XFF0E7CDA),
                        focusedTextColor = Color.White,
                        focusedBorderColor = Color(0XFF0E7CDA),
                    ),
                    textStyle = TextStyle(color = Color.Black),
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(16.dp),

                            ),
                )
            }
            Text(
                text = "NIK yang diinput harus 16 digit yang akan dipadankan\n NIK akan di cek status pekerjaan ASN dan akan dicek ke DTKS",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle(1),
                style = TextStyle(fontSize = 12.sp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            // Tampilkan loading indicator jika loadingState true
            if (loadingState) {
                CircularProgressIndicator() // Loading indicator
            } else {
                CustomButton(
                    text = "Selanjutnya",
                    fullWidth = false,
                    onClick = {
//                        proposalViewModel.cekDataNIK(
//                            nik,
//                            onNext = { recipient ->
//                                onNextStepWithData(recipient)
//                            },
//                            onFailure = { errorMessage ->
//                                Toast.makeText(ctx, "Failed to create response: $errorMessage", Toast.LENGTH_LONG).show()
//                            }
//                        )
                        proposalViewModel.cekDataNIK(nik) { recipient ->
                            onNextStepWithData(recipient)
                            Log.d("data recipient proposal","$recipient")
                        }
                    }
                )
            }
        }
    }
}
