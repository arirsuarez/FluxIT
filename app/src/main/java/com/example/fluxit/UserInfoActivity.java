package com.example.fluxit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fluxit.model.pojo.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends AppCompatActivity {

    public static final String KEY_USER = "user";

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
        Bundle bundle = intent.getExtras();
        User user = (User) bundle.getSerializable(KEY_USER);

       profileLargePictureString = user.getPicture().getLarge();
        emailString = user.getEmail();
        completeUserName = user.getName().getTitle() + " " + user.getName().getFirst() + " " + user.getName().getLast();
        age = user.getDob().getAge().toString();

        userEmailUserActivity.setText(emailString);
        userCompleteName.setText(completeUserName);
        userAge.setText(age);
        Glide.with(this)
                .load(profileLargePictureString)
                .into(profileLargePicture);


    }
}
