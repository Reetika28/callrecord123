package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.talentcodeworks.callrecorder.ComplaintActivity;
import com.talentcodeworks.callrecorder.R;
import com.talentcodeworks.callrecorder.RatingBarActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import models.ComplaintModel;
import models.DescriptionModels;

/**
 * Created by ahextech on 25/7/17.
 */

public class ComplaintAdapter extends BaseAdapter {

    ArrayList<ComplaintModel> list;
    Context context;
    private static LayoutInflater inflater=null;
    public ComplaintAdapter(Activity mainActivity, ArrayList<ComplaintModel> list) {
        // TODO Auto-generated constructor stub
        this.list=list;
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        Log.i("size",""+list.size());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.indexOf(getItem(position));
    }

    public class Holder
    {
        TextView text;
        TextView name;
        TextView time;
        Button moreInfo;
        TextView date;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ComplaintAdapter.Holder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.complaint_item, null);
            holder=new ComplaintAdapter.Holder();
         /* holder.date=(TextView)convertView.findViewById(R.id.textdate);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE d, MMM");
            String date = sdf.format(new Date());
            holder.date.setText(date);*/

           holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.text.setText("complaint "+(position+1));

            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.moreInfo = (Button)convertView.findViewById(R.id.moreinfo);




            convertView.setTag(holder);

        }else {
            holder = (ComplaintAdapter.Holder) convertView.getTag();
        }

        holder.time.setText(list.get(position).getTime());
        holder.name.setText(list.get(position).getName());
        holder.moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Log.i("key",""+list.get(position).getKey());

                Intent intent = new Intent(context, ComplaintActivity.class);
                intent.putExtra("key",list.get(position).getKey());
                context.startActivity(intent);

            }


        });

        return convertView;


    }
}
