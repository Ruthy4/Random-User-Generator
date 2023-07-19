package com.example.randomusergenerator.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.randomusergenerator.R
import com.example.randomusergenerator.data.local.*
import com.example.randomusergenerator.ui.resources.DarkBlueColor
import com.example.randomusergenerator.ui.resources.DetailImageSize
import com.example.randomusergenerator.ui.resources.Dimensions
import com.example.randomusergenerator.ui.resources.Dimensions.space_x1
import com.example.randomusergenerator.ui.resources.Dimensions.space_x1_5
import com.example.randomusergenerator.ui.resources.Dimensions.space_x1_75
import com.example.randomusergenerator.ui.resources.Dimensions.space_x2
import com.example.randomusergenerator.ui.resources.Dimensions.space_x2_5
import com.example.randomusergenerator.ui.resources.Dimensions.space_x3_5
import com.example.randomusergenerator.ui.resources.FontSize.fontSize16
import com.example.randomusergenerator.ui.resources.FontSize.fontSize20
import com.example.randomusergenerator.ui.resources.FontSize.fontSize36

@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserDetailsScreen(user: UserData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(space_x1_75)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(space_x2_5))
        Box(
            modifier = Modifier
                .size(DetailImageSize)
                .clip(shape = CircleShape)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = rememberImagePainter(data = user.picture?.thumbnail),
                contentDescription = stringResource(R.string.description_user_card),
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = "${user.name?.title} ${user.name?.first} ${user.name?.last}",
            style = TextStyle(
                fontSize = fontSize36,
                fontWeight = FontWeight(400),
                color = DarkBlueColor,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(top = space_x3_5)
        )
        Text(
            text = user.login?.username.toString(),
            style = TextStyle(
                fontSize = fontSize20,
                color = Color.Black
            ),
            modifier = Modifier.padding(top = space_x1)
        )

        Spacer(modifier = Modifier.height(space_x3_5))
        DetailsCard(
            stringResource(R.string.address),
            "${user.location?.street?.name}, ${user.location?.city ?: ""}, " +
                "${user.location?.state ?: ""}, ${user.location?.country ?: ""}"
        )
        Spacer(modifier = Modifier.height(space_x3_5))
        DetailsCard(stringResource(R.string.gender), "${user.gender}")
        Spacer(modifier = Modifier.height(space_x3_5))
        DetailsCard(stringResource(R.string.age), "${user.dob?.age}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsCard(cardTitle: String, cardDescription: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = space_x2)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(Dimensions.space_x0_25),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(Modifier.padding(space_x1_5)) {
            Text(
                text = cardTitle,
                style = TextStyle(
                    fontSize = fontSize16,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = space_x1)
            )
            Text(
                text = cardDescription,
                style = TextStyle(
                    fontSize = fontSize16
                ),
                modifier = Modifier.padding(top = space_x1)
            )
        }
    }
}
