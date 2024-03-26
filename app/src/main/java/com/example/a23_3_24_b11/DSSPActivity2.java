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

public class DSSPActivity2 extends AppCompatActivity {
    EditText edtTenSP, edtGiaSP;
    Button btnThemSP;
    ListView lvDanhSachSP;
    List<SanPham> lvData;
    SanPhamAdapter lvAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("dsSanPham");
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dssp_2_activity);
        
        setInit();
        setControl();
        setEvent();

    }
    private void setInit() {
        lvData = new ArrayList<>();
        lvAdapter = new SanPhamAdapter(DSSPActivity2.this, R.layout.layout_sp, lvData);
    }
    private void setEvent() {
        lvDanhSachSP.setAdapter(lvAdapter);

        btnThemSP.setOnClickListener(v -> {
            String tenSP = edtTenSP.getText().toString();
            int giaSP = Integer.parseInt(edtGiaSP.getText().toString());

            SanPham sp = new SanPham(tenSP, giaSP);
            //write 2 flutter
            myRef.child(myRef.push().getKey()).setValue(sp);

        });

        readRealTimeFireBase();
    }
    private void setControl() {
        edtTenSP = findViewById(R.id.edtTenSP);
        edtGiaSP = findViewById(R.id.edtGiaSP);
        btnThemSP = findViewById(R.id.btnThemSP);
        lvDanhSachSP = findViewById(R.id.lvDanhSachSP);
    }
    private void readRealTimeFireBase(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lvData.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    SanPham sp = child.getValue(SanPham.class);
                    lvData.add(sp);
                }
                lvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(DSSPActivity2.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
