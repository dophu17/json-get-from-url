package net.dauhuthom.jsonapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by phu on 12/13/2016.
 */

public class ProductAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<Product> list;

    public ProductAdapter(Activity activity, ArrayList<Product> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        final View row = layoutInflater.inflate(R.layout.list_item, null);
        TextView tvID = (TextView) row.findViewById(R.id.tvID);
        TextView tvName = (TextView) row.findViewById(R.id.tvName);
        TextView tvPrice = (TextView) row.findViewById(R.id.tvPrice);

        final Product product = list.get(i);
        tvID.setText(product.id + "");
        tvName.setText(product.name);
        tvPrice.setText(product.price + "");

        return row;
    }
}
