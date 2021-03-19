package edu.paraicmcdonagh.discoverypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BMIMain extends AppCompatActivity {

    EditText weight = null;
    EditText height = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmimain);

        int maxweight = 300;
        int maxheight = 200;
        int minweight = 20;
        int minheight = 50;
        String message = "This is an unacceptable ";

        if( minheight < 50 || maxheight > 200) {
            message += "height";
            doRestart();
        }
        else if (minweight < 20 || maxweight > 300) {
            message +="weight";
            doRestart();
        }

       // Toast.makeText(BMIMain.this, message, Toast.LENGTH_SHORT).show();

        // link local variables to XML
        weight=findViewById(R.id.etWeight);
        height=findViewById(R.id.etHeight);
    }



    private void doRestart() {
        weight.setText("0");
        height.setText("0");

    }

    public void doReset(View view) {
        // resetting code
        weight.setText("0");
        height.setText("0");
    }

    public void doCalculate(View view) {
        // switch activity
        Intent BmiActivity = new Intent(view.getContext(), BmiActivity.class);

        //Conversion
        String kgStr = weight.getText().toString();
       int kg = Integer.valueOf(kgStr);
       String cmStr = height.getText().toString();
        int cm = Integer.valueOf(cmStr);

        // added after page switch
        BmiActivity.putExtra("userskg",kg);
        BmiActivity.putExtra("userscm",cm);

        startActivity(BmiActivity);

    }
    public void doSMSLog(View view) {
        Intent SMSLog = new Intent(view.getContext(), Messagesboard.class);
        startActivity(SMSLog);
    }
    public void doTimerPage(View view) {
        Intent StepTimer = new Intent( this, StepTimer.class);
        startActivity(StepTimer);
    }
    public void doDiscovery(View view) {
        Intent MainActivity = new Intent(this, MainActivity.class);
        startActivity(MainActivity);
    }
    public void doProfilePage(View view) {
        Intent MainActivity = new Intent(this, signinorlogin.class);
        startActivity(MainActivity);
    }
}