package edu.paraicmcdonagh.discoverypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InlineSuggestionsRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignIn extends AppCompatActivity {

    TextInputEditText Username, Password, Email, PreferredMartialArt, Addone, Addtwo, Addthree, PhoneNo, FirstName, LastName;
    Button signUp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        Email = findViewById(R.id.email);
        FirstName = findViewById(R.id.firstname);
        LastName = findViewById(R.id.lastname);
        PreferredMartialArt = findViewById(R.id.preferedmartialArt);
        Addone = findViewById(R.id.addone);
        Addtwo = findViewById(R.id.addtwo);
        Addthree = findViewById(R.id.addthree);
        PhoneNo = findViewById(R.id.phonenumber);
        progressBar = findViewById(R.id.progress);
        signUp = findViewById(R.id.BtnSignUp);

        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 final String username, password, email, preferredmartialart, addone, addtwo, addthree, firstname, lastname, phoneno;

                 username = String.valueOf(Username.getText());
                 password = String.valueOf(Password.getText());
                 email = String.valueOf(Email.getText());
                 preferredmartialart = String.valueOf(PreferredMartialArt.getText());
                 addone = String.valueOf(Addone.getText());
                 addtwo = String.valueOf(Addtwo.getText());
                 addthree = String.valueOf(Addthree.getText());
                 firstname = String.valueOf(FirstName.getText());
                 lastname = String.valueOf(LastName.getText());
                 phoneno = String.valueOf(PhoneNo.getText());


                if(!username.equals("") && !password.equals("") && !email.equals("")  && !firstname.equals("") && !lastname.equals("")
                        && !preferredmartialart.equals("") && !addone.equals("") && !addtwo.equals("") && !addthree.equals("") && !phoneno.equals(""))
                {
                    progressBar.setVisibility(View.VISIBLE);

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[10];

                                    field[0] = "UserName";
                                    field[1] = "EmailAdd";
                                    field[2] = "Password";
                                    field[3] = "PreferedMartialArt";
                                    field[4] = "FirstName";
                                    field[5] = "LastName";
                                    field[6] = "AddressOne";
                                    field[7] = "AddressTwo";
                                    field[8] = "AddressThree";
                                    field[9] = "PhoneNumber";

                            String[] data = new String[10];
                            data[0] = username;
                            data[1] = email;
                            data[2] = password;
                            data[3] = preferredmartialart;
                            data[4] = firstname;
                            data[5] = lastname;
                            data[6] = addone;
                            data[7] = addtwo;
                            data[8] = addthree;
                            data[9] = phoneno;


                            PutData putData = new PutData("http://192.168.42.119/userdetails/signUp.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();

                                    if(result.equals("Sign up was successful")){
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                        //Intent intent = new Intent(getApplicationContext(), Login.class);
                                       // startActivity(intent);
                                        //finish();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                    }
                                    Log.i("PutData", result);
                                }
                            }

                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}