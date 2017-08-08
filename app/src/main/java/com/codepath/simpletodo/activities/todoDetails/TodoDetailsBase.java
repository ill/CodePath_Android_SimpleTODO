package com.codepath.simpletodo.activities.todoDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.models.TodoItem;

import java.util.Calendar;
import java.util.Date;

public class TodoDetailsBase extends Fragment {
    public EditText etTitle;
    public DatePicker dpDatePicker;
    public Spinner spPriorityPicker;
    public ArrayAdapter<TodoItem.Priority> priorityAdapter;

    public TodoDetailsBase() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_details_base, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etTitle = (EditText)view.findViewById(R.id.etTitle);

        dpDatePicker = (DatePicker)view.findViewById(R.id.dpDatePicker);

        spPriorityPicker = (Spinner)view.findViewById(R.id.spPriorityPicker);

        //set up the spinner values
        priorityAdapter = new ArrayAdapter<TodoItem.Priority>(getContext(),
                android.R.layout.simple_spinner_item,
                TodoItem.Priority.values());

        spPriorityPicker.setAdapter(priorityAdapter);
    }

    public String getTitleString() {
        return etTitle.getText().toString();
    }

    public void setTitleString(String title) {
        etTitle.setText("");
        etTitle.append(title);
    }

    public Date getDate() {
        int day = dpDatePicker.getDayOfMonth();
        int month = dpDatePicker.getMonth() + 1;
        int year = dpDatePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(dpDatePicker.getYear(),
                dpDatePicker.getMonth(),
                dpDatePicker.getDayOfMonth());

        return calendar.getTime();
    }

    public void setDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        dpDatePicker.updateDate(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    public TodoItem.Priority getPriority() {
        return (TodoItem.Priority) spPriorityPicker.getSelectedItem();
    }

    public void setPriority(TodoItem.Priority priority) {
        //assumes value is always in the order of the enums
        spPriorityPicker.setSelection(priority.getValue());
    }
}
