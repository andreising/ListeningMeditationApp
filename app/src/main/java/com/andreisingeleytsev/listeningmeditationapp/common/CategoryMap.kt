package com.andreisingeleytsev.listeningmeditationapp.common

import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.CategoryMeditation

object CategoryMap {
    val map = mapOf(
        0 to CategoryMeditation.Communicate,
        1 to CategoryMeditation.Curiosity,
        2 to CategoryMeditation.ManagingAnxiety,
        3 to CategoryMeditation.SleepingSoundly,
        4 to CategoryMeditation.StressRelief
    )
    val list = listOf(
        CategoryMeditation.Communicate,
        CategoryMeditation.Curiosity,
        CategoryMeditation.ManagingAnxiety,
        CategoryMeditation.SleepingSoundly,
        CategoryMeditation.StressRelief
    )
}