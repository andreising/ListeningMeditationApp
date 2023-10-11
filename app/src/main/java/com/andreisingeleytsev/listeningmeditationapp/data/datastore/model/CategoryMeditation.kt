package com.andreisingeleytsev.listeningmeditationapp.data.datastore.model

import com.andreisingeleytsev.listeningmeditationapp.R

sealed class CategoryMeditation(
    val titleIndex: Int,
    val iconIndex: Int,
    val itemsList: List<ItemMeditation>,
    val tipIndex: Int
) {
    data object Communicate : CategoryMeditation(
        R.string.communicate,
        R.drawable.icon_comminicate,
        listOf(ItemMeditation.ItemMeditation1, ItemMeditation.ItemMeditation2),
        R.string.tip_1
    )

    data object StressRelief :
        CategoryMeditation(
            R.string.stress_relief, R.drawable.icon_stress_relief,
            listOf(ItemMeditation.ItemMeditation3, ItemMeditation.ItemMeditation4),
            R.string.tip_2
        )

    data object Curiosity : CategoryMeditation(
        R.string.curiosity, R.drawable.icon_curiosity,
        listOf(ItemMeditation.ItemMeditation5, ItemMeditation.ItemMeditation6),
        R.string.tip_3
    )

    data object ManagingAnxiety :
        CategoryMeditation(
            R.string.man_anx, R.drawable.icon_managing_anxiety,
            listOf(ItemMeditation.ItemMeditation7, ItemMeditation.ItemMeditation8),
            R.string.tip_4
        )

    data object SleepingSoundly : CategoryMeditation(
        R.string.sleep_sound, R.drawable.icon_sleeping,
        listOf(ItemMeditation.ItemMeditation9, ItemMeditation.ItemMeditation10),
        R.string.tip_5
    )
}
