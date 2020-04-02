package com.example.login1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class UserActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button mBtnCheck;
    TextView mTvName, mTvEmail;
    ImageView mImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mAuth = FirebaseAuth.getInstance();






        init();
        setListener();
    }

    private void init() {

        mTvName = findViewById(R.id.tv_username);
        mTvEmail = findViewById(R.id.tv_useemail);
        mBtnCheck = findViewById(R.id.btn_in4);
//        mImg = findViewById(R.id.img_avatar);


    }

    private void setListener() {
        mBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // Name, email address, and profile photo Url
                    String name = user.getDisplayName();
                    String email = user.getEmail();
                    Uri photoUrl = user.getPhotoUrl();
//                    boolean emailVerified = user.isEmailVerified();
                    String uid = user.getUid();
//                    Toast.makeText(
//                            UserActivity.this
//                            ,  "Email :" + email + "\n"+
//                                    "Name :" + name  + "\n" +
//                                    "Email verified:" + emailVerified
//                            , Toast.LENGTH_SHORT).show();

                    mTvEmail.setText(email);
                    mTvName.setText(name);

                    //                    String url = "https://i.imgur.com/A6oYUD1.jpg";
                    //                    Picasso.get()
                    //                            .load(url)
                    //                            .resize(400,400)
                    //                            .into(mImg);
                }
            }
        });

    }



}
