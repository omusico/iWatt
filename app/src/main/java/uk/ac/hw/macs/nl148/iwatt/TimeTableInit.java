package uk.ac.hw.macs.nl148.iwatt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class TimeTableInit extends AppCompatActivity implements View.OnClickListener{

    Button monday;
    Button tuesday;
    Button wednsday;
    Button thursday;
    Button friday;
    ArrayList<TimeTableData> tlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_init);



        monday = (Button) findViewById(R.id.monday);
        tuesday = (Button) findViewById(R.id.tuesday);
        wednsday = (Button) findViewById(R.id.wednesday);
        thursday = (Button) findViewById(R.id.thursday);
        friday = (Button) findViewById(R.id.friday);


        monday.setOnClickListener(this);
        tuesday.setOnClickListener(this);
        wednsday.setOnClickListener(this);
        thursday.setOnClickListener(this);
        friday.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(monday == v)
        {
            Intent timetable = new Intent(this , TimeTable.class);

            timetable.putExtra("timetabledata", tlist);
            timetable.putExtra("dow", "mon");
            startActivity(timetable);
        }

        if(tuesday == v)
        {
            Intent timetable = new Intent(this , TimeTable.class);
            timetable.putExtra("timetabledata", tlist);
            timetable.putExtra("dow", "tue");
            startActivity(timetable);
        }

        if(wednsday == v)
        {
            Intent timetable = new Intent(this , TimeTable.class);

            timetable.putExtra("timetabledata",tlist);

            timetable.putExtra("dow" , "wed");
            startActivity(timetable);
        }

        if(thursday == v)
        {
            Intent timetable = new Intent(this , TimeTable.class);
            timetable.putExtra("timetabledata",tlist);
            timetable.putExtra("dow" , "thurs");
            startActivity(timetable);

        }

        if(friday == v)
        {
            Intent timetable = new Intent(this , TimeTable.class);
            timetable.putExtra("timetabledata",tlist);
            timetable.putExtra("dow" ,"fri");
            startActivity(timetable);
        }

    }
}
