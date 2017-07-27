package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.talentcodeworks.callrecorder.DescriptionActivity;
import com.talentcodeworks.callrecorder.R;
import com.talentcodeworks.callrecorder.RatingBarActivity;

import java.util.ArrayList;

import models.DescriptionModels;

/**
 * Created by ahextech on 17/7/17.
 */


public class CustomAdapter extends BaseAdapter{
    ArrayList<DescriptionModels> list;
    Context context;
    private static LayoutInflater inflater=null;
    public CustomAdapter(Activity mainActivity, ArrayList<DescriptionModels> list) {
        // TODO Auto-generated constructor stub
        this.list=list;
        context=mainActivity;
       inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        Log.i("size",""+list.size());
        return list.size();

    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return list.indexOf(getItem(position));
    }

    public class Holder
    {
        TextView text;
        TextView text1;
        TextView text2;
        TextView text3;
        TextView text4;
        TextView text5;
       // TextView text6;
        Button submit;
        RatingBar ratingBar;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder;
       // View rowView;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.row_item, null);
            holder=new Holder();
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.text1 = (TextView) convertView.findViewById(R.id.text1);
            holder.text2 = (TextView) convertView.findViewById(R.id.text2);
            holder.text3 = (TextView) convertView.findViewById(R.id.text3);
            holder.text4 = (TextView) convertView.findViewById(R.id.text4);
            holder.text5 = (TextView) convertView.findViewById(R.id.text5);
            //holder.text6 = (TextView) convertView.findViewById(R.id.text6);
            holder.submit = (Button) convertView.findViewById(R.id.submit1);
            holder.ratingBar = (RatingBar)convertView.findViewById(R.id.ratingBar);

            convertView.setTag(holder);

        }else {
            holder = (Holder) convertView.getTag();
        }
        holder.text.setText(list.get(position).getFirnumber());
        holder.text1.setText(list.get(position).getComplaintName());
        holder.text2.setText(list.get(position).getPhoneNumber());
        holder.text3.setText(list.get(position).getDate());
        holder.text4.setText(list.get(position).getTime());
        holder.text5.setText(list.get(position).getDescription());
        //holder.text6.setText(list.get(position).getReviews());

        if (list.get(position).getReviews().equals("0") || list.get(position).getReviews().equals("0.0")){
            holder.submit.setVisibility(View.VISIBLE);
            holder.ratingBar.setVisibility(View.GONE);
        }else {
            holder.submit.setVisibility(View.GONE);
            holder.ratingBar.setVisibility(View.VISIBLE);

            holder.ratingBar.setRating(Float.valueOf(list.get(position).getReviews()));
        }
       // holder.submit.setText(list.get(position).getReviews());

              final   boolean clickable = true;

        final String rating = holder.submit.getText().toString();
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("key",""+list.get(position).getKey());

                    Intent intent = new Intent(context, RatingBarActivity.class);
                    intent.putExtra("key",list.get(position).getKey());
                    context.startActivity(intent);

            }


        });


        Log.i("position",""+position);

        return convertView;

    }
/*public class CustomAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<DescriptionModels> data;
    private static LayoutInflater inflater=null;
    public Resources res;
    DescriptionModels tempValues;
    int i=0;

    public CustomAdapter(Activity a, ArrayList<DescriptionModels> d) {


        activity = a;
        data=d;
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{

        public TextView text;
        public TextView text1;
        public TextView text2;
        public TextView text3;
        public TextView text4;
        public TextView text5;


    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){


            vi = inflater.inflate(R.layout.row_item, parent,false);

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.text);
            holder.text1=(TextView)vi.findViewById(R.id.text1);
            holder.text2=(TextView)vi.findViewById(R.id.text2);
            holder.text3=(TextView)vi.findViewById(R.id.text3);
            holder.text4=(TextView)vi.findViewById(R.id.text4);
            holder.text5=(TextView)vi.findViewById(R.id.text5);


            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
            holder.text.setText("No Data");

        }
        else
        {

            tempValues=null;
            tempValues = ( DescriptionModels ) data.get( position );


            holder.text.setText( tempValues.getFirnumber() );
            holder.text1.setText( tempValues.getComplaintName() );
            holder.text2.setText( tempValues.getPhoneNumber() );
            holder.text3.setText( tempValues.getDate() );
            holder.text4.setText( tempValues.getTime() );
            holder.text5.setText( tempValues.getDescription() );

        }
        return vi;
    }*/


}
