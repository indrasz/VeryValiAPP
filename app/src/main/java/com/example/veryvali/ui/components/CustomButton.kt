package com.example.veryvali.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CustomButton(
    text: String,
    fullWidth: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .height(62.dp)
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier.wrapContentWidth()),
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(
            text = text,
            color = Color.Black,
        )
    }
}