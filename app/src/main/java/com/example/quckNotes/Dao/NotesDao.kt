package com.example.quckNotes.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.quckNotes.Model.Notes

@Dao
interface NotesDao {

    @Query("Select * From Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Query("Select * From Notes Where priority=3")
    fun getHighNotes(): LiveData<List<Notes>>

    @Query("Select * From Notes Where priority=2")
    fun getMediumNotes(): LiveData<List<Notes>>

    @Query("Select * From Notes Where priority=1")
    fun getLowNotes(): LiveData<List<Notes>>

    @Query("Select * From Notes Where favorite='True'")
    fun getFavNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("Delete From Notes Where id=:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)
}