package com.example.androidversions;

import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidversions.AndroidVersion;
import com.example.androidversions.AndroidVersionAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    AndroidVersionAdapter adapter;
    FloatingActionButton btnSort;

    List<AndroidVersion> versions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rvVersions);
        btnSort = findViewById(R.id.btnSort);

        rv.setLayoutManager(new LinearLayoutManager(this));
        versions = buildData();
        adapter = new AndroidVersionAdapter(this, versions);
        rv.setAdapter(adapter);

        btnSort.setOnClickListener(v -> {
            toggleSort();
        });
    }

    private boolean sortByName = true;

    private void toggleSort() {
        if (sortByName) {
            Collections.sort(versions, Comparator.comparing(av -> av.codeName.toLowerCase()));
            Snackbar.make(rv, "Sorted by Code Name (A→Z)", Snackbar.LENGTH_SHORT).show();
        } else {
            Collections.sort(versions, (a, b) -> {
                double va = extractMinVersion(a.version);
                double vb = extractMinVersion(b.version);
                return Double.compare(vb, va); // تنازلي
            });
            Snackbar.make(rv, "Sorted by Version (desc)", Snackbar.LENGTH_SHORT).show();
        }
        sortByName = !sortByName;
        adapter.notifyDataSetChanged();
    }

    private double extractMinVersion(String range) {
        try {

            String cleaned = range.replace("–", "-").replace("—", "-");
            String left = cleaned.split("-")[0].trim();
            String[] parts = left.split("\\.");
            if (parts.length >= 2) {
                return Double.parseDouble(parts[0] + "." + parts[1]);
            } else {
                return Double.parseDouble(left);
            }
        } catch (Exception e) {
            return 0.0;
        }
    }

    private List<AndroidVersion> buildData() {
        List<AndroidVersion> list = new ArrayList<>();

        list.add(new AndroidVersion(draw("donut"), "Donut", "1.6"));
        list.add(new AndroidVersion(draw("eclair"), "Éclair", "2.0 – 2.1"));
        list.add(new AndroidVersion(draw("froyo"), "Froyo", "2.2 – 2.2.3"));
        list.add(new AndroidVersion(draw("gingerbread"), "Gingerbread", "2.3 – 2.3.7"));
        list.add(new AndroidVersion(draw("honeycomb"), "Honeycomb", "3.0 – 3.2.6"));
        list.add(new AndroidVersion(draw("ics"), "Ice Cream Sandwich", "4.0 – 4.0.4"));
        list.add(new AndroidVersion(draw("jellybean"), "Jelly Bean", "4.1 – 4.3.1"));
        list.add(new AndroidVersion(draw("kitkat"), "KitKat", "4.4 – 4.4.4"));
        list.add(new AndroidVersion(draw("lollipop"), "Lollipop", "5.0 – 5.1.1"));
        list.add(new AndroidVersion(draw("marshmallow"), "Marshmallow", "6.0 – 6.0.1"));
        list.add(new AndroidVersion(draw("nougat"), "Nougat", "7.0 – 7.1.2"));
        list.add(new AndroidVersion(draw("oreo"), "Oreo", "8.0 – 8.1"));

        return list;
    }

    @DrawableRes
    private int draw(String name) {
        int res = getResources().getIdentifier(name, "drawable", getPackageName());
        if (res == 0) res = getResources().getIdentifier("ic_launcher", "mipmap", getPackageName());
        return res;
    }
}
