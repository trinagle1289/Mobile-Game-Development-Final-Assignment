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

class RecyclerViewAdapter :
    ListAdapter<FinancialData, RecyclerViewAdapter.FinancialViewHolder>(FinancialComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinancialViewHolder {
        return FinancialViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FinancialViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.time, current.amount, current.message)
    }

    class FinancialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTime: TextView = itemView.findViewById(R.id.date)
        private val tvAmount: TextView = itemView.findViewById(R.id.amount)
        private val tvMessage: TextView = itemView.findViewById(R.id.description)

        fun bind(time: String?, amount: Int, message: String?) {
            tvTime.text = time
            tvAmount.text = amount.toString()
            tvMessage.text = message
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
}
