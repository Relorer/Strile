package com.example.strile.database.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface Repository<M> {
    LiveData<List<M>> getAll();

    void insert(M model);

    void update(M model);

    void delete(M model);
}
