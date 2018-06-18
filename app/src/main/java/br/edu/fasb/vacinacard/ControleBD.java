package br.edu.fasb.vacinacard;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ControleBD {

    private SQLiteDatabase db;
    private CriarBanco banco;

    public ControleBD(Context context){
        banco = new CriarBanco(context);
    }

public Cursor carregaDados(){
    Cursor cursor;
    String[] campos = {banco.ID, banco.NOME};
    db = banco.getReadableDatabase();
    cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

    if (cursor!=null) {
        cursor.moveToFirst();
    }
    db.close();
    return  cursor;
}
}
