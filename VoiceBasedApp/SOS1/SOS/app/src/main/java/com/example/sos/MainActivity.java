package com.example.sos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText number;
    private TextView text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //now lets ask the user for the permissions explicitly
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        //lets get the number
        number = findViewById(R.id.number);
        text1 = findViewById(R.id.text1);

    }



    public void getspeechInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //now i will get the default language of the system
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {

            //i have used this if else statement to check that device is compatible with this kind of functions or not
            startActivityForResult(intent, 10);


        } else {
            Toast.makeText(this, "device not supported with voice capabilities", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (requestCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    text1.setText(result.get(0));

                    String message = text1.getText().toString().trim();
                    String getnumber = number.getText().toString().trim();

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(getnumber, null, message, null, null);
                    //in the above code we just code that what ever we will say it will just convert into a string


                } else {
                    Toast.makeText(this, "message sent ", Toast.LENGTH_SHORT).show();
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    text1.setText(result.get(0));

                    String message = text1.getText().toString().trim();
                    String getnumber = number.getText().toString().trim();

                    SmsManager smsManager = SmsManager.getDefault();
                    String primaryMessage="need help";
                    smsManager.sendTextMessage(getnumber, null, primaryMessage, null, null);
                    smsManager.sendTextMessage(getnumber, null, message, null, null);

                }

        }


    }






}
