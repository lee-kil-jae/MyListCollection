package com.kiljae.mylistcollection.common

import com.kiljae.mylistcollection.viewmodel.MultiItemViewModel
import com.kiljae.mylistcollection.viewmodel.DefaultViewModel
import com.kiljae.mylistcollection.viewmodel.MultiPagingViewModel
import com.kiljae.mylistcollection.viewmodel.MultiRoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var viewModelPart = module {
    viewModel {
        DefaultViewModel()
    }

    viewModel {
        MultiItemViewModel()
    }

    viewModel {
        MultiPagingViewModel()
    }

    viewModel {
        MultiRoomViewModel()
    }
}

var myDiModule = listOf(
    viewModelPart
)