package com.dynamicdevs.phonesellerapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dynamicdevs.phonesellerapp.databinding.PhoneItemLayoutBinding;
import com.dynamicdevs.phonesellerapp.model.data.Phone;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PhoneAdapter extends BaseAdapter {
    public interface PhoneDelegate{
        void selectPhone(Phone phone);
    }

    private List<Phone> phoneList = new ArrayList<Phone>();
    private PhoneDelegate phoneDelegate;
    private PhoneItemLayoutBinding binding;

    public PhoneAdapter(PhoneDelegate phoneDelegate) {this.phoneDelegate = phoneDelegate;}

    public void setPhoneList(List<Phone> phoneList) {this.phoneList = phoneList;}

    @Override
    public int getCount() {return phoneList.size();}

    @Override
    public Phone getItem(int i) {return phoneList.get(i);}

    @Override
    public long getItemId(int i) {return (long)phoneList.get(i).getID();}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        binding = PhoneItemLayoutBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        DecimalFormat df = new DecimalFormat("#.00");

        Phone phone = phoneList.get(i);
        binding.makerTextview.setText(phone.getMaker());
        binding.modelTextview.setText(phone.getModel());
        binding.priceTextview.setText(df.format(phone.getPrice()));

        binding.getRoot().setOnClickListener(v -> {
            phoneDelegate.selectPhone(phone);
        });
        return binding.getRoot();
    }
}
