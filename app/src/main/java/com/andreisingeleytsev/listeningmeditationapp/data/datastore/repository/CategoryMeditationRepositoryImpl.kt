package com.andreisingeleytsev.listeningmeditationapp.data.datastore.repository

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.andreisingeleytsev.listeningmeditationapp.common.CategoryMap
import com.andreisingeleytsev.listeningmeditationapp.common.Constants
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.DataStoreHub
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.model.CategoryMeditation
import com.andreisingeleytsev.listeningmeditationapp.domain.repository.CategoryMeditationRepository
import com.andreisingeleytsev.listeningmeditationapp.domain.repository.OnboardStateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class CategoryMeditationRepositoryImpl(private val dataStoreHub: DataStoreHub):
    CategoryMeditationRepository {

    private object PreferencesKeys {
        val categoryKey = intPreferencesKey(name = Constants.CATEGORY_KEY)
    }

    override suspend fun onChoseCategory(categoryMeditation: CategoryMeditation) {
        val index = CategoryMap.map.filterValues { category ->
            category == categoryMeditation
        }.keys.first()
        dataStoreHub.dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferencesKeys.categoryKey] = index
        }
    }

    override fun getCategory(): Flow<CategoryMeditation> {
        return dataStoreHub.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val index = preferences[PreferencesKeys.categoryKey] ?: 0
                CategoryMap.map[index]!!
            }
    }


}