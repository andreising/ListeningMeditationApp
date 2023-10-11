package com.andreisingeleytsev.listeningmeditationapp.domain.use_case.music_player

import com.andreisingeleytsev.listeningmeditationapp.domain.repository.MediaPlayerManager
import javax.inject.Inject

class PlayerStopUseCase @Inject constructor(
    private val manager: MediaPlayerManager
) {
    operator fun invoke(){
        manager.stop()
    }
}