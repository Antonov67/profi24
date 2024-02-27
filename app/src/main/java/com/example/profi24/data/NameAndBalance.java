package com.example.profi24.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NameAndBalance {

    @SerializedName("fio")
    @Expose
    public String fio;
    @SerializedName("balance")
    @Expose
    public String balance;


}
