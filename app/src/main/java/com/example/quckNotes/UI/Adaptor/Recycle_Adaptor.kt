package com.example.quckNotes.UI.Adaptor

import android.annotation.SuppressLint
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quckNotes.Model.Notes
import com.example.quckNotes.R
import com.example.quckNotes.ViewModel.NotesViewModel

var viewModel:NotesViewModel=NotesViewModel(application = Application())

class Recycle_Adaptor(var notesList: List<Notes>) :
    RecyclerView.Adapter<Recycle_Adaptor.ViewHolder>() {

    var setOnItemClickListener: ((Notes) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.title)
        val subtitle: TextView = itemView.findViewById(R.id.subtitle)
        val date: TextView = itemView.findViewById(R.id.datetext)
        val priority: View = itemView.findViewById(R.id.priority)
        val favorite: ImageView = itemView.findViewById(R.id.fav)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_notes, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prio = notesList[position].priority
        var fav = notesList[position].favorite
        holder.apply {
            title.text = notesList[position].title
            subtitle.text = notesList[position].subtitle
            date.text = notesList[position].date
            itemView.setOnClickListener {

                setOnItemClickListener?.invoke(notesList[position])
            }
        }

        holder.favorite.setOnClickListener {
            if (notesList[position].favorite=="True"){
                fav="False"
                val data=Notes(notesList[position].id,notesList[position].title,notesList[position].subtitle,notesList[position].notes,notesList[position].date,notesList[position].priority,fav)
                viewModel.updateNotes(data)
                notifyDataSetChanged()
                holder.favorite.setImageResource(R.drawable.unchecked)
            }else{
                fav="True"
                val data=Notes(notesList[position].id,notesList[position].title,notesList[position].subtitle,notesList[position].notes,notesList[position].date,notesList[position].priority,fav)
                viewModel.updateNotes(data)
                notifyDataSetChanged()
                holder.favorite.setImageResource(R.drawable.checked)
            }
        }

        when (prio) {
            "1" -> {
                holder.priority.setBackgroundResource(R.drawable.green_dot)
            }

            "2" -> {
                holder.priority.setBackgroundResource(R.drawable.yellow_dot)
            }

            else -> {
                holder.priority.setBackgroundResource(R.drawable.red_dot)
            }
        }

        if (fav == "True") {
            holder.favorite.setImageResource(R.drawable.checked)
        } else {
            holder.favorite.setImageResource(R.drawable.unchecked)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(searchedNotes: ArrayList<Notes>) {
        notesList = searchedNotes
        notifyDataSetChanged()
    }

}

