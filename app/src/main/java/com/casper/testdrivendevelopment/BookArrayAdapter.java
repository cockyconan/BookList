package com.casper.testdrivendevelopment;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chen on 2019/10/22.
 */
//重载函数getview把数据转换成视图
public class BookArrayAdapter extends ArrayAdapter<Book> {

    private int resourceid;
    public BookArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        resourceid=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater=LayoutInflater.from(this.getContext());
        View item = mInflater.inflate(this.resourceid,null);
        ImageView img = (ImageView)item.findViewById(R.id.image_view_book_cover);
        TextView name = (TextView)item.findViewById(R.id.text_view_book_title);


        Book good_item =this.getItem(position);

        img.setImageResource(good_item.getCoverResourceId());
        name.setText("title: "+good_item.getTitle());


        return item;
    }


}
