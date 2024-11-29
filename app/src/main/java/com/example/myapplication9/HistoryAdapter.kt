package com.example.myapplication9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(
    private var historyItems: List<HistoryItem>,
    private val onDeleteClick: (Long) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val timestampTextView: TextView = view.findViewById(R.id.timestampTextView)
        val deleteButton: Button = view.findViewById(R.id.deleteButton) // 确保你在布局中定义了这个按钮
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val historyItem = historyItems[position]
        holder.titleTextView.text = historyItem.title
        holder.timestampTextView.text = historyItem.timestamp

        // 設置刪除按鈕的點擊事件
        holder.deleteButton.setOnClickListener {
            onDeleteClick(historyItem.id)
        }
    }

    override fun getItemCount(): Int {
        return historyItems.size
    }

    // 更新資料的方法
    fun updateData(newHistoryItems: List<HistoryItem>) {
        historyItems = newHistoryItems
        notifyDataSetChanged()
    }
}
