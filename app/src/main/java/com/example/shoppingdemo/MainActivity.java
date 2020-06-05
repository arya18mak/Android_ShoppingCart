package com.example.shoppingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderKt;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.item_count_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView txtView = findViewById(R.id.textView);
        TextView txtView1 = findViewById(R.id.textView2);
        EditText editText = findViewById(R.id.editText2);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button btn = findViewById(R.id.button);

        MainActivityViewModel mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        List<DbTable> dbTable = new ArrayList<>();
        ItemAdapter itemAdapter = new ItemAdapter(dbTable);

        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String amount = spinner.getSelectedItem().toString();
                DbTable dbTable = new DbTable();
                dbTable.item = name;
                dbTable.quantity = Integer.valueOf(amount);
                mViewModel.insertNewItem(dbTable);
                editText.getText().clear();
            }
        });

        mViewModel.getItems().observe(this, new Observer<List<DbTable>>() {
            @Override
            public void onChanged(List<DbTable> dbTables) {
                itemAdapter.setData(dbTables);
                itemAdapter.notifyDataSetChanged();
            }
        });

        mViewModel.getItemCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                txtView1.setText(String.valueOf(integer));
            }
        });
    }
}
