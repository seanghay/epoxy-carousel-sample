package com.seanghay.explorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.epoxy.Carousel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Carousel.setDefaultGlobalSnapHelperFactory(null)
        setContentView(R.layout.activity_main)
    }
}