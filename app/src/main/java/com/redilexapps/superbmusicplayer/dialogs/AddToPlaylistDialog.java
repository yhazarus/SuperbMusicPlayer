package com.redilexapps.superbmusicplayer.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.redilexapps.superbmusicplayer.R;
import com.redilexapps.superbmusicplayer.loader.PlaylistLoader;
import com.redilexapps.superbmusicplayer.model.Playlist;
import com.redilexapps.superbmusicplayer.model.Song;
import com.redilexapps.superbmusicplayer.util.PlaylistsUtil;

import java.util.ArrayList;
import java.util.List;

public class AddToPlaylistDialog extends DialogFragment {

    @NonNull
    public static AddToPlaylistDialog create(Song song) {
        ArrayList<Song> list = new ArrayList<>();
        list.add(song);
        return create(list);
    }

    @NonNull
    public static AddToPlaylistDialog create(ArrayList<Song> songs) {
        AddToPlaylistDialog dialog = new AddToPlaylistDialog();
        Bundle args = new Bundle();
        args.putParcelableArrayList("songs", songs);
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final List<Playlist> playlists = PlaylistLoader.getAllPlaylists(getActivity());
        CharSequence[] playlistNames = new CharSequence[playlists.size() + 1];
        playlistNames[0] = getActivity().getResources().getString(R.string.action_new_playlist);
        for (int i = 1; i < playlistNames.length; i++) {
            playlistNames[i] = playlists.get(i - 1).name;
        }
        return new MaterialDialog.Builder(getActivity())
                .title(R.string.add_playlist_title)
                .items(playlistNames)
                .itemsCallback((materialDialog, view, i, charSequence) -> {
                    //noinspection unchecked
                    final ArrayList<Song> songs = getArguments().getParcelableArrayList("songs");
                    if (songs == null) return;
                    if (i == 0) {
                        materialDialog.dismiss();
                        CreatePlaylistDialog.create(songs).show(getActivity().getSupportFragmentManager(), "ADD_TO_PLAYLIST");
                    } else {
                        materialDialog.dismiss();
                        PlaylistsUtil.addToPlaylist(getActivity(), songs, playlists.get(i - 1).id, true);
                    }
                })
                .build();
    }
}
