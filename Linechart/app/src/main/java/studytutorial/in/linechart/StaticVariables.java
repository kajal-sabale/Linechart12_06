package studytutorial.in.linechart;

/**
 * Created by a622890 on 27-03-2017.
 */

import android.bluetooth.BluetoothSocket;

public class StaticVariables {
    static BluetoothSocket socket;

    public static BluetoothSocket getSocket() {
        return socket;
    }

    public static void setSocket(BluetoothSocket socketnew) {
        socket = socketnew;
    }

}