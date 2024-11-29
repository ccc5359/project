package com.example.myapplication9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.app.Activity


class ChangeTeamNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_team_name)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        // 找到确认按钮
        val confirmButton: Button = findViewById(R.id.button_confirm)
        confirmButton.setOnClickListener {
            // 获取EditText中的文本
            val teamAName: String = findViewById<EditText>(R.id.team_a).text.toString()
            val teamBName: String = findViewById<EditText>(R.id.team_b).text.toString()

            // 应用文本到Team A 和 Team B 的 TextView 上
            val intent = Intent()
            intent.putExtra("@string/team_a", teamAName)
            intent.putExtra("@string/team_b", teamBName)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}

