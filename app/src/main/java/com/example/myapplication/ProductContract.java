package com.example.myapplication;

import android.provider.BaseColumns;

public final class ProductContract {
    private ProductContract() {}

    /* Inner class that defines the table contents */
    public static class ArticleEntry implements BaseColumns {
        public static final String TABLE_NAME = "article";
        public static final String COLUMN_NAME_LABEL = "libelle";
        public static final String COLUMN_NAME_PU = "PU";
    }
}
