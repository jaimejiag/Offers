package com.jaime.offering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaime.offering.R;
import com.jaime.offering.models.Offer;
import com.jaime.offering.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaime on 01/02/2017.
 */

public class OffersAdapater extends ArrayAdapter<Offer> {
    public static final int ORDER_ASC = 0;
    public static final int ORDER_DES = 1;
    public static final int ORDER_TYPE = 2;

    private Context mContext;
    private boolean mShowImportance;


    public OffersAdapater(Context context, boolean showImportance) {
        super(context, R.layout.offer_item, new ArrayList<>(Repository.getInstance()));

        mContext = context;
        mShowImportance = showImportance;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = convertView;
        OfferHolder holder;

        if (rootView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            rootView = inflater.inflate(R.layout.offer_item, null);
            holder = new OfferHolder();

            holder.imgType = (ImageView) rootView.findViewById(R.id.img_type);
            holder.txvName = (TextView) rootView.findViewById(R.id.txv_name);
            holder.txvShop = (TextView) rootView.findViewById(R.id.txv_shop);
            holder.txvDate = (TextView) rootView.findViewById(R.id.txv_date);

            rootView.setTag(holder);
        } else
            holder = (OfferHolder) rootView.getTag();

        holder.imgType.setImageResource(getItem(position).getImage());
        holder.txvName.setText(getItem(position).getName());
        holder.txvShop.setText(getItem(position).getShop());
        holder.txvDate.setText(getItem(position).getDate());

        if (mShowImportance)
            rootView.setBackgroundResource(getItem(position).getImportance());

        return rootView;
    }


    public void filterList(boolean[] filterShow) {
        ArrayList<Offer> temp = new ArrayList<>();

        for (int i = 0; i < getCount(); i++) {
            switch (getItem(i).getImage()) {
                case R.mipmap.ic_home:
                    if (filterShow[0])
                        temp.add(getItem(i));
                    break;

                case R.mipmap.ic_mobile:
                    if (filterShow[1])
                        temp.add(getItem(i));
                    break;

                case R.mipmap.ic_sports:
                    if (filterShow[2])
                        temp.add(getItem(i));
                    break;
            }
        }

        clear();
        addAll(temp);
    }


    class OfferHolder {
        public ImageView imgType;
        public TextView txvName;
        public TextView txvShop;
        public TextView txvDate;
    }


    public void orderBy(int orderCondition) {
        if (orderCondition == ORDER_ASC)
            sort(Offer.ORDER_ASC);
        else if (orderCondition == ORDER_DES)
            sort(Offer.ORDER_DES);
        else
            sort(Offer.ORDER_TYPE);
    }
}
