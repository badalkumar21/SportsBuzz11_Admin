package com.cricker.admin.Activity;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.cricker.admin.R;
import com.firebase.client.Firebase;

public class FeedbackActivity extends AppCompatActivity {

    EditText namedata, emaildata, messagedata;
    Button send, details;
    Firebase firebase;

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        namedata = (EditText) findViewById(R.id.namedata);
        emaildata = (EditText) findViewById(R.id.emaildata);
        messagedata = (EditText) findViewById(R.id.messagedata);


        send = (Button) findViewById(R.id.btn_send);
        details = (Button) findViewById(R.id.btn_details);

        Firebase.setAndroidContext(this);

        String UniqueID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);


        firebase = new Firebase("https://all-sports-predictions.firebaseio.com/FeedbackActivity/Users" + UniqueID);

        send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {

                details.setEnabled(true);

                final String name = namedata.getText().toString();
                final String email = emaildata.getText().toString();
                final String message = messagedata.getText().toString();

                Firebase child_name = firebase.child("Name");
                child_name.setValue(name);

                if (name.isEmpty()) {
                    namedata.setError("This is a required field");
                    send.setEnabled(false);
                } else {
                    namedata.setError(null);
                    send.setEnabled(true);
                }

                Firebase child_email = firebase.child("Email");
                child_email.setValue(email);

                if (email.isEmpty()) {
                    emaildata.setError("This is a required field");
                    send.setEnabled(false);
                } else {
                    emaildata.setError(null);
                    send.setEnabled(true);
                }

                Firebase child_message = firebase.child("Message");
                child_message.setValue(message);

                if (message.isEmpty()) {
                    messagedata.setError("This is a required field");
                    send.setEnabled(false);
                } else {
                    messagedata.setError(null);
                    send.setEnabled(true);
                }

                Toast.makeText(getApplicationContext(), "Thank you for your FeedbackActivity", Toast.LENGTH_SHORT).show();

                namedata.getText().clear();
                emaildata.getText().clear();
                messagedata.getText().clear();

                details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(FeedbackActivity.this)
                                .setTitle("FeedbackActivity Details")
                                .setMessage("Name: " + name + "\n\nEmail: " + email + "\n\nFeedbackActivity: " + message).show();
                    }
                });

            }
        });


    }

}
