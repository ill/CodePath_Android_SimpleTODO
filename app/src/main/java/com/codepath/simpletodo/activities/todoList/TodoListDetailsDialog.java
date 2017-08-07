package com.codepath.simpletodo.activities.todoList;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.activities.todoDetails.TodoDetails;
import com.codepath.simpletodo.models.TodoItem;

public class TodoListDetailsDialog extends DialogFragment {
    public TodoItem todoItem;
    public TodoDetails frTodoDetails;

    public TodoListDetailsDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list_details_dialog, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frTodoDetails = (TodoDetails)getActivity().getSupportFragmentManager().findFragmentById(R.id.frTodoDetails);
        //frTodoDetails.listener = this;
        frTodoDetails.setTodoItem(todoItem);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //https://stackoverflow.com/questions/7008183/error-inflating-fragment-in-dialog-the-second-time
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(frTodoDetails);
        fragmentTransaction.commit();
    }
}
