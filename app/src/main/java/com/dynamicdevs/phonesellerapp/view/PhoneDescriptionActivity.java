package com.dynamicdevs.phonesellerapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dynamicdevs.phonesellerapp.databinding.ActivityPhoneDescriptionBinding;
import com.dynamicdevs.phonesellerapp.model.data.Phone;
import com.dynamicdevs.phonesellerapp.presenter.PhonePresenter;
import com.dynamicdevs.phonesellerapp.presenter.PresenterContract;

import java.text.DecimalFormat;
import java.util.List;

import static com.dynamicdevs.phonesellerapp.util.Constants.PHONE_DATA_KEY;

public class PhoneDescriptionActivity extends AppCompatActivity implements PresenterContract.ObjectView {
    private ActivityPhoneDescriptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Phone phone = getIntent().getParcelableExtra(PHONE_DATA_KEY);

        if (null != phone){
            DecimalFormat df = new DecimalFormat("#.00");
            binding.makerTextview.setText(phone.getMaker());
            binding.modelTextview.setText(phone.getModel());
            binding.priceTextview.setText(df.format(phone.getPrice()));
        }

        binding.previousButton.setOnClickListener(view ->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        binding.deleteButton.setOnClickListener(view ->{
            PhonePresenter phonePresenter = new PhonePresenter(this);
            phonePresenter.deleteRecord(phone);
            phonePresenter.getRecords();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void displayObject(List<Object> objs) {

    }

    @Override
    public void displayError(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}