package br.unicamp.ft.d166336_m202618.trashtime.ui.search;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.SerieList;
import br.unicamp.ft.d166336_m202618.trashtime.ui.list.SerieAdaptor;

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
