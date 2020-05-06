package com.example.strile.sevice.recycler_view_adapter.models;

public interface CaseModel {
    String getName();
    boolean isComplete();
    CaseModel setState(boolean state);
}
