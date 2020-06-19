package br.unicamp.ft.d166336_m202618.trashtime.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;

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
                if (alunoAdapterOnClickListner != null){
                    TextView txt = v.findViewById(R.id.list_serie_name);
                    String array[] = txt.getText().toString().split(",");
                    alunoAdapterOnClickListner.onItemClick(array[0]);
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
        return list_series.size();
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

    public void setList_series(ArrayList<Serie> list_series) {
        this.list_series = list_series;
    }

    /**
     * Interface de clique
     */
    public interface AlunoAdapterOnClickListner {
        void onItemClick(String name);
    }

    private AlunoAdapterOnClickListner alunoAdapterOnClickListner;

    public void setAlunoAdapterOnClickListner(AlunoAdapterOnClickListner a){
        this.alunoAdapterOnClickListner = a;
    }
}
