package com.redilexapps.superbmusicplayer.ui.fragments.mainactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.redilexapps.superbmusicplayer.ui.activities.MainActivity;

public abstract class AbsMainActivityFragment extends Fragment {

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
