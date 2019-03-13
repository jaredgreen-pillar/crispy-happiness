package com.example.crispyhappiness

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vidyo.VidyoClient.Connector.Connector
import com.vidyo.VidyoClient.Connector.ConnectorPkg
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Connector.IConnect {
    private lateinit var vc: Connector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ConnectorPkg.setApplicationUIContext(this)
        ConnectorPkg.initialize()

        button.setOnClickListener {
            start()
        }

        button2.setOnClickListener {
            connect()
        }

        button3.setOnClickListener {
            disconnect()
        }
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
        logFail(reason.toString())
    }

    override fun onDisconnected(reason: Connector.ConnectorDisconnectReason?) {
        logFail(reason.toString())
    }

    fun logFail(reason: String?) {
        Log.d("VIDYOFAIL", reason ?: "UNDEFINED")
    }
}
