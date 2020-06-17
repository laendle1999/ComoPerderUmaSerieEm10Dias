package br.unicamp.ft.d166336_m202618.trashtime.ui.search;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.services.JsonReciver;
import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;
import br.unicamp.ft.d166336_m202618.trashtime.services.TmdbService;
import br.unicamp.ft.d166336_m202618.trashtime.ui.list.SerieAdaptor;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements JsonReciver {

    private RecyclerView recyclerView;
    private EditText editText;
    private TmdbService tmdbService;
    private SearchAdaptor searchAdaptor;
    private  ProgressDialog progressDialog;


    public SearchFragment() {
        tmdbService = new TmdbService("http://api.themoviedb.org/3",
                "5472dbfc461c85f5a29197d9c1fef7d5",
                "pt-br",
                SearchFragment.this
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        progressDialog = new ProgressDialog(getContext());

        searchAdaptor = new SearchAdaptor();

        recyclerView = view.findViewById(R.id.recycler_view_search);

        editText = view.findViewById(R.id.search_search);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                String search = editText.getText().toString();

                if (search.length() >= 3) {

                    progressDialog.setMessage("Loading...");
                    progressDialog.show();

                    searchAdaptor.cleanSeries();

                    tmdbService.search(search);
                }

                return false;
            }
        });



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(searchAdaptor);

        return view;
    }

    @Override
    public void recieveJson(JSONObject jsonObject) {
        progressDialog.dismiss();

        Log.i("testaando", "recebeu json");

        searchAdaptor.setSeries(jsonObject);

        searchAdaptor.notifyDataSetChanged();
    }
}
