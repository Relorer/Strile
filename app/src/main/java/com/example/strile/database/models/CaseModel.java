package com.example.strile.database.models;

import com.example.strile.database.entities.Case;
import com.example.strile.sevice.call_back_interfaces.CompleteCallback;
import com.example.strile.sevice.call_back_interfaces.CompleteLoadCallback;

public interface CaseModel {
    void loadCases(CompleteLoadCallback callback);

    void updateCase(Case c, CompleteCallback callback);

    void deleteCase(Case c, CompleteCallback callback);

    void insertCase(Case c, CompleteCallback callback);
}
