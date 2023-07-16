package com.example.randomusergenerator.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.randomusergenerator.data.local.UserData
import com.example.randomusergenerator.ui.resources.DarkBlueColor
import com.example.randomusergenerator.ui.resources.Dimensions.space_half
import com.example.randomusergenerator.ui.resources.Dimensions.space_x0_25
import com.example.randomusergenerator.ui.resources.Dimensions.space_x1
import com.example.randomusergenerator.ui.resources.Dimensions.space_x2
import com.example.randomusergenerator.ui.resources.Dimensions.space_x7
import com.example.randomusergenerator.ui.resources.FontSize.fontSize12
import com.example.randomusergenerator.ui.resources.FontSize.fontSize14

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
@Composable
fun UserCard(
    user: UserData
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = space_x2, vertical = space_x1)
    ) {
        Box(
            modifier = Modifier
                .size(space_x7)
                .clip(shape = CircleShape)
        ) {
            Image(
                painter = rememberImagePainter(data = user.picture?.thumbnail),
                contentDescription = "User card",
                modifier = Modifier.fillMaxSize()
            )
        }
        Card(
            modifier = Modifier
                .padding(start = space_x2)
                .weight(1f),
            elevation = CardDefaults.cardElevation(space_x0_25),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Column(modifier = Modifier.padding(space_x2)) {
                Text(
                    text = "${user.name?.first} ${user.name?.last}",
                    style =
                    TextStyle(
                        fontSize = fontSize14,
                        fontWeight = FontWeight(500),
                        color = DarkBlueColor,
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = space_half)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "City Icon",
                        tint = DarkBlueColor,
                        modifier = Modifier.size(space_x2)
                    )
                    Spacer(modifier = Modifier.width(space_half))
                    Text(
                        text = user.location?.city ?: "",
                        style = TextStyle(
                            fontSize = fontSize12,
                            fontWeight = FontWeight(400),
                            color = DarkBlueColor,
                        )
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = space_half)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Country Icon",
                        tint = DarkBlueColor,
                        modifier = Modifier.size(space_x2)
                    )
                    Spacer(modifier = Modifier.width(space_half))
                    Text(
                        text = user.location?.country ?: "",
                        style = TextStyle(
                            fontSize = fontSize12,
                            fontWeight = FontWeight(400),
                            color = DarkBlueColor,
                        )
                    )
                }
            }
        }
    }
}
