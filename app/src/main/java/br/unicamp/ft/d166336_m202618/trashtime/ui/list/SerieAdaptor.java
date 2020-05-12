package br.unicamp.ft.d166336_m202618.trashtime.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.unicamp.ft.d166336_m202618.trashtime.R;

public class SerieAdaptor extends RecyclerView.Adapter {

    private ArrayList<Serie> series;

    public SerieAdaptor(ArrayList<Serie> series) {
        this.series = series;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_serie_list, parent, false
        );

        return new SerieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SerieViewHolder)holder).bind(series.get(position));
    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }
}
