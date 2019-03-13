package com.example.crispyhappiness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.vidyo.VidyoClient.Connector.Connector
import com.vidyo.VidyoClient.Connector.ConnectorPkg

class MainActivity : AppCompatActivity(), Connector.IConnect {
    private lateinit var vc: Connector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ConnectorPkg.setApplicationUIContext(this)
        ConnectorPkg.initialize()
    }

    override fun onSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(p0: Connector.ConnectorFailReason?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDisconnected(p0: Connector.ConnectorDisconnectReason?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
