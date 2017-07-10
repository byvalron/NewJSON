package com.example.loc_by.newjson;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loc_by on 10.07.2017.
 */

public class CountrySingleton {
    private static CountrySingleton   _instance;
    private CountrySingleton() { }

    public synchronized static CountrySingleton getInstance()
    {
        if (_instance == null)
        {
            _instance = new CountrySingleton();
        }
        return _instance;
    }

    List<String> log = new ArrayList();
    public List<String> getLog() {
        return this.log;
    }

    public void addToListLog(String str) {
        this.log.add(0, str);
    }

}
