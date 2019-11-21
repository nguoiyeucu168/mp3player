package vn.kat.mp3player.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import vn.kat.mp3player.R;

public class pre_login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login_);
        getSupportActionBar().hide();
    }

    public void onClickLogin(View view){
        Intent intent= new Intent(pre_login_Activity.this,login_Activity.class);
        startActivity(intent);
    }
}
