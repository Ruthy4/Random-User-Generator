package com.example.randomusergenerator.view.userdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.randomusergenerator.ui.resources.Dimensions
import com.example.randomusergenerator.ui.resources.FontSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsCard(cardTitle: String, cardDescription: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = Dimensions.space_x2)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(Dimensions.space_x0_25),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(Modifier.padding(Dimensions.space_x1_5)) {
            Text(
                text = cardTitle,
                style = TextStyle(
                    fontSize = FontSize.fontSize16,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = Dimensions.space_x1)
            )
            Text(
                text = cardDescription,
                style = TextStyle(
                    fontSize = FontSize.fontSize16
                ),
                modifier = Modifier.padding(top = Dimensions.space_x1)
            )
        }
    }
}
