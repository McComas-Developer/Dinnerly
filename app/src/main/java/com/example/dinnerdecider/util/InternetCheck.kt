package com.example.dinnerdecider.util


import java.io.IOException

object Connectivity {

    /**
     * Checks whether device is currently online or not by Pinging a server
     * This uses Google's DNS (very unlikely to ever be down)
     * Preferable compared to connectivity/session manager as connected != online
     */
    var isOnline: Boolean = false
        private set
        get() {
            val runtime = Runtime.getRuntime()
            try {
                val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
                val exitValue = ipProcess.waitFor()
                return exitValue == 0
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return false
        }
}