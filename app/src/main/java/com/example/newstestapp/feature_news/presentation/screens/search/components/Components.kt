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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.newstestapp.feature_news.domain.model.Article
import com.example.newstestapp.feature_news.presentation.navigation.NewsScreens
import com.example.newstestapp.feature_news.presentation.util.getTimeSpanInDays
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ArticleRow(
    article: Article,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                val encodedUrl =
                    URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
                navController.navigate(route = NewsScreens.DetailsScreen.name + "/${encodedUrl}")
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
                WeatherStateImage(imageUrl = article.urlToImage)
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = article.title,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.Start),
                text = "${getTimeSpanInDays(article.publishedAt)} days ago",
                color = Color.LightGray,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = article.description)
        }
    }
}

@Composable
fun WeatherStateImage(imageUrl: String?) {
    imageUrl?.let {
        Image(
            painter = rememberImagePainter(it,
                builder = {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
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