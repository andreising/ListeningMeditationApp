package com.andreisingeleytsev.listeningmeditationapp.domain.repository

import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.CategoryMeditation
import kotlinx.coroutines.flow.Flow

interface CategoryMeditationRepository {
    suspend fun onChoseCategory(categoryMeditation: CategoryMeditation)

    fun getCategory(): Flow<CategoryMeditation>
}