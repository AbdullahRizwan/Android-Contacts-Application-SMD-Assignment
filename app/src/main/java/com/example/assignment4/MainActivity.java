package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView t1,t2;
    int i=0;
    Button b1;
    TimePicker time1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.alarm1);
        t2=findViewById(R.id.alarm2);

        b1=findViewById(R.id.submit);
        time1=findViewById(R.id.timepicker);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c= Calendar.getInstance();

                c.set(
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH),
                    time1.getHour(),
                    time1.getMinute(),
                        0
                );

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                if(t1.getText().equals("Alarm1"))
                    t1.setText( sdf.format(c.getTimeInMillis()));

                else if(t2.getText().equals("Alarm2"))
                    t2.setText( sdf.format(c.getTimeInMillis()));

                setAlarm(c.getTimeInMillis(),++i);

            }
        });

    }

    private void setAlarm(long timeInMillis , int i){
        if(i==1)
        {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent in= new Intent(this,MyAlarm.class);
            PendingIntent p= PendingIntent.getBroadcast(this,i,in,0);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timeInMillis,AlarmManager.INTERVAL_DAY,p);
            Toast.makeText(this, "Alarm 1 Has Been Set", Toast.LENGTH_SHORT).show();
        }
        else if(i==2){
            AlarmManager alarmManager1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent in1= new Intent(this,MyAlarm.class);
            PendingIntent p1= PendingIntent.getBroadcast(this,i,in1,0);
            alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP,timeInMillis,AlarmManager.INTERVAL_DAY,p1);
            Toast.makeText(this, "Alarm 2 Has Been Set", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"Cannot add more than 2 alarms",Toast.LENGTH_SHORT).show();
    }
}
