package com.example.crispyhappiness

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.vidyo.VidyoClient.Connector.Connector
import com.vidyo.VidyoClient.Connector.ConnectorPkg
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Connector.IConnect {
    private lateinit var vc: Connector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions()

        if(hasPersmissions()) {
            ConnectorPkg.setApplicationUIContext(this)
            ConnectorPkg.initialize()

            start.setOnClickListener {
                start() // Doesn't work if moved out of click event???
            }

            connect.setOnClickListener {
                connect()
            }

            disconnect.setOnClickListener {
                disconnect()
            }

        }
    }

    private fun hasPersmissions(): Boolean {
        return arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO).all {
            return ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO),
            1
        ) // Request code?
    }

    private fun start() {
        vc = Connector(
            videoFrame,
            Connector.ConnectorViewStyle.VIDYO_CONNECTORVIEWSTYLE_Default,
            15,
            "warning info@VidyoClient info@VidyoConnector",
            "",
            0
        )
        vc.showViewAt(videoFrame, 0, 0, videoFrame.width, videoFrame.height)
    }

    private fun connect() {
        val token = "INSERT TOKEN HERE"
        vc.connect("prod.vidyo.io", token, "DemoUser", "DemoRoom", this)
    }

    private fun disconnect() {
        vc.disconnect()
    }

    override fun onSuccess() {}

    override fun onFailure(reason: Connector.ConnectorFailReason?) {
        Log.d("VIDYOFAIL", reason?.toString() ?: "UNDEFINED")
    }

    override fun onDisconnected(reason: Connector.ConnectorDisconnectReason?) {
        Log.d("VIDYODISCONNECT", reason?.toString() ?: "UNDEFINED")
    }
}
