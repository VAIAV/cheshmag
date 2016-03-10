package ir.webarena.cheshmag;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.net.InetAddress;

/**
 * Created by USER on 3/10/2016.
 */
public class ConnectionDetector {

    Context context;
    Boolean isConnected;

    public boolean isInternetAvailable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        return isConnected;
    }
}
