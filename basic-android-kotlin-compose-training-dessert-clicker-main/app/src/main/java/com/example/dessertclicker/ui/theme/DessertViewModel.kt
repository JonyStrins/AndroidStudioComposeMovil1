package com.example.dessertclicker.ui.theme

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.data.Datos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {
    private val _dessertUiState = MutableStateFlow(Datos())
    val dessertUiState: StateFlow<Datos> = _dessertUiState.asStateFlow()

    fun onDessertClicked(){
        _dessertUiState.update { cupcakeUiState ->
            val postreVendido = cupcakeUiState.dessertsSold +1
            val siguientePostre = determineDessertIndex(postreVendido)
            cupcakeUiState.copy(
                currentDessertIndex = siguientePostre,
                revenue = cupcakeUiState.revenue + cupcakeUiState.currentDessertPrice,
                dessertsSold = postreVendido,
                currentDessertImageId = dessertList[siguientePostre].imageId,
                currentDessertPrice = dessertList[siguientePostre].price
            )
        }
    }

    private fun determineDessertIndex(postreVendido: Int): Int{
        var postreId = 0
        for( i in dessertList.indices){
            if(postreVendido >= dessertList[i].startProductionAmount){
                postreId = i
            }else{
                break
            }
        }

        return postreId
    }
}