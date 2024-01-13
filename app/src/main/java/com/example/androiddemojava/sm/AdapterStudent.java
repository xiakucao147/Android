package com.example.androiddemojava.sm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androiddemojava.R;

import java.util.LinkedList;

public class AdapterStudent extends BaseAdapter {
    private LinkedList<Student> mData;
    private Context context;
    public AdapterStudent(LinkedList<Student> mData,Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_student,parent,false);
            viewHolder.tv_name      = (TextView)convertView.findViewById(R.id.tv_name   );
            viewHolder.tv_sex       = (TextView)convertView.findViewById(R.id.tv_sex    );
            viewHolder.tv_faculty   = (TextView)convertView.findViewById(R.id.tv_faculty);
            viewHolder.tv_major     = (TextView)convertView.findViewById(R.id.tv_major  );
            viewHolder.tv_no        = (TextView)convertView.findViewById(R.id.tv_no     );
           // 用setTag将convertView和viewHolder关联
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //取出bean对象
        Student student = mData.get(position);
        //设置控件的数据
        viewHolder.tv_name   .setText(student.getName());
        viewHolder.tv_sex    .setText(student.getSex());
        viewHolder.tv_faculty.setText(student.getFaculty());
        viewHolder.tv_major  .setText(student.getMajor());
        viewHolder.tv_no     .setText(student.getStudent_num());

        return convertView;
    }
    class ViewHolder{
        TextView tv_name;
        TextView tv_sex;
        TextView tv_faculty;
        TextView tv_major;
        TextView tv_no;
    }
}
