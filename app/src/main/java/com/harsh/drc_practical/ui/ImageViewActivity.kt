package com.harsh.drc_practical.ui

import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.harsh.drc_practical.R
import com.harsh.drc_practical.base.Constants
import kotlinx.android.synthetic.main.activity_image_view.*

class ImageViewActivity : AppCompatActivity() {

    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var mScaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        intent.extras?.let {
            val imageUrl = intent.getStringExtra(Constants.IMAGE_URL).orEmpty()
            if (imageUrl.isNotEmpty()) {
                Glide.with(this)
                    .load(imageUrl)
                    .skipMemoryCache(false)
                    .centerCrop()
                    .into(ivZoomable)

                scaleGestureDetector = ScaleGestureDetector(this, ScaleDetection())
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return scaleGestureDetector.onTouchEvent(event)
    }

    inner class ScaleDetection : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            mScaleFactor *= detector.scaleFactor
            if (mScaleFactor > 1 && mScaleFactor < 5) {
                ivZoomable.scaleX = mScaleFactor
                ivZoomable.scaleY = mScaleFactor
            }
            return true
        }

    }
}