package com.example.dvoladaaliado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dvoladaaliado.adapter.ListAdapter;
import com.example.dvoladaaliado.pojo.Option;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static ArrayList<Option> options = new ArrayList<>();
    RecyclerView recyclerView;
    EditText name;
    EditText description;
    EditText title;
    EditText price;
    Button btnAdd;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAdd :
                validationOptions();
                break;
            case R.id.buttonSave :
                validationModifier();
                break;
            default:
                Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT);
                break;
        }
    }

    public void initializeComponents(){
        recyclerView = findViewById(R.id.list);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        btnAdd = findViewById(R.id.buttonAdd);
        btnSave = findViewById(R.id.buttonSave);

        //For onClickListener
        btnAdd.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    public void validationOptions(){
        if (!title.getText().toString().isEmpty()){
            if (!price.getText().toString().isEmpty()){
                if (isNumeric(price.getText().toString())){
                    buttonAdd();
                }else{
                    Toast.makeText(this, "El precio debe ser entero o decimal", Toast.LENGTH_SHORT);
                }
            }else {
                Toast.makeText(this, "Por favor inserta un precio", Toast.LENGTH_SHORT);
            }
        }else {
            Toast.makeText(this, "Por favor inserta una opcion", Toast.LENGTH_SHORT);
        }
    }

    public void validationModifier(){
        if (!title.getText().toString().isEmpty()){
            if (!price.getText().toString().isEmpty()){
                if (options == null || options.size() == 0){
                    if (isNumeric(price.getText().toString())){
                        buttonSave();
                    }else{
                        Toast.makeText(this, "El precio debe ser entero o decimal", Toast.LENGTH_SHORT);
                    }
                }else {
                    Toast.makeText(this, "Por favor inserta opciones", Toast.LENGTH_SHORT);
                }
            }else {
                Toast.makeText(this, "Por favor inserta un precio", Toast.LENGTH_SHORT);
            }
        }else {
            Toast.makeText(this, "Por favor inserta una opcion", Toast.LENGTH_SHORT);
        }
    }

    public void buttonAdd(){
        options.add(new Option(title.getText().toString(), Double.parseDouble(price.getText().toString())));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(options));
        title.setText("");
        price.setText("");
    }

    public void buttonSave(){
        Intent intent = new Intent(this, ModifierActivity.class);
        intent.putExtra("modifier", name.getText().toString());
        intent.putExtra("description", description.getText().toString());
        //intent.putExtra("list", options);
        startActivity(intent);
    }

    public boolean isNumeric(String string){
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
}
