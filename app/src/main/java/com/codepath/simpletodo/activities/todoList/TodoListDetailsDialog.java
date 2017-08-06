package com.codepath.simpletodo.activities.todoList;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.simpletodo.R;

public class TodoListDetailsDialog extends DialogFragment {


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
    public void onDestroyView() {
        super.onDestroyView();

        //https://stackoverflow.com/questions/7008183/error-inflating-fragment-in-dialog-the-second-time
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(getFragmentManager().findFragmentById(R.id.frTodoDetails));
        fragmentTransaction.commit();
    }
}