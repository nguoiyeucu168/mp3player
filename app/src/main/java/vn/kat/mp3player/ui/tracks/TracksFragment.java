package vn.kat.mp3player.ui.tracks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import vn.kat.mp3player.ImageHelper;
import vn.kat.mp3player.R;
import vn.kat.mp3player.activities.TestPlayActivity;

public class TracksFragment extends Fragment {

    private TracksViewModel tracksViewModel;
    Button btnGoToPlay;
    TextView tvTestSong;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tracksViewModel =
                ViewModelProviders.of(this).get(TracksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tracks, container, false);
        tvTestSong = root.findViewById(R.id.tvTestSong);
        tvTestSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TestPlayActivity.class);
                startActivity(intent);
            }
        });
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        tracksViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        btnGoToPlay = root.findViewById(R.id.btnGoToPlay);
//        btnGoToPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), TestPlayActivity.class);
//                startActivity(intent);
//            }
//        });

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.songs_icon);
        Bitmap circularBitmap = ImageHelper.getRoundedCornerBitmap(bitmap, 100);
        ImageView circularImageView = (ImageView) root.findViewById(R.id.avatar_tracks);
        circularImageView.setImageBitmap(circularBitmap);
        return root;
    }

}