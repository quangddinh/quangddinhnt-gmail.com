package com.example.login1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;

public class UserActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button mBtnSave;
    TextView mTvName, mTvEmail, mTvId;
    ImageView mImg;
    EditText mEdtName;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mAuth = FirebaseAuth.getInstance();



        init();
        toolbarclick();
        show();
        getDislayname();
    }

    private void getDislayname() {

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (mEdtName.getText().toString().equals(""))
                {
                        Toast.makeText(UserActivity.this , "Fail", Toast.LENGTH_SHORT).show();
                        return;
                }
                else {
                    if (user != null) {
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(mEdtName.getText().toString())
                                .build();
                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(UserActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(UserActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                    } return;
                }


            }
        });

    }

    private void toolbarclick() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.item_Exit){
                    Intent intent = new Intent(UserActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

    }

    private void show() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            // boolean emailVerified = user.isEmailVerified();
            String uid = "ID: " + user.getUid();
            mTvEmail.setText(email);
            mTvName.setText(name);
            mTvId.setText(uid);

        }

        private void init () {
            toolbar = findViewById(R.id.toolbar);
            setActionBar(toolbar);
            toolbar.inflateMenu(R.menu.toolbar);
            mTvName = findViewById(R.id.tv_username);
            mTvEmail = findViewById(R.id.tv_useemail);
            mEdtName = findViewById(R.id.edt_insert_name);
            mBtnSave = findViewById(R.id.btn_save);
            mTvId = findViewById(R.id.tv_id);
            //        mImg = findViewById(R.id.img_avatar);


        }

}
