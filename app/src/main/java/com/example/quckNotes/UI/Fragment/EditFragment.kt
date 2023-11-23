package com.example.quckNotes.UI.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.quckNotes.MainActivity
import com.example.quckNotes.Model.Notes
import com.example.quckNotes.R
import com.example.quckNotes.ViewModel.NotesViewModel
import com.example.quckNotes.databinding.FragmentEditBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.util.Date

@Suppress("DEPRECATION")
class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private var priority: String = "1"
    private var id: Int? = null
    private var favorite: String = "False"
    private val viewModel: NotesViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding= FragmentEditBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).changeTitle("Update Note's")
        val title = requireArguments().getString("Title")
        val subtitle = requireArguments().getString("Subtitle")
        val notes = requireArguments().getString("Notes")
        favorite = requireArguments().getString("favorite")!!
        id = requireArguments().getInt("Id")
        priority = requireArguments().getString("priority").toString()
        binding.editTitle.setText(title)
        binding.editsubtitle.setText(subtitle)
        binding.editNotes.setText(notes)

        when (priority) {
            "1" -> {
                binding.pgreen.setImageResource(R.drawable.baseline_done_24)
                binding.pyellow.setImageResource(0)
                binding.pred.setImageResource(0)
            }

            "2" -> {
                binding.pyellow.setImageResource(R.drawable.baseline_done_24)
                binding.pgreen.setImageResource(0)
                binding.pred.setImageResource(0)
            }

            else -> {
                binding.pred.setImageResource(R.drawable.baseline_done_24)
                binding.pyellow.setImageResource(0)
                binding.pgreen.setImageResource(0)
            }
        }

        if (favorite == "True") {
            binding.fav.setImageResource(R.drawable.checked)
        }
        else {
            binding.fav.setImageResource(R.drawable.unchecked)
        }

        binding.pred.setOnClickListener {
            priority = "3"
            binding.pred.setImageResource(R.drawable.baseline_done_24)
            binding.pyellow.setImageResource(0)
            binding.pgreen.setImageResource(0)

        }

        binding.pyellow.setOnClickListener {
            priority = "2"
            binding.pyellow.setImageResource(R.drawable.baseline_done_24)
            binding.pgreen.setImageResource(0)
            binding.pred.setImageResource(0)

        }

        binding.pgreen.setOnClickListener {
            priority = "1"
            binding.pgreen.setImageResource(R.drawable.baseline_done_24)
            binding.pyellow.setImageResource(0)
            binding.pred.setImageResource(0)

        }

        binding.btnSaveNotes.setOnClickListener {
            updateNotes()
        }

        binding.fav.setOnClickListener {
            onClick()
        }

        return binding.root
    }

    private fun updateNotes() {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editsubtitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val d = Date()
        val notesDate: String = DateFormat.format("MMMM d, yyyy ", d.time).toString()
        if (title == "") {
            Snackbar.make(binding.frameLayout, "Title Field Is Empty,Please give a title", Snackbar.LENGTH_SHORT).show()
        }
        /*else if (subtitle == "")
        {
            editFragmentView?.let { it1 -> Snackbar.make(it1, "Title Field Is Empty,Please give a title", Snackbar.LENGTH_SHORT).show() }
            Toast.makeText(requireContext(), "SubTitle Field Is Empty", Toast.LENGTH_SHORT).show()
        }
        */
        else if (notes == "") {
            Snackbar.make(binding.frameLayout, "Notes Field Is Empty,Enter some Note", Snackbar.LENGTH_SHORT).show()
        }
        else {
            val data = Notes(
                id = id,
                title = title,
                subtitle = subtitle,
                notes = notes,
                date = notesDate,
                priority = priority,
                favorite = favorite
            )
            viewModel.updateNotes(data)
            Toast.makeText(requireContext(),"Change's Has Been Saved",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editFragment_to_homeFragment)
            findNavController(requireView()).popBackStack(
                R.id.editFragment, true
            )
        }
    }

    private fun onClick() {
        if (favorite == "False") {
            favorite = "True"
            binding.fav.setImageResource(R.drawable.checked)
        }
        else {
            favorite = "False"
            binding.fav.setImageResource(R.drawable.unchecked)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.delete) {
            val bottomSheet = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dialog_delete)

            bottomSheet.findViewById<TextView>(R.id.textview_yes)?.setOnClickListener {
                viewModel.deleteNotes(id!!)
                bottomSheet.dismiss()
                findNavController().navigate(R.id.action_editFragment_to_homeFragment)
                findNavController(requireView()).popBackStack(
                    R.id.editFragment, true
                )

            }

            bottomSheet.findViewById<TextView>(R.id.textview_no)?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()

        }

        return super.onOptionsItemSelected(item)
    }

}