package com.example.abhi.jsshndemo.activities;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.service.NotificationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventDetailsActivity extends AppCompatActivity {

  private TextView eventName;
  private TextView eventDesc;
  private TextView firstPrize;
  private TextView secPrize;
  private TextView contact;
  private ImageButton callButton;
  private SwitchCompat interestedSwitch,goingSwitch;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_details);

    eventName = findViewById(R.id.text_event_details_name);
    eventDesc = findViewById(R.id.text_event_details_description);
    firstPrize = findViewById(R.id.text_event_details_firstprize);
    secPrize = findViewById(R.id.text_event_details_secondprize);
    contact = findViewById(R.id.text_event_details_contact);
    callButton = findViewById(R.id.callbutton_eventdetails);
    interestedSwitch=findViewById(R.id.switchcompat_interested);
    goingSwitch=findViewById(R.id.switchcompat_going);

    //*************Dummy Data***************//
    eventName.setText("Technovision");
    eventDesc.setText("Rules \n \n #1: dkjasdkjdkadkadkj \n #2 hfjdshgjhgfhghjfgdsjhf \n jkhfkjdhfkjshfkjdshkdsjhfkjsdhfjhds \n kjhdjfdjshf \n sds");
    firstPrize.setText("2000");
    secPrize.setText("1500");
    contact.setText("abhi-- 99####9248 \n hdgfh -- 99####9393");

    callButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "99990649248"));
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {
          // TODO: Consider calling
          //    ActivityCompat#requestPermissions
          // here to request the missing permissions, and then overriding
          //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
          //                                          int[] grantResults)
          // to handle the case where the user grants the permission. See the documentation
          // for ActivityCompat#requestPermissions for more details.
          return;
        }
        startActivity(intent);

      }
    });

    goingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
          notifyme();
          Toast.makeText(getBaseContext(),"We will notify you..",Toast.LENGTH_SHORT).show();
        }
        else {
          canclenotifyme();
        }
      }
    });
    //Getting values from shared prefrences

  }

  private void canclenotifyme() {
    Intent intent = new Intent(this, NotificationService.AlarmReceiver.class);
    int id=getSharedPreferences("notify",0).getInt("eventname",1);
    PendingIntent sender = PendingIntent.getBroadcast(this,id , intent, PendingIntent.FLAG_ONE_SHOT);
    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    alarmManager.cancel(sender);
  }

  private void notifyme() {
    String toParse = ""; // Results in "2-5-2012 20:43"
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // I assume d-M, you may refer to M-d for month-day instead.
    Date date = null; // You will need try/catch around this
    try {
      date = formatter.parse(toParse);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    SharedPreferences sf=getSharedPreferences("notify",0);
    int notificationid=sf.getInt("key",1);
    sf.edit().putInt("eventname",notificationid).commit();
    long millis = date.getTime();
    Intent intent=new Intent(getApplicationContext(), NotificationService.AlarmReceiver.class);
    intent.putExtra("keynotify", notificationid);
    intent.putExtra("eventname","eventname");
    Log.v("keynotify1", notificationid + "");


    PendingIntent pi=PendingIntent.getBroadcast(getApplicationContext(),notificationid,intent,PendingIntent.FLAG_ONE_SHOT);
    sf.edit().putInt("key",notificationid+1).commit();
    AlarmManager am=(AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
    am.set(AlarmManager.RTC_WAKEUP,millis-900000,pi);
  }

  private void settextstyle(TextView tv1) {
    Typeface custom_font = Typeface.createFromAsset(getApplicationContext().getAssets(), "font/huggable.ttf");
    tv1.setTypeface(custom_font);
  }
}
