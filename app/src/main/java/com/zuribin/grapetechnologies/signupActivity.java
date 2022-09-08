package com.zuribin.grapetechnologies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signupActivity extends AppCompatActivity {

    private EditText emailSignup, passSignup;
    private Button signUp, tvsignIn;
    private ProgressBar signupProgress;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        emailSignup = findViewById(R.id.signupEmailEdtxt);
        passSignup = findViewById(R.id.signupPassEdtxt);

        signUp = findViewById(R.id.signupBtn);
        tvsignIn = findViewById(R.id.tvsigninBtn);
        signupProgress = findViewById(R.id.sinupProgressBar);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSignup();
            }
        });

        tvsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signupActivity.this, signinActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void performSignup(){

        String email = emailSignup.getText().toString();
        String password = passSignup.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        signupProgress.setVisibility(View.VISIBLE);
                        signUp.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Toast.makeText(signupActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signupActivity.this, dashboardActivity.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(signupActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    }}
                });
    }

}