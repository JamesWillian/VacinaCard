package br.edu.fasb.vacinacard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class acLogin extends AppCompatActivity {

    private EditText cpf;
    private EditText senha;
    private Button   login;

    private List<Usuario>  resultUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        cpf = (EditText) findViewById(R.id.edtUsuario);
        senha = (EditText) findViewById(R.id.edtSenha);
        login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            private ConsultaUsuario consUser;

            @Override
            public void onClick(View v) {

                if (cpf.getText().toString().trim().length() == 0) {
                    Toast.makeText(acLogin.this, "CPF não informado.", Toast.LENGTH_LONG).show();
                    return;
                }

                if (senha.getText().toString().trim().length() == 0) {
                    Toast.makeText(acLogin.this, "Informe a senha.", Toast.LENGTH_LONG).show();
                    return;
                }

                final ConsultaUsuario consUser = new ConsultaUsuario(acLogin.this);

                //CONSULTA O USUÁRIO PELO CPF E SENHA
                Thread pesquisaUser = new Thread(new Runnable()
                {
                    public void run()
                    {
                        resultUser = consUser.getUsuario( cpf.getText().toString(), senha.getText().toString() );
                    }
                });
                pesquisaUser.start();

                if (consUser != null) {
                    Intent acUser = new Intent(acLogin.this, acUsuario.class);
//                    acUser.putExtra("cpf", resultUser.get(1).getCpf());
//                    acUser.putExtra("senha", resultUser.get(1).getSenha());
                    startActivity(acUser);
                }

            }
        });
    }
}
