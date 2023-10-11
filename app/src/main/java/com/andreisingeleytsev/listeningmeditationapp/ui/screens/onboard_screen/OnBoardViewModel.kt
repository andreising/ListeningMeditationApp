package com.andreisingeleytsev.listeningmeditationapp.ui.screens.onboard_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.CategoryMeditation
import com.andreisingeleytsev.listeningmeditationapp.domain.use_case.onboard.OnBoardFinishUseCase
import com.andreisingeleytsev.listeningmeditationapp.domain.use_case.category.SaveCategoryMeditationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val finishOnBoardFinishUseCase: OnBoardFinishUseCase,
    private val choseCategoryUseCase: SaveCategoryMeditationUseCase
): ViewModel() {

    private val _choseCategory = mutableStateOf<CategoryMeditation>(CategoryMeditation.Communicate)
    val choseCategory: State<CategoryMeditation> = _choseCategory

    fun changeCategory(category: CategoryMeditation){
        _choseCategory.value = category
    }

    fun onBoardFinished(){
        viewModelScope.launch {
            finishOnBoardFinishUseCase.invoke()
            choseCategoryUseCase.invoke(_choseCategory.value)
        }
    }


}