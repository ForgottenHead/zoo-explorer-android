package com.mendelu.xstast12.zooexplorer.ui.screens.map

import com.mendelu.xstast12.zooexplorer.models.Animal

sealed class MapScreenUiState{
    object Start : MapScreenUiState()
    class Success(var animals: List<Animal>): MapScreenUiState()
}
