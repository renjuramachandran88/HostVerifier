package com.ml.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "verifier")
data class VerifierEntity(
    @ColumnInfo(name = "data")
    var verifierData: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
)
