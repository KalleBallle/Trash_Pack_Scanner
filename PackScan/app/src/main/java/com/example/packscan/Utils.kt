package com.example.packscan

import android.R
import androidx.compose.ui.graphics.Color


fun String.ToHash(): Color
{
    return Color(android.graphics.Color.parseColor(this))
}