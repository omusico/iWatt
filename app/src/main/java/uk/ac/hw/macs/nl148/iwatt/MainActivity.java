package uk.ac.hw.macs.nl148.iwatt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {


    DBHelper dbHelper;
    Button timetable_button;
    Button knowgo_button;
    Button settings_button;
    List<Student> students = new ArrayList<>();
    RuntimeExceptionDao<Student, Integer> studentDao = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * ATTEMPTING TO RETRIEVE USER NAME AND DISPLAY ON Button for settings
         */

       try {
           dbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
           settings_button = (Button) findViewById(R.id.welcome);


           studentDao = dbHelper.getStudentExceptionDao();

           String name = "test";
           List<Student> students = studentDao.queryForAll();


           name = students.get(0).getName();


           Log.d("ello", students.toString());

           //Toast.makeText(this,"list size" + student.size(),Toast.LENGTH_SHORT).show();
           //studentDao.delete(student);
           settings_button.setText("Hi " + name);
           Typeface name_tf = Typeface.createFromAsset(getAssets(),"Simple tfb.ttf");
           settings_button.setTypeface(name_tf);
           OpenHelperManager.releaseHelper();
       }catch (Exception e)
       {
           e.printStackTrace();
       }



        timetable_button = (Button) findViewById(R.id.timetable);
        timetable_button.setOnClickListener(this);
        knowgo_button = (Button) findViewById(R.id.knowgo);
        knowgo_button.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(knowgo_button == v)
        {
            Intent knowgo = new Intent(this , KnowGo.class);
            startActivity(knowgo);

        }
        if(timetable_button == v)
        {
            Intent timetableinit = new Intent(this, TimeTableInit.class);
            startActivity(timetableinit);
        }



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
