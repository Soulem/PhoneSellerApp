package com.dynamicdevs.phonesellerapp.model.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Phone implements Parcelable {
    private String maker;
    private String model;
    private double price;
    private int phoneID;

    protected Phone(Parcel in){
        phoneID = in.readInt();
        price = in.readDouble();
        model = in.readString();
        maker = in.readString();
    }

    public Phone(int phoneID, double price, String model, String maker){
        this.phoneID = phoneID;
        this.price = price;
        this.model = model;
        this.maker = maker;
    }

    public Phone(double price, String model, String maker){
        this.price = price;
        this.model = model;
        this.maker = maker;
        phoneID = -1;
    }

    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };

    @Override
    public int describeContents() {return 0;};

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeInt(phoneID);
        parcel.writeDouble(price);
        parcel.writeString(model);
        parcel.writeString(maker);
    }

    public String getMaker(){return maker;}
    public String getModel(){return model;}
    public double getPrice(){return price;}
    public int getID(){return phoneID; }
}
