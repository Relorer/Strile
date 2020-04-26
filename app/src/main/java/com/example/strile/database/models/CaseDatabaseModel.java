package com.example.strile.database.models;

import com.example.strile.sevice.call_back_interfaces.CompleteCallback;
import com.example.strile.sevice.call_back_interfaces.CompleteLoadCallback;

public interface CaseDatabaseModel {
    void loadCases(CompleteLoadCallback callback);

    void updateCase(com.example.strile.database.entities.CaseModel c, CompleteCallback callback);

    void deleteCase(com.example.strile.database.entities.CaseModel c, CompleteCallback callback);

    void insertCase(com.example.strile.database.entities.CaseModel c, CompleteCallback callback);
}
