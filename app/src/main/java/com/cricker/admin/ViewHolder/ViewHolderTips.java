package com.cricker.admin.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cricker.admin.R;

public class ViewHolderTips extends RecyclerView.ViewHolder {

    View mView;
    private ClickListener mClickListener;
    public Button buttonSave;
    public EditText editTextTips;

    public ViewHolderTips(final View itemView) {
        super(itemView);

        mView = itemView;

        buttonSave = itemView.findViewById(R.id.save_btn);
        editTextTips = itemView.findViewById(R.id.tips);

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

    public void setTips(String title) {
        EditText squad_title = (EditText) mView.findViewById(R.id.tips);
        squad_title.setText(title);
    }

    public void setOnclickListener(ClickListener clickListener) {

        mClickListener = clickListener;

    }

    public interface ClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }


}
