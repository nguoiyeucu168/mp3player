package vn.kat.mp3player.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import vn.kat.mp3player.R;

public class Welcome_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcome_);
    }

    public void onClickNext(View view){
        Intent intent= new Intent(Welcome_Activity.this,login_Activity.class);
        startActivity(intent);
    }
}
