package com.example.loc_by.newjson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loc_by on 10.07.2017.
 */

public class CitiesSingleton {
    private static CitiesSingleton   _instance;
    private CitiesSingleton() { }

    public synchronized static CitiesSingleton getInstance()
    {
        if (_instance == null)
        {
            _instance = new CitiesSingleton();
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
