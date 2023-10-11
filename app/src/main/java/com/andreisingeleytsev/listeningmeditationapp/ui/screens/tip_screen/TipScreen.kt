package com.andreisingeleytsev.listeningmeditationapp.ui.screens.tip_screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.andreisingeleytsev.listeningmeditationapp.common.CategoryMap
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.CategoryMeditation
import com.andreisingeleytsev.listeningmeditationapp.ui.components.CategoryItemUI
import com.andreisingeleytsev.listeningmeditationapp.ui.theme.Orange
import com.andreisingeleytsev.listeningmeditationapp.ui.utils.MeditationAppFonts

@Composable
fun TipScreen(navHostController: NavHostController) {
    val currentCategory = remember {
        mutableStateOf<CategoryMeditation>(CategoryMeditation.Communicate)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(Modifier.padding(20.dp)) {
            IconButton(onClick = {
                navHostController.popBackStack()
            }, modifier = Modifier.size(30.dp)) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Orange, modifier = Modifier.fillMaxSize()
                )
            }
        }
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
                        currentCategoryMeditation = currentCategory.value
                    ) {
                        currentCategory.value = item
                    }
                }
            }
        }
        Text(
            text = stringResource(id = currentCategory.value.tipIndex),
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black,
                fontFamily = MeditationAppFonts.font
            ), modifier = Modifier
                .padding(18.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        )
    }
}