package com.example.android.acmsuleima.model.mlh;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hackathons {
    public List<Event> getEvents() {
        return events;
    }

    @SerializedName("array")
    List<Event> events;

}