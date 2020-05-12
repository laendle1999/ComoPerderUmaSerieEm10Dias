package br.unicamp.ft.d166336_m202618.trashtime.ui.tranding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.unicamp.ft.d166336_m202618.trashtime.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrandingFragment extends Fragment {

    public TrandingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tranding, container, false);
    }
}
