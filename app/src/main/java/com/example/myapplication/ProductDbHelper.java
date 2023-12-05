package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Articles.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProductContract.ArticleEntry.TABLE_NAME + " (" +
                    ProductContract.ArticleEntry._ID + " INTEGER PRIMARY KEY," +
                    ProductContract.ArticleEntry.COLUMN_NAME_LABEL + " TEXT," +
                    ProductContract.ArticleEntry.COLUMN_NAME_PU + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductContract.ArticleEntry.TABLE_NAME;

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertArticle(String libelle, int pu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductContract.ArticleEntry.COLUMN_NAME_LABEL, libelle);
        values.put(ProductContract.ArticleEntry.COLUMN_NAME_PU, pu);

        long newRowId = db.insert(ProductContract.ArticleEntry.TABLE_NAME, null, values);
    }

    public List<Product> getAllArticles() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Product> products = new ArrayList<>();

        String[] projection = {
                ProductContract.ArticleEntry._ID,
                ProductContract.ArticleEntry.COLUMN_NAME_LABEL,
                ProductContract.ArticleEntry.COLUMN_NAME_PU
        };

        Cursor cursor = db.query(
                ProductContract.ArticleEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ProductContract.ArticleEntry._ID));
            String libelle = cursor.getString(cursor.getColumnIndexOrThrow(ProductContract.ArticleEntry.COLUMN_NAME_LABEL));
            int pu = cursor.getInt(cursor.getColumnIndexOrThrow(ProductContract.ArticleEntry.COLUMN_NAME_PU));
            products.add(new Product(id, libelle, pu));
        }
        cursor.close();

        return products;
    }
}
