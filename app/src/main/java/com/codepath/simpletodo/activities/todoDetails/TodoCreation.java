package com.codepath.simpletodo.activities.todoDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.models.TodoItem;

import java.util.Date;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodoCreation extends Fragment {
    public Button btCreate;
    public TodoDetailsBase frTodoDetailsBase;
    public TodoCreationFragmentListener listener;

    public interface TodoCreationFragmentListener {
        void onTodoCreationFragmentItemCreated(TodoItem todoItem, TodoCreation todoCreation);
    }

    public TodoCreation() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_creation, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frTodoDetailsBase = (TodoDetailsBase)getChildFragmentManager().findFragmentById(R.id.frTodoDetailsBase);
        btCreate = (Button)view.findViewById(R.id.btCreate);

        //Took a while to figure out that setting the onClick listener in the xml doesn't work for fragments!
        //https://stackoverflow.com/questions/14139774/android-app-crashing-fragment-and-xml-onclick
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreate();
            }
        });
    }

    public void onCreate() {
        TodoItem todoItem = createTodoItem();

        listener.onTodoCreationFragmentItemCreated(todoItem, this);
    }

    public TodoItem createTodoItem() {
        TodoItem todoItem = new TodoItem();
        todoItem.id = UUID.randomUUID();
        todoItem.creationDate = new Date();
        todoItem.title = frTodoDetailsBase.getTitleString();
        todoItem.save();

        return todoItem;
    }
}
