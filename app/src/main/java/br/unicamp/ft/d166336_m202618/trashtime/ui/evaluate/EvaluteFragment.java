package br.unicamp.ft.d166336_m202618.trashtime.ui.evaluate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;
import br.unicamp.ft.d166336_m202618.trashtime.models.SerieList;
import br.unicamp.ft.d166336_m202618.trashtime.repositories.SerieRepository;
import br.unicamp.ft.d166336_m202618.trashtime.services.JsonReciver;
import br.unicamp.ft.d166336_m202618.trashtime.services.TmdbService;
import br.unicamp.ft.d166336_m202618.trashtime.ui.quiz.QuizPackage;
import br.unicamp.ft.d166336_m202618.trashtime.ui.recommendations.RecommendationsAdaptor;
import br.unicamp.ft.d166336_m202618.trashtime.ui.search.SearchAdaptor;
import br.unicamp.ft.d166336_m202618.trashtime.ui.search.SearchFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluteFragment extends Fragment implements JsonReciver {

    private View view;
    private TmdbService tmdbService;
    private Serie serie;
    private SerieRepository serieRepository;

    private TextView name, overview, tmdb_grade, our_grade;
    private ImageView imageView;
    private RatingBar ratingBar;

    private LinearLayout add_btn, upd_btn;

    private Button add_serie, change_serie, delete_serie;

    private RecyclerView recyclerView;
    private RecommendationsAdaptor recommendationsAdaptor;

    public EvaluteFragment() {

        serie = new Serie();

        tmdbService = new TmdbService("https://api.themoviedb.org/3",
                "5472dbfc461c85f5a29197d9c1fef7d5",
                "pt-br",
                EvaluteFragment.this
        );
    }


    @Override
    public void onStart() {
        super.onStart();

        serieRepository = new SerieRepository(getContext());

        if (serie.getId() != 0) {
            serie = serieRepository.find(serie.getId());

            ratingBar.setRating(serie.getGrade());

            Log.i("testando aqui viu", serie.getFormattedDate("dd/MM/yyyy"));
        }

    }

    @Override
    public void onStop() {
        super.onStop();

        serieRepository.destroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_evalute, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_recommendations);

        Bundle bundle = getArguments();

        EvalutePackage evalutePackage = (EvalutePackage) bundle.getSerializable("serie");

        tmdbService.loadData(evalutePackage.getTmdbCode() + "");

        recommendationsAdaptor = new RecommendationsAdaptor(evalutePackage.getTmdbCode() + "");

        name = view.findViewById(R.id.evalute_serie_name);
        overview = view.findViewById(R.id.evalute_serie_overview);
        tmdb_grade = view.findViewById(R.id.evalute_serie_tmdb_grade);
        our_grade = view.findViewById(R.id.evalute_serie_our_grade);
        imageView = view.findViewById(R.id.evalute_image);
        ratingBar = view.findViewById(R.id.evalute_serie_rating);

        add_btn = view.findViewById(R.id.evalute_add_serie_layout);
        upd_btn = view.findViewById(R.id.evalute_update_serie_layout);


        if (evalutePackage.getId() != 0) {

            serie.setId(evalutePackage.getId());

            add_btn.setVisibility(LinearLayout.GONE);

            upd_btn.setVisibility(LinearLayout.VISIBLE);

            ratingBar.setRating(evalutePackage.getGrade());


        }

        add_serie = view.findViewById(R.id.evalute_add_serie);
        change_serie = view.findViewById(R.id.evalute_change_serie);
        delete_serie = view.findViewById(R.id.evalute_remove_serie);

        add_serie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float grade = ratingBar.getRating();

                serie.setGrade(grade);

                int id = serieRepository.insertOrChangeData(serie);

                serie.setId(id);

                add_btn.setVisibility(LinearLayout.GONE);

                upd_btn.setVisibility(LinearLayout.VISIBLE);
            }
        });

        change_serie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float grade = ratingBar.getRating();

                serie.setGrade(grade);

                serieRepository.insertOrChangeData(serie);

            }
        });

        delete_serie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                serieRepository.removeData(serie.getId());

                upd_btn.setVisibility(LinearLayout.GONE);

                add_btn.setVisibility(LinearLayout.VISIBLE);
            }
        });

        RecommendationsAdaptor.SerieAdapterOnClickListner onClickListner = new RecommendationsAdaptor.SerieAdapterOnClickListner() {

            @Override
            public void onItemClick(String name) {
                int code = recommendationsAdaptor.filterSeries(name);

                Serie serie = serieRepository.findByCode(code);

                EvalutePackage evalutePackage;

                if (serie == null) {

                    evalutePackage = new EvalutePackage(0, code);
                } else {

                    evalutePackage = new EvalutePackage(serie.getId(), code);
                }

                Bundle bundle = new Bundle();

                bundle.putSerializable("serie", evalutePackage);

                NavController navController = NavHostFragment.findNavController(EvaluteFragment.this);

                navController.navigate(R.id.evalute_fragment, bundle);

            }
        };

        recommendationsAdaptor.setSerieAdapterOnClickListner(onClickListner);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        recyclerView.setAdapter(recommendationsAdaptor);

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

            if (jsonObject.has("next_episode_to_air") && !jsonObject.isNull("next_episode_to_air")) {

                String next_ep = jsonObject.getJSONObject("next_episode_to_air").getString("air_date");
                serie.setDate(next_ep, "yyy-MM-dd");
            }

            serie.setGrade(grade);
            serie.setName(name);
            serie.setImage(image);
            serie.setTmdb_code(tmdb_code);
            serie.setGrade(0);

            this.name.setText(name);

            this.overview.setText(overview);

            this.tmdb_grade.setText(String.valueOf(grade));

            Picasso.with(getContext()).load(serie.getFormattedImage()).into(imageView);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
