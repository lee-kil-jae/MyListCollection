package com.kiljae.mylistcollection.common

import com.kiljae.mylistcollection.model.room.MyDatabase
import com.kiljae.mylistcollection.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var dbPart = module {
    factory { MyDatabase.getInstance(get()).myDao() }
}

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

    viewModel {
        MultiRoomPagingViewModel(get())
    }
}

var myDiModule = listOf(
        dbPart,
        viewModelPart
)