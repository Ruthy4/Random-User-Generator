package com.example.randomusergenerator.view.userdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.randomusergenerator.R
import com.example.randomusergenerator.data.local.*
import com.example.randomusergenerator.ui.resources.DarkBlueColor
import com.example.randomusergenerator.ui.resources.DetailImageSize
import com.example.randomusergenerator.ui.resources.Dimensions.space_x1
import com.example.randomusergenerator.ui.resources.Dimensions.space_x1_75
import com.example.randomusergenerator.ui.resources.Dimensions.space_x2_5
import com.example.randomusergenerator.ui.resources.Dimensions.space_x3_5
import com.example.randomusergenerator.ui.resources.Dimensions.space_x5
import com.example.randomusergenerator.ui.resources.FontSize.fontSize20
import com.example.randomusergenerator.ui.resources.FontSize.fontSize36
import com.example.randomusergenerator.user.viewmodel.UserViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserDetailsScreen(user: UserData) {
    val userViewModel: UserViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(space_x1_75)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .align(Alignment.Start)
                .size(space_x5)
                .clickable { userViewModel.navigateBack() },
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "navigate back"
        )
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
            text = user.fullName,
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
            user.getAddress
        )
        Spacer(modifier = Modifier.height(space_x3_5))
        DetailsCard(stringResource(R.string.gender), "${user.gender}")
        Spacer(modifier = Modifier.height(space_x3_5))
        DetailsCard(stringResource(R.string.age), "${user.dob?.age}")
    }
}

@Preview
@Composable
fun PreviewUserDetailScreen() {
    UserDetailsScreen(UserData())
}
