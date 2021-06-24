package com.dynamicdevs.phonesellerapp.presenter;

import android.content.Context;

import java.util.List;

public interface PresenterContract {
    interface Presenter {
        void getRecords();
        void insertNewRecord(Object obj);
        void deleteRecord(Object obj);
    }

    interface ObjectView {
        void displayObject(List<Object> objs);
        void displayError(String message);
        Context getContext();
    }
}
