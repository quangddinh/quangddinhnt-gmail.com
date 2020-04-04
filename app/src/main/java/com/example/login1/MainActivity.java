package com.example.login1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText edtTk, edtMk ;
    Button btnDangnhap;
    Button mBtnRegister;
    private FirebaseAuth mAuth;
    CheckBox mCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        map();
        setListener();
        check();
    }

    private void check() {
        mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edtMk.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    edtMk.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void setListener() {

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = edtTk.getText().toString();
                String pass2 = edtMk.getText().toString();

                if ( email2.equals("") || pass2.equals(""))
                {
                    Toast.makeText(MainActivity.this , "Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                mAuth.signInWithEmailAndPassword(email2, pass2)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Intent intent = new Intent(MainActivity.this,UserActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this, "Log in success", Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Fail!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                }
            }
        });

    }

    private void map() {
        edtTk = findViewById(R.id.edittextEmail);
        edtMk = findViewById(R.id.edittextPassword);
        btnDangnhap = findViewById(R.id.buttonDangnhap);
        mAuth = FirebaseAuth.getInstance();
        mBtnRegister = findViewById(R.id.btn_register);
        mCheck = findViewById(R.id.checkpass);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
    }

}
