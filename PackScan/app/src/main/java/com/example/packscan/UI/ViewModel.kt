package com.example.packscan.UI

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packscan.Data.Figure

import com.example.packscan.Data.FigureDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.checkerframework.checker.units.qual.Prefix


class FigureViewModel(private val figureDao: FigureDao) : ViewModel() {

        var inputID by mutableStateOf("")
            private set
        var currFigure by mutableStateOf<Figure?>(null)
            private set

        var figureExists by mutableStateOf<Boolean?>(null)
            private set

        fun onIdChange(newText: String) {
            inputID = newText
        }


    fun checkFigureWithPrefix(prefix: String) {
        val id = prefix + inputID





        viewModelScope.launch(Dispatchers.IO) {
            val figure = figureDao.getFigureById(id)
            withContext(Dispatchers.Main) {
                currFigure = figure
                figureExists = (figure != null)
            }
        }
    }

        fun updateImagePath(newPath: String, figureId: String) {
            viewModelScope.launch(Dispatchers.IO) {
                figureDao.updateImagePath(newPath, figureId)


                withContext(Dispatchers.Main) {
                    currFigure = currFigure?.copy(imagePath = newPath)
                }
            }
        }
        fun updateCollect( figureId: String) {
            viewModelScope.launch(Dispatchers.IO) {
                currFigure?.let {  nonNullFig ->
                    figureDao.markAsCollected(nonNullFig.id)
                    figureDao.updateQuantity(nonNullFig.quantity +1,nonNullFig.id)

                }


                withContext(Dispatchers.Main) {
                    currFigure = currFigure?.copy(
                        quantity = (currFigure?.quantity ?: 0) + 1, collected = true )

                }
            }
        }


        fun clearState() {
            inputID = ""
            figureExists = null
        }
    }
