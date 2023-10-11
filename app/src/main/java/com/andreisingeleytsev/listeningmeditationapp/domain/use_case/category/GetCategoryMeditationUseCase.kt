package com.andreisingeleytsev.listeningmeditationapp.domain.use_case.category

import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.CategoryMeditation
import com.andreisingeleytsev.listeningmeditationapp.domain.repository.CategoryMeditationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryMeditationUseCase @Inject constructor(
    private val repository: CategoryMeditationRepository
) {
    operator fun invoke(): Flow<CategoryMeditation>{
        return repository.getCategory()
    }
}