package com.codepath.simpletodo.activities.todoList;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.models.TodoItem;
import com.codepath.simpletodo.models.TodoItemArrayAdapter;
import com.codepath.simpletodo.models.TodoItem_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvItems;
    List<TodoItem> todoItems;
    TodoItemArrayAdapter todoItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView)findViewById(R.id.lvItems);
        reloadData();
        setupListViewListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btCreate:
                addItem();
                break;

            default:
                break;
        }

        return true;
    }

    private void setupListViewListeners() {
        //tap edit listener
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewItem(position);
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

    private void addItem() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        TodoListCreationDialog todoCreationDialog = new TodoListCreationDialog();
        todoCreationDialog.listener = new TodoListCreationDialog.TodoCreationDialogListener() {
            @Override
            public void onTodoCreationDialogItemCreated(TodoItem todoItem, TodoListCreationDialog todoCreationDialog) {
                todoCreationDialog.dismiss();
                reloadData();
            }
        };
        todoCreationDialog.show(fragmentManager, "fragment_todo_list_creation_dialog");
    }

    private void viewItem(int itemIndex) {
        TodoItem todoItem = todoItems.get(itemIndex);

        FragmentManager fragmentManager = getSupportFragmentManager();
        TodoListDetailsDialog todoDetailsDialog = new TodoListDetailsDialog();
        todoDetailsDialog.listener = new TodoListDetailsDialog.TodoDetailsDialogListener() {
            @Override
            public void onTodoDetailsDialogItemSaved(TodoListDetailsDialog todoDetailsDialog) {
                todoDetailsDialog.dismiss();
                reloadData();
            }
        };
        todoDetailsDialog.todoItem = todoItem;
        todoDetailsDialog.show(fragmentManager, "fragment_todo_list_details_dialog");
    }

    private void reloadData() {
        todoItems = SQLite.select()
                .from(TodoItem.class)
                .orderBy(TodoItem_Table.priority, false)
                .orderBy(TodoItem_Table.creationDate, true)
                .queryList();

        todoItemsAdapter = new TodoItemArrayAdapter(this, todoItems);
        lvItems.setAdapter(todoItemsAdapter);
    }
}
