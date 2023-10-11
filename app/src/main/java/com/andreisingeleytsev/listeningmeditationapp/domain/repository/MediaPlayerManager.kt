package com.andreisingeleytsev.listeningmeditationapp.domain.repository

interface MediaPlayerManager {
    fun changeTrack(trackIndex: Int)
    fun repeat():Boolean
    fun playPause(): Boolean
    fun stop()
}