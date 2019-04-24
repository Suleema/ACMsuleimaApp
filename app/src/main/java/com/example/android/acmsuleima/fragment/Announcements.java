package com.example.android.acmsuleima.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.acmsuleima.R;
import com.example.android.acmsuleima.fragment.recyclerview.RecyclerAnnouncements;
import com.example.android.acmsuleima.model.Announcement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Announcements extends Fragment {
    //Instance of Firebasedatabase
    final private FirebaseDatabase database = FirebaseDatabase.getInstance();


    private RecyclerAnnouncements mAnnouncementAdapter;

    //Our announcements
    private List<Announcement> mAnnouncements;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAnnouncements = new ArrayList<>();

        View rootView = inflater.inflate(R.layout.fragment_announcements, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.announcements_recycler);
        mAnnouncementAdapter = new RecyclerAnnouncements(mAnnouncements,container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(mAnnouncementAdapter);
        getActivity().setTitle("Announcements");

        retrieveData();

        return rootView;
    }

    public void retrieveData() {
        //Gets the reference from the firebase database.
        //Instance of our actual database
        DatabaseReference mDatabaseReference = database.getReference();

        //queries specific location on database, in this case the child of the root (project) node
        //is Announcements, so we want that information.
        Query query = mDatabaseReference.child("Announcements");

        //In order to listen for changes, we will add a listener.
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            //Returns a new "dataSnapshot" of how the data in the database looks.
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //For every single snapshot in our data snapshot, we will get their children - these will
                //be the "announcement1" and "announcement2".
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    //Here, we use our model to create an object of the singleSnapshot.
                    Announcement announcement = singleSnapshot.getValue(Announcement.class);
                    //Adds this onto the list we have in this class.
                    mAnnouncements.add(announcement);
                }
                mAnnouncementAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Do Nothing
            }
        });
    }
}