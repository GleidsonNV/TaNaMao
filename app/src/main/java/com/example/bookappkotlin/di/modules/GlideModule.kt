package com.example.bookappkotlin.di.modules

import com.example.bookappkotlin.screens.home.ui.adapters.ImageLoader
import org.koin.dsl.module

var glideModule = module{

    single {
        val imageLoader = ImageLoader()
        imageLoader
    }
}