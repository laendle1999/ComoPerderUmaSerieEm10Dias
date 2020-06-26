package br.unicamp.ft.d166336_m202618.trashtime.ui.quiz;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.unicamp.ft.d166336_m202618.trashtime.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    private TextView quizResultResult, quizResultTitle;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        Bundle bundle = getArguments();

        QuizPackage quizPackage = (QuizPackage) bundle.getSerializable("quiz");

        quizResultResult = view.findViewById(R.id.quiz_result_result);

        String[] options = getActivity().getResources().getStringArray(R.array.quiz_result_texts);

        quizResultResult.setText(options[quizPackage.getResult()]);

        quizResultTitle = view.findViewById(R.id.quiz_result_result_title);

        String[] titles = getActivity().getResources().getStringArray(R.array.quiz_result_titles);

        quizResultTitle.setText(titles[quizPackage.getResult()]);

        imageView = view.findViewById(R.id.quiz_result_image);

        TypedArray images = getActivity().getResources().obtainTypedArray(R.array.quiz_result_images);

        //Log.i("testando meu filho", images[0] + "");
        imageView.setImageResource(images.getResourceId(quizPackage.getResult(), 0));
        //imageView.setImageResource(R.drawable.image_default);

        return view;
    }
}


