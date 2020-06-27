package br.unicamp.ft.d166336_m202618.trashtime.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;
import br.unicamp.ft.d166336_m202618.trashtime.repositories.SerieRepository;
import br.unicamp.ft.d166336_m202618.trashtime.ui.evaluate.EvalutePackage;
import br.unicamp.ft.d166336_m202618.trashtime.ui.includes.nexteps.NextEpsAdaptor;
import br.unicamp.ft.d166336_m202618.trashtime.ui.includes.popular.PopularAdaptor;
import br.unicamp.ft.d166336_m202618.trashtime.ui.includes.toprelated.TopRelatedAdaptor;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView next_eps, top_rated, popular;
    private SerieRepository serieRepository;
    private NextEpsAdaptor newEpsAdaptor;
    private TopRelatedAdaptor topAdaptor;
    private PopularAdaptor popularAdaptor;
    private TextView no_next_eps;


    @Override
    public void onStart() {
        super.onStart();

        serieRepository = new SerieRepository(getContext());

        newEpsAdaptor.setSeries(serieRepository.seriesWithNextEp());
        newEpsAdaptor.notifyDataSetChanged();

        if (newEpsAdaptor.getItemCount() == 0) {
            no_next_eps.setVisibility(View.VISIBLE);
            next_eps.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        serieRepository.destroy();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        next_eps = root.findViewById(R.id.home_recycler_new_eps);
        top_rated = root.findViewById(R.id.home_recycler_top_rated);
        popular = root.findViewById(R.id.home_recycler_popular);
        no_next_eps = root.findViewById(R.id.home_no_next_eps_message);

        newEpsAdaptor = new NextEpsAdaptor(new ArrayList<Serie>());
        topAdaptor = new TopRelatedAdaptor();
        popularAdaptor = new PopularAdaptor();

        NextEpsAdaptor.SerieAdapterOnClickListner onClickListner = new NextEpsAdaptor.SerieAdapterOnClickListner() {

            @Override
            public void onItemClick(String name) {
                int id = newEpsAdaptor.filterSeries(name);

                Serie serie = serieRepository.find(id);

                Log.i("testando", serie.toString());

                EvalutePackage evalutePackage;

                evalutePackage = new EvalutePackage(serie.getId(), serie.getTmdb_code(), serie.getGrade());

                Bundle bundle = new Bundle();

                bundle.putSerializable("serie", evalutePackage);

                NavController navController = NavHostFragment.findNavController(HomeFragment.this);

                navController.navigate(R.id.evalute_fragment, bundle);

            }
        };

        newEpsAdaptor.setSerieAdapterOnClickListner(onClickListner);

        TopRelatedAdaptor.SerieAdapterOnClickListner onClickTopRelatedListner = new TopRelatedAdaptor.SerieAdapterOnClickListner() {

            @Override
            public void onItemClick(String name) {
                int code = topAdaptor.filterSeries(name);

                Serie serie = serieRepository.findByCode(code);

                EvalutePackage evalutePackage;

                if (serie == null) {

                    evalutePackage = new EvalutePackage(0, code);
                } else {

                    evalutePackage = new EvalutePackage(serie.getId(), code);
                }

                Bundle bundle = new Bundle();

                bundle.putSerializable("serie", evalutePackage);

                NavController navController = NavHostFragment.findNavController(HomeFragment.this);

                navController.navigate(R.id.evalute_fragment, bundle);

            }
        };

        topAdaptor.setSerieAdapterOnClickListner(onClickTopRelatedListner);

        PopularAdaptor.SerieAdapterOnClickListner onClickPopularListner = new PopularAdaptor.SerieAdapterOnClickListner() {

            @Override
            public void onItemClick(String name) {
                int code = popularAdaptor.filterSeries(name);

                Serie serie = serieRepository.findByCode(code);

                EvalutePackage evalutePackage;

                if (serie == null) {

                    evalutePackage = new EvalutePackage(0, code);
                } else {

                    evalutePackage = new EvalutePackage(serie.getId(), code);
                }

                Bundle bundle = new Bundle();

                bundle.putSerializable("serie", evalutePackage);

                NavController navController = NavHostFragment.findNavController(HomeFragment.this);

                navController.navigate(R.id.evalute_fragment, bundle);

            }
        };

        popularAdaptor.setSerieAdapterOnClickListner(onClickPopularListner);

        next_eps.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        top_rated.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        popular.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        next_eps.setAdapter(newEpsAdaptor);
        top_rated.setAdapter(topAdaptor);
        popular.setAdapter(popularAdaptor);

        return root;
    }
}
