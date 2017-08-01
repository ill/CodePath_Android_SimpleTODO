package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int EDIT_ITEM_REQUEST_CODE = 1337;

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView)findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case EDIT_ITEM_REQUEST_CODE:
                    onEditedItem(data.getIntExtra(EditItemActivity.ITEM_INDEX, 0),
                            data.getStringExtra(EditItemActivity.ITEM_NAME));

                    break;
            }
        }
    }

    private void setupListViewListeners() {
        //tap edit listener
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editItem(position);
            }
        });

        //long click delete listener
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removeItem(position);
                return true;
            }
        });
    }

    private void removeItem(int itemIndex) {
        items.remove(itemIndex);
        itemsAdapter.notifyDataSetChanged();

        writeItems();
    }

    public void onAddItem(View view) {
        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        items.add(itemText);
        writeItems();
        etNewItem.setText("");
    }

    private void editItem(int itemIndex) {
        Intent editItemIntent = new Intent(this, EditItemActivity.class);

        editItemIntent.putExtra(EditItemActivity.ITEM_INDEX, itemIndex);
        editItemIntent.putExtra(EditItemActivity.ITEM_NAME, items.get(itemIndex));

        startActivityForResult(editItemIntent, EDIT_ITEM_REQUEST_CODE);
    }

    private void onEditedItem(int itemIndex, String itemText) {
        items.set(itemIndex, itemText);
        itemsAdapter.notifyDataSetChanged();

        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");

        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        }
        catch (IOException e) {
            items = new ArrayList<>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");

        try {
            FileUtils.writeLines(todoFile, items);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
