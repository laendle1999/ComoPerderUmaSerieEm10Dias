package br.unicamp.ft.d166336_m202618.trashtime.ui.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;

/**
 * A simple {@link Fragment} subclass.
 */
public class SerieFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText editText;


    public SerieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_serie, container, false);

        final SerieAdaptor serieAdaptor = new SerieAdaptor(
                new ArrayList(Arrays.asList(Serie.getAlunos(getContext())))
        );

        recyclerView = view.findViewById(R.id.recycler_view_serie);

        editText = view.findViewById(R.id.series_search);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                String search = editText.getText().toString();

                if (search.length() >= 3) {
                    serieAdaptor.setSeries(new ArrayList(Arrays.asList(Serie.search(getContext(), search))));
                    serieAdaptor.notifyDataSetChanged();
                } else {

                    serieAdaptor.setSeries(new ArrayList(Arrays.asList(Serie.getAlunos(getContext()))));
                    serieAdaptor.notifyDataSetChanged();
                }

                return false;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(serieAdaptor);

        return view;
    }
}
