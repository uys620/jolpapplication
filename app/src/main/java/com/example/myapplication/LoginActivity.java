package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private EditText mEmail;
    private EditText mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail=findViewById(R.id.login_email);
        mPassword=findViewById(R.id.login_password);

        findViewById(R.id.login_signup).setOnClickListener(this);
        findViewById(R.id.login_success).setOnClickListener((this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user !=null){
            //Toast.makeText(this, "auto login"+user.getUid(), Toast.LENGTH_SHORT).show(); 잘 돌아가나 확인
            startActivity(new Intent(this, FrontPageActivity.class));
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_signup:
                startActivity(new Intent(this,signupActivity.class));
                break;
            case R.id.login_success:
                mAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        //Toast.makeText(LoginActivity.this, "Login success"+user.getUid(), Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, FrontPageActivity.class));
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Login error",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;

        }

    }
}