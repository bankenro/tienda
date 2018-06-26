package com.example.globaltics.nomygroupsa.Clases.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by GlobalTIC's on 2/12/2017.
 */

public class UsuariosSQLite extends SQLiteOpenHelper {
    private SQLiteDatabase databasewrite = getWritableDatabase();
    private SQLiteDatabase databaseread = getReadableDatabase();
    public UsuariosSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void queryData(String sql){
        databasewrite.execSQL(sql);
    }
    public void insertData(String nombre, String codigo, String imagen, String tipo){
        String sql = "INSERT INTO USERS VALUES (NULL,?,?,?,?)";
        SQLiteStatement statement = databasewrite.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,nombre);
        statement.bindString(2,codigo);
        statement.bindString(3,imagen);
        statement.bindString(4,tipo);
        statement.executeInsert();
    }
    public void deleteData(String codigo){
        String sql = "DELETE FROM USERS WHERE codigo = ?";
        SQLiteStatement statement = databasewrite.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,codigo);
        statement.executeUpdateDelete();
    }
    public Cursor getData(String sql){
        return databaseread.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
