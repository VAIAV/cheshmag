package ir.webarena.cheshmag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView textView = (TextView) findViewById(R.id.textview_txt);



        CalendarTool calendarTool = new CalendarTool(2016,1,29);

        //textView.setText(calendarTool.getIranianDate());
    }
}
