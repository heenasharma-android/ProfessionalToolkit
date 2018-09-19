package health.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import health.app.Activities.PackageDetailActivity;
import health.app.R;
import health.app.Response.MySessionResponse;
import health.app.Utilities.Preferences;

/**
 * Created by Developer Six on 11/6/2017.
 */

public class SessionByDateAdapter extends RecyclerView.Adapter<SessionByDateAdapter.ViewHolder> {
    private List<MySessionResponse.SessionData.PackageList> sessionsList;
    private Context context;
    String imageUrl;
    String hours,minutes,hours1;
    public SessionByDateAdapter(Context context, List<MySessionResponse.SessionData.PackageList> sessionsList, String imageUrl) {
        this.sessionsList = sessionsList;
        this.context = context;
        this.imageUrl=imageUrl;
    }

    @Override
    public SessionByDateAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.session_date_list, viewGroup, false);
        return new SessionByDateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SessionByDateAdapter.ViewHolder viewHolder, final int position) {
        MySessionResponse.SessionData.PackageList sessions=sessionsList.get(position);
        if (Preferences.getInstance().getUserType().equalsIgnoreCase("Customer")){
            viewHolder.tvName.setText("Trainer Name:- "+sessions.getFirst_name()+" "+sessions.getLast_name());
        }
        else if (Preferences.getInstance().getUserType().equalsIgnoreCase("Trainer"))
        {
            viewHolder.tvName.setText("Client Name:- "+sessions.getFirst_name()+" "+sessions.getLast_name());
        }
        viewHolder.tvPhone.setText("Phone:- "+sessions.getPhone());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySessionResponse.SessionData.PackageList sessions1 = (MySessionResponse.SessionData.PackageList) sessionsList.get(position);
                Intent intent = new Intent(context,PackageDetailActivity.class);
                intent.putExtra(PackageDetailActivity.PACKAGE, sessions1);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        setLength(sessions.getSessionTime(),viewHolder);
        Picasso.with(viewHolder.profileImage.getContext()).load(imageUrl+sessions.getProfile_image()).into(viewHolder.profileImage);
        ArrayList<String> items = new ArrayList(Arrays.asList(sessions.getSessionDays().split("\\s*,\\s*")));
        for (int i=0;i<items.size();i++)
        {
            if (items.get(i).equalsIgnoreCase("Sunday"))
            {
                viewHolder.tvSun.setBackgroundResource(R.drawable.circle_orange);
            }
            else if (items.get(i).equalsIgnoreCase("Monday"))
            {
                viewHolder.tvMon.setBackgroundResource(R.drawable.circle_orange);
            }
            else if (items.get(i).equalsIgnoreCase("Tuesday"))
            {
                viewHolder.tvTus.setBackgroundResource(R.drawable.circle_orange);
            }
            else if (items.get(i).equalsIgnoreCase("Wednesday"))
            {
                viewHolder.tvWed.setBackgroundResource(R.drawable.circle_orange);
            }
            else if (items.get(i).equalsIgnoreCase("Thursday"))
            {
                viewHolder.tvThu.setBackgroundResource(R.drawable.circle_orange);
            }
            else if (items.get(i).equalsIgnoreCase("Friday"))
            {
                viewHolder.tvFri.setBackgroundResource(R.drawable.circle_orange);
            }
            else if (items.get(i).equalsIgnoreCase("Saturday"))
            {
                viewHolder.tvSat.setBackgroundResource(R.drawable.circle_orange);

            }
        }}

    private void setLength(String selectDuration,ViewHolder viewHolder) {
        String[] splited = selectDuration.split(":");
        hours=splited[0];
        minutes=splited[1];
        if (hours.startsWith("0")) {
            Log.d("startsWith","startsWith"+hours.substring(1));
            hours1= hours.substring(1);
            if (hours1.equalsIgnoreCase("0")) {
                viewHolder.tvPrice.setText("Session Length:- "+minutes + " mins");
            }
            else {
                if (minutes.equalsIgnoreCase("00")) {
                    viewHolder.tvPrice.setText("Session Length:- "+hours1 + " hr");
                }
                else {
                    viewHolder.tvPrice.setText("Session Length:- "+hours1 + " hr " + minutes + " mins");
                }
            }
        }
        else {
            if (minutes.equalsIgnoreCase("00")) {
                Log.d("startsWith1","startsWith1"+"startsWith1");
                viewHolder.tvPrice.setText("Session Length:- "+hours + " hr");
            }
            else {
                viewHolder.tvPrice.setText("Session Length:- "+hours + " hr " + minutes + " mins");
            }
        }
    }

    @Override
    public int getItemCount() {
        return sessionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout view1,view2;
        CircleImageView profileImage;
        private TextView tvName,tvPhone,tvPrice,tvSun,tvMon,tvTus,tvWed,tvThu,tvFri,tvSat;
        private TextView sessionName,sessionPrice,sessionLocation,sessionStatus,sessionStartTime,sessionDay,sessionMonth,sessionYear;
        public ViewHolder(View view) {
            super(view);
            view1=(LinearLayout)view.findViewById(R.id.view1);
            view2=(LinearLayout)view.findViewById(R.id.view2);
            sessionName = (TextView)view.findViewById(R.id.tv_session_name);
            sessionPrice = (TextView) view.findViewById(R.id.tv_session_price);
            sessionLocation=(TextView)view.findViewById(R.id.tv_session_location);
            sessionStatus = (TextView)view.findViewById(R.id.tv_session_status);
            sessionStartTime = (TextView)view.findViewById(R.id.tv_session_start_time);
            sessionDay = (TextView)view.findViewById(R.id.tv_day);
            sessionMonth = (TextView)view.findViewById(R.id.tv_month);
            sessionYear = (TextView)view.findViewById(R.id.tv_year);
            profileImage=(CircleImageView)view.findViewById(R.id.civ_profile);
            tvName=(TextView)view.findViewById(R.id.tv_name);
            tvPhone=(TextView)view.findViewById(R.id.tv_phone);
            tvPrice=(TextView)view.findViewById(R.id.tv_price);
            tvSun=(TextView)view.findViewById(R.id.tv_sun);
            tvMon=(TextView)view.findViewById(R.id.tv_mon);
            tvTus=(TextView)view.findViewById(R.id.tv_tus);
            tvWed=(TextView)view.findViewById(R.id.tv_wed);
            tvThu=(TextView)view.findViewById(R.id.tv_thu);
            tvFri=(TextView)view.findViewById(R.id.tv_fri);
            tvSat=(TextView)view.findViewById(R.id.tv_sat);


        }
    }

}