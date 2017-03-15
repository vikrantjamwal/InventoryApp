package com.android.vik.inventoryapp;

import android.provider.BaseColumns;

public class ProductContract {

    public ProductContract() {
    }

    public static abstract class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "products";
        public static final String COLUMN_NAME_NAME = "nameColumn";
        public static final String COLUMN_NAME_QUANTITY = "quantityColumn";
        public static final String COLUMN_NAME_PRICE_DOLLAR = "priceDollarColumn";
        public static final String COLUMN_NAME_PRICE_CENTS = "priceCentsColumn";
        public static final String COLUMN_NAME_SUPPLIER_PHONE_NUMBER = "supplierPhoneNumberColumn";
        public static final String COLUMN_NAME_IMAGE_RES_ID = "imageResIdColumn";
    }
}
