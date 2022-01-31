package com.azizulhakim.gifsplashscreen;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class SignUpActivity extends AppCompatActivity {
    GifImageView gifImageView;
    TextView logo_name, slogan_name;
    TextInputLayout sign_up_full_name, username_signup, email_signup, phoneNo_signup, password_signup;
    Button btn_go_signup, login_signup;

    //DatabaseReference databaseReference;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //Hooks
        gifImageView = findViewById(R.id.gifImageView);
        logo_name = findViewById(R.id.logo_name);
        slogan_name = findViewById(R.id.slogan_name);
        sign_up_full_name = findViewById(R.id.sign_up_full_name);
        username_signup = findViewById(R.id.username_signup);
        email_signup = findViewById(R.id.email_signup);
        phoneNo_signup = findViewById(R.id.phoneNo_signup);
        password_signup = findViewById(R.id.password_signup);
        btn_go_signup = findViewById(R.id.btn_go_signup);
        login_signup = findViewById(R.id.login_signup);


        //Save data to the Firebase Database
        btn_go_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToDB();
            }

            private void saveDataToDB() {

                if (!validateName() | !validateUsername() | !validateEmail() | !validatePhoneNo() | !validatePassword()) {
                    return;
                }

                String name = sign_up_full_name.getEditText().getText().toString().trim();
                String username = username_signup.getEditText().getText().toString().trim();
                String email = email_signup.getEditText().getText().toString().trim();
                String phone = phoneNo_signup.getEditText().getText().toString().trim();
                String password = password_signup.getEditText().getText().toString().trim();

                Intent intent = new Intent(getApplicationContext(), VerifyPhoneNumber.class);
                intent.putExtra("phone", phone);
                startActivity(intent);

//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("users");
//
//                UserHelperClass userHelperClass = new UserHelperClass(name, username, email, phone, password);
//                reference.child(username).setValue(userHelperClass);
//                Toast.makeText(SignUpActivity.this, "Your account has been created successfully!", Toast.LENGTH_SHORT).show();

                sign_up_full_name.getEditText().setText("");
                username_signup.getEditText().setText("");
                email_signup.getEditText().setText("");
                phoneNo_signup.getEditText().setText("");
                password_signup.getEditText().setText("");
            }
        });
//        //Go to LoginActivity
//        login_signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
//
//                Pair[] pairs = new Pair[7];
//                pairs[0] = new Pair<View, String>(gifImageView, "logo_image");
//                pairs[1] = new Pair<View, String>(logo_name, "logo_text");
//                pairs[2] = new Pair<View, String>(slogan_name, "logo_desc");
//                pairs[3] = new Pair<View, String>(username_signup, "username_trans");
//                pairs[4] = new Pair<View, String>(password_signup, "password_trans");
//                pairs[5] = new Pair<View, String>(btn_go_signup, "go_trans");
//                pairs[6] = new Pair<View, String>(login_signup, "login_trans");
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this, pairs);
//                startActivity(intent, options.toBundle());
//                finish();
//
//            }
//        });

    }

    public Boolean validateName() {
        String value = sign_up_full_name.getEditText().getText().toString().trim();
        if (value.isEmpty()) {
            sign_up_full_name.setError("Field cannot be empty");
            return false;
        } else {
            sign_up_full_name.setError(null);
            sign_up_full_name.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateUsername() {
        String value = username_signup.getEditText().getText().toString().trim();
        String noWhiteSpace = "\\A\\w{4,20}";
        if (value.isEmpty()) {
            username_signup.setError("Field cannot be empty");
            return false;
        } else if (value.length() >= 15) {
            username_signup.setError("Username too long");
            return false;
        } else if (!value.matches(noWhiteSpace)) {
            username_signup.setError("White spaces are not allowed");
            return false;
        } else {
            username_signup.setError(null);
            username_signup.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateEmail() {
        String value = email_signup.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (value.isEmpty()) {
            email_signup.setError("Field cannot be empty");
            return false;
        } else if (!value.matches(emailPattern)) {
            email_signup.setError("Invalid email address");
            return false;
        } else {
            email_signup.setError(null);
            email_signup.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validatePhoneNo() {
        String value = phoneNo_signup.getEditText().getText().toString().trim();
        if (value.isEmpty()) {
            phoneNo_signup.setError("Field cannot be empty");
            return false;
        } else {
            email_signup.setError(null);
            phoneNo_signup.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validatePassword() {
        String value = password_signup.getEditText().getText().toString().trim();
        String passwordVal =
                "^(?=.*[0-9])"
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=])"
                        + "(?=\\S+$).{8,12}$";

        if (value.isEmpty()) {
            password_signup.setError("Field cannot be empty");
            return false;
        } else if (!value.matches(passwordVal)) {
            password_signup.setError("Password is too weak");
            return false;
        } else {
            password_signup.setError(null);
            password_signup.setErrorEnabled(false);
            return true;
        }
    }

}