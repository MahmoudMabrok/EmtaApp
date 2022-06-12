package edu.mahmoud.emta.ui.home

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import edu.mahmoud.emta.DateOperation
import edu.mahmoud.emta.R
import edu.mahmoud.emta.data.EmtaItem
import kotlin.math.absoluteValue

class EmtaViewHolder(itemView: View, onCLick: (Long) -> Unit) : RecyclerView.ViewHolder(itemView) {

    private val tvRemaining: TextView = itemView.findViewById(R.id.tvRemaining)
    private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

    init {
        itemView.setOnClickListener {
            item?.let {
                onCLick.invoke(it.id)
            }
        }
    }

    var item: EmtaItem? = null

    fun bind(data: EmtaItem) {
        item = data
        val date = DateOperation.getDateFromMillis(data.dateMillis)
        val remaining = DateOperation.getDifferenceTillNow(date)
        tvRemaining.text = "${remaining.absoluteValue}"
        tvDate.text = DateOperation.dateToString(date)
        tvTitle.text = data.title
        colorRemaining(remaining)
        Log.d("TestApp", "bind: $data")
    }

    private fun colorRemaining(remaining: Long) {
        val colorID = if (remaining > 0) R.color.futureDate else R.color.pastDate
        val color = ContextCompat.getColor(itemView.context, colorID)
        tvRemaining.setTextColor(color)
    }
}
