package br.unicamp.ft.d166336_m202618.trashtime.ui.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.unicamp.ft.d166336_m202618.trashtime.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    private TextView quizResultName;
    private TextView quizResultSigno;
    private TextView quizResultSleep;
    private TextView quizResultFood;
    private TextView quizResultWorstFinal;
    private TextView quizResultCooker;
    private TextView quizResultResult;
    private TextView quizResultTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        Bundle bundle = getArguments();

        QuizPackage quizPackage = (QuizPackage) bundle.getSerializable("quiz");

        quizResultName = view.findViewById(R.id.quiz_result_name);

        quizResultName.setText(quizPackage.getName());

        quizResultSigno = view.findViewById(R.id.quiz_result_sleep);

        quizResultSigno.setText(quizPackage.getSleep());

        quizResultSigno = view.findViewById(R.id.quiz_result_food);

        quizResultSigno.setText(quizPackage.getFood());

        quizResultSigno = view.findViewById(R.id.quiz_result_worstfinal);

        quizResultSigno.setText(quizPackage.getWorstFinal());

        quizResultSigno = view.findViewById(R.id.quiz_result_signo);

        quizResultSigno.setText(quizPackage.getSigno());

        quizResultSigno = view.findViewById(R.id.quiz_result_cooker);

        quizResultSigno.setText(quizPackage.getCooker());

        quizResultResult = view.findViewById(R.id.quiz_result_result);

        String[] options = getActivity().getResources().getStringArray(R.array.quiz_result_texts);

        quizResultResult.setText(options[quizPackage.getResult()]);

        quizResultTitle = view.findViewById(R.id.quiz_result_result_title);

        String[] titles = getActivity().getResources().getStringArray(R.array.quiz_result_titles);

        quizResultTitle.setText(titles[quizPackage.getResult()]);

        return view;
    }
}


