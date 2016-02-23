package uk.ac.hw.macs.nl148.iwatt;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

/**
 * Created by mrnel on 01/02/2016.
 */
public class GettingStartedFragFour extends Fragment {

    private Button finishGettingStarted;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_getting_started_four, container, false);
        finishGettingStarted = (Button) view.findViewById(R.id.finish_getting_started);
        Typeface finish_tf = Typeface.createFromAsset(getActivity().getAssets(),"Simple tfb.ttf");
        finishGettingStarted.setTypeface(finish_tf);


        finishGettingStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dbHelper = OpenHelperManager.getHelper(getActivity(), DBHelper.class);


                RuntimeExceptionDao<Student, Integer> studentDao = dbHelper.getStudentExceptionDao();

                String name = "test";
                List<Student> students = studentDao.queryForAll();


                OpenHelperManager.releaseHelper();

                if (students.isEmpty()) {
                    Toast.makeText(getActivity(), "Please complete All fields", Toast.LENGTH_SHORT).show();
                } else  if(!students.isEmpty()){
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                    getActivity().finish();
                }

            }
        });

        return view;
    }







}



