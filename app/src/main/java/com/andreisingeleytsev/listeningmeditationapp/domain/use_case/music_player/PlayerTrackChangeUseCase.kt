package com.andreisingeleytsev.listeningmeditationapp.domain.use_case.music_player

import com.andreisingeleytsev.listeningmeditationapp.domain.repository.CategoryMeditationRepository
import com.andreisingeleytsev.listeningmeditationapp.domain.repository.MediaPlayerManager
import javax.inject.Inject

class PlayerTrackChangeUseCase @Inject constructor(
    private val manager: MediaPlayerManager
) {
    operator fun invoke(trackIndex: Int){
        manager.changeTrack(trackIndex = trackIndex)
    }
}