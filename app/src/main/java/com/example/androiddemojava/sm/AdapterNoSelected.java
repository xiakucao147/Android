package com.example.androiddemojava.sm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androiddemojava.R;

import java.util.LinkedList;

public class AdapterNoSelected extends BaseAdapter {
    private LinkedList<Course> mData;
    private Context context;
    public AdapterNoSelected(LinkedList<Course> mData, Context context){
        this.mData=mData;
        this.context=context;
    }

    @Override
    public int getCount() {

        return mData.size();
    }

    @Override
    public Object getItem(int position) {

        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        //如果view未被实例化过，缓存池中没有对应的缓存
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_course,parent,false);
            viewHolder.tv_course_name = (TextView) convertView.findViewById(R.id.tv_course_name);
            viewHolder.tv_course_credit_hour = (TextView) convertView.findViewById(R.id.tv_course_credit_hour);

            //用setTag将convertView和viewHolder关联
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //取出bean对象
        Course Course = mData.get(position);
        //设置控件的数据
        viewHolder.tv_course_name.setText(Course.getName());
        viewHolder.tv_course_credit_hour.setText(Course.getScore());


        return convertView;
    }

    class ViewHolder{
        TextView tv_course_name;
        TextView tv_course_credit_hour;
    }
}
