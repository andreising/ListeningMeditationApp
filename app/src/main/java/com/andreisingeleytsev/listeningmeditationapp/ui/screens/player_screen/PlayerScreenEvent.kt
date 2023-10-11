package com.andreisingeleytsev.listeningmeditationapp.ui.screens.player_screen

sealed class PlayerScreenEvent{
    data object OnRepeatPressed: PlayerScreenEvent()
    data object OnLastTrack: PlayerScreenEvent()
    data object OnMainButtonPressed: PlayerScreenEvent()
    data object OnNextTrack: PlayerScreenEvent()
}
