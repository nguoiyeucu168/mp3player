package vn.kat.mp3player.ui.albums;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import vn.kat.mp3player.activities.MainActivity;
import vn.kat.mp3player.R;
import vn.kat.mp3player.activities.Play_Activity;

public class AlbumsFragment extends Fragment {

    private AlbumsViewModel albumsViewModel;
    Button btnTestPlay;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        albumsViewModel =
                ViewModelProviders.of(this).get(AlbumsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_albums, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        btnTestPlay = root.findViewById(R.id.btnTestPlay);
        albumsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btnTestPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Play_Activity.class);
                startActivity(intent);
            }
        });
        return root;
    }


}