package vn.kat.mp3player.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import vn.kat.mp3player.R;
import vn.kat.mp3player.adapter.ViewPagerAdapter;
import vn.kat.mp3player.fragments.FragmentAlbums;
import vn.kat.mp3player.fragments.FragmentPlaylist;
import vn.kat.mp3player.fragments.FragmentTracks;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ViewPager vpAlbums;
    TabLayout tlProfile;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        vpAlbums = root.findViewById(R.id.vpAlbums);
        tlProfile = root.findViewById(R.id.tlProfile);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        addTabs(vpAlbums);
        //add viewPager vafo tabLayout
        tlProfile.setupWithViewPager(vpAlbums);
        return root;
    }

    public void addTabs(ViewPager viewPager){
        ViewPagerAdapter adapter =  new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentAlbums(), "Albums");
        adapter.addFragment(new FragmentTracks(), "Tracks");
        adapter.addFragment(new FragmentPlaylist(), "Playlist");

        viewPager.setAdapter(adapter);
    }

}