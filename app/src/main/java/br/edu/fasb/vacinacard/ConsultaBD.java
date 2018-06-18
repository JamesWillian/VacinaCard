package br.edu.fasb.vacinacard;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ConsultaBD extends Activity {
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ControleBD crud = new ControleBD(getBaseContext());
        Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {CriarBanco.ID, CriarBanco.NOME};
        int[] idViews = new int[] {R.id.txtNome};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_main,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.lvCampanhas);
        lista.setAdapter(adaptador);
    }
}