package com.mendelu.xstast12.zooexplorer.ui.screens.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm
import com.google.maps.android.compose.*
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.map.CustomMapRenderer
import com.mendelu.xstast12.zooexplorer.map.MarkerUtil
import com.mendelu.xstast12.zooexplorer.models.Animal
import com.mendelu.xstast12.zooexplorer.navigation.INavigationRouter
import com.mendelu.xstast12.zooexplorer.ui.elements.CustomDialogLocation
import com.mendelu.xstast12.zooexplorer.ui.elements.MapCard
import com.mendelu.xstast12.zooexplorer.ui.elements.MyNavigationBar
import com.mendelu.xstast12.zooexplorer.ui.theme.md_theme_light_tertiaryContainer
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MapScreen(navigation: INavigationRouter,
              viewModel: MapScreenViewModel = getViewModel()) {

    //MARK: SYSTEM UI CONTROLLER COLORS
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    //TODO: Set systembar to transparent in map
    SideEffect {
        systemUiController.setStatusBarColor(
            //color = Color.Transparent,
            color = md_theme_light_tertiaryContainer,
            darkIcons = useDarkIcons
        )
        //TODO: CHANGE FOR SUPPORT OF DARK MODE
        systemUiController.setNavigationBarColor(
            color = md_theme_light_tertiaryContainer,
        )
    }
//    DisposableEffect(systemUiController, useDarkIcons) {
//        // Update all of the system bar colors to be transparent, and use
//        // dark icons if we're in light theme
////        systemUiController.setSystemBarsColor(
////            color = Color.Transparent,
////            darkIcons = useDarkIcons
////        )
//        systemUiController.setStatusBarColor(
//            color = Color.Transparent,
//            darkIcons = useDarkIcons
//        )
//        // setStatusBarColor() and setNavigationBarColor() also exist
//        onDispose {
//
//        }
//    }

    val animalData = remember{ mutableStateListOf<Animal>() }

    viewModel.mapUiState.value.let { 
        when(it){
            MapScreenUiState.Start -> {}

            is MapScreenUiState.Success -> {
                animalData.clear()
                animalData.addAll(it.animals)
            }
        }
    }

//    BottomSheetScaffold(sheetContent = {},
//                        content =  { paddingValues ->
//                                MapScreenContent(modifier = Modifier.padding(
//                                    bottom = paddingValues.calculateBottomPadding()),
//                                    animals = animalData,
//                                    navigation = navigation) },
//    )

    Scaffold(
        content =  { paddingValues ->
            MapScreenContent(modifier = Modifier.padding(
                                        bottom = paddingValues.calculateBottomPadding()),
                            animals = animalData,
                            navigation = navigation,
                            viewModel = viewModel) },
        bottomBar = { MyNavigationBar(navigation = navigation) },

        )
}

@SuppressLint("MissingPermission")
@OptIn(MapsComposeExperimentalApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class
)
@Composable
fun MapScreenContent(modifier: Modifier, animals: List<Animal>, navigation: INavigationRouter, viewModel: MapScreenViewModel) {

    val mapUiSettings by remember { mutableStateOf(
        MapUiSettings(
            zoomControlsEnabled = false,
            myLocationButtonEnabled = false,
            mapToolbarEnabled = false)
    ) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(49.232499, 16.533065),
            16f)
    }

    var currentMarker by remember { mutableStateOf<Marker?>(null)}

    var googleMap by remember { mutableStateOf<GoogleMap?>(null) }
    var clusterRenderer by remember { mutableStateOf<CustomMapRenderer?>(null)}
    var clusterManager by remember { mutableStateOf<ClusterManager<Animal>?>(null)}
    val context = LocalContext.current


    val locationPermissionState =
        rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    val locationEnabled = remember { mutableStateOf(false) }
    if (locationPermissionState.status.isGranted){
        locationEnabled.value = true
    }

    val fusedLocationClient: FusedLocationProviderClient
        = LocationServices.getFusedLocationProviderClient(context)

