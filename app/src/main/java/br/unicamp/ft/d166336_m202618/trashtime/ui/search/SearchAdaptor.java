package br.unicamp.ft.d166336_m202618.trashtime.ui.search;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;
import br.unicamp.ft.d166336_m202618.trashtime.models.SerieList;
import br.unicamp.ft.d166336_m202618.trashtime.ui.list.SerieViewHolder;

public class SearchAdaptor extends RecyclerView.Adapter {

    private ArrayList<SerieList> series;

    public SearchAdaptor() {
        series = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_search_list, parent, false
        );

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SearchViewHolder)holder).bind(series.get(position));
    }

    @Override
    public int getItemCount() {

        if (series != null) {

            return series.size();
        }

        return 0;
    }

    public void cleanSeries() {
        series = new ArrayList<>();
    }

    public void setSeries(JSONObject jsonObject) {

        JSONArray array = null;
        try {
            array = jsonObject.getJSONArray("results");

            for (int i = 0; i < array.length(); i++) {
                JSONObject serie = array.getJSONObject(i);

                int id = Integer.parseInt(serie.getString("id"));

                float grade = Float.parseFloat(serie.getString("vote_average"));

                SerieList serieList = new SerieList(id, serie.getString("original_name"), serie.getString("poster_path"), grade);

                this.series.add(serieList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
