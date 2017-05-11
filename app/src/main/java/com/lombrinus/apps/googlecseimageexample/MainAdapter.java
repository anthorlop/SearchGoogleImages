package com.lombrinus.apps.googlecseimageexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.lombrinus.projects.mods.gimageparser.gimages.Item;

/**
 * Created by antonio.hormigo on 10/2/17.
 */

public class MainAdapter extends BaseAdapter {

    private List<Item> mList;
    private Context context;
    LayoutInflater inflater;

    private final OnItemClickListener mListener;

    public MainAdapter(Context ctx, List<Item> objects, OnItemClickListener listener)
    {
        context = ctx;

        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mList = objects;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_row, null);
        }
        ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
        Picasso.with(context).load(mList.get(position).getLink()).into(iv);

        View cardView = convertView.findViewById(R.id.cardViewLayout);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(mList.get(position));
            }
        });

        return convertView;
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }
}
