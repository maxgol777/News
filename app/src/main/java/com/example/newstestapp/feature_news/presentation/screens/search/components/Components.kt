package com.example.newstestapp.feature_news.presentation.screens.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.newstestapp.feature_news.data.remote.ArticleDto
import com.example.newstestapp.feature_news.presentation.screens.destinations.DetailScreenDestination
import com.example.newstestapp.feature_news.presentation.util.getTimeSpanInDays
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun ArticleRow(
    articleDto: ArticleDto,
    navController: DestinationsNavigator
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                navController.navigate(DetailScreenDestination(articleDto.url))
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 5.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                WeatherStateImage(imageUrl = articleDto.imageUrl)
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = articleDto.title,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.Start),
                text = "${getTimeSpanInDays(articleDto.publishedAt)} days ago",
                color = Color.LightGray,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = articleDto.description)
        }
    }
}

@Composable
fun WeatherStateImage(imageUrl: String?) {
    imageUrl?.let {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(it)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }).build()
            ),
            contentDescription = " icon image",
            modifier = Modifier.size(80.dp),
        )
    }
}

@Composable
fun CommonTextField(
    valueState: MutableState<String>,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeActions: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = placeholder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeActions
        ),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    )
}