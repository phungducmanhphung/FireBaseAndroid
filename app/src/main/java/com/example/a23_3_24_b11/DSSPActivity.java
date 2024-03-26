package com.example.a23_3_24_b11;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a23_3_24_b11.adapter.SanPhamAdapter;
import com.example.a23_3_24_b11.model.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DSSPActivity extends AppCompatActivity {
    EditText edtTenSP, edtGiaSP;
    Button btnThemSP;
    ListView lvDanhSachSP;
    List<SanPham> lvData;
    SanPhamAdapter lvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dssp_activity);
        
        setInit();
        setControl();
        setEvent();

    }
    private void setInit() {
        lvData = new ArrayList<>();
        lvAdapter = new SanPhamAdapter(DSSPActivity.this, R.layout.layout_sp, lvData);
    }
    private void setEvent() {
        lvDanhSachSP.setAdapter(lvAdapter);

        btnThemSP.setOnClickListener(v -> {
            String tenSP = edtTenSP.getText().toString();
            int giaSP = Integer.parseInt(edtGiaSP.getText().toString());

            SanPham sp = new SanPham(tenSP, giaSP);
            lvData.add(sp);

            write2FireBase("dsSanPham", lvData);
        });

        readRealTimeFireBase("dsSanPham");
    }
    private void setControl() {
        edtTenSP = findViewById(R.id.edtTenSP);
        edtGiaSP = findViewById(R.id.edtGiaSP);
        btnThemSP = findViewById(R.id.btnThemSP);
        lvDanhSachSP = findViewById(R.id.lvDanhSachSP);
    }
    private void write2FireBase(String key, Object value){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(key);
        myRef.setValue(value);
    }
    private void readRealTimeFireBase(String key){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(key);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    GenericTypeIndicator<List<SanPham>> typeIndicator = new GenericTypeIndicator<List<SanPham>>() {};
                    List<SanPham> listSP = dataSnapshot.getValue(typeIndicator);
                    lvData.clear();
                    lvData.addAll(listSP);
                    lvAdapter.notifyDataSetChanged();
                    Toast.makeText(DSSPActivity.this, Integer.toString(listSP.size()), Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex){
                    Toast.makeText(DSSPActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("MY TAG", ex.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(DSSPActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
