package ir.webarena.cheshmag;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */

        Intent listIntent = getIntent();
        Bundle listIntentExtras = listIntent.getExtras();
        String newsIdValue = listIntentExtras.getString("newsIdValue");
        Toast.makeText(NewsDetailActivity.this, newsIdValue, Toast.LENGTH_SHORT).show();
    }

}
