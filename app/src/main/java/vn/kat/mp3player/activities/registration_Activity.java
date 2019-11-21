package vn.kat.mp3player.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import vn.kat.mp3player.R;

public class registration_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);
        getSupportActionBar().hide();
    }

    public void onClickCreateAccount(View view){
        Intent intent= new Intent(registration_Activity.this,pre_login_Activity.class);
        startActivity(intent);
    }
}
