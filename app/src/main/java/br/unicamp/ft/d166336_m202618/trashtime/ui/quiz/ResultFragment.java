package br.unicamp.ft.d166336_m202618.trashtime.ui.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.unicamp.ft.d166336_m202618.trashtime.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    private TextView quizResultName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        Bundle bundle = getArguments();

        QuizPackage quizPackage = (QuizPackage) bundle.getSerializable("quiz");

        quizResultName = view.findViewById(R.id.quiz_result_name);

        quizResultName.setText(quizPackage.getName());

        return view;
    }
}
