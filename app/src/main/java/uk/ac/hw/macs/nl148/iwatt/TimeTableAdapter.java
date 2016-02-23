package uk.ac.hw.macs.nl148.iwatt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mrnel on 19/02/2016.
 */
public class TimeTableAdapter extends ArrayAdapter<TimeTableData> {


    public TimeTableAdapter(Context context, ArrayList<TimeTableData> tableData) {
        super(context, R.layout.rowlayout, tableData);
    }

    private static class ViewHolder{
        TextView coursename;
        TextView type;
        TextView room1;
        TextView room2;
        TextView starttime;
        TextView endtime;
        TextView semester;
    }

    public View getView(int position , View convertView , ViewGroup parent)
    {
        TimeTableData data = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.rowlayout,parent,false);
            viewHolder.coursename = (TextView) convertView.findViewById(R.id.coursetitle);
            viewHolder.type = (TextView) convertView.findViewById(R.id.coursetype);
            viewHolder.room1 = (TextView) convertView.findViewById(R.id.room1);
            viewHolder.room2 = (TextView) convertView.findViewById(R.id.room2);
            viewHolder.starttime = (TextView) convertView.findViewById(R.id.start_time);
            viewHolder.endtime = (TextView) convertView.findViewById(R.id.endtime);
            viewHolder.semester = (TextView) convertView.findViewById(R.id.semester);
            convertView.setTag(viewHolder);


        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.coursename.setText(data.getTitle());
        viewHolder.type.setText(data.getType());
        viewHolder.room1.setText(data.getRoom1());
        viewHolder.room2.setText(data.getRoom2());
        viewHolder.starttime.setText(data.getStarttime());
        viewHolder.endtime.setText(data.getEndtime());
        viewHolder.semester.setText(data.getSemester());

        return convertView;
    }
}
