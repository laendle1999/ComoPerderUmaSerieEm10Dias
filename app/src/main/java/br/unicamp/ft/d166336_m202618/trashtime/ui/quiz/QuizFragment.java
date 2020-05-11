package br.unicamp.ft.d166336_m202618.trashtime.ui.quiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unicamp.ft.d166336_m202618.trashtime.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {

    private Button submit;
    private EditText name;
    private Spinner signos, worstEnds;
    private CheckBox foodPopcorn, foodPizza, foodLaunch, foodChocolate, foodCaviar, foodFries, foodScoobySnacks, foodNone;
    private RadioGroup sleep, thanos, cooker;
    private Map<Integer, RadioButton> cookerMap, thanosMap, sleepMap;

    private List food = new ArrayList();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_quiz, container, false);

        submit = view.findViewById(R.id.quiz_submit);

        name = view.findViewById(R.id.quiz_name);

        signos = view.findViewById(R.id.quiz_signo);
        worstEnds = view.findViewById(R.id.quiz_worst_final);

        foodPopcorn = view.findViewById(R.id.quiz_food_popcorn);
        foodPopcorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        foodPizza = view.findViewById(R.id.quiz_food_pizza);
        foodPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        foodLaunch = view.findViewById(R.id.quiz_food_lanch);
        foodLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        foodChocolate = view.findViewById(R.id.quiz_food_chocolate);
        foodChocolate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        foodCaviar = view.findViewById(R.id.quiz_food_caviar);
        foodCaviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        foodFries = view.findViewById(R.id.quiz_food_fries);
        foodFries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        foodScoobySnacks = view.findViewById(R.id.quiz_food_scoobySnack);
        foodScoobySnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        foodNone = view.findViewById(R.id.quiz_food_none);
        foodNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        sleep = view.findViewById(R.id.quiz_sleep);
        thanos = view.findViewById(R.id.quiz_thanos);
        cooker = view.findViewById(R.id.quiz_cooker);

        cookerMap = new HashMap<>();
        cookerMap.put(R.id.quiz_cooker_option_churras, (RadioButton) view.findViewById(R.id.quiz_cooker_option_churras));
        cookerMap.put(R.id.quiz_cooker_option_confeiteiro, (RadioButton) view.findViewById(R.id.quiz_cooker_option_confeiteiro));
        cookerMap.put(R.id.quiz_cooker_option_entrada, (RadioButton) view.findViewById(R.id.quiz_cooker_option_entrada));
        cookerMap.put(R.id.quiz_cooker_option_miojo, (RadioButton) view.findViewById(R.id.quiz_cooker_option_miojo));
        cookerMap.put(R.id.quiz_cooker_option_nada, (RadioButton) view.findViewById(R.id.quiz_cooker_option_nada));
        cookerMap.put(R.id.quiz_cooker_option_se_acha, (RadioButton) view.findViewById(R.id.quiz_cooker_option_se_acha));
        cookerMap.put(R.id.quiz_cooker_option_masterchef, (RadioButton) view.findViewById(R.id.quiz_cooker_option_masterchef));

        sleepMap = new HashMap<>();
        sleepMap.put(R.id.quiz_sleep_option_bed, (RadioButton) view.findViewById(R.id.quiz_sleep_option_bed));
        sleepMap.put(R.id.quiz_sleep_option_bus, (RadioButton) view.findViewById(R.id.quiz_sleep_option_bus));
        sleepMap.put(R.id.quiz_sleep_option_office, (RadioButton) view.findViewById(R.id.quiz_sleep_option_office));
        sleepMap.put(R.id.quiz_sleep_option_sofa, (RadioButton) view.findViewById(R.id.quiz_sleep_option_sofa));

        thanosMap = new HashMap<>();
        thanosMap.put(R.id.quiz_thanos_option_no, (RadioButton) view.findViewById(R.id.quiz_thanos_option_no));
        thanosMap.put(R.id.quiz_thanos_option_who, (RadioButton) view.findViewById(R.id.quiz_thanos_option_who));
        thanosMap.put(R.id.quiz_thanos_option_yes, (RadioButton) view.findViewById(R.id.quiz_thanos_option_yes));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNewFragment(v);
            }
        });

        return view;
    }

    public void onCheckBoxClicked(View view) {
        boolean checked = ((CheckBox)view).isChecked();
        String value = ((CheckBox)view).getText().toString();

        if(checked) {
            food.add(value);
        }else{
            food.remove(value);
        }

    }

    public void callNewFragment (View view) {

        QuizPackage quizPackage = new QuizPackage();

        quizPackage.setName(name.getText().toString());

        quizPackage.setSigno(signos.getSelectedItem().toString());

        quizPackage.setWorstFinal(worstEnds.getSelectedItem().toString());

        quizPackage.setFood(food);

        Log.i("testando", (cooker.getCheckedRadioButtonId() == R.id.quiz_cooker_option_churras) ? "sim" : "nao");

        RadioButton selectedFood = view.findViewById(cooker.getCheckedRadioButtonId());

        Log.i("testando", (selectedFood == null) ? "sim" : "nao");

        Log.i("testando", (cookerMap.get(cooker.getCheckedRadioButtonId()).getText().toString()));

        quizPackage.setCooker(cookerMap.get(cooker.getCheckedRadioButtonId()).getText().toString());

        quizPackage.setSleep(sleepMap.get(sleep.getCheckedRadioButtonId()).getText().toString());

        quizPackage.setThanos(thanosMap.get(thanos.getCheckedRadioButtonId()).getText().toString());

        //Intent intent = new Intent(this.getActivity(), ResultFragment.class);

        //intent.putExtra("quiz", quizPackage);

        //startActivity(intent);

        Bundle bundle = new Bundle();

        bundle.putSerializable("quiz", quizPackage);

        NavController navController = NavHostFragment.findNavController(QuizFragment.this);
        navController.navigate(R.id.result_fragment, bundle);
    }
}
