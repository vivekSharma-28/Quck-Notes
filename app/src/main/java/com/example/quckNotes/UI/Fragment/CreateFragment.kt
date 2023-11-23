package com.example.quckNotes.UI.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.quckNotes.MainActivity
import com.example.quckNotes.Model.Notes
import com.example.quckNotes.R
import com.example.quckNotes.ViewModel.NotesViewModel
import com.example.quckNotes.databinding.FragmentCreateBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Date


class CreateFragment : Fragment() {

    private var priority: String = "1"
    private var favorite: String = "False"
    private val viewModel: NotesViewModel by viewModels()
    private lateinit var binding: FragmentCreateBinding

    @SuppressLint("UseRequireInsteadOfGet", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding= FragmentCreateBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).changeTitle("Create Note's")
        binding.apply {
            pgreen.setImageResource(R.drawable.baseline_done_24)

            pred.setOnClickListener {
                priority = "3"
                pred.setImageResource(R.drawable.baseline_done_24)
                pyellow.setImageResource(0)
                pgreen.setImageResource(0)

            }

            pyellow.setOnClickListener {
                priority = "2"
                pyellow.setImageResource(R.drawable.baseline_done_24)
                pgreen.setImageResource(0)
                pred.setImageResource(0)

            }

            pgreen.setOnClickListener {
                priority = "1"
                pgreen.setImageResource(R.drawable.baseline_done_24)
                pyellow.setImageResource(0)
                pred.setImageResource(0)

            }

            favorite.setOnClickListener {
                onClick()
            }

            btnSaveNotes.setOnClickListener {
                createNotes(it)
            }
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editsubtitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val d = Date()
        val notesDate: String = DateFormat.format("MMMM d, yyyy ", d.time).toString()
        if (title == "") {
            binding.frameLayout.let { it1 -> Snackbar.make(it1,"Title Field Is Empty,Please give a title",Snackbar.LENGTH_SHORT).show() }
        }
        /* else if (subtitle == "") {
            createFragmentView?.let { it1 -> Snackbar.make(it1,"SubTitle Field Is Empty,Please give a subtitle",Snackbar.LENGTH_SHORT).show() }
           Toast.makeText(requireContext(), "SubTitle Field Is Empty", Toast.LENGTH_SHORT).show()
        }*/
        else if (notes == "") {
            binding.frameLayout.let { it1 -> Snackbar.make(it1,"Notes Field Is Empty,Enter some Note",Snackbar.LENGTH_SHORT).show() }
        }
        else {

            val data = Notes(
                null,
                title = title,
                subtitle = subtitle,
                notes = notes,
                date = notesDate,
                priority = priority,
                favorite = favorite
            )
            viewModel.addNotes(data)
            Navigation.findNavController(it!!).navigate(R.id.action_createFragment_to_homeFragment)
            Navigation.findNavController(requireView()).popBackStack(
                R.id.createFragment, true
            )
        }
    }

    private fun onClick() {
        if (favorite == "False") {
            favorite = "True"
            binding.favorite.setImageResource(R.drawable.checked)
        } else {
            favorite = "False"
            binding.favorite.setImageResource(R.drawable.unchecked)
        }
    }
}