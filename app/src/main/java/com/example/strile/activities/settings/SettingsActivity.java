package com.example.strile.activities.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.strile.R;
import com.example.strile.sevice.presenter.PresenterManager;

public class SettingsActivity extends AppCompatActivity {

    private SettingsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            presenter = new SettingsPresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        setContentView(R.layout.activity_settings);

        TextView textTitle = findViewById(R.id.text_title);
        ImageView imageSpecialPurposeLeft = findViewById(R.id.image_special_purpose_button_left);
        View backButton = findViewById(R.id.special_purpose_button_left);

        textTitle.setText(R.string.settings);
        imageSpecialPurposeLeft.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                R.drawable.back_arrow, null));
        backButton.setOnClickListener(v -> {
            presenter.backButtonClicked();
        });
    }

    public static void start(Activity caller) {
        Intent intent = new Intent(caller, SettingsActivity.class);
        caller.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }
}
