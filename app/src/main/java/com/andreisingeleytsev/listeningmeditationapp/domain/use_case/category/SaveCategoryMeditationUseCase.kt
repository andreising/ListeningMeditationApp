package com.andreisingeleytsev.listeningmeditationapp.domain.use_case.category

import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.CategoryMeditation
import com.andreisingeleytsev.listeningmeditationapp.domain.repository.CategoryMeditationRepository
import javax.inject.Inject

class SaveCategoryMeditationUseCase@Inject constructor(
    private val repository: CategoryMeditationRepository
) {
    suspend operator fun invoke(categoryMeditation: CategoryMeditation){
        repository.onChoseCategory(categoryMeditation)
    }
}