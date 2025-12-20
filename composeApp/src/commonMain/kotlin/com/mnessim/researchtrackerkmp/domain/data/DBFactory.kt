package com.mnessim.researchtrackerkmp.domain.data

import app.cash.sqldelight.db.SqlDriver
import com.mnessim.Database

expect class DBFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DBFactory): Database {
    val driver = driverFactory.createDriver()
    val database = Database(driver)
    return database
}