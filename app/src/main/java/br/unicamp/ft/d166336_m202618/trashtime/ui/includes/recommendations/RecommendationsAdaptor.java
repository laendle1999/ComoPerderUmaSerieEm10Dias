package br.unicamp.ft.d166336_m202618.trashtime.ui.includes.recommendations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.SerieList;
import br.unicamp.ft.d166336_m202618.trashtime.services.JsonReciver;
import br.unicamp.ft.d166336_m202618.trashtime.services.TmdbService;

public class RecommendationsAdaptor extends RecyclerView.Adapter implements JsonReciver {

    private ArrayList<SerieList> series;
    private TmdbService tmdbService;

    public RecommendationsAdaptor(String code) {
        series = new ArrayList<>();

        tmdbService = new TmdbService("http://api.themoviedb.org/3",
                "5472dbfc461c85f5a29197d9c1fef7d5",
                "en-us",
                RecommendationsAdaptor.this
        );

        tmdbService.recommendations(code);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_recommendations_list, parent, false
        );

        final RecommendationsViewHolder recommendationsViewHolder = new RecommendationsViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serieAdapterOnClickListner != null){
                    SerieList serie = series.get(recommendationsViewHolder.getAdapterPosition());
                    serieAdapterOnClickListner.onItemClick(serie.getName());
                }
            }
        });

        return recommendationsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RecommendationsViewHolder)holder).bind(series.get(position));
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


    }

    public int filterSeries (String name) {
        Iterator<SerieList> itr = series.iterator();
        int index = 0;
        while (itr.hasNext()){

            if(itr.next().getName().equals(name)){
                break;
            }
            index++;
        }

        return series.get(index).getId();
    }

    @Override
    public void recieveJson(JSONObject jsonObject) {

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

        notifyDataSetChanged();
    }

    /**
     * Interface de clique
     */
    public interface SerieAdapterOnClickListner {
        void onItemClick(String name);
    }

    private SerieAdapterOnClickListner serieAdapterOnClickListner;

    public void setSerieAdapterOnClickListner(SerieAdapterOnClickListner a){
        this.serieAdapterOnClickListner = a;
    }
}
