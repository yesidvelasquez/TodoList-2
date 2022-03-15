package com.example.to_dolist_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity  extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {

    private EditText editText;
    private Button button;
    private ListView itemslist;

    private ArrayList<String> tareas;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        itemslist = findViewById(R.id.tasklist);


        tareas = SaveInfo.leertareas(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tareas);
        itemslist.setAdapter(adapter);
        button.setOnClickListener(this);
        itemslist.setOnItemLongClickListener(this);
    }


    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button:
                String itemEntered = editText.getText().toString();
                adapter.add(itemEntered);
                editText.setText("");
                SaveInfo.guardar(tareas,this);

                Toast.makeText(this, "La tarea fue agregada", Toast.LENGTH_LONG).show();
                break;
        }
    }


    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        tareas.remove(i);
        adapter.notifyDataSetChanged();
        Toast.makeText(this,"Tarea eliminada", Toast.LENGTH_LONG).show();
        return true;
    }
}