package com.sabersoft.ishannarula.meet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailField;
    EditText passwordField;
    Button submitButton;
    Button signupButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase("https://meet-2332f.firebaseio.com/");
        final FirebaseAuth auth = FirebaseAuth.getInstance();

        emailField = (EditText)findViewById(R.id.emailField);
        passwordField = (EditText)findViewById(R.id.passwordField);
        submitButton = (Button) findViewById(R.id.submitButton);
        signupButton = (Button) findViewById(R.id.signupButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        final Intent mainIntent = new Intent(this, MainActivity.class);
        final Intent signupIntent = new Intent(this, SignupActivity.class);

        progressBar.setVisibility(View.INVISIBLE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);

                        if (task.isSuccessful())
                            startActivity(mainIntent);

                        else
                            Toast.makeText(getApplicationContext(), "Your email/password is incorrect", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signupIntent);
            }
        });

    }
}
