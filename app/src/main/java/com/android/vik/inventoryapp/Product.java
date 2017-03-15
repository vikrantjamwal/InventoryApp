package com.android.vik.inventoryapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    public static final String PRODUCT_BUNDLE_KEY = "productBundleKey";
    public static final String PRODUCT_NAME_KEY = "productNameKey";
    private String name;
    private String supplierPhoneNumber;
    private int imageResourceId;
    private int numDollars;
    private int numCents;
    private int quantity;

    public Product(int numDollars, int numCents, int quantity, String name, String supplierPhoneNumber,
                   int imageResourceId) {
        this.setName(name);
        this.setNumCents(numCents);
        this.setNumDollars(numDollars);
        this.setQuantity(quantity);
        this.setSupplierPhoneNumber(supplierPhoneNumber);
        this.setImageResourceId(imageResourceId);
    }

    protected Product(Parcel in) {
        name = in.readString();
        supplierPhoneNumber = in.readString();
        imageResourceId = in.readInt();
        numDollars = in.readInt();
        numCents = in.readInt();
        quantity = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getNumDollars() {
        return numDollars;
    }

    public void setNumDollars(int numDollars) {
        this.numDollars = numDollars;
    }

    public int getNumCents() {
        return numCents;
    }

    public void setNumCents(int numCents) {
        this.numCents = numCents;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getSupplierPhoneNumberForIntentCall() {
        StringBuilder stringBuilder = new StringBuilder("tel:");
        stringBuilder.append(supplierPhoneNumber);
        return stringBuilder.toString();
    }

    public void setSupplierPhoneNumber(String supplierPhoneNumber) {
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    public String getSupplierPhoneNumber() {
        return this.supplierPhoneNumber;
    }

    public String getPriceForDisplay() {
        StringBuilder stringBuilder = new StringBuilder("$");
        stringBuilder.append(String.valueOf(getNumDollars()));
        stringBuilder.append(".");
        stringBuilder.append(String.valueOf(getNumCents()));
        if (getNumCents() == 0)
            stringBuilder.append("0");
        return stringBuilder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(supplierPhoneNumber);
        dest.writeInt(imageResourceId);
        dest.writeInt(numDollars);
        dest.writeInt(numCents);
        dest.writeInt(quantity);
    }
}
