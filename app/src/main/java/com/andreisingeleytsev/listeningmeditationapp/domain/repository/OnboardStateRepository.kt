package com.andreisingeleytsev.listeningmeditationapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardStateRepository {

    suspend fun onFinishedSave()

    fun getOnboardState(): Flow<Boolean>
}