package com.ucb.deliveryapp.ui.screens

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.maplibre.android.MapLibre
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.camera.CameraUpdateFactory

@Composable
fun MapLibreView(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    remember {
        try {
            MapLibre.getInstance(context)
        } catch (_: Exception) { }
    }

    val mapView = remember {
        MapView(context).apply {
            onCreate(Bundle())
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mapView.onDestroy()
        }
    }

    AndroidView(factory = { mapView }, modifier = modifier) { mv ->
        mv.getMapAsync { mapLibreMap ->
            mapLibreMap.setStyle(
                Style.Builder().fromUri("https://demotiles.maplibre.org/style.json")
            ) {
                val position = CameraPosition.Builder()
                    .target(LatLng(-17.3895, -66.1568))
                    .zoom(12.0)
                    .build()
                mapLibreMap.moveCamera(CameraUpdateFactory.newCameraPosition(position))
            }
        }
    }
}


