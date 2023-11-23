package com.example.quckNotes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.quckNotes.Database.NotesDatabase
import com.example.quckNotes.Model.Notes
import com.example.quckNotes.Repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).notesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(notes)
        }
    }

    fun getNotes(): LiveData<List<Notes>> {
        return repository.getAllData()
    }

    fun highNotes(): LiveData<List<Notes>> {
        return repository.highNotes()
    }

    fun mediumNotes(): LiveData<List<Notes>> {
        return repository.mediumNotes()
    }

    fun lowNotes(): LiveData<List<Notes>> {
        return repository.lowNotes()
    }

    fun favNotes(): LiveData<List<Notes>> {
        return repository.favNotes()
    }

    fun deleteNotes(id: Int) {
        repository.deleteNote(id)
    }

    fun updateNotes(notes: Notes) {
        repository.updateNote(notes)
    }
}