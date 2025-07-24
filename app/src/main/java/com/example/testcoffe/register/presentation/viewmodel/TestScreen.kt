package com.example.testcoffe.register.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testcoffe.locations.presentation.viewmodel.LocationViewModel

@Composable
fun TestScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    locationVM: LocationViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
//        viewModel.register("bla", "bb", "bb")
        viewModel.login("bla", "bb")

        snapshotFlow { viewModel.tokenState }.collect { token ->
            token?.onSuccess {
                locationVM.loadLocations(it.token)
            }

        }

    }
}

