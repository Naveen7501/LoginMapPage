package com.example.loginpagemap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class ListProductAdaptor extends BaseAdapter {

    private Context mcontext;
    private List<Product> mproductlist;

    public ListProductAdaptor(Context mcontext, List<Product> mproductlist) {
        this.mcontext = mcontext;
        this.mproductlist = mproductlist;
    }

    @Override
    public int getCount() {
        return mproductlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mproductlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mproductlist.get(position).getRetailerID();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v=View.inflate(mcontext,R.layout.item_listview,null);
        TextView RetailerName=(TextView) v.findViewById(R.id.txtRetailerName);
        TextView RetailerId=(TextView) v.findViewById(R.id.txtRetailerID);
        TextView Address1=(TextView) v.findViewById(R.id.txtAddress1);
        TextView ContactNumber=(TextView) v.findViewById(R.id.txtContactNumber);
        TextView Latitude=(TextView) v.findViewById(R.id.txtLatitude);
        TextView Longitude=(TextView) v.findViewById(R.id.txtLongitude);
        TextView pincode=(TextView) v.findViewById(R.id.txtpincode);
        TextView Phno1=(TextView) v.findViewById(R.id.txtPhno1);

        RetailerName.setText(mproductlist.get(position).getRetailerName());
        RetailerId.setText(String.valueOf(mproductlist.get(position).getRetailerID()));
        Address1.setText(mproductlist.get(position).getAddress1());
        ContactNumber.setText(mproductlist.get(position).getContactNumber());
        Latitude.setText(mproductlist.get(position).getLatitude());
        Longitude.setText(mproductlist.get(position).getLongitude());
        pincode.setText(mproductlist.get(position).getPincode());
        Phno1.setText(mproductlist.get(position).getPhno1());
        return v;
    }
}
