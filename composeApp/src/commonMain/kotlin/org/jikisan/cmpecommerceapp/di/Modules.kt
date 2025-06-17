package org.jikisan.cmpecommerceapp.di

import org.jikisan.cmpecommerceapp.view.screens.homescreen.HomeViewModel
import org.jikisan.cmpecommerceapp.viewmodel.ProductApi
import org.jikisan.cmpecommerceapp.viewmodel.ProductDetailRepository
import org.jikisan.cmpecommerceapp.viewmodel.ProductDetailViewModel
import org.jikisan.cmpecommerceapp.viewmodel.ProductRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single{ ProductApi() }

    singleOf(::ProductRepository).bind<ProductRepository>()
    viewModelOf(::HomeViewModel)

    singleOf(::ProductDetailRepository).bind<ProductDetailRepository>()
    viewModelOf(::ProductDetailViewModel)
}
