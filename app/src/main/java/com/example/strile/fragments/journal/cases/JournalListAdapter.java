package com.example.strile.fragments.journal.cases;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.strile.R;
import com.example.strile.database.entities.Case;
import com.example.strile.sevice.event_handler_interfaces.OnCheckedCaseChangeListener;
import com.example.strile.sevice.event_handler_interfaces.OnClickCaseListener;

import java.util.List;

public class JournalListAdapter extends RecyclerView.Adapter<JournalListAdapter.CaseViewHolder> {

    private OnClickCaseListener onClickCaseListener;
    private OnCheckedCaseChangeListener onCheckedCaseChangeListener;

    private SortedList<Case> sortedList;

    JournalListAdapter() {
        sortedList = new SortedList<>(Case.class, new SortedList.Callback<Case>() {
            @Override
            public int compare(Case o1, Case o2) {
                return 0;
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Case oldItem, Case newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Case item1, Case item2) {
                return item1.getId() == item2.getId();
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public CaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CaseViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journal, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CaseViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    void setSortedList(List<Case> list) {
        if (list == null) sortedList.clear();
        else sortedList.replaceAll(list);
    }

    void setOnClickItemListener(OnClickCaseListener listener) {
        onClickCaseListener = listener;
    }

    void setOnCheckedItemChangeListener(OnCheckedCaseChangeListener listener) {
        onCheckedCaseChangeListener = listener;
    }

    class CaseViewHolder extends RecyclerView.ViewHolder {
        View view;

        CheckBox done;
        TextView name;
        TextView info;
        ImageView special;

        Case current;

        CaseViewHolder(@NonNull View itemView) {
            super(itemView);
            done = itemView.findViewById(R.id.checkbox_done);
            name = itemView.findViewById(R.id.text_name);
            info = itemView.findViewById(R.id.text_info);
            special = itemView.findViewById(R.id.image_special);
            view = itemView;
            done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    current.setState(isChecked);
                    onCheckedCaseChangeListener.onCheckedChanged(current, isChecked);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickCaseListener.onClick(current);
                }
            });
        }

        void bind(Case current) {
            this.current = current;
            name.setText(current.getName());
            if (current.infoImportant()) info.setTextColor(view.getContext().getColor(R.color.colorRed));
            else info.setTextColor(view.getContext().getColor(R.color.colorLightGray));
            info.setText(current.getInfo());
            done.setChecked(current.completed());
            if (current.isGoalTime())
                special.setImageDrawable(ResourcesCompat.getDrawable(view.getResources(), R.drawable.time_goal, null));
            else
                special.setImageDrawable(null);
        }
    }

}
