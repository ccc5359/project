package com.example.myapplication9

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // 设置工具栏
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        dbHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 创建适配器，传递删除按钮的回调
        adapter = HistoryAdapter(getAllHistory()) { id ->
            deleteHistory(id)
        }
        recyclerView.adapter = adapter
    }

    private fun insertHistory(title: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TITLE, title)
            put(DatabaseHelper.COLUMN_TIMESTAMP, System.currentTimeMillis().toString())
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values)
    }

    private fun getAllHistory(): List<HistoryItem> {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_TITLE,
            DatabaseHelper.COLUMN_TIMESTAMP
        )

        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val historyList = mutableListOf<HistoryItem>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE))
                val timestamp = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_TIMESTAMP))
                historyList.add(HistoryItem(id, title, timestamp))
            }
        }
        cursor.close()
        return historyList
    }

    private fun deleteHistory(id: Long) {
        val db = dbHelper.writableDatabase
        db.delete(DatabaseHelper.TABLE_NAME, "${DatabaseHelper.COLUMN_ID} = ?", arrayOf(id.toString()))
        // 刷新 RecyclerView
        adapter = HistoryAdapter(getAllHistory()) { deleteHistory(it) }
        recyclerView.adapter = adapter
    }
}
