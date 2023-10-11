package com.andreisingeleytsev.listeningmeditationapp.ui.screens.home_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.listeningmeditationapp.common.CategoryMap
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.CategoryMeditation
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.ItemMeditation
import com.andreisingeleytsev.listeningmeditationapp.domain.use_case.category.GetCategoryMeditationUseCase
import com.andreisingeleytsev.listeningmeditationapp.ui.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getCategoryMeditationUseCase: GetCategoryMeditationUseCase
) : ViewModel() {
    private val _uiEvent = Channel<String>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private fun sendUIEvent(route: String){
        viewModelScope.launch {
            _uiEvent.send(route)
        }
    }

    private val _currentCategoryMeditation =
        mutableStateOf<CategoryMeditation>(CategoryMeditation.Communicate)
    val currentCategoryMeditation:State<CategoryMeditation> = _currentCategoryMeditation

    fun onChangeCategory(categoryMeditation: CategoryMeditation){
        _currentCategoryMeditation.value = categoryMeditation
    }
    fun onNavigate(isCurrent: Boolean, itemMeditation: ItemMeditation){

        viewModelScope.launch {
            var category: CategoryMeditation = CategoryMeditation.Communicate
            favouriteCategory.collect{
                 category = if (isCurrent) _currentCategoryMeditation.value else it
                val index = CategoryMap.map.filterValues { categoryMeditation ->
                    category == categoryMeditation
                }.keys.first()
                val itemIndex = category.itemsList.indexOf(itemMeditation)
                var route = Routes.PLAYER_SCREEN + "/$index" + "/$itemIndex"
                sendUIEvent(route)
            }
        }


    }
    val favouriteCategory = getCategoryMeditationUseCase.invoke()


}