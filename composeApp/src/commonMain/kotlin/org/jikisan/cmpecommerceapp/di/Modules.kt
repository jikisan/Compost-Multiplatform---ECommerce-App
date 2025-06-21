package org.jikisan.cmpecommerceapp.di

import org.jikisan.cmpecommerceapp.viewmodel.ProductApi
import org.jikisan.cmpecommerceapp.viewmodel.home.HomeViewModel
import org.jikisan.cmpecommerceapp.viewmodel.cart.CartRepository
import org.jikisan.cmpecommerceapp.viewmodel.cart.CartViewModel
import org.jikisan.cmpecommerceapp.viewmodel.productdetails.ProductDetailRepository
import org.jikisan.cmpecommerceapp.viewmodel.productdetails.ProductDetailViewModel
import org.jikisan.cmpecommerceapp.viewmodel.home.HomeRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single{ ProductApi() }

    singleOf(::HomeRepository).bind<HomeRepository>()
    viewModelOf(::HomeViewModel)

    singleOf(::ProductDetailRepository).bind<ProductDetailRepository>()
    viewModelOf(::ProductDetailViewModel)

    singleOf(::CartRepository).bind<CartRepository>()
    viewModelOf(::CartViewModel)
}
