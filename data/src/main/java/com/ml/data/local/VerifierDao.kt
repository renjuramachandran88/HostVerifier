package com.ml.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ml.data.entity.VerifierEntity

@Dao
interface VerifierDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVerifierData(verifierEntity: VerifierEntity)

    @Query("SELECT * FROM verifier")
    fun getVerifierData(): List<VerifierEntity>

    @Query("DELETE FROM verifier")
    fun clear()
}