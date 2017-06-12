package studytutorial.in.linechart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.util.UUID;




import android.support.v7.app.ActionBarActivity;
import android.app.SearchManager.OnCancelListener;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * This class is used from bluetooth receiver side and it waits .
 * When we click on button it goes in listening  state and acts like a server.
 * When we get connection request from sender then conneciton will get established.
 *
 */
public class MainActivity extends ActionBarActivity  implements OnClickListener{
    /**
     * Create object of button for listening.
     */
    Button b;
  /*  EditText et;*/

    private static final String TAG = "MyActivity";
    /**
     * The BluetoothAdapter perform fundamental Bluetooth tasks, such as initiate device discovery,
     * query a list of bonded (paired) devices, instantiate a BluetoothDevice using a known MAC address,
     * and create a BluetoothServerSocket to listen for connection requests from other devices, and
     * start a scan for Bluetooth LE devices.
     * */
    private BluetoothAdapter BA;

    private final String NAME="BluetoothChat";

    public static final int MESSAGE_READ = 0;
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;


    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b=(Button) findViewById(R.id.button1);
        BA = BluetoothAdapter.getDefaultAdapter();
        b.setOnClickListener(this);
    }
    @Override
    public void onClick(View V) {
        AcceptThread at=new AcceptThread();
        at.start();

    }

    void intentChnge(){
        Intent i=new Intent(this,ReciveActivity.class);
        startActivity(i);
    }


    /**
     * This class is used for accepting the requested socket.
     */
    private class AcceptThread extends Thread {

        private final BluetoothServerSocket mmServerSocket;
        final UUID MY_UUID = UUID
                .fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");

        /**
         * Using NAME and My_UUID we created the bluetooth server socket.
         */

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket
            // because mmServerSocket is final.
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code.
                tmp = BA.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
                /*Log.i(TAG, "ListenUsingRfid called successfully");*/
            } catch (IOException e) {
                /*Log.e(TAG, "Socket's listen() method failed", e);*/
            }
            mmServerSocket = tmp;
        }

        public void run() {

            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned.
            Log.e(TAG, "Inside Run method");
            while (true) {
                try {
                    Log.i(TAG, "before accept() method");
                    socket = mmServerSocket.accept();
                    Log.i(TAG, "Socket's accepted successfully");
                    //   MainActivity.this.intentChnge();

                } catch (IOException e) {
                    Log.e(TAG, "Socket's accept() method failed", e);
                    break;
                }

                if (socket != null) {

                    Intent i = new Intent(MainActivity.this, ReciveActivity.class);
                    StaticVariables.setSocket(socket);
                    startActivity(i);

                    break;
                }
            }
        }

        /**
         * Closes the connect socket and causes the thread to finish.
          */

        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close the connect socket", e);
            }
        }
    }
}
