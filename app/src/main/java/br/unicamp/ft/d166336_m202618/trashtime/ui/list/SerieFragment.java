package br.unicamp.ft.d166336_m202618.trashtime.ui.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.d166336_m202618.trashtime.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SerieFragment extends Fragment {

    private RecyclerView recyclerView;


    public SerieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_serie, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_serie);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final SerieAdaptor serieAdaptor = new SerieAdaptor(
                new ArrayList(Arrays.asList(Serie.getAlunos(getContext())))
        );

        recyclerView.setAdapter(serieAdaptor);

        return view;
    }
}
