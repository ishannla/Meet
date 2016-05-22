package com.sabersoft.ishannarula.meet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    EditText firstnameField;
    EditText lastnameField;
    EditText emailField;
    EditText passwordField;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firstnameField = (EditText)findViewById(R.id.firstnameField);
        lastnameField = (EditText)findViewById(R.id.lastnameField);
        emailField = (EditText)findViewById(R.id.emailField);
        passwordField = (EditText)findViewById(R.id.passwordField);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstnameField.getText().toString();
                String lastName = lastnameField.getText().toString();
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

            }
        });
    }
}
