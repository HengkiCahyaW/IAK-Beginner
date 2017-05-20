package com.example.hengkicahyawijaya.iakbeginner;

/**
 * Created by Hengki Cahya Wijaya on 20/05/2017.
 */

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity{
    EditText mTextNama;
    TextView mTextHarga, mTextQty;
    Button mButtonOrder, mButtonPlus, mButtonMin;
    // spinner
    Spinner mSpinnerMenu;
    List<String> mListMenu = new ArrayList<>();
    int harga = 0;
    int qty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextNama = (EditText) findViewById(R.id.mTextNama);
        mTextHarga = (TextView) findViewById(R.id.mTextHarga);
        mButtonOrder = (Button) findViewById(R.id.mButtonOrder);
        mTextQty = (TextView) findViewById(R.id.mTextQty);
        mButtonPlus = (Button) findViewById(R.id.mButtonPlus);
        mButtonMin = (Button) findViewById(R.id.mButtonMin);

        mSpinnerMenu = (Spinner) findViewById(R.id.mSpinerMenu);
        mListMenu.add("-------");
        mListMenu.add("Martabak");
        mListMenu.add("Piscok Bakar");
        mListMenu.add("Ice Cream Sandwich");
        mListMenu.add("Lumpia Basah");
        ArrayAdapter<String> dataAdpter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mListMenu);
        dataAdpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerMenu.setAdapter(dataAdpter);
    }

    public void onClickOrder(View view){
        String to  = "hengki.cahya.w@gmail.com";
        String subject = mTextNama.getText().toString();
        String message = "Saya Pesan "
                +mSpinnerMenu.getSelectedItem()
                +" Sebanyak "
                +mTextQty.getText()
                +" buah, Seharga "
                +mTextHarga.getText();

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto"));
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(emailIntent, "Kirim email dengan"));
    }

    public void onClickPlus (View view){
            harga = harga + 5;
            qty = qty + 1;
            mTextQty.setText(qty+"");
            mTextHarga.setText("$"+harga);
    }

    public void onClickMin(View view){
        if(harga != 0){
            harga = harga - 5;
            qty = qty - 1;
            mTextQty.setText(qty+"");
            mTextHarga.setText("$"+harga);
        }
        else {
            harga = 0;
            qty = 0;
            mTextQty.setText(qty+"");
            mTextHarga.setText("$"+harga);
        }
    }

    public void onClickReset(View view){
        harga = 0;
        qty = 0;
        mTextNama.setText("");
        mTextQty.setText(qty+"");
        mTextHarga.setText("$"+harga);
        mSpinnerMenu.setSelection(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

