package com.ml.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ml.data.entity.VerifierEntity
import com.ml.data.local.VerifierDatabase.Companion.VERIFIER_DATABASE

@Database(
    entities = [VerifierEntity::class],
    version = VERIFIER_DATABASE,
    exportSchema = false
)
abstract class VerifierDatabase : RoomDatabase() {

    companion object {
        const val VERIFIER_DATABASE = 1
    }

    abstract fun verifierDataDaoService(): VerifierDao
}