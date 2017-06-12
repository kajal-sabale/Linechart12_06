package studytutorial.in.linechart;

/**
 * Created by a622890 on 27-03-2017.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DataRecieving {


    private static final String TAG = "MY_APP_DEBUG_TAG";
    private Handler mHandler; // handler that gets info from Bluetooth service

    // Defines several constants used when transmitting messages between the
    // service and the UI.
    private interface MessageConstants {
        public static final int MESSAGE_READ = 0;
        public static final int MESSAGE_WRITE = 1;
        public static final int MESSAGE_TOAST = 2;

        // ... (Add other message types here as needed.)
    }
    public DataRecieving(BluetoothSocket bs,Handler handler) {
        // TODO Auto-generated constructor stub
        mHandler=handler;
        ConnectedThread ct=new ConnectedThread(bs);
        ct.start();
    }


    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private byte[] mmBuffer; // mmBuffer store for the stream

        /**
         *This class is used for reading data from sender side.
         */
        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams; using temp objects because
            // member streams are final.
            try {
                tmpIn = socket.getInputStream();
                Log.i(TAG, "input stream created successfully");
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating input stream", e);
            }
            try {
                tmpOut = socket.getOutputStream();
                Log.e(TAG, "got output stream");
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating output stream", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            mmBuffer = new byte[1024];
            int numBytes; // bytes returned from read()
            Log.e(TAG, "inside  run method of recieve data");
            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                try {
                    //reading input stream
                    //int a=10;
                    numBytes = mmInStream.read(mmBuffer);

                    System.out.println("value received in syso "+numBytes);
                    Log.e(TAG, " recieve data is"+numBytes);
                    Integer i=new Integer(numBytes);
                    i.toString();
                    Log.i(TAG,"value received is "+i.toString());
                    //  (byte)i;
                    // i.decode(string)



                    Message readMsg = mHandler.obtainMessage(
                            MessageConstants.MESSAGE_READ, numBytes, -1,
                            mmBuffer);

                    readMsg.sendToTarget();
                    byte[] readByte = (byte[]) readMsg.obj;
                    String myVal=new String(readByte, 0, readMsg.arg1);

                    // Log.i("TAG",readMsg.toString());
                    Log.e(TAG,myVal+" this is printed message");

                } catch (IOException e) {
                    Log.d(TAG, "Input stream was disconnected", e);
                    break;
                }
            }
        }


        /**
         * this method used to close the socket
         */

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close the connect socket", e);
            }
        }
    }

}
