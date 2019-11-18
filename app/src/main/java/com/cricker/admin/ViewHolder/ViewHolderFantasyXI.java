package com.cricker.admin.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cricker.admin.R;
import com.squareup.picasso.Picasso;

public class ViewHolderFantasyXI extends RecyclerView.ViewHolder {

    View mView;
    private ClickListener mClickListener;
    public CheckBox checkBox;

    public ViewHolderFantasyXI(final View itemView) {
        super(itemView);

        mView = itemView;
        checkBox = itemView.findViewById(R.id.checkBox);
//
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mClickListener.onItemClick(v, getAdapterPosition());
//            }
//        });
//
//        itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                mClickListener.onItemLongClick(v, getAdapterPosition());
//                return true;
//            }
//        });

    }

    public void setName(String title) {
        TextView fantasy_xi_title = (TextView) mView.findViewById(R.id.fantasy_xi_name);
        fantasy_xi_title.setText(title);
    }

    public void setImage(Context ctx, String image) {
        ImageView fantasy_xi_image = (ImageView) mView.findViewById(R.id.fantasy_xi_image);
        Picasso.with(ctx).load(image).resize(100, 100).onlyScaleDown().into(fantasy_xi_image);
    }

    public void setOnclickListener(ClickListener clickListener) {

        mClickListener = clickListener;

    }

    public interface ClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }


}
