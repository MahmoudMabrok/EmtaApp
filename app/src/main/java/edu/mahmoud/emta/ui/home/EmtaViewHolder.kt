package edu.mahmoud.emta.ui.home

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.mahmoud.emta.DateOperation
import edu.mahmoud.emta.R
import edu.mahmoud.emta.data.EmtaItem

class EmtaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvRemaining: TextView = itemView.findViewById(R.id.tvRemaining)
    private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

    fun bind(data: EmtaItem) {
        val date = DateOperation.getDateFromMillis(data.dateMillis)
        tvRemaining.text = "${DateOperation.getDifferenceTillNow(date)}"
        tvDate.text = DateOperation.dateToString(date)
        tvTitle.text = data.title
        Log.d("TestApp", "bind: $data")
    }
}
