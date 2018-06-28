package br.edu.fasb.vacinacard;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView nome;
    private TextView idade;
    private TextView endereco;
    private TextView tiposangue;
    private TextView mae;
    private TextView pai;

    private ListView vacinas;
    private ListView campanhas;

    private TextView vacinou;
    private TextView campanha;
    private TextView data;
    private TextView local;
    private TextView proximadose;

    private EditText cpf;
    private EditText senha;
    private Button   login;

    private List<Usuario>  resultUser;
    private List<Vacinas>  resultVacinas;
    private List<Campanha> resultCampanha;
    private List<Vacinou>  resultVacinou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        nome = (TextView) findViewById(R.id.txtNome);
        idade = (TextView) findViewById(R.id.txtIdade);
        endereco = (TextView) findViewById(R.id.txtEndereco);
        tiposangue = (TextView) findViewById(R.id.txtTipoSangue);
        mae = (TextView) findViewById(R.id.txtMae);
        pai = (TextView) findViewById(R.id.txtPai);

        vacinas = (ListView) findViewById(R.id.lvVacinas);
        campanhas = (ListView) findViewById(R.id.lvCampanhas);

        vacinou = (TextView) findViewById(R.id.txtVacinado);
        campanha = (TextView) findViewById(R.id.txtCamp);
        data = (TextView) findViewById(R.id.txtData);
        local = (TextView) findViewById(R.id.txtLocal);
        proximadose = (TextView) findViewById(R.id.txtProxDose);

        cpf = (EditText) findViewById(R.id.edtUsuario);
        senha = (EditText) findViewById(R.id.edtSenha);
        login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cpf.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "CPF não informado.", Toast.LENGTH_LONG).show();
                    return;
                }

                if (senha.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Informe a senha.", Toast.LENGTH_LONG).show();
                    return;
                }

                new PesquisaUser().execute();
                new PesquisaVacina().execute();
            }
        });
    }



    private void CriaListaVacinas() {
        ArrayAdapter<Vacinas> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, resultVacinas);
        vacinas.setAdapter(adapter);
    }

    private class PesquisaUser extends AsyncTask<Void, Void, List<Usuario>> {

        ProgressDialog load;//IRÁ APRESENTAR UMA TELA DE AGUARDANDO PARA O USUÁRIO

        @Override
        protected void onPreExecute() {
            //DEVE-SE INICIALIZAR "CRIAR" O PROGRESS DIALOG.
            //PASSANDO A INFORMAÇÃO PARA O USUÁRIO PARA AGUARDAR...
            load = ProgressDialog.show(MainActivity.this, "Buscando...", "Por Favor Aguarde...");
        }

        @Override
        protected List<Usuario> doInBackground(Void... voids) {
            //EXECUTA A BUSCA E DEVOLVE O RESULTADO.

            ConsultaUsuario consUser = new ConsultaUsuario(MainActivity.this);

            return consUser.getUsuario( cpf.getText().toString() );
        }

        @Override
        protected void onPostExecute(List<Usuario> user) {
            //ADICIONA O RESULTADO E FECHA A TELA DE BUSCANDO.
            resultUser = user;
            load.dismiss();

//            CriaListaVacinas();
        }
    }

    private class PesquisaVacina extends AsyncTask<Void, Void, List<Vacinas>> {

        ProgressDialog load;//IRÁ APRESENTAR UMA TELA DE AGUARDANDO PARA O USUÁRIO

        @Override
        protected void onPreExecute() {
            //DEVE-SE INICIALIZAR "CRIAR" O PROGRESS DIALOG.
            //PASSANDO A INFORMAÇÃO PARA O USUÁRIO PARA AGUARDAR...
            load = ProgressDialog.show(MainActivity.this, "Buscando...", "Por Favor Aguarde...");
        }

        @Override
        protected List<Vacinas> doInBackground(Void... voids) {
            //EXECUTA A BUSCA E DEVOLVE O RESULTADO.

            ConsultaVacinas vacinas = new ConsultaVacinas(MainActivity.this);

            return vacinas.getVacinas();
        }

        @Override
        protected void onPostExecute(List<Vacinas> vacinas) {
            //ADICIONA O RESULTADO E FECHA A TELA DE BUSCANDO.
            resultVacinas = vacinas;
            load.dismiss();

            CriaListaVacinas();
        }
    }
}
