package br.edu.fasb.vacinacard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class acUsuario extends AppCompatActivity {

    private TextView nome;
    private TextView idade;
    private TextView endereco;
    private TextView tiposangue;
    private TextView mae;
    private TextView pai;

    private ListView vacinas;

    private List<Usuario>  resultUser;
    private List<Vacinas> resultVacinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (TextView) findViewById(R.id.txtNome);
        idade = (TextView) findViewById(R.id.txtIdade);
        endereco = (TextView) findViewById(R.id.txtEndereco);
        tiposangue = (TextView) findViewById(R.id.txtTipoSangue);
        mae = (TextView) findViewById(R.id.txtMae);
        pai = (TextView) findViewById(R.id.txtPai);

        vacinas = (ListView) findViewById(R.id.lvVacinas);

        final ConsultaVacinas consVacinas = new ConsultaVacinas(acUsuario.this);

        //CONSULTA A LISTA DE VACINAS
        Thread pesquisaVacinas = new Thread(new Runnable() {
            public void run() {
                resultVacinas = consVacinas.getVacinas();
            }
        });
        pesquisaVacinas.start();

        final ConsultaUsuario consUser = new ConsultaUsuario(acUsuario.this);

        //CONSULTA O USUÁRIO PELO CPF E SENHA
        Thread pesquisaUser = new Thread(new Runnable()
        {
            public void run()
            {
                resultUser = consUser.getUsuario( "00200102544", "10203040" );
            }
        });
        pesquisaUser.start();

        nome.setText( resultUser.get(0).getNome() );
        idade.setText( resultUser.get(0).getIdade() );
        endereco.setText( resultUser.get(0).getEndereco() );
        pai.setText( resultUser.get(0).getPai() );
        mae.setText( resultUser.get(0).getMae() );
        tiposangue.setText( resultUser.get(0).getTipoSangue() );

        CriaListaVacinas();

        vacinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent camp = new Intent(getApplication(), acCampanha.class);

                camp.putExtra("vacina", position);
                startActivity(camp);
            }
        });
    }

    private void CriaListaVacinas() {

        ArrayAdapter<Vacinas> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, resultVacinas);
        vacinas.setAdapter(adapter);

    }
}
