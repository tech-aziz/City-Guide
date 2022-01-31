package com.azizulhakim.gifsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    TextView profile_name, profile_desc;
    TextInputLayout fullName, email, phoneNo, password;
    //Global variable to hold user data inside this activity
    String _USERNAME, _NAME, _EMAIL, _PHONENO, _PASSWORD;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);

        //Hooks
        profile_name = findViewById(R.id.profile_name);
        profile_desc = findViewById(R.id.profile_desc);
        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        phoneNo = findViewById(R.id.phoneNo);
        password = findViewById(R.id.password);

        reference = FirebaseDatabase.getInstance().getReference("users");

        //ShowAllData
        showAllUserData();
    }

    private void showAllUserData() {

        Intent intent = getIntent();

        _USERNAME = intent.getStringExtra("username");
        _NAME = intent.getStringExtra("name");
        _EMAIL = intent.getStringExtra("email");
        _PHONENO = intent.getStringExtra("phone");
        _PASSWORD = intent.getStringExtra("password");


        profile_name.setText(_USERNAME);
        profile_desc.setText(_USERNAME);
        fullName.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        phoneNo.getEditText().setText(_PHONENO);
        password.getEditText().setText(_PASSWORD);


    }

    public void update(View view) {

        if (isNameChanged() || isPasswordChanged()){
            Toast.makeText(this, "data has been updated", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Data is same and cannot be updated", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(password.getEditText().getText().toString())){
            reference.child(_USERNAME).child("password").setValue((password.getEditText().getText().toString()));
            _PASSWORD = password.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }

    }

    private boolean isNameChanged() {
        if (!_NAME.equals(fullName.getEditText().getText().toString())){
            reference.child(_USERNAME).child("name").setValue(fullName.getEditText().getText().toString());
            _NAME = fullName.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }
    }
}