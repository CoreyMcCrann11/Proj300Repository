package edu.paraicmcdonagh.discoverypage;


import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Messagesboard extends AppCompatActivity {


    private static final int PERMISSION_REQUEST_CODE = 1;
    Button btnSend;
    EditText tvMessages;
    EditText tvNumber;
    IntentFilter intentFilter;
    TextView txtMsg;
    ListView listView;
    EditText Message;

    //TextView MessageDisplay;

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //display the message in the textview
            //TextView intTxt = (TextView) findViewById(R.id.txtMsg);
            //intTxt.setText(intent.getExtras().getString("messages"));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smspage);

        //intent to filter for sms message received
        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");

        Message = findViewById(R.id.tvMessage);
        btnSend=(Button) findViewById(R.id.btnSend);
        tvMessages=(EditText) findViewById(R.id.tvMessage);
        tvNumber=(EditText) findViewById(R.id.tvNumber);




        //MessageDisplay = findViewById(R.id.tvMessageDisplay);
        //txtMsg = findViewById(R.id.txtMsg);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        listView = (ListView) findViewById(R.id.listView);
        getJSON("http://192.168.42.119/messages/messages.php");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
            }
        }
    }

    public void getJSON(final String urlWebservice)
    {
        class GetJSON extends AsyncTask<Void, Void, String>{


            @Override
            protected void onPreExecute() {super.onPreExecute();}

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);

                try{
                    loadintoListView(s);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try{
                    URL url = new URL(urlWebservice);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine()) != null){
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }



        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadintoListView(String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);
        String[] messages = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            messages[i] = obj.getString("message");

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_1, messages );
        listView.setAdapter(arrayAdapter);
    }


    public void doSend(View view) {
        tvMessages.setText("");
        tvNumber.setText("");
        Toast.makeText(this,"Message sent", Toast.LENGTH_SHORT).show();

       //final String sender,  receiver;




                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[3];
                        field[0] = "Sendernumber";
                        field[1] = "message";
                        field[2] = "receivernumber";

                        final String sender = "0";
                        final String message = tvMessages.getText().toString();
                        final String receiver = "0";

                        String[] data = new String[3];
                        data[0] = sender;
                        data[1] = message;
                        data[2] = receiver;

                        PutData putData = new PutData("http://192.168.42.119/messages/sendmessages.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();

                                if (result.equals("Message Sent succesfully")) {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                }
                                Log.i("Putdata", result);
                            }
                        }


                    }
                });



    }
    public void doBack(View view) {
        Intent SMSLog = new Intent( this, SMSLog.class);
        startActivity(SMSLog);
    }
    @Override
    protected void onResume() {
        //register the receiver
        registerReceiver(intentReceiver, intentFilter);
        super.onResume();
    }
    @Override
    protected void onPause() {
        //register the receiver
        unregisterReceiver(intentReceiver);
        super.onPause();
    }
}
