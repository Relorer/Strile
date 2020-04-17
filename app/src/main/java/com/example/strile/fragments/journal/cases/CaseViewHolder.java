package com.example.strile.fragments.journal.cases;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strile.R;
import com.example.strile.database.entities.Case;

abstract class CaseViewHolder extends RecyclerView.ViewHolder {

    View view;
    Case current;

    CheckBox done;
    TextView name;
    TextView info;
    ImageView special;

    CaseViewHolder(@NonNull View itemView) {
        super(itemView);
        done = itemView.findViewById(R.id.checkbox_done);
        name = itemView.findViewById(R.id.text_name);
        info = itemView.findViewById(R.id.text_info);
        special = itemView.findViewById(R.id.image_special);
        view = itemView;
    }

    abstract void bind(Case current);
}