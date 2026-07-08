package com.example.packscan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.packscan.Data.DatabaseProvider
import com.example.packscan.UI.Navi.Navigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //DatabaseProvider.getDatabase(applicationContext)
        setContent {
            Navigation()



        }
    }
}

