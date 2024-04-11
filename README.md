In Android, a broadcast is like a message that the system sends out when certain things happen, 
like :
**when you turn on your device**,
**get a text message**, 
**receive a phone call**, 
or **switch to airplane mode**. 

It's a way for different parts of your phone to know what's happening and react accordingly. 

For example, when your device connects to the internet, a broadcast is sent out so that apps can know and do things like update data or show notifications.

**Static Broadcast Receivers:** These types of Receivers are declared in the manifest file and works even if the app is closed.

**Dynamic Broadcast Receivers:** These types of receivers work only if the app is active or minimized.

**NOTE :** Since from **API Level 26**, most of the broadcast can only be caught by the **dynamic receiver**.

**Menifest.XML :**

        <receiver
            android:name=".ConnectivityReceiver"
            android:exported="true">
            <intent-filter>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
            </intent-filter>
        </receiver>
        
**ConnectivityReceiver :**

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

**MainActivity :**

   class MainActivity : AppCompatActivity() {
    private val connectivityReceiver = ConnectivityReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Register the broadcast receiver
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectivityReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        //Unregister the broadcast receiver
        unregisterReceiver(connectivityReceiver)
    }
   }
      

