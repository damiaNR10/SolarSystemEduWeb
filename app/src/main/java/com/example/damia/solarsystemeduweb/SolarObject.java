package com.example.damia.solarsystemeduweb;

import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class SolarObject implements Serializable{

    private String name;
    private String text;
    private String image;


    private String video;
    private SolarObject[] moons;

    public SolarObject(){

    }

    public SolarObject(String name){
        this.name = name;
    }

    public SolarObject(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString("name");
        image = String.format("images/%s.jpg", name.toLowerCase());
        text = String.format("texts/%s.txt", name.toLowerCase());
        video = jsonObject.optString("video");
        JSONArray moons = jsonObject.optJSONArray("moons");
        if(moons != null){
            this.moons = getSolarObjectsFromJsonArray(moons);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public SolarObject[] getMoons() {
        return moons;
    }

    public void setMoons(SolarObject[] moons) {
        this.moons = moons;
    }

    public static SolarObject[] loadArrayFromJson(Context context, String type){
        try {
            String json = loadStringFromAssets(context, "solar.json");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(type);

            return getSolarObjectsFromJsonArray(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new SolarObject[0];
    }

    @NonNull
    private static SolarObject[] getSolarObjectsFromJsonArray(JSONArray jsonArray) throws JSONException {
        SolarObject[] solarObjects = new SolarObject[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            SolarObject solarObject = new SolarObject(jsonArray.getJSONObject(i));
            solarObjects[i] = solarObject;
        }
        return solarObjects;
    }

    public static String loadStringFromAssets(Context context, String fileName) throws IOException {//metoda wczytująca plik i zwraca go jako stringa
        InputStream inputStream = context.getAssets().open(fileName);
        int size = inputStream.available();//sprawdzamy ile bajtow jest dostępnych
        byte[] buffor = new byte[size];

        inputStream.read(buffor);
        inputStream.close();
        return new String(buffor, "UTF-8");
    }


    public boolean hasMoon() {
        return moons != null && moons.length > 0;
    }

    public String getImagePath() {
        return String.format("file:///android_asset/%s", getImage());
    }
}
