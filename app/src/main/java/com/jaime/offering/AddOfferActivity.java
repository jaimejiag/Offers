package com.jaime.offering;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jaime.offering.models.Offer;
import com.jaime.offering.repositories.Repository;

public class AddOfferActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtShop;
    private EditText edtDate;
    private Spinner spType;
    private Spinner spImportance;
    private Button btnAdd;

    private int mPosType;
    private int mPosImportance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);

        edtName = (EditText) findViewById(R.id.edt_name);
        edtShop = (EditText) findViewById(R.id.edt_shop);
        edtDate = (EditText) findViewById(R.id.edt_date);
        spType = (Spinner) findViewById(R.id.sp_type);
        spImportance = (Spinner) findViewById(R.id.sp_importance);
        btnAdd = (Button) findViewById(R.id.btn_add);

        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mPosType = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spImportance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mPosImportance = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.type));
        ArrayAdapter importanceAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.importance));
        spType.setAdapter(typeAdapter);
        spImportance.setAdapter(importanceAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOffer();
                Intent intent = new Intent();
                setResult(1, intent);
                finish();
            }
        });
    }


    private void addOffer() {
        Offer offer = null;
        int type = 0;
        int importance = 0;

        if (mPosType == 0)
            type = R.mipmap.ic_home;
        else if (mPosType == 1)
            type = R.mipmap.ic_mobile;
        else
            type = R.mipmap.ic_sports;

        if (mPosImportance == 0)
            importance = R.color.importance_low;
        else if (mPosImportance == 1)
            importance = R.color.importance_normal;
        else
            importance = R.color.importance_high;

        offer = new Offer(edtName.getText().toString(),
                edtShop.getText().toString(),
                edtDate.getText().toString(),
                type,
                importance);

        Repository.getInstance().add(offer);
    }
}


