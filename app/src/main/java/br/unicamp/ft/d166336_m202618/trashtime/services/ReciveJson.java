package br.unicamp.ft.d166336_m202618.trashtime.services;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ReciveJson extends AsyncTask<String, Void, String> {

    private JsonReciver jsonReciver;

    public ReciveJson(JsonReciver jsonReciver) {

        this.jsonReciver = jsonReciver;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(1000);

            httpURLConnection.setConnectTimeout(1500);

            httpURLConnection.connect();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream())
            );

            StringBuilder stringBuilder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {

                stringBuilder.append(line);
            }

            return stringBuilder.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(String s) {
        try {
            Log.i("testando", s);
            JSONObject jsonObject = new JSONObject(s);

            sendJson(jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendJson(JSONObject jsonObject){
        if(jsonReciver!= null){
            jsonReciver.recieveJson(jsonObject);
        }
    }
}
