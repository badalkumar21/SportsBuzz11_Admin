package com.cricker.admin.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cricker.admin.R;
import com.squareup.picasso.Picasso;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public View mView;
    private ClickListener mClickListener;


    public TextView text_edit;
    public TextView text_delete;

    public MyViewHolder(final View itemView) {
        super(itemView);

        mView = itemView;

        text_edit = itemView.findViewById(R.id.text_edit);
        text_delete = itemView.findViewById(R.id.text_delete);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });

    }

    public void setTitle(String title) {
        TextView post_title = (TextView) mView.findViewById(R.id.post_title1);
        post_title.setText(title);
    }

    public void setDesc(String desc) {
        TextView post_desc = (TextView) mView.findViewById(R.id.post_desc1);
        post_desc.setText(desc);
    }

    public void setImage1(Context ctx, String image1) {
        ImageView post_image = (ImageView) mView.findViewById(R.id.post_image1);
        Picasso.with(ctx).load(image1).resize(100, 100).onlyScaleDown().into(post_image);
    }

    public void setImage2(Context ctx, String image2) {
        ImageView post_image = (ImageView) mView.findViewById(R.id.post_image2);
        Picasso.with(ctx).load(image2).resize(100, 100).onlyScaleDown().into(post_image);
    }

    public void setImage1Def(Context ctx) {
        ImageView post_image = (ImageView) mView.findViewById(R.id.post_image1);
        Picasso.with(ctx).load(R.drawable.def_icon).resize(100, 100).onlyScaleDown().into(post_image);
    }

    public void setImage2Def(Context ctx) {
        ImageView post_image = (ImageView) mView.findViewById(R.id.post_image2);
        Picasso.with(ctx).load(R.drawable.def_icon).resize(100, 100).onlyScaleDown().into(post_image);
    }


    public void setAds(Context ctx, String ads) {
        ImageView adImage = (ImageView) mView.findViewById(R.id.adImage);
        Picasso.with(ctx).load(ads).into(adImage);
    }

    public void setT1(String t1) {
        TextView post_t1 = (TextView) mView.findViewById(R.id.team1_name);
        post_t1.setText(t1);
    }

    public void setT2(String t2) {
        TextView post_t2 = (TextView) mView.findViewById(R.id.team2_name);
        post_t2.setText(t2);
    }

    public void setStatus_r(String status_r) {
        TextView status = (TextView) mView.findViewById(R.id.status_upcoming1);
        status.setText(status_r);
    }

    public void setStatus_g(String status_g) {
        TextView status = (TextView) mView.findViewById(R.id.status_available1);
        status.setText(status_g);
    }

    public void setOnclickListener(ClickListener clickListener) {

        mClickListener = clickListener;

    }

    public interface ClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }


}
