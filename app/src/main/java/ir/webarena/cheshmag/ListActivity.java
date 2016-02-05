package ir.webarena.cheshmag;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListActivity extends AppCompatActivity {

    String dataUrl;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dataUrl = "http://cheshmag.com/dev/?limit=1";

        textView = (TextView) findViewById(R.id.text_view_test);

        textView.setText(dataUrl);

        Log.d("rooh_onCreate", "on create");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("rooh_run_getData", "inside thread");
                getData(dataUrl);
            }
        }).start();




        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    public void getData(String dataUrl){
        Log.d("rooh_inside_getData", "ooooooo");
        try {
            URL targetUrl = new URL(dataUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) targetUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("user-agent", "Mozilla/5.0");
            int responseCode = httpURLConnection.getResponseCode();
            Log.d("rooh_responseCode", responseCode+"" );
            if (responseCode == HttpURLConnection.HTTP_OK){
                Log.d("rooh_connection", "OK");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                String receivedData = "";
                while ((receivedData = bufferedReader.readLine()) != null){
                    stringBuffer.append(receivedData);
                }
                bufferedReader.close();
                Log.d("rooh_response", stringBuffer + "");

                JSONArray allDataArray = new JSONArray(stringBuffer.toString());

                Log.d("rooh_allDataArray", allDataArray.length() + "");

                String firstElement = allDataArray.getString(0);

                Log.d("rooh_FirstElement", firstElement);

                JSONObject firstObject = new JSONObject(firstElement);

                String newsId = firstObject.getString("id");
                final String newsDate = firstObject.getString("date");
                final String newsTitle = firstObject.getString("title");




                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("rooh_runOnUiThread", "eutyrtiu");
                        textView.setText(newsTitle+" - "+newsDate);
                    }
                });
            } else {
                Log.d("rooh_connection", "Fail");
            }
        } catch (Exception e){
            Log.d("rooh_exception", e + "");
            Toast.makeText(ListActivity.this, e + "", Toast.LENGTH_SHORT).show();
        }


    }

}
