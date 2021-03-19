package edu.paraicmcdonagh.discoverypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class login extends AppCompatActivity {
    TextInputEditText UserName, Password, PhoneNumber;
    Button logIn;
    static login INSTANCE;
    String loggedIn = "No";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserName = findViewById(R.id.UserName);
        Password = findViewById(R.id.Password);

        logIn = findViewById(R.id.btnLogin);
        logIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String userName, userpassword;


                userName = String.valueOf(UserName.getText());
                userpassword = String.valueOf(Password.getText());




                if (!userName.equals("") && !userpassword.equals(""))
                {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String field[] = new String[2];

                            field[0] = "UserName";
                            field[1] = "Password";

                            String[] data = new String[2];

                            data[0] = userName;
                            data[1] = userpassword;

                            PutData putData = new PutData("http://192.168.42.119/userdetails/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()){

                                    String result = putData.getResult();
                                    if(result.equals("Log in Success")){
                                        loggedIn = "Yes";
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(), profilePage.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                    Log.i("PutData", result);
                                }
                            }
                        }

                    });



                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }

                Intent passloginInfo = new Intent(getApplicationContext(), profilePage.class);
                passloginInfo.putExtra("UserName", userName);




                startActivity(passloginInfo);
            }
        });
    }

    public static login getActivityInstance()
    {
        return INSTANCE;
    }

    public String getLogin()
    {
        return this.loggedIn;
    }
}