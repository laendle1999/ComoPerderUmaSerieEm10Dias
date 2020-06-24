package br.unicamp.ft.d166336_m202618.trashtime.ui.includes.nexteps;

import android.content.Context;
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
import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;
import br.unicamp.ft.d166336_m202618.trashtime.models.SerieList;
import br.unicamp.ft.d166336_m202618.trashtime.repositories.SerieRepository;
import br.unicamp.ft.d166336_m202618.trashtime.services.JsonReciver;
import br.unicamp.ft.d166336_m202618.trashtime.services.TmdbService;

public class NextEpsAdaptor extends RecyclerView.Adapter {

    private ArrayList<Serie> series;
    private SerieRepository serieRepository;
    private TmdbService tmdbService;

    public NextEpsAdaptor(Context context) {
        serieRepository = new SerieRepository(context);
        series = serieRepository.seriesWithNextEp();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_recommendations_list, parent, false
        );

        final NextEpsViewHolder nextEpsViewHolder = new NextEpsViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serieAdapterOnClickListner != null){
                    Serie serie = series.get(nextEpsViewHolder.getAdapterPosition());
                    serieAdapterOnClickListner.onItemClick(serie.getName());
                }
            }
        });

        return nextEpsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((NextEpsViewHolder)holder).bind(series.get(position));
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
        Iterator<Serie> itr = series.iterator();
        int index = 0;
        while (itr.hasNext()){

            if(itr.next().getName().equals(name)){
                break;
            }
            index++;
        }

        return series.get(index).getId();
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
