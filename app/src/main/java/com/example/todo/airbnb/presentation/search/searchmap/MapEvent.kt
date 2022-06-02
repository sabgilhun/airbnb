package com.example.todo.airbnb.presentation.search.searchmap

import com.example.todo.airbnb.data.Spot
import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    data class OnMapLongClick(val latLng: LatLng) : MapEvent()
    data class OnInfoWindowLongClick(val spot: Spot): MapEvent()
}
