package com.chris.eban.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chris.eban.R

class ZipActivity : AppCompatActivity() {

    init {
        System.loadLibrary("p7zip")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zip)
    }
}
