package com.codepath.simpletodo.activities.todoList;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.activities.todoDetails.TodoCreation;
import com.codepath.simpletodo.models.TodoItem;

public class TodoListCreationDialog extends DialogFragment implements TodoCreation.TodoCreationFragmentListener {
    public TodoCreation frTodoCreation;
    public TodoCreationDialogListener listener;

    public interface TodoCreationDialogListener {
        void onTodoCreationDialogItemCreated(TodoItem todoItem, TodoListCreationDialog todoCreationDialog);
    }

    public TodoListCreationDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list_creation_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frTodoCreation = (TodoCreation)getFragmentManager().findFragmentById(R.id.frTodoCreation);
        frTodoCreation.listener = this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //https://stackoverflow.com/questions/7008183/error-inflating-fragment-in-dialog-the-second-time
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(getActivity().getSupportFragmentManager().findFragmentById(R.id.frTodoCreation));
        fragmentTransaction.commit();
    }

    @Override
    public void onTodoCreationFragmentItemCreated(TodoItem todoItem, TodoCreation todoCreation) {
        listener.onTodoCreationDialogItemCreated(todoItem, this);
    }
}
