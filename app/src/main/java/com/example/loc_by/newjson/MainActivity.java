package com.example.loc_by.newjson;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ListView list;
    String coutry, town;
    JSONArray cities;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        this.adapter = new ArrayAdapter<>(this, 17367043, CountrySingleton.getInstance().getLog());
        list.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();

        new ParseAsyncTask().execute();
    }

    private class ParseAsyncTask extends AsyncTask<Void, Void, String> {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);

            try {
                JSONObject data = new JSONObject(strJson);
                for(Iterator<String> countries = data.keys(); countries.hasNext();){
                    coutry = countries.next();
                    CountrySingleton.getInstance().addToListLog(coutry);

                    cities = data.getJSONArray(coutry);
                       JSONObject rey = new JSONObject(String.valueOf(cities));
                        for(Iterator<String> city = rey.keys(); city.hasNext();){
                            town = city.next();
                            CitiesSingleton.getInstance().addToListLog(town);
                        }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        }

    }

