package com.codepath.simpletodo.activities.todoDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.codepath.simpletodo.R;

public class TodoDetailsBase extends Fragment {
    public EditText etTitle;

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
    }

    public String getTitleString() {
        return etTitle.getText().toString();
    }

    public void setTitleString(String title) {
        etTitle.setText("");
        etTitle.append(title);
    }
}
