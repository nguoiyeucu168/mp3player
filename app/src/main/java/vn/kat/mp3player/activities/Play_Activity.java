package vn.kat.mp3player.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import vn.kat.mp3player.R;
import vn.kat.mp3player.adapter.SongAdapter;
import vn.kat.mp3player.model.SongModel;

public class Play_Activity extends AppCompatActivity {

    private ArrayList<SongModel> _songs = new ArrayList<SongModel>();
    RecyclerView recyclerView;
    SeekBar seekBar;
    SongAdapter songAdapter;
    MediaPlayer mediaPlayer;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_);

        initsView();

//        SongModel songModel = new SongModel("abc", "111", "D:\\Download\\Music\\Girl.mp3");
//        _songs.add(songModel);
        songAdapter = new SongAdapter(this, _songs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(songAdapter);

        songAdapter.setOnItemClickListener(new SongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final Button b, View v, final SongModel obj, int position) {

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (b.getText().toString().equals("Stop")) {
                                b.setText("Play");
                                mediaPlayer.stop();
                                mediaPlayer.reset();
                                mediaPlayer.release();
                                mediaPlayer = null;
                            } else {
                                mediaPlayer = new MediaPlayer();
                                mediaPlayer.setDataSource(obj.getSongUrl());
                                mediaPlayer.prepareAsync();
                                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mediaPlayer) {
                                        mediaPlayer.start();
                                        seekBar.setProgress(0);
                                        seekBar.setMax(mediaPlayer.getDuration());
                                        b.setText("Stop");
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                handler.postDelayed(r, 100);


            }
        });

        CheckPermission();

        Thread t = new Play_Activity.MyThread();
        t.start();

    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void CheckPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
                return;
            } else {
                finish();
            }
        } else {
            loadSongs();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadSongs();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    CheckPermission();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    private void initsView() {
        recyclerView = findViewById(R.id.recycleView);
        seekBar = findViewById(R.id.seekBar);
    }

    private void loadSongs() {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                    SongModel songModel = new SongModel(name, artist, url);
                    _songs.add(songModel);

                } while (cursor.moveToNext());
            }
            cursor.close();
            songAdapter = new SongAdapter(this, _songs);
        }
    }
}
