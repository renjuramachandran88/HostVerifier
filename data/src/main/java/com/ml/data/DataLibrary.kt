package com.ml.data

import android.content.Context
import com.ml.data.local.VerifierDao
import com.ml.data.local.VerifierDatabase

class DataLibrary(context: Context) {
    private var component: DataComponent =
        DaggerDataComponent.factory().create(context)


    fun veriferDataBase(): VerifierDatabase {
        return component.veriferDataBase()
    }

    fun veriferDao(): VerifierDao {
        return component.veriferDao()
    }
}