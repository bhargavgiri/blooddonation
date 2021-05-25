package com.example.blooddonetion;

import androidx.annotation.NonNull;

public class Userid {

    public String user_id;
    public <T extends Userid>T withId(@NonNull final String id)
    {
        this.user_id=id;
        return (T) this;
    }

}
