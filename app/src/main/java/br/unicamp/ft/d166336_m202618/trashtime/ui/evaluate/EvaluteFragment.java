package br.unicamp.ft.d166336_m202618.trashtime.ui.evaluate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import br.unicamp.ft.d166336_m202618.trashtime.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluteFragment extends Fragment {

    private View view;

    private TextView name, overview, tmdb_grade, our_grade;
    private ImageView imageView;
    private RatingBar ratingBar;

    public EvaluteFragment() {
        // Required empty public constructor
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
}
