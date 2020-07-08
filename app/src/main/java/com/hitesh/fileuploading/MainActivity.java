package com.hitesh.fileuploading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.hitesh.fileuploading.Phone_Login.PhoneVerification;



public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    private FirebaseAuth firebaseAuth;
    private ImageButton phone, amazon;
    String first = "", email = "", mobile = "";
    private static int SPLASH_SCREEN_TIME_OUT = 2000;

    private String userID;

    //Tag for the logs optional
    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();
        phone = (ImageButton) findViewById(R.id.phone);


        if (firebaseAuth.getCurrentUser() != null) {

            first = firebaseAuth.getCurrentUser().getDisplayName();
            email = firebaseAuth.getCurrentUser().getEmail();
            mobile = firebaseAuth.getCurrentUser().getPhoneNumber();
            userID = firebaseAuth.getCurrentUser().getUid();
            Intent i1 = new Intent(MainActivity.this, UserSection.class);
            i1.putExtra("email", email);
            i1.putExtra("name", first);
            i1.putExtra("mobile", mobile);
            i1.putExtra("userid", userID);
            startActivity(i1);
            finish();

        } else {
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, PhoneVerification.class);
                    // intent.putExtra("userid", userID);
                    startActivity(intent);
                    finish();
                }
            });
        }


    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to close the application? Your Progress won't be saved")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                        finishAffinity();
                    }
                }).setNegativeButton("no", null).show();
    }

}
