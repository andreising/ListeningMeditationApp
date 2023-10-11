package com.andreisingeleytsev.listeningmeditationapp.ui.screens.player_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.listeningmeditationapp.R
import com.andreisingeleytsev.listeningmeditationapp.ui.theme.Orange
import com.andreisingeleytsev.listeningmeditationapp.ui.utils.MeditationAppFonts

@Composable
fun PlayerScreen(
    viewModel: PlayerScreenViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val isShuffle = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f))
    ) {
        Row(
            Modifier
                .padding(20.dp)
                .weight(1f)
        ) {
            IconButton(onClick = {
                navHostController.popBackStack()
            }, modifier = Modifier.size(30.dp)) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.White, modifier = Modifier.fillMaxSize()
                )
            }
        }
        Card(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .weight(7f)
        ) {
            Image(
                painter = painterResource(id = viewModel.currentItemMeditation.value!!.picture),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = stringResource(
                id = viewModel.currentItemMeditation.value?.stringResource ?: R.string.app_name
            ),
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = MeditationAppFonts.font, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ), modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        )
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                viewModel.onEvent(PlayerScreenEvent.OnRepeatPressed)
            }, modifier = Modifier.weight(2f)) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_repeat),
                    contentDescription = null,
                    tint = if (viewModel.isLooping.value) Orange else Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = {
                viewModel.onEvent(PlayerScreenEvent.OnLastTrack)
            }, modifier = Modifier.weight(2f)) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_last),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            IconButton(onClick = {
                viewModel.onEvent(PlayerScreenEvent.OnMainButtonPressed)
            }, modifier = Modifier.weight(5f)) {
                Icon(
                    painter = painterResource(id = if (!viewModel.isPlaying.value) R.drawable.icon_play_track else R.drawable.icon_pause),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize()
                )
            }
            IconButton(onClick = {
                viewModel.onEvent(PlayerScreenEvent.OnNextTrack)
            }, modifier = Modifier.weight(2f)) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_next),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            IconButton(onClick = {
                isShuffle.value = !isShuffle.value
            }, modifier = Modifier.weight(2f)) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_shuffle),
                    contentDescription = null,
                    tint = if (isShuffle.value) Orange else Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
