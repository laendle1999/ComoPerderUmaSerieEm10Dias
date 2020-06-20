package br.unicamp.ft.d166336_m202618.trashtime.ui.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;

public class SerieViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView name;
    private LinearLayout grade;
    private View itemView;

    public SerieViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemView = itemView;

        image = itemView.findViewById(R.id.list_serie_image);

        name = itemView.findViewById(R.id.list_serie_name);

        grade = itemView.findViewById(R.id.grade_linear_layout);
    }

    public void bind (Serie serie) {
        Picasso.with(itemView.getContext()).load(serie.getFormattedImage()).into(image);

        name.setText(serie.getName());

        grade.removeAllViews();

        for (int i = 1; i <= serie.getGrade(); i++) {
            ImageView imageView = new ImageView(itemView.getContext());

            imageView.setAdjustViewBounds(true);

            imageView.setImageResource(R.drawable.ic_trash);

            imageView.setLayoutParams(
                    new LinearLayout.LayoutParams(50,60)
            );

            grade.addView(imageView);
        }

        if (serie.getGrade() % 1.0 != 0) {
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
