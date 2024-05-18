package com.example.veryvali.ui.screen.proposal

import android.util.Log
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.veryvali.data.model.Recipient
import com.example.veryvali.di.ProposalViewModel

@Composable
fun Proposal1Form(
    proposalViewModel: ProposalViewModel,
    onNextStepWithData: (Recipient) -> Unit,
) {
    var nik by remember { mutableStateOf("") }
    val loadingState by proposalViewModel.loadingState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF0E7CDA), shape = RoundedCornerShape(24.dp))
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