package uk.ac.hw.macs.nl148.iwatt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrnel on 31/01/2016.
 */
public class Splash extends Activity {

  //  private ArrayList<String> list = new ArrayList<String>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Firebase.setAndroidContext(this);


        final ImageView imgv = (ImageView) findViewById(R.id.imageView);
        final Animation ani = AnimationUtils.loadAnimation(getBaseContext(),R.anim.still);
        //final Animation ani = AnimationUtils.loadAnimation(getBaseContext(),R.anim.);

        imgv.startAnimation(ani);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Firebase programme = new Firebase("https://glowing-heat-7374.firebaseio.com/");




                programme.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        ArrayList<String> listCourse = new ArrayList<String>();
                        ArrayList<String> listLength = new ArrayList<String>();
                        //Toast.makeText(getActivity().getApplicationContext(), snapshot.getChildren().toString(), Toast.LENGTH_SHORT).show();
                        for (DataSnapshot progshot : snapshot.getChildren()) {
                            OnlineProgramme prog = progshot.getValue(OnlineProgramme.class);
                            listCourse.add(prog.getProgDesc());
                            listLength.add(prog.getLength());


                            System.out.println(prog.getFullTitle());

                        }
                        //System.out.println("list size is " + list.size());


                        DBHelper dbHelper = OpenHelperManager.getHelper(getApplicationContext(), DBHelper.class);
                        RuntimeExceptionDao<Student, Integer> studentDao = dbHelper.getStudentExceptionDao();
                        List<Student> student = studentDao.queryForAll();
                        //studentDao.delete(student);
                        OpenHelperManager.releaseHelper();

                        /**
                         * Checks to see if local database is empty , if empty
                         */
                        if(student.isEmpty()) {

                            Intent gettingStarted = new Intent(getBaseContext(), GettingStarted.class);
                            gettingStarted.putStringArrayListExtra("course", listCourse);
                            gettingStarted.putStringArrayListExtra("length", listCourse);

                            finish();
                            startActivity(gettingStarted);
                        }
                        else if(!student.isEmpty())
                        {

                            Intent main = new Intent(getBaseContext(), MainActivity.class);
                            finish();
                            startActivity(main);
                        }




                    }

                    @Override
                    public void onCancelled(FirebaseError error) {
                    }
                });


                //

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }



}
