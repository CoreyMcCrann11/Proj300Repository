package edu.paraicmcdonagh.discoverypage;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SMSPage extends MainActivity {

    TextView MessageDisplay;
    EditText user,message;
    Button sendsms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_page);

        user = findViewById(R.id.etUser);
        message = findViewById(R.id.etMessage);
        sendsms = findViewById(R.id.btnSend);
        MessageDisplay = findViewById(R.id.tvmessagedisplay);
        sendsms.setOnClickListener(new OnClickListener() {



            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

                        sendSMS();
                    }else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }

            }
        });


    }

    public void sendSMS(){
        String User = user.getText().toString().trim();
        String Message = message.getText().toString().trim();

        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(User,null,Message,null,null);
            Toast.makeText(this, "Message is Sent", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to send this Message", Toast.LENGTH_SHORT).show();
        }

    }

    public void doSend(View view) {
        MessageDisplay.setText("Your message is " + String.valueOf(message));

    }

    public void doDiscovery(View view) {
        Intent mainactivity = new Intent(this, MainActivity.class);
        startActivity(mainactivity);
    }
}
