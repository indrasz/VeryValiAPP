package com.example.veryvali.ui.screen.proposal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.veryvali.data.repository.IndividualRepository
import com.example.veryvali.data.repository.ProposalRepository
import com.example.veryvali.data.repository.RecipientRepository
import com.example.veryvali.data.repository.SurveyRepository

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProposalScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val recipientRepository = RecipientRepository() // Inisialisasi repository di sini
    val individualRepository = IndividualRepository() // Inisialisasi repository di sini
    val surveyRepository = SurveyRepository()
    val proposalRepository = ProposalRepository()// Inisialisasi repository di sini
    var currentStep by remember { mutableIntStateOf(1) }
    var proposal1Data by remember { mutableStateOf("") }
    var proposal2Data by remember { mutableStateOf("") }
    var proposal3Data by remember { mutableStateOf("") }
    var proposal4Data by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Proposal $currentStep",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentStep > 1) {
                            currentStep--
                        } else {
                            navController.popBackStack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 100.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomProgressIndicator(currentStep)
            when (currentStep) {
                1 -> {
                    Proposal1Form(innerPadding, proposal1Data, recipientRepository) { data ->
                        proposal1Data = data
                        currentStep++
                    }
                }
                2 -> {
                    Proposal2Form(innerPadding, proposal2Data, individualRepository) { data ->
                        proposal2Data = data
                        currentStep++
                    }
                }
                3 -> {
                    Proposal3Form(innerPadding, proposal3Data, surveyRepository) { data ->
                        proposal3Data = data
                        currentStep++
                    }
                }
                4 -> {
                    Proposal4Form(innerPadding, proposal4Data, proposalRepository) { data ->
                        proposal4Data = data
                        currentStep++
                    }
                }
                else -> {
                    navController.navigate("home")
                }
            }
        }
    }
}

@Composable
fun CustomProgressIndicator(currentStep: Int) {
    val totalSteps = 4 // Jumlah total langkah

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        LinearProgressIndicator(
            color = Color(0xFF8B8FFF),
            progress = currentStep.toFloat() / totalSteps.toFloat(),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (step in 1..totalSteps) {
                val backgroundColor = if (step <= currentStep) Color(0xFF8B8FFF) else Color(0xFFD0D0D0)
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(24.dp)
                        .background(backgroundColor, shape = CircleShape)
                ) {
                    Text(
                        text = "$step",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}


