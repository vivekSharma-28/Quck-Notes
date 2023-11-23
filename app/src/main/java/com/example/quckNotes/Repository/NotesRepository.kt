package com.example.quckNotes.Repository

import androidx.lifecycle.LiveData
import com.example.quckNotes.Dao.NotesDao
import com.example.quckNotes.Model.Notes

class NotesRepository(private val dao: NotesDao) {

    fun getAllData(): LiveData<List<Notes>> {
        return dao.getNotes()
    }

    fun highNotes(): LiveData<List<Notes>> {
        return dao.getHighNotes()
    }

    fun mediumNotes(): LiveData<List<Notes>> {
        return dao.getMediumNotes()
    }

    fun lowNotes(): LiveData<List<Notes>> {
        return dao.getLowNotes()
    }

    fun favNotes(): LiveData<List<Notes>> {
        return dao.getFavNotes()
    }

    fun insertNote(notes: Notes) {
        dao.insertNotes(notes)
    }

    fun deleteNote(id: Int) {
        dao.deleteNotes(id)
    }

    fun updateNote(notes: Notes) {
        dao.updateNotes(notes)
    }

}