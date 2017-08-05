package com.codepath.simpletodo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.codepath.simpletodo.R;

public class EditItemActivity extends AppCompatActivity {
    public static final String ITEM_NAME = "ITEM_NAME";
    public static final String ITEM_INDEX = "ITEM_INDEX";

    EditText etEditItem;
    int itemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        etEditItem = (EditText)findViewById(R.id.etEditItem);

        String itemName = getIntent().getStringExtra(ITEM_NAME);
        itemIndex = getIntent().getIntExtra(ITEM_INDEX, 0);

        //append puts cursor at the end
        etEditItem.append(itemName);
    }

    public void onSave(View view) {
        Intent saveIntent = new Intent();

        saveIntent.putExtra(ITEM_NAME, etEditItem.getText().toString());
        saveIntent.putExtra(ITEM_INDEX, itemIndex);

        setResult(RESULT_OK, saveIntent);
        finish();
    }
}
