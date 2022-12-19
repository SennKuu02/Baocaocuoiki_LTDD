package com.example.baocaocuoiky_vothanhhai.LoginScreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baocaocuoiky_vothanhhai.MenuActivity;
import com.example.baocaocuoiky_vothanhhai.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail;
    EditText edtPassword;
    Button btLogin;
    Button btSignin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Anhxa();
        btSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doOpenSignin();
            }
        });
        btLogin.setOnClickListener(view -> loginUser());
    }

    private void loginUser() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        boolean isvalidateData = validateData(email,password);
        if (!isvalidateData){
            return;
        }
        loginAccountisFireBase(email,password);
    }

    void loginAccountisFireBase(String email, String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        ChangeProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                ChangeProgress(false);
                if(task.isSuccessful()){
                        doOpenMain();
                }else {
                    Toast.makeText(LoginActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void ChangeProgress(boolean inProgress){
        if (inProgress){
            progressBar.setVisibility(View.VISIBLE);
            btLogin.setVisibility(View.GONE);
        }else {
            progressBar.setVisibility(View.GONE);
            btLogin.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email,String pass){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Email is invalid!");
            return false;
        }
        if (pass.length()<6){
            edtPassword.setError("Password length is invalid!");
            return false;
        }
        return true;
    }

    private void Anhxa(){
        edtEmail= (EditText)findViewById(R.id.edtUsername);
        edtPassword= (EditText)findViewById(R.id.edtPassword);
        btLogin= (Button) findViewById(R.id.btLogin);
        btSignin= (Button) findViewById(R.id.btSignin);
        progressBar= (ProgressBar) findViewById(R.id.progress_bar);
    }
    public void doOpenSignin(){
        Intent myIntent=new Intent(this, SigninActivity.class);
        startActivity(myIntent);
    }
    public void doOpenMain(){
        Intent myIntent2=new Intent(this, MenuActivity.class);
        startActivity(myIntent2);
        finish();
    }
}