package com.andreisingeleytsev.listeningmeditationapp.domain.repository.implementation

import android.app.Application
import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import com.andreisingeleytsev.listeningmeditationapp.domain.repository.MediaPlayerManager

class MediaPlayerManagerImpl(private val context: Context) : MediaPlayerManager {
    private val mediaPlayer: MediaPlayer = MediaPlayer()
    override fun changeTrack(trackIndex: Int) {
        val resources = context.resources

        val desc = resources.openRawResourceFd(trackIndex)
        mediaPlayer.reset()
        mediaPlayer.setDataSource(desc.fileDescriptor, desc.startOffset, desc.length)
        mediaPlayer.prepare()
        mediaPlayer.start()

    }

    override fun repeat(): Boolean {
        mediaPlayer.isLooping = !mediaPlayer.isLooping
        return mediaPlayer.isLooping
    }

    override fun playPause(): Boolean {
        if (mediaPlayer.isPlaying) mediaPlayer.pause()
        else mediaPlayer.start()
        return mediaPlayer.isPlaying
    }

    override fun stop() {
        mediaPlayer.stop()
    }
}