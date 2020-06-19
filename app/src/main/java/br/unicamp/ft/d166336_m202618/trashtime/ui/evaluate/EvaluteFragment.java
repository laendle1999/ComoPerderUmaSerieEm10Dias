package br.unicamp.ft.d166336_m202618.trashtime.ui.evaluate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;
import br.unicamp.ft.d166336_m202618.trashtime.models.SerieList;
import br.unicamp.ft.d166336_m202618.trashtime.services.JsonReciver;
import br.unicamp.ft.d166336_m202618.trashtime.services.TmdbService;
import br.unicamp.ft.d166336_m202618.trashtime.ui.quiz.QuizPackage;
import br.unicamp.ft.d166336_m202618.trashtime.ui.search.SearchFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluteFragment extends Fragment implements JsonReciver {

    private View view;
    private TmdbService tmdbService;
    private Serie serie;

    private TextView name, overview, tmdb_grade, our_grade;
    private ImageView imageView;
    private RatingBar ratingBar;

    public EvaluteFragment() {
        tmdbService = new TmdbService("https://api.themoviedb.org/3",
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

        Bundle bundle = getArguments();

        EvalutePackage evalutePackage = (EvalutePackage) bundle.getSerializable("tmdb_code");

        tmdbService.loadData(evalutePackage.getId() + "");

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

        try {

            int tmdb_code = Integer.parseInt(jsonObject.getString("id"));

            float grade = Float.parseFloat(jsonObject.getString("vote_average"));

            String name = jsonObject.getString("name");

            String image = jsonObject.getString("poster_path");

            String overview = jsonObject.getString("overview");

            serie = new Serie(tmdb_code, name, image, 0);

            this.name.setText(name);

            this.overview.setText(overview);

            this.tmdb_grade.setText(String.valueOf(grade));

            Picasso.with(getContext()).load(serie.getImage()).into(imageView);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
