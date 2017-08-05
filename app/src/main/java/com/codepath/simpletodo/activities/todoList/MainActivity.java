package com.codepath.simpletodo.activities.todoList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.activities.EditItemActivity;
import com.codepath.simpletodo.models.TodoItem;
import com.codepath.simpletodo.models.TodoItemArrayAdapter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private final int EDIT_ITEM_REQUEST_CODE = 1337;

    ListView lvItems;
    List<TodoItem> todoItems;
    TodoItemArrayAdapter todoItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView)findViewById(R.id.lvItems);
        readItems();
        todoItemsAdapter = new TodoItemArrayAdapter(this, todoItems);
        lvItems.setAdapter(todoItemsAdapter);
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
        TodoItem todoItem = todoItems.get(itemIndex);

        todoItems.remove(itemIndex);
        todoItemsAdapter.notifyDataSetChanged();

        todoItem.delete();
    }

    public void onAddItem(View view) {
        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();

        TodoItem todoItem = new TodoItem();
        todoItem.id = UUID.randomUUID();
        todoItem.creationDate = new Date();
        todoItem.title = itemText;
        todoItem.save();

        todoItems.add(todoItem);

        etNewItem.setText("");
    }

    private void editItem(int itemIndex) {
        TodoItem todoItem = todoItems.get(itemIndex);

        Intent editItemIntent = new Intent(this, EditItemActivity.class);

        editItemIntent.putExtra(EditItemActivity.ITEM_INDEX, itemIndex);
        editItemIntent.putExtra(EditItemActivity.ITEM_NAME, todoItem.title);

        startActivityForResult(editItemIntent, EDIT_ITEM_REQUEST_CODE);
    }

    private void onEditedItem(int itemIndex, String itemText) {
        TodoItem todoItem = todoItems.get(itemIndex);
        todoItem.title = itemText;
        todoItem.save();

        todoItemsAdapter.notifyDataSetChanged();
    }

    private void readItems() {
        todoItems = SQLite.select().from(TodoItem.class).queryList();
    }
}
