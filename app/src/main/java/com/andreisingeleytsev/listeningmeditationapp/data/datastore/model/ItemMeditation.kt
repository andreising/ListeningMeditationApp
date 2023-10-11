package com.andreisingeleytsev.listeningmeditationapp.data.datastore.model

import android.graphics.Picture
import com.andreisingeleytsev.listeningmeditationapp.R

sealed class ItemMeditation(val stringResource: Int, val picture: Int, val music: Int) {
    data object ItemMeditation1 :
        ItemMeditation(R.string.inner_harmony_meditation, R.drawable.song_1, R.raw.song_1)
    data object ItemMeditation2 :
        ItemMeditation(R.string.peaceful_mindfulness_session, R.drawable.song_2, R.raw.song_2)
    data object ItemMeditation3 :
        ItemMeditation(R.string.serenity_oasis_meditation, R.drawable.song_3, R.raw.song_3)
    data object ItemMeditation4 :
        ItemMeditation(R.string.tranquil_zen_journey, R.drawable.song_4, R.raw.song_4)
    data object ItemMeditation5 :
        ItemMeditation(R.string.healing_heart_meditation, R.drawable.song_5, R.raw.song_5)
    data object ItemMeditation6 :
        ItemMeditation(R.string.mindful_bliss_retreat, R.drawable.song_6, R.raw.song_6)
    data object ItemMeditation7 :
        ItemMeditation(R.string.spiritual_renewal_meditation, R.drawable.song_7, R.raw.song_7)
    data object ItemMeditation8 :
        ItemMeditation(R.string.calm_waters_meditation, R.drawable.song_8, R.raw.song_8)
    data object ItemMeditation9 :
        ItemMeditation(R.string.elevate_your_soul_meditation, R.drawable.song_9, R.raw.song_9)
    data object ItemMeditation10 :
        ItemMeditation(R.string.radiant_inner_light_meditation, R.drawable.song_10, R.raw.song_10)
}










