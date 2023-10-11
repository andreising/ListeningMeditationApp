package com.andreisingeleytsev.listeningmeditationapp.ui.screens.onboard_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andreisingeleytsev.listeningmeditationapp.R
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.CategoryMeditation
import com.andreisingeleytsev.listeningmeditationapp.ui.activity.MainViewModel
import com.andreisingeleytsev.listeningmeditationapp.ui.components.CategoryItemUI
import com.andreisingeleytsev.listeningmeditationapp.ui.theme.MainColor
import com.andreisingeleytsev.listeningmeditationapp.ui.theme.Primary
import com.andreisingeleytsev.listeningmeditationapp.ui.utils.MeditationAppFonts

@Composable
fun OnBoardScreen(viewModel: OnBoardViewModel = hiltViewModel()) {
    val isFirstPage = remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(21F)
        ) {
            if (isFirstPage.value) OnBoardPart1()
            else OnBoardPart2(currentCategoryMeditation = viewModel.choseCategory) { categoryMeditation ->
                viewModel.changeCategory(categoryMeditation)
            }
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .weight(4F),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    if (isFirstPage.value) isFirstPage.value = !isFirstPage.value
                    else viewModel.onBoardFinished()
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Primary
                ), modifier = Modifier
                    .fillMaxWidth(), shape = RoundedCornerShape(10.dp)

            ) {
                Text(
                    text = stringResource(R.string.next).capitalize(Locale.current),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.White,
                        fontFamily = MeditationAppFonts.font
                    ), modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }
    }
}

@Composable
fun OnBoardPart1() {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.onboard_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(10F),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3F), contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.onboard_1), style = TextStyle(
                    fontSize = 24.sp,
                    color = Primary,
                    fontFamily = MeditationAppFonts.font
                ), modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun OnBoardPart2(
    currentCategoryMeditation: State<CategoryMeditation>,
    onClick: (CategoryMeditation) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(18.dp), verticalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(R.string.onboard_2),
            style = TextStyle(
                fontSize = 24.sp,
                color = Primary,
                fontFamily = MeditationAppFonts.font,
                fontWeight = FontWeight.Bold
            ),
        )
        Text(
            text = stringResource(R.string.onboard_3),
            style = TextStyle(
                fontSize = 14.sp,
                color = Primary,
                fontFamily = MeditationAppFonts.font
            )
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CategoryItemUI(
                categoryMeditation = CategoryMeditation.Communicate,
                isOnBoardItem = true,
                currentCategoryMeditation = currentCategoryMeditation.value
            ) {
                onClick.invoke(CategoryMeditation.Communicate)
            }
            CategoryItemUI(
                categoryMeditation = CategoryMeditation.StressRelief,
                isOnBoardItem = true,
                currentCategoryMeditation = currentCategoryMeditation.value
            ) {
                onClick.invoke(CategoryMeditation.StressRelief)
            }
            CategoryItemUI(
                categoryMeditation = CategoryMeditation.Curiosity,
                isOnBoardItem = true,
                currentCategoryMeditation = currentCategoryMeditation.value
            ) {
                onClick.invoke(CategoryMeditation.Curiosity)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            CategoryItemUI(
                categoryMeditation = CategoryMeditation.ManagingAnxiety,
                isOnBoardItem = true,
                currentCategoryMeditation = currentCategoryMeditation.value
            ) {
                onClick.invoke(CategoryMeditation.ManagingAnxiety)
            }
            Spacer(modifier = Modifier.width(20.dp))
            CategoryItemUI(
                categoryMeditation = CategoryMeditation.SleepingSoundly,
                isOnBoardItem = true,
                currentCategoryMeditation = currentCategoryMeditation.value
            ) {
                onClick.invoke(CategoryMeditation.SleepingSoundly)
            }
        }
    }
}

