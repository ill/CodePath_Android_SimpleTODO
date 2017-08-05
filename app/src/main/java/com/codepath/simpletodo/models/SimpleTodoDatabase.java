package com.codepath.simpletodo.models;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by ilyaseletsky on 8/4/17.
 */
@Database(name = SimpleTodoDatabase.NAME, version = SimpleTodoDatabase.VERSION)
public class SimpleTodoDatabase {
    public static final String NAME = "SimpleTodoDatabase";

    public static final int VERSION = 1;
}
