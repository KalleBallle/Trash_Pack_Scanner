package com.example.packscan.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface FigureDao {

    @Insert
    suspend fun insertFigure(figure: Figure)
    @Insert
    suspend fun insertManyFigures(figures: List<Figure>)

    @Update
    suspend fun updateFigure(figure: Figure)

    @Delete
    suspend fun deleteFigure(figure: Figure)

    @Query("SELECT * FROM figures")
    fun getAllFigures(): Flow<List<Figure>>

     @Query("SELECT * FROM figures WHERE series = :seriesName")
     fun getAllSeries(seriesName : String): Flow<List<Figure>>


    @Query("UPDATE figures SET collected = 1 WHERE id = :figureId")
    suspend fun markAsCollected(figureId: String)
    @Query("UPDATE figures SET imagePath = :newImagePath WHERE id = :figureId")
    suspend fun updateImagePath( newImagePath : String, figureId: String)
    @Query("UPDATE figures SET quantity = :newQuantity WHERE id = :figureId")
    suspend fun updateQuantity( newQuantity : Int, figureId: String)

    @Query("SELECT * FROM figures WHERE id = :figureId")
    suspend fun getFigureById(figureId: String): Figure?

}

