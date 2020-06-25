package br.unicamp.ft.d166336_m202618.trashtime.ui.list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;
import br.unicamp.ft.d166336_m202618.trashtime.models.SerieList;
import br.unicamp.ft.d166336_m202618.trashtime.ui.search.SearchAdaptor;

public class SerieAdaptor extends RecyclerView.Adapter {

    private ArrayList<Serie> list_series;
    private ArrayList<Serie> all_series;
    public SerieAdaptor(ArrayList<Serie> series) {
        this.list_series = series;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_serie_list, parent, false
        );

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serieAdapterOnClickListner != null){
                    TextView txt = v.findViewById(R.id.list_serie_name);
                    String array[] = txt.getText().toString().split(",");
                    Log.i("testando", array[0]);
                    serieAdapterOnClickListner.onItemClick(array[0]);
                }
            }
        });

        return new SerieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SerieViewHolder)holder).bind(list_series.get(position));
    }

    @Override
    public int getItemCount() {
        if (list_series != null) {
            return list_series.size();
        }

        return 0;
    }

    public void search (String words) {
        if (all_series == null) {
            all_series = list_series;
        }

        list_series = new ArrayList<>();

        for(Serie serie : all_series) {
            if (serie.getName().toLowerCase().contains(words.toLowerCase())) {
                list_series.add(serie);
            }
        }

        notifyDataSetChanged();
    }

    public void originalDataSet () {
        list_series = all_series;

        notifyDataSetChanged();
    }

    public int filterSeries (String name) {
        Iterator<Serie> itr = list_series.iterator();
        int index = 0;
        while (itr.hasNext()){

            if(itr.next().getName().equals(name)){
                break;
            }
            index++;
        }

        return list_series.get(index).getId();
    }

    public void setList_series(ArrayList<Serie> list_series) {
        this.list_series = list_series;
    }


    /**
     * Interface de clique
     */
    public interface SerieAdapterOnClickListner {
        void onItemClick(String name);
    }

    private SerieAdaptor.SerieAdapterOnClickListner serieAdapterOnClickListner;

    public void setSerieAdapterOnClickListner(SerieAdaptor.SerieAdapterOnClickListner a){
        this.serieAdapterOnClickListner = a;
    }
}
