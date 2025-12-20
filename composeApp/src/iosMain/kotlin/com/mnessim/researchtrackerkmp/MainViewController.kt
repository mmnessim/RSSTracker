package com.mnessim.researchtrackerkmp

import androidx.compose.ui.window.ComposeUIViewController
import com.mnessim.researchtrackerkmp.domain.data.DBFactory
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun MainViewController() = ComposeUIViewController {
    val iosPlatformModule = module {
        single<DBFactory> { DBFactory() }
    }
    startKoin {
        modules(commonModules + iosPlatformModule)
    }
    App()
}