package com.djr.newssphere.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.djr.newssphere.data.model.Headline

@Dao
interface HeadlineDao {

    @Query("SELECT * FROM headlines ORDER BY date DESC")
    fun getAllHeadlines(): List<Headline>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeadlines(headlines: List<Headline>)

    @Query("DELETE FROM headlines")
    suspend fun deleteAllHeadlines()
}