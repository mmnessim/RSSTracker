package com.mnessim.researchtrackerkmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mnessim.researchtrackerkmp.domain.data.DBFactory
import com.mnessim.researchtrackerkmp.domain.data.createDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val database = createDatabase(DBFactory(context = applicationContext))

        setContent {
            App(database)
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//
//    App()
//}