package com.example.covid_members_per_family.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.covid_members_per_family.model.Familias;

import java.util.ArrayList;

public class FamiliasDB extends SQLiteOpenHelper {

    private static final String DATABASE = "dbfamilias";
    private static final int VERSION =1;

    public FamiliasDB (Context context){
        super(context, DATABASE,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String familia = "CREATE TABLE familia(id INTERGER PRIMARY KEY AUTOINCREMENT NOT NULL, sobrenome TEXT NOT NULL, numFamiliares INTERGER NOT NULL, numInfectados INTERGER NOT NULL);";
        db.execSQL(familia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String familia = "DROP TABLE IF EXISTS familia";
        db.execSQL(familia);
    }

    public void salvarFamilia (Familias familia){
        ContentValues values = new ContentValues();

        values.put("sobrenome", familia.getSobrenome());
        values.put("numFamiliares", familia.getNumFamiliares());
        values.put("numInfectados", familia.getNumInfectados());

        getWritableDatabase().insert("familia",null,values);
    }

    public ArrayList<Familias> getLista(){
        String [] columns = {"id", "sobrenome", "numFamiliares", "numInfectados"};
        Cursor cursor = getWritableDatabase().query("familia", columns, null,null,null, null, null, null);
        ArrayList<Familias> familias = new ArrayList<Familias>();

        while (cursor.moveToNext()){
            Familias familia = new Familias();
            familia.setId(cursor.getLong(0));
            familia.setSobrenome(cursor.getString(1));
            familia.setNumFamiliares(cursor.getInt(2));
            familia.setNumInfectados(cursor.getInt(3));

            familias.add(familia);
        }
        return familias;
    }
}
