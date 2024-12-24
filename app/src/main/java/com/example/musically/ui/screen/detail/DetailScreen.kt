package com.example.musically.ui.screen.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.musically.R
import com.example.musically.di.Injection
import com.example.musically.ui.ViewModelFactory
import com.example.musically.ui.common.UiState
import com.example.musically.ui.theme.MusicallyTheme
import kotlin.math.sin

@Composable
fun DetailScreen(
    musicallyId: Long,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMusicallyById(musicallyId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.musically.title,
                    data.musically.singers,
                    data.musically.image,
                    data.musically.listener,
                    data.musically.synopsis,
                    navController = navController
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    title: String,
    singers: String,
    @DrawableRes image: Int,
    listener: String,
    synopsis: String,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(320.dp)
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp) 
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Black.copy(alpha = 0.3f), Color.Black)
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "About songs",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$listener",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = synopsis,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = UI_MODE_NIGHT_YES)
fun DetailScreenPreview() {
    MusicallyTheme {
        val navController = rememberNavController()
        DetailContent(
            "Sleep Well",
            "d4vd",
            R.drawable.d4vd,
            "30.949.870",
            "Singer and songwriter d4vd (pronounced David) is known for his moody, bedroom-style indie pop and R&B.",
            navController = navController
        )
    }
}