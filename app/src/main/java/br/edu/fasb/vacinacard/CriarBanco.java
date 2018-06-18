package br.edu.fasb.vacinacard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriarBanco extends SQLiteOpenHelper{

    public static final String NOME_BANCO = "vacinacard.db";
    public static final String TABELA = "USUARIO";
    public static final String ID = "ID";
    public static final String NOME = "NOME";
    public static final int VERSAO = 1;

    public CriarBanco(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NOME+" TEXT"+
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
