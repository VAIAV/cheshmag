package ir.webarena.cheshmag;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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
    String[] newsArray;
    TextView textView;
    ListView listView;
    String[] newsAllInOneArray = new String[] {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dataUrl = "http://cheshmag.com/dev/?limit=20";

        listView = (ListView) findViewById(R.id.list_view);
        //textView = (TextView) findViewById(R.id.text_view_test);

        //textView.setText(dataUrl);

        Log.d("rooh_onCreate", "on create");

        /*
        newsArray = new String[] {"11111111", "222222", "333333", "44444444", "55555555", "66666666", "77777777"};

        CustomAdapter customAdapter = new CustomAdapter(newsArray, this);
        listView.setAdapter(customAdapter);
        */

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
            Log.d("rooh_responseCode", responseCode + "");
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

                Log.d("rooh_allDataArraylength", allDataArray.length() + "");


                final String[] newsIdArray = new String[allDataArray.length()];
                final String[] newsTitleArray = new String[allDataArray.length()];
                final String[] newsDateArray = new String[allDataArray.length()];


                for(int i = 0; i < allDataArray.length(); i++){
                    //newsAllInOneArray[i] = allDataArray.getString(i);
                    //JSONObject firstObject = new JSONObject(firstElement);
                    String nthElement = allDataArray.getString(i);
                    Log.d("rooh_inloop", i+"");
                    JSONObject nthObject = new JSONObject(nthElement);
                    newsIdArray[i] = nthObject.getString("id");
                    newsDateArray[i] = nthObject.getString("date");
                    newsTitleArray[i] = nthObject.getString("title");
                }


                /*
                Log.d("rooh_id_1", newsIdArray[1]);
                Log.d("rooh_date_1", newsDateArray[1]);
                Log.d("rooh_title_1", newsTitleArray[1]);

                Log.d("rooh_id_2", newsIdArray[2]);
                Log.d("rooh_date_2", newsDateArray[2]);
                Log.d("rooh_title_2", newsTitleArray[2]);
                */

                /*
                String firstElement = allDataArray.getString(0);

                Log.d("rooh_FirstElement", firstElement);

                JSONObject firstObject = new JSONObject(firstElement);

                String newsId = firstObject.getString("id");
                String newsDate = firstObject.getString("date");
                String newsTitle = firstObject.getString("title");

                String htmlFormatNews = "<font color=\"blue\">" + newsTitle + "</font> - <font color=\"gray\" size=\"10px\">"+newsDate+"</font>";

                */


                final CustomAdapter customAdapter = new CustomAdapter(newsIdArray, newsDateArray, newsTitleArray, this);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("rooh_runOnUiThread", "OK");
                        //textView.setText(newsTitle+" - "+newsDate);

                        //textView.setText(Html.fromHtml(htmlFormatNews));
                        //newsArray = new String[] {"11111111", "222222", "333333", "44444444", "55555555", "66666666", "77777777"};


                        listView.setAdapter(customAdapter);

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
