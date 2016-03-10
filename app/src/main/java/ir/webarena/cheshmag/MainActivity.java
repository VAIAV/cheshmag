package ir.webarena.cheshmag;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView today_text = (TextView) findViewById(R.id.today_txt);
        Button news_btn = (Button) findViewById(R.id.news_btn);
        Button thesis_btn = (Button) findViewById(R.id.thesis_btn);
        Button crisis_btn = (Button) findViewById(R.id.crisis_btn);

        ConnectionDetector connectionDetector = new ConnectionDetector();

        if (connectionDetector.isInternetAvailable(this)) {
            Toast.makeText(MainActivity.this, "سایت قابل دسترسی است", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "سایت در دسترس نیست، لطفا اتصال به اینترنت را بررسی کنید", Toast.LENGTH_SHORT).show();
        }

        Date curDate = new Date(); // today
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,M,d"); // set date format
        String formattedDateString = dateFormat.format(curDate); // apply date format for today

        String[] dateAspects = formattedDateString.split(","); // split date (2016,1,30) and put it into an array

        int todayYear = Integer.parseInt(dateAspects[0]); //    convert year to int because the CalendarTool accepts int
        int todayMonth = Integer.parseInt(dateAspects[1]); //   convert month to int ......
        int todayDay = Integer.parseInt(dateAspects[2]); //     convert day to int ....

        CalendarTool calendarTool = new CalendarTool(todayYear, todayMonth, todayDay); // using CalendarTool class

        today_text.setText(calendarTool.getIranianDate()); // Put today date to textview (شنبه 10 بهمن 1394)

        news_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoList = new Intent(MainActivity.this, ListActivity.class);
                gotoList.putExtra("category", "news");
                startActivity(gotoList);
            }
        });

        thesis_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoList = new Intent(MainActivity.this, ListActivity.class);
                gotoList.putExtra("category", "thesis");
                startActivity(gotoList);
            }
        });

        crisis_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoList = new Intent(MainActivity.this, ListActivity.class);
                gotoList.putExtra("category", "crisis");
                startActivity(gotoList);
            }
        });


    }
}
