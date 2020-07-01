package com.example.listviewadvanced;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TraiCayAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<TraiCay> traiCayList;

    public TraiCayAdapter(Context context, int layout, List<TraiCay> traiCayList) {
        this.context = context;
        this.layout = layout;
        this.traiCayList = traiCayList;
    }

    @Override
    public int getCount() {
        return traiCayList.size(); // co bao nhieu dong trong list thi tra ra
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    private class ViewHolder {
        ImageView imgHinh;
        TextView txtTen, txtMoTa;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout, null);
            holder = new ViewHolder();

            //anh xa view
            holder.txtTen = (TextView) view.findViewById(R.id.textViewTen);
            holder.txtMoTa = (TextView) view.findViewById(R.id.textViewMoTa);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        view = inflater.inflate(layout, null);
//
//        //anh xa view
//        TextView txtten = (TextView) view.findViewById(R.id.textViewTen);
//        TextView txtmota = (TextView) view.findViewById(R.id.textViewMoTa);
//        ImageView imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);

        //gan gia tri

        TraiCay traiCay = traiCayList.get(i);

        holder.txtTen.setText(traiCay.getTen());
        holder.txtMoTa.setText(traiCay.getMoTa());
        holder.imgHinh.setImageResource(traiCay.getHinh());

        //gan Animation cho adapter
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_scale);
        view.startAnimation(animation);
        
        return view;
    }
}
