package com.example.simplewatchapp

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast



class MainActivity : WearableActivity() {

    val pics = arrayListOf<Int>()
    var currentPic = 1
    private var x1: Float = 0.toFloat()
    private var x2: Float = 0.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        pics.add(R.drawable.pic1)
        pics.add(R.drawable.pic2)
        pics.add(R.drawable.pic3)

        // Enables Always-on
        setAmbientEnabled()

    }

    fun previous(){
        currentPic--
        if(currentPic<0) currentPic = pics.size
        imageView.setImageResource(pics[currentPic])
        imageView.setImageResource(R.drawable.pic1)
    }

    fun next(){
        currentPic++
        if(currentPic>=pics.size) currentPic = 0
        imageView.setImageResource(pics[currentPic])
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN-> {
                x1 = event.x
                return true
            }
            MotionEvent.ACTION_UP-> {
                x2 = event.x
                if (x1 > x2) {
                    Toast.makeText(this, "Left swipe", Toast.LENGTH_SHORT).show()
                    previous()
                } else if (x2 > x1) {
                    Toast.makeText(this, "Right swipe", Toast.LENGTH_SHORT).show()
                    next()
                }
            }
        }
        return super.onTouchEvent(event)
    }

}
