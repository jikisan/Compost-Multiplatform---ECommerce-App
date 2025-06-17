package org.jikisan.cmpecommerceapp.di

import org.jikisan.cmpecommerceapp.viewmodel.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { createHttpClient() }
    }