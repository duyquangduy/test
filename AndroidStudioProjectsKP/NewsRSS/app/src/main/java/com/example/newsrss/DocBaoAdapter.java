package com.example.newsrss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DocBaoAdapter extends ArrayAdapter<DocBao> {

    public DocBaoAdapter(Context context, int resource, List<DocBao> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_doc_bao, null);
        }
        DocBao docBao = getItem(position);
        if (docBao != null) {
            // Anh xa + Gan gia tri
            TextView txtTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
            txtTitle.setText(docBao.getTitle());
            ImageView imageView = convertView.findViewById(R.id.imageView);
            //add ham picaso trong build.gradle module
            Picasso.with(getContext()).load(docBao.image).into(imageView);
        }
        return convertView;
    }

}
