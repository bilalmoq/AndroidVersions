package com.example.androidversions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidversions.AndroidVersion;

import java.util.List;

public class AndroidVersionAdapter extends RecyclerView.Adapter<AndroidVersionAdapter.VH> {

    private final Context context;
    private final List<AndroidVersion> data;

    public AndroidVersionAdapter(Context context, List<AndroidVersion> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_android_version, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        AndroidVersion item = data.get(position);
        h.imgLogo.setImageResource(item.imageResId);
        h.txtCodeName.setText(item.codeName);
        h.txtVersion.setText(item.version);

        int bg = (position % 2 == 0) ? 0xFFFFFFFF : 0xFFF7F7F7;
        ((View) h.itemView).setBackgroundColor(bg);

        h.itemView.setOnClickListener(v -> {
            String msg = "You selected: " + item.codeName + " (" + item.version + ")";
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        ImageView imgLogo;
        TextView txtCodeName, txtVersion;
        VH(@NonNull View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.imgLogo);
            txtCodeName = itemView.findViewById(R.id.txtCodeName);
            txtVersion = itemView.findViewById(R.id.txtVersion);
        }
    }
}
