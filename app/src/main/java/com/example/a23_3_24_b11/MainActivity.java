package com.example.a23_3_24_b11;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText edtThongBao;
    Button btnThongBao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnThongBao.setOnClickListener(v -> {
            String thongBaoText = edtThongBao.getText().toString();
            writeFireBase("thongBao", thongBaoText);
        });

        readRealTimeFireBase("thongBao");
    }
    private void writeFireBase(String key, String value){
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
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                try {
                    String value = dataSnapshot.getValue(String.class);
                    Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }
    private void setControl() {
        edtThongBao = findViewById(R.id.edtThongBao);
        btnThongBao = findViewById(R.id.btnThongBao);
    }

}