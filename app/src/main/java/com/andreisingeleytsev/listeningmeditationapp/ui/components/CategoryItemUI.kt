package com.andreisingeleytsev.listeningmeditationapp.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.CategoryMeditation
import com.andreisingeleytsev.listeningmeditationapp.ui.theme.Orange
import com.andreisingeleytsev.listeningmeditationapp.ui.theme.Primary
import com.andreisingeleytsev.listeningmeditationapp.ui.utils.MeditationAppFonts

@Composable
fun CategoryItemUI(
    categoryMeditation: CategoryMeditation,
    isOnBoardItem: Boolean,
    currentCategoryMeditation: CategoryMeditation,
    onClick: () -> Unit
) {
    val isChecked = categoryMeditation == currentCategoryMeditation
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable {
        onClick.invoke()
        Log.d("tag", currentCategoryMeditation.toString())
    }) {
        Card(
            modifier = Modifier.size(if (isOnBoardItem) 110.dp else 60.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = if (isChecked) Orange else Color.White
            ), elevation = CardDefaults.cardElevation(
                defaultElevation = if (isOnBoardItem) 4.dp else 2.dp
            )
        ) {
            Icon(
                painter = painterResource(id = categoryMeditation.iconIndex),
                contentDescription = null,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                tint = if (isChecked) Color.White
                else Orange
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = categoryMeditation.titleIndex).capitalize(Locale.current),
            style = TextStyle(
                fontSize = if (isOnBoardItem) 10.sp else 8.sp,
                color = if (isOnBoardItem) Orange else Color.Black,
                fontFamily = MeditationAppFonts.font,
                textAlign = TextAlign.Center
            ), modifier = Modifier.width(if (isOnBoardItem) 110.dp else 60.dp)
        )
    }
}