package com.example.strile.sevice.recycler_view_adapter.items.button_night_mode_selection;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.strile.R;
import com.example.strile.sevice.dialog.DialogNightModeOptions;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseHolder;

public class ButtonNightModeSelectionHolder extends BaseHolder<ButtonNightModeSelectionModel> {

    private final TextView info;

    public ButtonNightModeSelectionHolder(@NonNull View itemView,
                                          @NonNull OnModelChangedListener<ButtonNightModeSelectionModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        ImageView icon = itemView.findViewById(R.id.image_icon_settings);
        TextView name = itemView.findViewById(R.id.text_name);
        info = itemView.findViewById(R.id.text_info);

        icon.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.night_mod, null));
        icon.setColorFilter(itemView.getContext().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);

        name.setText(R.string.night_mode);
        info.setText(R.string.enabled);

        itemView.setOnClickListener(view -> openDialogNightModeOptions(model.getNightMode()));
    }

    @Override
    public void bind(ButtonNightModeSelectionModel model) {
        super.bind(model);
        changeTextInfo();
    }

    @SuppressLint("SwitchIntDef")
    private void changeTextInfo() {
        switch (model.getNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                info.setText(R.string.disabled);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                info.setText(R.string.enabled);
                break;
            default:
                info.setText(R.string.follow_system);
                break;
        }
    }

    private void openDialogNightModeOptions(@AppCompatDelegate.NightMode int nightMode) {
        final FragmentManager manager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
        final DialogNightModeOptions dialog = new DialogNightModeOptions(nightMode, result -> {
            onModelChangedListener.onChanged(model.setNightMode(result));
            changeTextInfo();
        });
        dialog.show(manager, "NightMode");
    }
}
