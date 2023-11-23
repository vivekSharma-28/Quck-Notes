package com.example.quckNotes.UI.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.quckNotes.MainActivity
import com.example.quckNotes.Model.Notes
import com.example.quckNotes.R
import com.example.quckNotes.UI.Adaptor.Recycle_Adaptor
import com.example.quckNotes.ViewModel.NotesViewModel
import com.example.quckNotes.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerAdaptor: Recycle_Adaptor
    private val viewModel: NotesViewModel by viewModels()
    private lateinit var noteList: ArrayList<Notes>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).changeTitle("Home")
        setHasOptionsMenu(true)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            noteList = notesList as ArrayList<Notes>
            binding.rccAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
            recyclerAdaptor = Recycle_Adaptor(notesList)
            binding.rccAllNotes.adapter = recyclerAdaptor
            recyclerAdaptor.setOnItemClickListener = {
                findNavController().navigate(
                    R.id.action_homeFragment_to_editFragment,
                    Bundle().apply {
                        putInt("Id", it.id!!)
                        putString("Title", it.title)
                        putString("Subtitle", it.subtitle)
                        putString("Notes", it.notes)
                        putString("priority", it.priority)
                        putString("favorite", it.favorite)
                    })
            }

        }

        binding.btnaddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)
        }

        binding.filter.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                noteList = notesList as ArrayList<Notes>
                binding.rccAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                recyclerAdaptor = Recycle_Adaptor(notesList)
                binding.rccAllNotes.adapter = recyclerAdaptor
                recyclerAdaptor.setOnItemClickListener = {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_editFragment,
                        Bundle().apply {
                            putInt("Id", it.id!!)
                            putString("Title", it.title)
                            putString("Subtitle", it.subtitle)
                            putString("Notes", it.notes)
                            putString("priority", it.priority)
                            putString("favorite", it.favorite)
                        })
                }

            }
        }

        binding.filterHigh.setOnClickListener {
            viewModel.highNotes().observe(viewLifecycleOwner) { notesList ->
                noteList = notesList as ArrayList<Notes>
                binding.rccAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                recyclerAdaptor = Recycle_Adaptor(notesList)
                binding.rccAllNotes.adapter = recyclerAdaptor
                recyclerAdaptor.setOnItemClickListener = {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_editFragment,
                        Bundle().apply {
                            putInt("Id", it.id!!)
                            putString("Title", it.title)
                            putString("Subtitle", it.subtitle)
                            putString("Notes", it.notes)
                            putString("priority", it.priority)
                            putString("favorite", it.favorite)
                        })
                }

            }
        }

        binding.filterMedium.setOnClickListener {
            viewModel.mediumNotes().observe(viewLifecycleOwner) { notesList ->
                noteList = notesList as ArrayList<Notes>
                binding.rccAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                recyclerAdaptor = Recycle_Adaptor(notesList)
                binding.rccAllNotes.adapter = recyclerAdaptor
                recyclerAdaptor.setOnItemClickListener = {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_editFragment,
                        Bundle().apply {
                            putInt("Id", it.id!!)
                            putString("Title", it.title)
                            putString("Subtitle", it.subtitle)
                            putString("Notes", it.notes)
                            putString("priority", it.priority)
                            putString("favorite", it.favorite)
                        })
                }

            }
        }

        binding.filterLow.setOnClickListener {
            viewModel.lowNotes().observe(viewLifecycleOwner) { notesList ->
                noteList = notesList as ArrayList<Notes>
                binding.rccAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                recyclerAdaptor = Recycle_Adaptor(notesList)
                binding.rccAllNotes.adapter = recyclerAdaptor
                recyclerAdaptor.setOnItemClickListener = {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_editFragment,
                        Bundle().apply {
                            putInt("Id", it.id!!)
                            putString("Title", it.title)
                            putString("Subtitle", it.subtitle)
                            putString("Notes", it.notes)
                            putString("priority", it.priority)
                            putString("favorite", it.favorite)
                        })
                }

            }
        }

        binding.filterFavorite.setOnClickListener {
            viewModel.favNotes().observe(viewLifecycleOwner) { notesList ->
                noteList = notesList as ArrayList<Notes>
                binding.rccAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                recyclerAdaptor = Recycle_Adaptor(notesList)
                binding.rccAllNotes.adapter = recyclerAdaptor
                recyclerAdaptor.setOnItemClickListener = {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_editFragment,
                        Bundle().apply {
                            putInt("Id", it.id!!)
                            putString("Title", it.title)
                            putString("Subtitle", it.subtitle)
                            putString("Notes", it.notes)
                            putString("priority", it.priority)
                            putString("favorite", it.favorite)
                        })
                }
            }
        }
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Text Here...."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                notesFilter(p0)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFilter(p0: String?) {

        val searchedNotes = arrayListOf<Notes>()

        for (i in noteList) {
            if (i.title.contains(p0!!) || i.subtitle.contains(p0) || i.notes.contains(p0)) {
                searchedNotes.add(i)
            }
        }

        recyclerAdaptor.filterList(searchedNotes)

    }

}


