package vn.kat.mp3player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.kat.mp3player.R;
import vn.kat.mp3player.model.SongModel;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    ArrayList<SongModel> _songs;
    Context context;

    OnItemClickListener onItemClickListener;

    public SongAdapter(Context context, ArrayList<SongModel> _songs) {
        this._songs = _songs;
        this.context = context;
    }

    public interface OnItemClickListener{
        void onItemClick(Button b, View v, SongModel obj, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(context).inflate(R.layout.row_song, parent, false);
        return new SongHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SongHolder holder, final int position) {
        final SongModel s = _songs.get(position);
        holder.songName.setText(s.songName);
        holder.artistName.setText(s.artistName);
        holder.btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(holder.btnAction, view, s, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return _songs.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        TextView songName, artistName;
        Button btnAction;

        public SongHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.tvSongName);
            artistName = itemView.findViewById(R.id.tvArtistName);
            btnAction = itemView.findViewById(R.id.btnAction);
        }
    }
}
