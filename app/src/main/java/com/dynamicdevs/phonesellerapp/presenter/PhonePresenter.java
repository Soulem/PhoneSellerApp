package com.dynamicdevs.phonesellerapp.presenter;

import android.content.Context;

import com.dynamicdevs.phonesellerapp.model.data.Phone;
import com.dynamicdevs.phonesellerapp.model.database.PhoneDBHelper;

import java.util.ArrayList;
import java.util.List;

public class PhonePresenter implements PresenterContract.Presenter {
    private PresenterContract.ObjectView view;
    private PhoneDBHelper dbHelper;

    public PhonePresenter(PresenterContract.ObjectView view){
        this.view = view;
        dbHelper = new PhoneDBHelper(view.getContext());
    }

    @Override
    public void getRecords(){
        new Thread(){
            @Override
            public void run(){
                super.run();
                try{
                    List<Object> objs = new ArrayList<Object>();
                    for (Phone p : dbHelper.getAllPhones()){
                        objs.add((Object)p);
                    }
                    view.displayObject(objs);
                }catch (Exception e){
                    view.displayError(e.getMessage());
                }
            }
        }.start();
    }

    @Override
    public void insertNewRecord(Object obj){
        new Thread(){
            @Override
            public void run(){
                super.run();
                try{
                    dbHelper.insertPhone((Phone)obj);
                    getRecords();
                }catch(Exception e){
                    view.displayError(e.getMessage());
                }
            }
        }.start();
    }

    @Override
    public void deleteRecord(Object obj){
        new Thread(){
            @Override
            public void run(){
                super.run();
                try{
                    dbHelper.deletePhone((Phone)obj);
                    getRecords();
                }catch(Exception e){
                    view.displayError(e.getMessage());
                }
            }
        }.start();
    }
}
