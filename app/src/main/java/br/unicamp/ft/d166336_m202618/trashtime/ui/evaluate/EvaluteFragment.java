package br.unicamp.ft.d166336_m202618.trashtime.ui.evaluate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.SerieList;
import br.unicamp.ft.d166336_m202618.trashtime.services.JsonReciver;
import br.unicamp.ft.d166336_m202618.trashtime.services.TmdbService;
import br.unicamp.ft.d166336_m202618.trashtime.ui.search.SearchFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluteFragment extends Fragment implements JsonReciver {

    private View view;
    private TmdbService tmdbService;

    private TextView name, overview, tmdb_grade, our_grade;
    private ImageView imageView;
    private RatingBar ratingBar;

    public EvaluteFragment() {
        tmdbService = new TmdbService("http://api.themoviedb.org/3",
                "5472dbfc461c85f5a29197d9c1fef7d5",
                "pt-br",
                EvaluteFragment.this
        );
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_evalute, container, false);

        name = view.findViewById(R.id.evalute_serie_name);
        overview = view.findViewById(R.id.evalute_serie_overview);
        tmdb_grade = view.findViewById(R.id.evalute_serie_tmdb_grade);
        our_grade = view.findViewById(R.id.evalute_serie_our_grade);
        imageView = view.findViewById(R.id.evalute_image);
        ratingBar = view.findViewById(R.id.evalute_serie_rating);

        return view;
    }

    @Override
    public void recieveJson(JSONObject jsonObject)  {

        JSONArray array = null;
        try {

                int id = Integer.parseInt(jsonObject.getString("id"));

                float grade = Float.parseFloat(jsonObject.getString("vote_average"));

                String name = jsonObject.getString("name");
                String overview = jsonObject.getString("name");

                SerieList serieList = new SerieList(id, , jsonObject.getString("poster_path"), grade);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
