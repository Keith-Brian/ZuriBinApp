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

public class signinActivity extends AppCompatActivity {

    private EditText emailSignin, passwordSignin;
    private Button Signin, tvSignup;
    private ProgressBar SigninProgress;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        emailSignin = findViewById(R.id.signinEmailEdtxt);
        passwordSignin = findViewById(R.id.signinPassEdtxt);
        Signin = findViewById(R.id.signinBtn);
        tvSignup = findViewById(R.id.tvsignupBtn);
        SigninProgress = findViewById(R.id.signinProgress);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSignin();
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signinActivity.this, signupActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void performSignin(){

        String email = emailSignin.getText().toString();
        String password = passwordSignin.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Signin.setVisibility(View.GONE);
                        SigninProgress.setVisibility(View.VISIBLE);

                        if(task.isSuccessful()){
                            Toast.makeText(signinActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(signinActivity.this, dashboardActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(signinActivity.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }


                });

}
}