//    val hasPermission =  remember { mutableStateOf(false) }
//    //TODO
//    hasPermission.value = !(ActivityCompat.checkSelfPermission(
//        context,
//        Manifest.permission.ACCESS_FINE_LOCATION
//    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//        context,
//        Manifest.permission.ACCESS_COARSE_LOCATION
//    ) != PackageManager.PERMISSION_GRANTED)

    DisposableEffect(Unit) {
        onDispose {
            googleMap?.clear()
        } }

    if(animals.isNotEmpty()){
        clusterManager?.addItems(animals)
        clusterManager?.cluster()
    }

    Box(
        modifier = modifier.fillMaxSize().testTag("MapTag"),
        contentAlignment = Alignment.BottomCenter
    ){
        GoogleMap(uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = locationEnabled.value)
        )
        {
            MapEffect(animals) { map ->
                if(googleMap == null) {
                    googleMap = map
                }

                if ( clusterManager == null){
                    clusterManager = ClusterManager<Animal>(context, map)
                }
//                if (clusterRenderer == null){
//                    clusterRenderer = CustomMapRenderer(context, map, clusterManager!!)
//                }
                clusterRenderer = CustomMapRenderer(context, map, clusterManager!!)
                clusterManager?.setAnimation(true)

                clusterManager?.apply {
                    algorithm = NonHierarchicalDistanceBasedAlgorithm()
                    renderer = clusterRenderer

                    //OnClusterItemClickListener
                    renderer.setOnClusterItemClickListener { item ->
                        if (currentMarker != null &&
                            clusterManager?.markerCollection?.markers?.contains(currentMarker)!!) {

                            currentMarker!!.setIcon(
                                MarkerUtil.setOnClickMarkerIcon(
                                    (currentMarker?.tag as Animal).animalClass , false, context
                                )
                            )
                            currentMarker = null
                        }

                        viewModel.currentAnimal = item
                        map.animateCamera(
                            CameraUpdateFactory
                                .newLatLngZoom(LatLng(item!!.lat, item.long), 19f)
                        )

                        currentMarker = clusterRenderer?.getMarker(item)
                        currentMarker?.tag = item
                        currentMarker!!.setIcon(
                            MarkerUtil.setOnClickMarkerIcon(
                                item.animalClass, true, context
                            )
                        )
                        true
                    }
                }

                map.setOnCameraIdleListener {
                    clusterManager?.cluster()
                }
                map.setOnMapClickListener {
                    clusterManager?.cluster()
                    changeCurrentMarkerToSmallIcon(currentMarker, clusterManager!!, context)
                    currentMarker = null

                }


            }
        }

        if(currentMarker != null){
            MapCard(marker = currentMarker!!,
                onClick = {
                    navigation.navigateToDetail(viewModel.currentAnimal!!.id)
                },
                image = viewModel.getPhoto(viewModel.currentAnimal!!.images[0])
            )

        }

    }


    Row(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(0.1f), horizontalAlignment = Alignment.Start) {
            Surface(shape = CircleShape,
                onClick = { navigation.navigateToInfo()},
                shadowElevation = 10.dp,
                color = md_theme_light_tertiaryContainer,
                border = BorderStroke(1.dp, color = Color.Black)
            ){
                Icon(painter = painterResource(id = R.drawable.ic_outline_info_24),
                    contentDescription = "infoButton" )
            }
        }
        
        Spacer(modifier = Modifier.weight(0.8f))

        Column(modifier = Modifier.weight(0.1f),horizontalAlignment = Alignment.End) {
            Surface(shape = CircleShape,
                modifier = Modifier.testTag("infoButton"),
                onClick = {
                    if (locationPermissionState.status.isGranted){
                        //CHECK NEDDED??
                        fusedLocationClient.lastLocation.addOnSuccessListener {
                            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                                    LatLng(it.latitude,
                                        it.longitude),
                            17f)
                        }

                    }else{
                        locationPermissionState.launchPermissionRequest()
                    }
                },
                shadowElevation = 10.dp,
                color = md_theme_light_tertiaryContainer,
                border = BorderStroke(1.dp, color = Color.Black)
            ){
                Icon(painter = painterResource(id = R.drawable.ic_outline_my_location_24),
                    contentDescription = "infoButton" )
            }
        }
    }
}


fun changeCurrentMarkerToSmallIcon(currentMarker: Marker?,
                                   clusterManager: ClusterManager<Animal>,
                                    context: Context){
    if (currentMarker != null &&
        clusterManager.markerCollection?.markers?.contains(currentMarker)!!) {

        currentMarker.setIcon(
            MarkerUtil.setOnClickMarkerIcon(
                (currentMarker.tag as Animal).animalClass , false, context
            )
        )
    }
}



