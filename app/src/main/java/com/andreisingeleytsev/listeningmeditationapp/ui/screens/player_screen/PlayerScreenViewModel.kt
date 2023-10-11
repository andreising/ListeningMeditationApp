package com.andreisingeleytsev.listeningmeditationapp.ui.screens.player_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.listeningmeditationapp.common.CategoryMap
import com.andreisingeleytsev.listeningmeditationapp.common.Constants
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.ItemMeditation
import com.andreisingeleytsev.listeningmeditationapp.domain.use_case.music_player.PlayerRepeatUseCase
import com.andreisingeleytsev.listeningmeditationapp.domain.use_case.music_player.PlayerStopUseCase
import com.andreisingeleytsev.listeningmeditationapp.domain.use_case.music_player.PlayerTrackChangeUseCase
import com.andreisingeleytsev.listeningmeditationapp.domain.use_case.music_player.PlayerTrackStartStopUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerScreenViewModel @Inject constructor(
    val useCaseRepeat: PlayerRepeatUseCase,
    private val useCaseChangeTrack: PlayerTrackChangeUseCase,
    val useCaseStartStop: PlayerTrackStartStopUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {


    fun onEvent(event: PlayerScreenEvent){
        when(event){
            is PlayerScreenEvent.OnLastTrack -> {
                changeTrack(false)
            }
            is PlayerScreenEvent.OnNextTrack -> {
                changeTrack(true)
            }
            is PlayerScreenEvent.OnMainButtonPressed -> {
                _isPlaying.value = useCaseStartStop.invoke()
            }
            is PlayerScreenEvent.OnRepeatPressed -> {
                _isLooping.value = useCaseRepeat.invoke()
            }
        }
    }



    private var itemsList = emptyList<ItemMeditation>()

    val currentItemMeditation = mutableStateOf<ItemMeditation?>(null)

    private val _isPlaying = mutableStateOf(true)
    val isPlaying: State<Boolean> = _isPlaying

    private val _isLooping = mutableStateOf(false)
    val isLooping: State<Boolean> = _isPlaying

    private fun changeTrack(isNext: Boolean){
        var index = itemsList.indexOf(currentItemMeditation.value)
        if (isNext) index++
        else index--
        try {
            currentItemMeditation.value = itemsList[index]
            useCaseChangeTrack.invoke(currentItemMeditation.value!!.music)
            _isPlaying.value = true
        } catch (_: ArrayIndexOutOfBoundsException){

        }
    }

    init {
        val index = savedStateHandle.get<String>(Constants.INDEX_KEY)?.toInt() ?: 0
        val itemIndex = savedStateHandle.get<String>(Constants.ITEM_INDEX_KEY)?.toInt() ?: 0
        val category = CategoryMap.map[index]
        itemsList = category!!.itemsList
        currentItemMeditation.value = itemsList[itemIndex]
        useCaseChangeTrack.invoke(currentItemMeditation.value!!.music)
    }
}