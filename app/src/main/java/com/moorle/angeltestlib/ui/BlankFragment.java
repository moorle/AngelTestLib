package com.moorle.angeltestlib.ui;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moorle.angeltestlib.R;

public class BlankFragment extends BaseFragment {

    private static final String STATE_SELECTED_ITEM_ID = "selected_nav_drawer_item_id";

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(STATE_SELECTED_ITEM_ID));
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BlankFragment newInstance(int sectionNumber) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt(STATE_SELECTED_ITEM_ID, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_blank;
    }


}
