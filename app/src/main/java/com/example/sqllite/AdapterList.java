package com.example.sqllite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class AdapterList extends ArrayAdapter {

    private Context mContext;
    private List<Personal> mPersonalList;

    AdapterList(@NonNull Context context, int resource, @NonNull List<Personal> objects) {
        super(context, resource, objects);
        this.mContext =context;
        this.mPersonalList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.items_personal,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.mTextName = convertView.findViewById(R.id.text_itemName);
            viewHolder.mTextPhone = convertView.findViewById(R.id.text_itemPhone);
            viewHolder.mTextAdd = convertView.findViewById(R.id.text_itemAdd);
            viewHolder.mTextGender = convertView.findViewById(R.id.text_itemGender);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Personal personal = mPersonalList.get(position);
        viewHolder.mTextName.setText(personal.getName());
        viewHolder.mTextPhone.setText(personal.getPhone());
        viewHolder.mTextAdd.setText(personal.getAdd());
        viewHolder.mTextGender.setText(personal.getGender());

        return convertView;

    }

    private class ViewHolder{
        private TextView mTextName,mTextPhone,mTextAdd,mTextGender;
    }
}
