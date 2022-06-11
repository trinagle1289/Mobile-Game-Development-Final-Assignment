package tw.edu.pu.s1071554.mobile_game_development_final_assignment.mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.R
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.database.FinancialData

class RecyclerViewAdapter(var onItemClickListener: OnItemClickListener) :
    ListAdapter<FinancialData, FinancialViewHolder>(FinancialComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinancialViewHolder {
        return FinancialViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FinancialViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, onItemClickListener)
    }
}

class FinancialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTime: TextView = itemView.findViewById(R.id.date)
    private val tvAmount: TextView = itemView.findViewById(R.id.amount)
    private val tvMessage: TextView = itemView.findViewById(R.id.description)

    fun bind(data: FinancialData, listener: OnItemClickListener) {
        itemView.setOnClickListener() {
            val position = adapterPosition // Which position inside the recyclerview?

            // if it haven't been removed.
            if (position != RecyclerView.NO_POSITION) // Call that listener interface.
            // And pass the index of that sticky.
                listener.onItemClick(data) // get item from super class
        }
        tvTime.text = data.time
        tvAmount.text = data.amount.toString()
        tvMessage.text = data.message
    }

    companion object {
        fun create(parent: ViewGroup): FinancialViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
            return FinancialViewHolder(view)
        }
    }
}

class FinancialComparator : DiffUtil.ItemCallback<FinancialData>() {
    override fun areItemsTheSame(oldItem: FinancialData, newItem: FinancialData): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: FinancialData, newItem: FinancialData): Boolean {
        return (oldItem.time == newItem.time)
                &&(oldItem.amount == newItem.amount)
                &&(oldItem.message == newItem.message)
    }
}

/*
 * This is an on click listener for the adapter.
 */
interface OnItemClickListener {
    fun onItemClick(financialData: FinancialData?)
}