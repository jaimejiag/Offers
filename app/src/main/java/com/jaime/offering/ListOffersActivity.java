package com.jaime.offering;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.jaime.offering.adapters.OffersAdapater;

public class ListOffersActivity extends AppCompatActivity {
    private ListView lvOfferList;
    private FloatingActionButton fabAdd;

    private OffersAdapater mAdapter;
    private boolean[] mFilters;
    private boolean mShowImportance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_offers);

        lvOfferList = (ListView) findViewById(R.id.lv_offers);
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListOffersActivity.this, AddOfferActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        mFilters = getIntent().getBooleanArrayExtra(ConfigurationActivity.CHECKS_KEY);
        mShowImportance = mFilters[3];
        mAdapter = new OffersAdapater(this, mShowImportance);
        mAdapter.filterList(mFilters);
        lvOfferList.setAdapter(mAdapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        recreate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_order_asc:
                mAdapter.orderBy(OffersAdapater.ORDER_ASC);
                break;

            case R.id.menu_order_des:
                mAdapter.orderBy(OffersAdapater.ORDER_DES);
                break;

            case R.id.menu_order_type:
                mAdapter.orderBy(OffersAdapater.ORDER_TYPE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
