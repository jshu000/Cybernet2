package com.example.cybernet2

import android.graphics.Path
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cybernet2.ui.theme.Cybernet2Theme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Paint

class MainActivity : AppCompatActivity() {

    lateinit var paintView: PaintView
    companion object{
        var path = Path()
        var paintBrush = android.graphics.Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        paintView = findViewById(R.id.paint_view)
        findViewById<Button>(R.id.eraser_button).setOnClickListener {
            paintView.activateEraser()
        }

        findViewById<Button>(R.id.brush_button).setOnClickListener {
            paintView.deactivateEraser()
        }
    }
}
