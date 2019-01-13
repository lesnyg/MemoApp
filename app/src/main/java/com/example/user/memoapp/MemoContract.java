package com.example.user.memoapp;

import android.provider.BaseColumns;

public class MemoContract {
    /*
        CREATE TABLE memo
        (
            _id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT,
            contents TEXT
        );

     */
    public static final String SQL_CREATE_MEMO_TABLE =
            String.format("CREATE TEBLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT);",
            MemoEntry.TABLE_NAME,
            MemoEntry._ID,
            MemoEntry.COLUMN_NAME_TITLE,
            MemoEntry.COLUMN_NAME_CONTENTS);

    private MemoContract() {    }
    public static class MemoEntry implements BaseColumns{
        public static final String TABLE_NAME = "memo";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENTS = "contents";
    }
}
