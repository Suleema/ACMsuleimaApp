package com.example.android.acmsuleima.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.acmsuleima.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Insta extends Fragment {


    public Insta() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Instagram");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}