package com.abhishekhzb.broadcastreceivers
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast

class ConnectivityReceiver : BroadcastReceiver() {

    /**
     * This method is called when the BroadcastReceiver is receiving an Intent broadcast.
     *
     * @param context
     * @param intent
     */
    override fun onReceive(context: Context, intent: Intent) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        // Handle the connectivity change as needed
        if (isConnected) {
            // Internet is connected
            Toast.makeText(context, "Internet connected", Toast.LENGTH_SHORT).show()
        } else {
            // Internet is disconnected
            Toast.makeText(context, "Internet disconnected", Toast.LENGTH_SHORT).show()
        }
    }
}
