package com.codepath.simpletodo.models;

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
    @Column
    @PrimaryKey
    public UUID id;

    @Column
    public String title;

    @Column
    public Date creationDate;
}
