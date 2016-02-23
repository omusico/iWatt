package uk.ac.hw.macs.nl148.iwatt;

import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mrnel on 01/02/2016.
 */
public class GettingStartedFragThree extends Fragment {

    AutoCompleteTextView myAutoComplete;
    EditText year;
    DBHelper dbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_getting_started_three, container, false);
        myAutoComplete = (AutoCompleteTextView) view.findViewById(R.id.auto);
        year = (EditText) view.findViewById(R.id.length);


        ArrayList<String> list = getActivity().getIntent().getStringArrayListExtra("course");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.select_dialog_item, list);
        TextView tx = (TextView) view.findViewById(R.id.year_tv);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Simple tfb.ttf");
        tx.setTypeface(tf);


        myAutoComplete.setDropDownBackgroundResource(R.drawable.backg);
        myAutoComplete.setThreshold(1);
        myAutoComplete.setTypeface(tf);
        myAutoComplete.setAdapter(arrayAdapter);

        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        Log.i(SyncStateContract.Constants.ACCOUNT_NAME, "onFling has been called!");
                        final int SWIPE_MIN_DISTANCE = 200;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 210;
                        try {
                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                                Log.i(SyncStateContract.Constants.ACCOUNT_NAME, "Right to Left");

                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                Log.i(SyncStateContract.Constants.ACCOUNT_NAME, "Left to Right");
                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });


        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                processProgrammeData();

                return gesture.onTouchEvent(event);
            }
        });


        return view;


    }


    private void processProgrammeData()
    {

        Firebase.setAndroidContext(getActivity());

        Firebase programme = new Firebase("https://glowing-heat-7374.firebaseio.com/");

        programme.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(myAutoComplete.getText().toString().isEmpty() || year.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(),"Please fill in all course programme fields" , Toast.LENGTH_SHORT).show();
                }
                else {

                    Pattern p = Pattern.compile(".*[a-zA-Z].*");
                    Matcher m = p.matcher(year.getText().toString());

                    for (DataSnapshot progshot : dataSnapshot.getChildren()) {
                        OnlineProgramme prog = progshot.getValue(OnlineProgramme.class);

                        if (m.find()) {
                            Toast.makeText(getActivity(), "This field cannot contain letters", Toast.LENGTH_SHORT).show();
                        } else if (prog.getProgDesc().contentEquals(myAutoComplete.getText())) {

                            // Toast.makeText(getActivity(),"yay!" , Toast.LENGTH_SHORT).show();

                            dbHelper = OpenHelperManager.getHelper(getActivity(), DBHelper.class);
                            String proglength = prog.getLength();
                            int length = Integer.parseInt(proglength);
                            int study_year = Integer.parseInt(year.getText().toString());
                            RuntimeExceptionDao<LocalProgramme, Integer> programmeDao = dbHelper.getProgrammeExceptionDao();

                            if (study_year > length) {
                                Toast.makeText(getActivity(), "Year entered exceeds course length. please enter a year smaller than " + proglength, Toast.LENGTH_LONG).show();
                            } else {
                                programmeDao.create(new LocalProgramme(prog.getProgCode(), myAutoComplete.getText().toString(), length, study_year));

                                List<LocalProgramme> programmes = programmeDao.queryForAll();
                                Log.d("this programe", programmes.toString());
                                System.out.println("This other programme" + programmes.toString());
                                //programmeDao.delete(programmes);
                                OpenHelperManager.releaseHelper();
                                System.out.println(prog.getProgDesc() + " +++ " + myAutoComplete.getText());
                            }
                        }


                    }
                }



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });








    }


}



