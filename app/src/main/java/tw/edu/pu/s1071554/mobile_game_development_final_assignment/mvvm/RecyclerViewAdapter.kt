package tw.edu.pu.s1071554.mobile_game_development_final_assignment.mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.R
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.database.FinancialData

class RecyclerViewAdapter (private var dataSet: List<FinancialData>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView
        val amount: TextView
        val description: TextView

        init {
            // Define click listener for the ViewHolder's View.
            date = view.findViewById(R.id.date)
            amount = view.findViewById(R.id.amount)
            description = view.findViewById(R.id.description)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recyclerview_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.date.text = "日期：" +dataSet[position].time
        viewHolder.amount.text = "金額" + dataSet[position].amount
        viewHolder.description.text = dataSet[position].message
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun submitList(l: List<FinancialData>) {
        dataSet = l;
    }
}
