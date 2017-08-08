package com.codepath.simpletodo.activities.todoDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.models.TodoItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodoDetails extends Fragment {
    TodoItem todoItem;
    public TodoDetailsFragmentListener listener;
    public Button btSave;
    public TodoDetailsBase frTodoDetailsBase;

    public interface TodoDetailsFragmentListener {
        void onTodoDetailsFragmentItemSaved(TodoDetails todoDetails);
    }

    public TodoDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frTodoDetailsBase = (TodoDetailsBase)getChildFragmentManager().findFragmentById(R.id.frTodoDetailsBase);
        btSave = (Button)view.findViewById(R.id.btSave);

        //Took a while to figure out that setting the onClick listener in the xml doesn't work for fragments!
        //https://stackoverflow.com/questions/14139774/android-app-crashing-fragment-and-xml-onclick
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSave();
            }
        });
    }

    public void setTodoItem(TodoItem todoItem) {
        this.todoItem = todoItem;

        populateDetails();
    }

    void populateDetails() {
        frTodoDetailsBase.setTitleString(todoItem.title);
        frTodoDetailsBase.setPriority(todoItem.priority);
        frTodoDetailsBase.setDate(todoItem.dueDate);
    }

    public void onSave() {
        saveTodoItem();

        listener.onTodoDetailsFragmentItemSaved(this);
    }

    public void saveTodoItem() {
        todoItem.title = frTodoDetailsBase.getTitleString();
        todoItem.priority = frTodoDetailsBase.getPriority();
        todoItem.dueDate = frTodoDetailsBase.getDate();
        todoItem.save();
    }
}
