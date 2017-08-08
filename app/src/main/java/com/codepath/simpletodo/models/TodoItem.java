package com.codepath.simpletodo.models;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.SimpleTodoApplication;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ilyaseletsky on 8/4/17.
 */
@Table(database = SimpleTodoDatabase.class)
public class TodoItem extends BaseModel {
    public enum Priority {
        LOW(0, R.string.priLow),
        MEDIUM(1, R.string.priMedium),
        HIGH(2, R.string.priHigh);

        private final int value;
        private final int resourceId;

        Priority(int value, int resourceId) {
            this.value = value;
            this.resourceId = resourceId;
        }

        @Override
        public String toString() {
            return SimpleTodoApplication.getContext().getString(getResourceId());
        }

        public int getValue() {
            return value;
        }

        public int getResourceId() {
            return resourceId;
        }
    }

    @Column
    @PrimaryKey
    public UUID id;

    @Column
    public String title;

    @Column
    public Date creationDate;

    @Column
    public Date dueDate;

    @Column
    public Priority priority;
}
