package com.example.webservicedatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<SinhVien> sinhVienList;

    public SinhVienAdapter(MainActivity context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtHoTen, txtDiaChi, txtNamSinh;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtHoTen = convertView.findViewById(R.id.textviewHoTenCustom);
            holder.txtDiaChi = convertView.findViewById(R.id.textviewDiaChiCustom);
            holder.txtNamSinh = convertView.findViewById(R.id.textviewNamSinhCustom);
            holder.imgDelete = convertView.findViewById(R.id.imageviewDelete);
            holder.imgEdit = convertView.findViewById(R.id.imageviewEdit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final SinhVien sinhVien = sinhVienList.get(position);
        holder.txtHoTen.setText(sinhVien.getHoTen());
        holder.txtNamSinh.setText("Nam sinh: " + sinhVien.getNamSinh());
        holder.txtDiaChi.setText(sinhVien.getDiaChi());

        //bat su kien xoa & sua
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateSinhVien.class);
                intent.putExtra("dataSinhVien", sinhVien);
                context.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XacNhanXoa(sinhVien.getHoTen(), sinhVien.getId());
            }
        });

        return convertView;
    }

    private void XacNhanXoa(String ten, final int id) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage("Ban co muon xoa sinh vien " + ten + " khong?");
        dialog.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.DeleteStudent(id);
            }
        });
        dialog.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}
