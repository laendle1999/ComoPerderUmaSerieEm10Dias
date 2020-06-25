package br.unicamp.ft.d166336_m202618.trashtime.ui.search;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;
import br.unicamp.ft.d166336_m202618.trashtime.models.SerieList;

public class SearchViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView name;
    private LinearLayout grade;
    private View itemView;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemView = itemView;

        image = itemView.findViewById(R.id.list_serie_image);

        name = itemView.findViewById(R.id.list_serie_name);

        grade = itemView.findViewById(R.id.grade_linear_layout);
    }

    public void bind (SerieList serie) {

        Picasso.with(itemView.getContext()).load(serie.getImage()).placeholder(R.drawable.image_default).into(image);

        name.setText(serie.getName());

        grade.removeAllViews();

        float serie_grade = serie.getGrade() / 2;

        float our_grade = 5 - serie_grade;

        for (int i = 1; i <= our_grade; i++) {
            ImageView imageView = new ImageView(itemView.getContext());

            imageView.setAdjustViewBounds(true);

            imageView.setImageResource(R.drawable.ic_trash);

            imageView.setLayoutParams(
                    new LinearLayout.LayoutParams(50,60)
            );

            grade.addView(imageView);
        }

        if (our_grade % 1.0 != 0) {
            ImageView imageView = new ImageView(itemView.getContext());

            imageView.setAdjustViewBounds(true);

            imageView.setImageResource(R.drawable.ic_half_trash);

            imageView.setLayoutParams(
                    new LinearLayout.LayoutParams(50, 60)
            );

            grade.addView(imageView);
        }
    }
}
