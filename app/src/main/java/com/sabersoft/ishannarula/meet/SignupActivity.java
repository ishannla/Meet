package com.sabersoft.ishannarula.meet;

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
import com.google.firebase.auth.FirebaseUser;
import com.sabersoft.ishannarula.meet.objects.User;

public class SignupActivity extends AppCompatActivity {

    EditText firstnameField;
    EditText lastnameField;
    EditText phoneField;
    EditText emailField;
    EditText passwordField;
    Button submitButton;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Firebase.setAndroidContext(this);
        final Firebase users = new Firebase("https://meet-2332f.firebaseio.com/users");
        final FirebaseAuth auth = FirebaseAuth.getInstance();

        firstnameField = (EditText)findViewById(R.id.firstnameField);
        lastnameField = (EditText)findViewById(R.id.lastnameField);
        phoneField = (EditText)findViewById(R.id.phoneField);
        emailField = (EditText)findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        submitButton = (Button) findViewById(R.id.submitButton);
        progress = (ProgressBar) findViewById(R.id.progressBar);

        progress.setVisibility(View.INVISIBLE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress.setVisibility(View.VISIBLE);

                final String firstName = firstnameField.getText().toString();
                final String lastName = lastnameField.getText().toString();
                final String phoneNumber = phoneField.getText().toString();
                final String email = emailField.getText().toString();
                final String password = passwordField.getText().toString();


                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progress.setVisibility(View.INVISIBLE);

                        if (task.isSuccessful()){

                            Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_LONG).show();
                            User newUser = new User(firstName, lastName, phoneNumber, email);

                            FirebaseUser currentUser = auth.getCurrentUser();
                            String id = currentUser.getUid();
                            users.child(id).setValue(newUser);

                        }

                        else
                            Toast.makeText(getApplicationContext(), "An error has occured.", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
    }
}
