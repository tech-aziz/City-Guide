package com.azizulhakim.gifsplashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import pl.droidsonroids.gif.GifImageView;

public class LoginActivity extends AppCompatActivity {

    GifImageView gifImageView;
    TextView logo_name, slogan_name;
    TextInputLayout username_login, password_login;
    Button btn_go_login, signUp_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        gifImageView = findViewById(R.id.gifImageView);
        logo_name = findViewById(R.id.logo_name);
        slogan_name = findViewById(R.id.slogan_name);
        username_login = findViewById(R.id.username_login);
        password_login = findViewById(R.id.password_login);
        btn_go_login = findViewById(R.id.btn_go_login);
        signUp_login = findViewById(R.id.signUp_login);

        btn_go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        signUp_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(gifImageView, "logo_image");
                pairs[1] = new Pair<View, String>(logo_name, "logo_text");
                pairs[2] = new Pair<View, String>(slogan_name, "logo_desc");
                pairs[3] = new Pair<View, String>(username_login, "username_trans");
                pairs[4] = new Pair<View, String>(password_login, "password_trans");
                pairs[5] = new Pair<View, String>(btn_go_login, "go_trans");
                pairs[6] = new Pair<View, String>(signUp_login, "login_trans");


                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(intent, options.toBundle());
                finish();


            }
        });
    }


    public Boolean validateUsername() {
        String value = username_login.getEditText().getText().toString().trim();
        if (value.isEmpty()) {
            username_login.setError("Field cannot be empty");
            return false;
        } else {
            username_login.setError(null);
            username_login.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validatePassword() {
        String value = password_login.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            password_login.setError("Field cannot be empty");
            return false;
        } else {
            password_login.setError(null);
            password_login.setErrorEnabled(false);
            return true;
        }
    }

    private void loginUser() {
            //validate login info
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }

    }

    private void isUser() {

        final String UserEnteredUsername = username_login.getEditText().getText().toString().trim();
        final String UserEnteredPassword = password_login.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(UserEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    username_login.setError(null);
                    username_login.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(UserEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(UserEnteredPassword)){

                        username_login.setError(null);
                        username_login.setErrorEnabled(false);

                        String nameFromDB = snapshot.child(UserEnteredUsername).child("name").getValue(String.class);
                        String userNameFromDB = snapshot.child(UserEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(UserEnteredUsername).child("phone").getValue(String.class);
                        String emailFromDB = snapshot.child(UserEnteredUsername).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", userNameFromDB);
                        intent.putExtra("phone", phoneNoFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);

                    }else {
                        password_login.setError("Wrong Password!");
                        password_login.requestFocus();
                    }
                }
                else{
                    username_login.setError("No such user exist");
                    username_login.requestFocus();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }

}