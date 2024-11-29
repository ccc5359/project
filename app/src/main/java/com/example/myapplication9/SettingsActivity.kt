package com.example.myapplication9

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View

class SettingsActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // 设置工具栏
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            // 点击返回按钮时，结束当前活动并返回到上一个活动（即主画面）
            finish()
        }

        // 设置按钮点击事件监听器
        findViewById<View>(R.id.change_team_name).setOnClickListener(this)
        findViewById<View>(R.id.history).setOnClickListener(this)
        findViewById<View>(R.id.background_mode).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.change_team_name -> onChangeTeamNameClicked(view)
            R.id.history -> onHistoryRecordClicked(view)
            R.id.background_mode -> onBackgroundModeClicked(view)
        }
    }

    // 更改队伍名称的点击事件处理方法
    fun onChangeTeamNameClicked(view: View) {
        val intent = Intent(this, `ChangeTeamNameActivity`::class.java)
        startActivity(intent)
    }

    // 查看历史纪录的点击事件处理方法
    fun onHistoryRecordClicked(view: View) {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    // 后台模式的点击事件处理方法
    fun onBackgroundModeClicked(view: View) {
        val intent = Intent(this, BackgroundModeActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val REQUEST_CODE_CHANGE_TEAM_NAME = 1
    }
}
