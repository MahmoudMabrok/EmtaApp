package edu.mahmoud.emta.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.mahmoud.emta.R
import edu.mahmoud.emta.data.EmtaItem


class EmtaListAdapter(private val list: MutableList<EmtaItem> = arrayListOf()) :
    RecyclerView.Adapter<EmtaViewHolder>() {

    fun updateList(newList: List<EmtaItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): EmtaViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_emta, viewGroup, false)
        return EmtaViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmtaViewHolder, i: Int) {
        holder.bind(list[i])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
