package uk.ac.hw.macs.nl148.iwatt;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Firebase.setAndroidContext(this);
        Firebase timetable = new Firebase("https://timetablehw.firebaseio.com/");





        timetable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ListView  listView = (ListView) findViewById(R.id.tableview);
                ViewGroup headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.header, listView, false);
                listView.addHeaderView(headerView);
                ArrayList<TimeTableData> data = new ArrayList<TimeTableData>();
                String dow = getIntent().getStringExtra("dow");
                for (DataSnapshot progshot : dataSnapshot.getChildren()) {
                    TimeTableData timetable = progshot.getValue(TimeTableData.class);

                    System.out.print("THIS IS AN ENTRY" + timetable.toString());
                    if (timetable.getDow().contentEquals(dow)) {
                        data.add(timetable);
                    }
                }

                TimeTableAdapter timeTableAdapter = new TimeTableAdapter(getApplicationContext(), data);
                listView.setAdapter(timeTableAdapter);
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                        TimeTableData obj = (TimeTableData) listView.getAdapter().getItem(position);
                        String room1 = obj.getRoom1();
                        String room2 = obj.getRoom2();

                        Intent classMap = new Intent(getBaseContext() , ClassMap.class);
                        classMap.putExtra("room1", room1);
                        classMap.putExtra("room2",room2);

                        startActivity(classMap);

                        return false;
                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }
}
