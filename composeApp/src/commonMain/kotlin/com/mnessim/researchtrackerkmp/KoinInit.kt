package com.mnessim.researchtrackerkmp

import com.mnessim.Database
import com.mnessim.researchtrackerkmp.domain.data.DBFactory
import org.koin.dsl.module

val databaseModule = module {
    single<Database> {
        val factory = get<DBFactory>()
        Database(factory.createDriver())
    }
}

//fun initKoin(vararg platformModules: Module) {
//    val modulesList = listOf<Module>(databaseModule) + platformModules
//    startKoin {
//        modules(modulesList)
//    }
//}