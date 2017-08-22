package com.codepath.simpletodo.models;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.simpletodo.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ilyaseletsky on 8/4/17.
 */

public class TodoItemArrayAdapter extends ArrayAdapter<TodoItem> {
    public TodoItemArrayAdapter(Context context, List<TodoItem> todoItems) {
        super(context, 0, todoItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem todoItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_todo, parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvDueDate = (TextView) convertView.findViewById(R.id.tvDueDate);
        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);

        if (tvTitle != null) {
            tvTitle.setText(todoItem.title);
        }

        if (tvDueDate != null) {
            tvDueDate.setText(String.format(getContext().getResources().getString(R.string.tvDueAt),
                    new SimpleDateFormat("dd/MM/yyyy").format(todoItem.dueDate)));
        }

        if (tvPriority != null) {
            tvPriority.setText(todoItem.priority.toString());

            switch(todoItem.priority) {
                case LOW:
                    tvPriority.setTextColor(Color.GREEN);
                    tvPriority.setTypeface(Typeface.DEFAULT);
                    break;

                case MEDIUM:
                    //make medium be normal and not show
                    tvPriority.setText("");
                    break;

                case HIGH:
                    tvPriority.setTextColor(Color.RED);
                    tvPriority.setTypeface(Typeface.DEFAULT_BOLD);
                    break;
            }
        }

        return convertView;
    }
}
