package com.example.strile.ui.screens.main;

import com.example.strile.R;
import com.example.strile.data_firebase.models.User;
import com.example.strile.data_firebase.repositories.UserRepository;
import com.example.strile.infrastructure.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

import java.util.Objects;

public class MainPresenter extends BasePresenter<MainActivity> {

    private int itemId = R.id.item_journal;

    @Override
    protected void updateView() {
        new UserRepository().update();
        view().setVisibleFragment(itemId);
    }

    public void bottomNavigationSelectedItemChanged(int selectedID) {
        itemId = selectedID;
    }
}