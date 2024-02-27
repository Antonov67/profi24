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

    public NameAndBalance(String fio, String balance) {
        this.fio = fio;
        this.balance = balance;
    }
}
