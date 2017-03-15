package com.android.vik.inventoryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailsActivity extends AppCompatActivity {

    TextView productName;
    TextView productPrice;
    TextView productQuantity;
    ImageView imageIcon;
    Button buttonDelete;
    public static final int EDIT_PRODUCT_REQUEST = 10;
    public static final int EDIT_PRODUCT_QUANTITY_RESPONSE = 11;
    public static final int DELETE_PRODUCT_RESPONSE = 12;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productName = (TextView) findViewById(R.id.textViewProductName);
        productPrice = (TextView) findViewById(R.id.textViewProductPrice);
        productQuantity = (TextView) findViewById(R.id.textViewProductQuantity);
        imageIcon = (ImageView) findViewById(R.id.imageViewProductIcon);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        product = getIntent().getParcelableExtra(Product.PRODUCT_BUNDLE_KEY);
        productName.setText(product.getName());
        productPrice.setText(product.getPriceForDisplay());
        productQuantity.setText(String.valueOf(product.getQuantity()));
        imageIcon.setImageResource(product.getImageResourceId());
    }

    public void decreaseQuantity(View v) {
        if (product.getQuantity() == 0) {
            Toast.makeText(getApplicationContext(), "Quantity cannot be zero", Toast.LENGTH_SHORT).show();
            return;
        }
        product.setQuantity(product.getQuantity() - 1);
        productQuantity.setText(String.valueOf(product.getQuantity()));
    }

    public void increaseQuantity(View v) {
        product.setQuantity(product.getQuantity() + 1);
        productQuantity.setText(String.valueOf(product.getQuantity()));
    }

    public void saveQuantity(View v) {
        Intent intent = new Intent();
        intent.putExtra(Product.PRODUCT_BUNDLE_KEY, product);
        setResult(EDIT_PRODUCT_QUANTITY_RESPONSE, intent);
        finish();
    }

    public void decrease(Product product) {
        if (product.getQuantity() == 0) {
            Toast.makeText(getApplicationContext(), "Quantity cannot be zero", Toast.LENGTH_SHORT).show();
            return;
        }
        product.setQuantity(product.getQuantity() - 1);
    }

    public void orderProduct(View v) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse(product.getSupplierPhoneNumberForIntentCall()));
        try {
            startActivity(callIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "Activity not found!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteProduct(View v) {
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(Product.PRODUCT_NAME_KEY, product.getName());
                        setResult(DELETE_PRODUCT_RESPONSE, intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}