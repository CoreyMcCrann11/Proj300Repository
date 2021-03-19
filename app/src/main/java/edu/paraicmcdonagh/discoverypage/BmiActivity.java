package edu.paraicmcdonagh.discoverypage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BmiActivity extends AppCompatActivity{

    TextView resultDisplay = null;
    EditText weight, height = null;
    double bmi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);


        resultDisplay = (TextView) findViewById(R.id.tvDisplay);

        // read data from pipe
        int kg = getIntent().getIntExtra("userskg", 0);
        int cm = getIntent().getIntExtra("userscm", 0);

        bmi=kg/Math.pow((cm/100.0),2);

        int b = (int) bmi;

        int hs = cm * cm ;
        int hsq = hs / 100;
       // int ans = kg / hsq;
        resultDisplay.setText("Your bmi is " + String.valueOf(b));
    }

    public void doFinish(View view) {
       //onBackPressed();
        finish();
        //back to first page
        // resetting code
         //weight.setText("0");
         //height.setText("0");
    }

}


