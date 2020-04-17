package com.example.strile.fragments.journal.cases;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.strile.R;
import com.example.strile.database.entities.Case;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.entities.Task;
import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.event_handler_interfaces.OnCheckedCaseChangeListener;
import com.example.strile.sevice.event_handler_interfaces.OnCheckedButtonHidingChangeListener;
import com.example.strile.sevice.event_handler_interfaces.OnClickCaseListener;
import com.example.strile.sevice.recycler_view_adapter.ButtonHidingModel;
import com.example.strile.sevice.recycler_view_adapter.ItemModel;
import com.example.strile.sevice.recycler_view_adapter.RendererRecyclerViewAdapter;
import com.example.strile.sevice.recycler_view_adapter.ViewRenderer;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

class JournalListAdapter extends RendererRecyclerViewAdapter {

    private OnClickCaseListener onClickCaseListener;
    private OnCheckedCaseChangeListener onCheckedCaseChangeListener;
    private OnCheckedButtonHidingChangeListener onCheckedButtonHidingChangeListener;

    JournalListAdapter() {
        registerRenderer(new ViewRenderer<Habit, HabitViewHolder>() {

            @Override
            public void bindView(@NonNull Habit model, @NonNull HabitViewHolder holder) {
                holder.bind(model);
            }

            @NonNull
            @Override
            public HabitViewHolder createViewHolder(@Nullable ViewGroup parent) {
                return new HabitViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journal, parent, false));
            }

            @Override
            public int getType() {
                return ItemModel.HABIT_TYPE;
            }
        });
        registerRenderer(new ViewRenderer<Task, TaskViewHolder>() {

            @Override
            public void bindView(@NonNull Task model, @NonNull TaskViewHolder holder) {
                holder.bind(model);
            }

            @NonNull
            @Override
            public TaskViewHolder createViewHolder(@Nullable ViewGroup parent) {
                return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journal, parent, false));
            }

            @Override
            public int getType() {
                return ItemModel.TASK_TYPE;
            }
        });
        registerRenderer(new ViewRenderer<ButtonHidingModel, ButtonHidingHolder>() {

            @Override
            public void bindView(@NonNull ButtonHidingModel model, @NonNull ButtonHidingHolder holder) {
                holder.bind(model);
            }

            @NonNull
            @Override
            public ButtonHidingHolder createViewHolder(@Nullable ViewGroup parent) {
                return new ButtonHidingHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.button_hiding, parent, false));
            }

            @Override
            public int getType() {
                return ItemModel.BUTTON_HIDING;
            }
        });
    }

    @Override
    protected SortedList<ItemModel> getSortedItems() {
        return new SortedList<>(ItemModel.class, new SortedList.Callback<ItemModel>() {
            @Override
            public int compare(ItemModel o1, ItemModel o2) {
                if (o1 instanceof ButtonHidingModel) return 1;
                if (o2 instanceof ButtonHidingModel) return -1;
                Case c1 = (Case) o1;
                Case c2 = (Case) o2;

                if (!c2.completed() && c1.completed()) {
                    return 1;
                }
                if (c2.completed() && !c1.completed()) {
                    return -1;
                }
                return (int) (c2.getId() - c1.getId());
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(ItemModel oldItem, ItemModel newItem) {
                return oldItem.getId() == newItem.getId() && oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(ItemModel item1, ItemModel item2) {
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

    void setOnClickItemListener(OnClickCaseListener onClickCaseListener) {
        this.onClickCaseListener = onClickCaseListener;
    }

    void setOnCheckedItemChangeListener(OnCheckedCaseChangeListener onCheckedCaseChangeListener) {
        this.onCheckedCaseChangeListener = onCheckedCaseChangeListener;
    }

    void setOnCheckedButtonHidingChangeListener(OnCheckedButtonHidingChangeListener onCheckedButtonHidingChangeListener) {
        this.onCheckedButtonHidingChangeListener = onCheckedButtonHidingChangeListener;
    }

    class HabitViewHolder extends CaseViewHolder {
        boolean first;
        HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        current.setState(isChecked);
                        if (onCheckedCaseChangeListener != null) {
                            onCheckedCaseChangeListener.onCheckedChanged(current, isChecked);
                            bind(current);
                        }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickCaseListener != null)
                        onClickCaseListener.onClick(current);
                }
            });
        }

        @Override
        void bind(Case current) {
            this.current = current;
            name.setText(current.getName());
            done.setChecked(current.completed());
            info.setText(((Habit) current).getStreak() + " day streak");
            if (((Habit) current).getGoalTimeSeconds() == 0) special.setImageDrawable(null);
            else
                special.setImageDrawable(ResourcesCompat.getDrawable(view.getResources(), R.drawable.time_goal, null));
        }
    }

    class TaskViewHolder extends CaseViewHolder {
        TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    current.setState(isChecked);
                    if (onCheckedCaseChangeListener != null)
                        onCheckedCaseChangeListener.onCheckedChanged(current, isChecked);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickCaseListener != null)
                        onClickCaseListener.onClick(current);
                }
            });
        }

        @Override
        void bind(Case current) {
            this.current = current;
            Task t = (Task) current;
            name.setText(current.getName());
            done.setChecked(current.completed());
            if (new Date().getTime() >= t.getDeadline() && t.isDeadline())
                info.setTextColor(view.getContext().getColor(R.color.colorRed));
            else
                info.setTextColor(view.getContext().getColor(R.color.colorLightGray));
            if (!t.isDeadline()) info.setText("No deadline");
            else {
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(t.getDeadline());
                info.setText(c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                        + " " + c.get(Calendar.DAY_OF_MONTH) + ", " + c.get(Calendar.YEAR));
            }
        }
    }

    class ButtonHidingHolder extends RecyclerView.ViewHolder {
        private ButtonHidingModel button;
        private View view;
        private TextView text;
        private TextView count;
        private CheckBox checkBox;

        ButtonHidingHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            text = view.findViewById(R.id.text_state);
            count = view.findViewById(R.id.text_count);
            checkBox = view.findViewById(R.id.checkbox_done);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button.setChecked(!button.isChecked());
                    setCheckedButtonHiding(button.isChecked());
                    if (onCheckedButtonHidingChangeListener != null)
                        onCheckedButtonHidingChangeListener.onCheckedChanged(button, button.isChecked());
                }
            });
        }

        void bind(ButtonHidingModel button) {
            this.button = button;
            text.setText(button.getText());
            count.setText(String.valueOf(button.getCount()));
            checkBox.setChecked(button.isChecked());
            setCheckedButtonHiding(button.isChecked());
        }

        void setCheckedButtonHiding(boolean checked) {
            checkBox.setChecked(checked);
            if (checked) {
                text.setText("Show Сompleted");
            } else {
                text.setText("Hide Сompleted");
            }
        }

    }

}
