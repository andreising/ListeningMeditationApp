package com.andreisingeleytsev.listeningmeditationapp.ui.screens.home_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.listeningmeditationapp.R
import com.andreisingeleytsev.listeningmeditationapp.common.CategoryMap
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.CategoryMeditation
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.ItemMeditation
import com.andreisingeleytsev.listeningmeditationapp.ui.components.CategoryItemUI
import com.andreisingeleytsev.listeningmeditationapp.ui.theme.Orange
import com.andreisingeleytsev.listeningmeditationapp.ui.utils.MeditationAppFonts
import com.andreisingeleytsev.listeningmeditationapp.ui.utils.Routes
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel(), navHostController: NavHostController) {

    val favouriteCategory =
        viewModel.favouriteCategory.collectAsState(initial = CategoryMeditation.Communicate)
    val choseCategory = remember {
        viewModel.currentCategoryMeditation
    }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            Log.d("tag", "check")
            navHostController.navigate(it)
        }
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.special_4_u),
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black,
                fontFamily = MeditationAppFonts.font
            ), modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            for (i in 0 until CategoryMap.list.size) {
                val item = CategoryMap.list[i]
                Spacer(modifier = Modifier.width(16.dp))
                Box(modifier = Modifier.padding(5.dp)) {
                    CategoryItemUI(
                        categoryMeditation = item,
                        isOnBoardItem = false,
                        currentCategoryMeditation = viewModel.currentCategoryMeditation.value
                    ) {
                        viewModel.onChangeCategory(item)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = stringResource(id = R.string.ready_to_start),
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black,
                fontFamily = MeditationAppFonts.font
            ), modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            for (i in 0 until choseCategory.value.itemsList.size) {
                val item = choseCategory.value.itemsList[i]
                Spacer(modifier = Modifier.width(16.dp))
                Box(modifier = Modifier.padding(5.dp)) {
                    MeditationUIItem1(meditation = item){
                        viewModel.onNavigate(true, it)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = stringResource(id = R.string.recomended),
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black,
                fontFamily = MeditationAppFonts.font
            ), modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            for (i in 0 until favouriteCategory.value.itemsList.size) {
                val item = favouriteCategory.value.itemsList[i]
                Spacer(modifier = Modifier.width(16.dp))
                Box(modifier = Modifier.padding(5.dp)) {
                    MeditationUIItem2(meditation = item){
                        viewModel.onNavigate(false, it)
                    }
                }
            }
        }
        Button(onClick = {
            navHostController.navigate(Routes.TIP_SCREEN)
        }, colors = ButtonDefaults.buttonColors(
            containerColor = Orange
        ), modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.tips),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White,
                    fontFamily = MeditationAppFonts.font
                )
            )
        }
    }
}

@Composable
fun MeditationUIItem1(meditation: ItemMeditation, onNavigate: (ItemMeditation)->Unit) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(140.dp)
            .clickable {
                onNavigate(meditation)
            }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Image(
                painter = painterResource(id = meditation.picture),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Row(modifier = Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.icon_play),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = stringResource(id = meditation.stringResource),
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color.White,
                            fontFamily = MeditationAppFonts.font
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.five_min),
                        style = TextStyle(
                            fontSize = 8.sp,
                            color = Color.White,
                            fontFamily = MeditationAppFonts.font
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun MeditationUIItem2(meditation: ItemMeditation, onNavigate: (ItemMeditation) -> Unit) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .clickable {
                onNavigate(meditation)
            }, shape = RoundedCornerShape(10.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Image(
                painter = painterResource(id = meditation.picture),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = meditation.stringResource),
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = MeditationAppFonts.font, textAlign = TextAlign.Center
                ), modifier = Modifier.padding(20.dp)
            )
        }
    }
}