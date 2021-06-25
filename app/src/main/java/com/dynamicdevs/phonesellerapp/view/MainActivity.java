package com.dynamicdevs.phonesellerapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dynamicdevs.phonesellerapp.databinding.ActivityMainBinding;
import com.dynamicdevs.phonesellerapp.model.data.Phone;
import com.dynamicdevs.phonesellerapp.presenter.PhonePresenter;
import com.dynamicdevs.phonesellerapp.presenter.PresenterContract;
import com.dynamicdevs.phonesellerapp.view.adapter.PhoneAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.dynamicdevs.phonesellerapp.util.Constants.PHONE_DATA_KEY;

public class MainActivity extends AppCompatActivity implements PhoneAdapter.PhoneDelegate, PresenterContract.ObjectView {
    private ActivityMainBinding binding;
    private PhoneAdapter phoneAdapter = new PhoneAdapter(this);
    private PresenterContract.Presenter phonePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.itemListview.setAdapter(phoneAdapter);
        phonePresenter = new PhonePresenter(this);
        phonePresenter.getRecords();

        binding.submitButton.setOnClickListener(view ->{
            double phonePrice = Double.valueOf(binding.priceInputTextview.getText().toString());
            if (0.0f >= phonePrice)
                Toast.makeText(this, "Price needs to be greater than $0.", Toast.LENGTH_LONG).show();
            else {
                String phoneModel = binding.modelInputTextview.getText().toString().trim();
                String phoneMaker = binding.makerInputTextview.getText().toString().trim();

                Phone newPhone = new Phone(phonePrice, phoneModel, phoneMaker);
                phonePresenter.insertNewRecord(newPhone);
            }
        });
    }

    @Override
    public void displayObject(List<Object> objs) {
        List<Phone> phones = new ArrayList<Phone>();

        for (Object obj : objs){
            phones.add((Phone)obj);
        }

        phoneAdapter.setPhoneList(phones);
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void selectPhone(Phone phone) {
        Intent intent = new Intent(this, PhoneDescriptionActivity.class);
        intent.putExtra(PHONE_DATA_KEY, phone);
        startActivity(intent);
    }
}