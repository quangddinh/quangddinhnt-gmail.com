package com.example.login1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    Button mBtnSign, mBtnTrangchu, mBtnBack;
    EditText mEdtemail, mEdtpass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mBtnSign = findViewById(R.id.btn_signup);
        mEdtemail = findViewById(R.id.edt_email);
        mEdtpass = findViewById(R.id.edt_pass);
        mBtnTrangchu = findViewById(R.id.btn_trangchu);
        mBtnBack = findViewById(R.id.btn_back);


        mBtnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEdtemail.getText().toString();
                String password = mEdtpass.getText().toString();

                if(email.equals("") || password.equals("") )
                {
                    Toast.makeText(RegisterActivity.this , "Bạn Chưa Nhập Thông Tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(RegisterActivity.this, "Sign up success", Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }
            }
        });
            // xac nhan dki
        mBtnTrangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = mEdtemail.getText().toString();
                String pass1 = mEdtpass.getText().toString();

                if(email1.equals("") || pass1.equals("") )
                {
                    Toast.makeText(RegisterActivity.this , "Bạn Chưa Nhập Thông Tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this , "Đăng kí thành công", Toast.LENGTH_SHORT).show();

                }

            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
