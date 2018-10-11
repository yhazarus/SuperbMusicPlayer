package com.redilexapps.superbmusicplayer.model.smartplaylist;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;

import com.redilexapps.superbmusicplayer.R;
import com.redilexapps.superbmusicplayer.loader.TopAndRecentlyPlayedSongsLoader;
import com.redilexapps.superbmusicplayer.model.Song;
import com.redilexapps.superbmusicplayer.provider.SongPlayCountStore;

import java.util.ArrayList;

public class MyTopSongsPlaylist extends AbsSmartPlaylist {

    public MyTopSongsPlaylist(@NonNull Context context) {
        super(context.getString(R.string.my_top_songs), R.drawable.ic_trending_up_white_24dp);
    }

    @NonNull
    @Override
    public ArrayList<Song> getSongs(@NonNull Context context) {
        return TopAndRecentlyPlayedSongsLoader.getTopSongs(context);
    }

    @Override
    public void clear(@NonNull Context context) {
        SongPlayCountStore.getInstance(context).clear();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    protected MyTopSongsPlaylist(Parcel in) {
        super(in);
    }

    public static final Creator<MyTopSongsPlaylist> CREATOR = new Creator<MyTopSongsPlaylist>() {
        public MyTopSongsPlaylist createFromParcel(Parcel source) {
            return new MyTopSongsPlaylist(source);
        }

        public MyTopSongsPlaylist[] newArray(int size) {
            return new MyTopSongsPlaylist[size];
        }
    };
}