package com.azizulhakim.gifsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    TextView name, email;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Hooks
        name = findViewById(R.id.nameId);
        email = findViewById(R.id.emailId);
        logout = findViewById(R.id.button);

        GoogleSignInAccount signInActivity = GoogleSignIn.getLastSignedInAccount(this);
        if (signInActivity != null) {
            name.setText(signInActivity.getDisplayName());
            email.setText(signInActivity.getEmail());
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this, GoogleSignInActivity.class);
                startActivity(intent);
            }
        });
    }
}