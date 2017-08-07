package com.codepath.simpletodo.activities.todoDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.models.TodoItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodoDetails extends Fragment {
    TodoItem todoItem;
    public TodoDetailsBase frTodoDetailsBase;

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
    }

    public void setTodoItem(TodoItem todoItem) {
        this.todoItem = todoItem;

        populateDetails();
    }

    void populateDetails() {
        frTodoDetailsBase.setTitleString(todoItem.title);
    }
}
