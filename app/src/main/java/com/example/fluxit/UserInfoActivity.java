package com.example.fluxit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends AppCompatActivity {

    @BindView(R.id.profileLargePicture)
    ImageView profileLargePicture;
    @BindView(R.id.userNameUserActivity)
    TextView userNameUserActivity;
    @BindView(R.id.userEmailUserActivity)
    TextView userEmailUserActivity;
    @BindView(R.id.userCompleteName)
    TextView userCompleteName;
    @BindView(R.id.userAge)
    TextView userAge;
    private String profileLargePictureString;
    private String emailString;
    private String completeUserName;
    private String age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);

        Intent intent = getIntent();

       profileLargePictureString = intent.getStringExtra("profileLargePicture");
        emailString = intent.getStringExtra("userEmailUserActivity");
        completeUserName = intent.getStringExtra("userCompleteNameTitle" + " " + "userCompleteNameFirst" + " " + "userCompleteNameLast");
        age = intent.getStringExtra("userAge");


        //TODO Null UserName & Age

        userEmailUserActivity.setText(emailString);
        userCompleteName.setText(completeUserName);
        userAge.setText(age);
        Glide.with(this)
                .load(profileLargePictureString)
                .into(profileLargePicture);



    }
}
