package com.example.notes;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button addNote;
    ArrayAdapter mArrayAdapter;
    ArrayList<String> mNameList = new ArrayList<>();
    TextView tw1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote = (Button) findViewById(R.id.add);
        addNote.setOnClickListener(this);
        tw1=(TextView) findViewById(R.id.textView1);//для проверки

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final ListView mainListView = findViewById(R.id.main_listView);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                Bundle arguments = getIntent().getExtras();
                String n=arguments.get("note").toString();

             //   String n = data.getStringExtra("note");
                tw1.setText(n); //для проверки
                mNameList.add(n);
                mArrayAdapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, mNameList);
                mainListView.setAdapter(mArrayAdapter);
            }
        }
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.menu_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Удаление имени")
                .setMessage("Удалить?")
                .setCancelable(false)
                .setNegativeButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        mNameList.remove((mNameList.get(position).toString()));
                        mArrayAdapter.notifyDataSetChanged();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


}

