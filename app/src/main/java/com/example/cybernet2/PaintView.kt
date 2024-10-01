package com.example.cybernet2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.cybernet2.MainActivity.Companion.paintBrush

class PaintView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var currentPath: Path = Path()
    private var currentBrushColor: Int = android.graphics.Color.RED
    private var currentStrokeWidth: Float = 8f
    private var isEraserActive: Boolean = false

    companion object {
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var strokeWidthList = ArrayList<Float>()
        var eraserColor = android.graphics.Color.WHITE
    }

    init {
        paintBrush.isAntiAlias = true
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = currentStrokeWidth
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x ?: 0f
        val y = event?.y ?: 0f

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath = Path()
                currentPath.moveTo(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(x, y)
                pathList.add(currentPath)
                colorList.add(currentBrushColor)
                strokeWidthList.add(currentStrokeWidth)
            }
            else -> return false
        }

        postInvalidate()  // Redraw the view
        return true
    }

    override fun onDraw(canvas: Canvas) {
        for (i in pathList.indices) {
            paintBrush.color = colorList[i]
            paintBrush.strokeWidth = strokeWidthList[i]
            canvas.drawPath(pathList[i], paintBrush)
        }
    }

    // Method to activate the eraser
    fun activateEraser() {
        isEraserActive = true
        currentBrushColor = eraserColor
        currentStrokeWidth = 30f
    }


    fun deactivateEraser() {
        isEraserActive = false
        currentBrushColor = android.graphics.Color.RED
        currentStrokeWidth = 8f
    }
}
