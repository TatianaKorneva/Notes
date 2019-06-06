package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final EditText Note=findViewById(R.id.note);
        switch (item.getItemId()) {
            case R.id.menu_save:
                if (Note.getText()!=null) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("note", Note.getText().toString());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
                else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                    builder.setTitle(getResources().getString(R.string.err));
                    AlertDialog alert = builder.create();
                    alert.show();

                }
                break;
            case R.id.menu_cancel:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
