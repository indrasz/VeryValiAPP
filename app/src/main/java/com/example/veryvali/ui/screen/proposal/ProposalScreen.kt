package com.example.veryvali.ui.screen.proposal

import android.os.Build
import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.veryvali.data.model.Individual
import com.example.veryvali.data.model.Recipient
import com.example.veryvali.data.model.SurveyType
import com.example.veryvali.data.model.User
import com.example.veryvali.data.repository.IndividualRepository
import com.example.veryvali.data.repository.ProposalRepository
import com.example.veryvali.data.repository.SurveyRepository
import com.example.veryvali.di.IndividualViewModel
import com.example.veryvali.di.ProposalViewModel
import com.example.veryvali.di.SurveyViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProposalScreen(
    navController: NavHostController,
    mapsLangitude: String,
    mapsLongitude: String,
    user: User?
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    var currentStep by remember { mutableIntStateOf(1) }

    var recipientData by remember { mutableStateOf<Recipient?>(null) }
    var individualData by remember { mutableStateOf("")}
    var surveyData by remember { mutableStateOf("") }

    val proposalViewModel: ProposalViewModel = viewModel()
    val individualViewModel: IndividualViewModel = viewModel()
    val surveyViewModel: SurveyViewModel = viewModel()
//    val proposalViewModel: SurveyViewModel = viewModel()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Pengusulan",
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
                    Proposal1Form(proposalViewModel) { recipient ->
                        recipientData = recipient
//                        Log.d("Recipient Data from proposal 1", "$recipientData")
                        currentStep++
                    }
                }
                2 -> {
                    Proposal2Form(individualViewModel, recipientData) {individualId ->
                        individualData = individualId
                        currentStep++
                    }
                }
                3 -> {
                    Proposal3Form(innerPadding, surveyViewModel, recipientData) { surveyId ->
                        surveyData = surveyId
                        currentStep++
                    }
                }
                4 -> {
                    Proposal4Form(innerPadding, proposalViewModel, recipientData, individualData, surveyData, mapsLangitude, mapsLongitude, user = user) {
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
            color = Color(0xFF23317D),
            progress = currentStep.toFloat() / totalSteps.toFloat(),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (step in 1..totalSteps) {
                val backgroundColor = if (step <= currentStep) Color(0xFF23317D) else Color(0xFFD0D0D0)
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(24.dp)
                        .background(backgroundColor, shape = CircleShape)
                ) {
                    Text(
                        text = "$step",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}


