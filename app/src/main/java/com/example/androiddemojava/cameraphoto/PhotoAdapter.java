package com.example.androiddemojava.cameraphoto;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androiddemojava.R;

import java.util.List;

public class PhotoAdapter extends BaseAdapter {
    private List photoDates;///Adapter的数据源，
    private Context photoContext;//一个上下文
    PhotoAdapter(Context context,List datas){
        this.photoDates=datas;
        this.photoContext=context;
    }
    @Override
    public int getCount() {

        return photoDates.size();
    }

    @Override
    public Object getItem(int position) {

        return photoDates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View photoView = View.inflate(photoContext, R.layout.item_photo, null);
        TextView tv_name = photoView.findViewById(R.id.photoName);
        TextView pic_info=photoView.findViewById(R.id.photoInfo);
        ImageView imageView=photoView.findViewById(R.id.photo);

        Photo s = (Photo) getItem(position);
        tv_name.setText(s.getFileName());
        pic_info.setText(s.getInfo());
        imageView.setImageBitmap(s.getBitmap());

        return photoView;
    }
}
