package br.unicamp.ft.d166336_m202618.trashtime.ui.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;
import br.unicamp.ft.d166336_m202618.trashtime.repositories.SerieRepository;
import br.unicamp.ft.d166336_m202618.trashtime.ui.evaluate.EvalutePackage;
import br.unicamp.ft.d166336_m202618.trashtime.ui.search.SearchAdaptor;
import br.unicamp.ft.d166336_m202618.trashtime.ui.search.SearchFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SerieFragment extends Fragment {

    private SerieRepository serieRepository;
    private RecyclerView recyclerView;
    private EditText editText;
    private final SerieAdaptor serieAdaptor;


    public SerieFragment() {
        serieAdaptor = new SerieAdaptor(
                new ArrayList<Serie>()
        );
    }

    @Override
    public void onStart() {
        super.onStart();

        serieRepository = new SerieRepository(getContext());

        serieAdaptor.setList_series(serieRepository.loadSeries());
        serieAdaptor.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();

        serieRepository.destroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_serie, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_serie);

        editText = view.findViewById(R.id.series_search);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                String search = editText.getText().toString();

                if (search.length() >= 3) {
                    serieAdaptor.search(search);
                } else {

                    serieAdaptor.originalDataSet();
                }

                return false;
            }
        });

        SerieAdaptor.SerieAdapterOnClickListner onClickListner = new SerieAdaptor.SerieAdapterOnClickListner() {

            @Override
            public void onItemClick(String name) {
                int id = serieAdaptor.filterSeries(name);

                Serie serie = serieRepository.find(id);

                Log.i("testando", serie.toString());

                EvalutePackage evalutePackage;

                evalutePackage = new EvalutePackage(serie.getId(), serie.getTmdb_code());
                
                Bundle bundle = new Bundle();

                bundle.putSerializable("serie", evalutePackage);

                NavController navController = NavHostFragment.findNavController(SerieFragment.this);

                navController.navigate(R.id.evalute_fragment, bundle);

            }
        };

        serieAdaptor.setSerieAdapterOnClickListner(onClickListner);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(serieAdaptor);

        return view;
    }
}
