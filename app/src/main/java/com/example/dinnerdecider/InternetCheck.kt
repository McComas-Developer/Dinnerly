package com.example.dinnerdecider

import android.os.AsyncTask
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket


internal class InternetCheck(private val mConsumer: Consumer) : AsyncTask<Void?, Void?, Boolean>() {

    interface Consumer {
        fun accept(internet: Boolean?)
    }

    override fun doInBackground(vararg p0: Void?): Boolean? {
        return try {
            val sock = Socket()
            sock.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
    }

    override fun onPostExecute(internet: Boolean) {
        mConsumer.accept(internet)
    }

    init {
        execute()
    }
}