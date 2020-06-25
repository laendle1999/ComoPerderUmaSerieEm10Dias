package br.unicamp.ft.d166336_m202618.trashtime.ui.includes.recommendations;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import br.unicamp.ft.d166336_m202618.trashtime.R;
import br.unicamp.ft.d166336_m202618.trashtime.models.SerieList;

public class RecommendationsViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private View itemView;

    public RecommendationsViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemView = itemView;

        image = itemView.findViewById(R.id.list_serie_image);

    }

    public void bind (SerieList serie) {

        Picasso.with(itemView.getContext()).load(serie.getImage()).placeholder(R.drawable.image_default).into(image);
    }
}
