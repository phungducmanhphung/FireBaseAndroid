package com.example.a23_3_24_b11.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.a23_3_24_b11.R;
import com.example.a23_3_24_b11.model.SanPham;

import java.util.List;

public class SanPhamAdapter extends ArrayAdapter {
    Context context;
    int resource;
    List<SanPham> data;
    View view;
    SanPham crrData;
    TextView tvTenSP, tvGiaSP;
    public SanPhamAdapter(@NonNull Context context, int resource, @NonNull List<SanPham> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(resource, null);
        crrData = data.get(position);
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        tvTenSP.setText(crrData.getTenSP());
        tvGiaSP.setText(Integer.toString(crrData.getGiaSP()));
    }

    private void setControl() {
        tvTenSP = view.findViewById(R.id.tvTenSP);
        tvGiaSP = view.findViewById(R.id.tvGiaSP);
    }
}
