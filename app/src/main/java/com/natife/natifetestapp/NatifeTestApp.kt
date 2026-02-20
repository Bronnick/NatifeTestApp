package com.natife.natifetestapp

import android.app.Application
import com.natife.natifetestapp.data.AppContainer

class NatifeTestApp : Application() {
    val appContainer by lazy { AppContainer(this) }
}