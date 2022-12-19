package com.example.baocaocuoiky_vothanhhai.LoginScreen;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.baocaocuoiky_vothanhhai.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends LoginActivity {
    Button btnBack,btnRegister;
    EditText edtPassword,edtEmail,edtRepeatPw;
    ProgressBar progressBar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Anhxa();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnRegister.setOnClickListener(view -> CreateAccount());
    }

    private void Anhxa(){
        btnBack     = (Button) findViewById(R.id.btLogin2);
        btnRegister = (Button) findViewById(R.id.btRegister);
        edtEmail = (EditText) findViewById(R.id.edtEmail2);
        edtPassword = (EditText) findViewById(R.id.edtPassword2);
        edtRepeatPw = (EditText) findViewById(R.id.edtRepeatPw2);
        progressBar= (ProgressBar) findViewById(R.id.progress_bar);
    }
    void CreateAccount(){
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String RPpassword = edtRepeatPw.getText().toString();

        boolean isvalidateData = validateData(email,password,RPpassword);
        if (!isvalidateData){
            return;
        }

        createAccountInFiseBase(email,password);
    }

    private void createAccountInFiseBase(String email, String password) {
        ChangeProgress(true);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SigninActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        ChangeProgress(false);
                        if (task.isSuccessful()){
                            Toast.makeText(SigninActivity.this,"Successfully create account",Toast.LENGTH_LONG).show();
                            //firebaseAuth.getCurrentUser().sendEmailVerification();
                            firebaseAuth.signOut();
                            finish();
                        }else {
                            Toast.makeText(SigninActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    void ChangeProgress(boolean inProgress){
        if (inProgress){
            progressBar.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.GONE);
        }else {
            progressBar.setVisibility(View.GONE);
            btnRegister.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email,String pass,String rppass){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Email is invalid!");
            return false;
        }
        if (pass.length()<6){
            edtPassword.setError("Password length is invalid!");
            return false;
        }
        if (!pass.equals(rppass)){
            edtRepeatPw.setError("Password not matched!");
            return false;
        }
        return true;
    }

}