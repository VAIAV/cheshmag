package ir.webarena.cheshmag;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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

        Intent listIntent = getIntent();
        Bundle listIntentExtras = listIntent.getExtras();
        String category = listIntentExtras.getString("category");

        Toast.makeText(ListActivity.this, category, Toast.LENGTH_SHORT).show();

        switch (category){
            case "news":
                dataUrl = "http://cheshmag.com/dev/news.php";
                break;
            case "thesis":
                dataUrl = "http://cheshmag.com/dev/thesis.php";
                break;
            case "crisis":
                dataUrl = "http://cheshmag.com/dev/crisis.php";
                break;
            default:
                dataUrl = "http://cheshmag.com/dev/news.php";
        }


        listView = (ListView) findViewById(R.id.list_view);


        Log.d("rooh_onCreate", "on create");


        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("rooh_run_getData", "inside thread");
                getData(dataUrl);
            }
        }).start();
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

                // TODO: save result in database, check if there's any new item

                JSONArray allDataArray = new JSONArray(stringBuffer.toString());

                Log.d("rooh_allDataArraylength", allDataArray.length() + "");


                final String[] newsIdArray = new String[allDataArray.length()];
                final String[] newsTitleArray = new String[allDataArray.length()];
                final String[] newsDateArray = new String[allDataArray.length()];

                for(int i = 0; i < allDataArray.length(); i++){
                    String nthElement = allDataArray.getString(i);
                    Log.d("rooh_inloop", i+"");
                    JSONObject nthObject = new JSONObject(nthElement);
                    newsIdArray[i] = nthObject.getString("id");
                    newsDateArray[i] = nthObject.getString("date");
                    newsTitleArray[i] = nthObject.getString("title");
                }

                // TODO: add setOnItemClickListener


                final CustomAdapter customAdapter = new CustomAdapter(newsIdArray, newsDateArray, newsTitleArray, this);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("rooh_runOnUiThread", "OK");

                        listView.setAdapter(customAdapter);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Object object = parent.getItemAtPosition(position);
                                String newsIdValue = ((TextView) view.findViewById(R.id.text_view_newsid)).getText().toString();
                                Toast.makeText(ListActivity.this, position + " " + newsIdValue , Toast.LENGTH_SHORT).show();
                                Intent goToDetail = new Intent(ListActivity.this, NewsDetailActivity.class);
                                goToDetail.putExtra("newsIdValue", newsIdValue);
                                startActivity(goToDetail);
                            }
                        });


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
