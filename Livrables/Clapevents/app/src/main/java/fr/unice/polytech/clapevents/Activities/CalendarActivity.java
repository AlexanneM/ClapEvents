package fr.unice.polytech.clapevents.Activities;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.GregorianCalendar;

import fr.unice.polytech.clapevents.R;

public class CalendarActivity extends AppCompatActivity {

    Intent intent;
    Intent calIntent;
    String newDate;
    int year;
    int month;
    int day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        intent = getIntent();

        newDate=intent.getStringExtra("date");


        ( (TextView) findViewById(R.id.date)).setText(newDate);
        ( (TextView) findViewById(R.id.place)).setText(getIntent().getStringExtra("place"));
        ( (TextView) findViewById(R.id.address)).setText(getIntent().getStringExtra("address"));

    }

    public void save(View view){
        calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");
        calIntent.putExtra(CalendarContract.Events.TITLE, intent.getStringExtra("title"));
        calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, intent.getStringExtra("address"));

        String[] items = newDate.split("/");
        year = Integer.valueOf(items[2]);
        month = Integer.valueOf(items[1]);
        day = Integer.valueOf(items[0]);

        Log.e("year", items[2]);

        GregorianCalendar calDate = new GregorianCalendar(year, month, day);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                calDate.getTimeInMillis());
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                calDate.getTimeInMillis());
        startActivity(calIntent);
    }


}
