package com.example.dvoladaaliado;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dvoladaaliado.adapter.ListAdapter;
import com.example.dvoladaaliado.pojo.Option;

import java.util.ArrayList;

public class ModifierActivity extends AppCompatActivity {
    TextView modifier;
    TextView description;
    RecyclerView recyclerView;
    ArrayList<Option> list = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);
        list = MainActivity.options;
        initializeComponents();
        createList();
    }

    public void createList(){
        //Obtener datos de enviados desde el Activity anterior
        Bundle data = this.getIntent().getExtras();
        //this.list = (ArrayList<Option>) getIntent().get("list");

        modifier.setText(data.getString("modifier"));
        description.setText(data.getString("description"));
        //Send list to ReciclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(list));

    }

    public void initializeComponents(){
        modifier = findViewById(R.id.modifierV);
        description = findViewById(R.id.descriptionV);
        recyclerView = findViewById(R.id.listV);
    }
}
