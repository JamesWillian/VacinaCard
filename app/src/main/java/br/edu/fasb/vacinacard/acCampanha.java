package br.edu.fasb.vacinacard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class acCampanha extends AppCompatActivity {

    private TextView vacinou;
    private TextView campanha;
    private TextView data;
    private TextView local;
    private TextView proximadose;

    private ListView campanhas;
    private List<Campanha> resultCampanha;
    private List<Vacinou>  resultVacinou;

    String vacina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campanhas);

        campanhas = (ListView) findViewById(R.id.lvCampanhas);

        vacinou = (TextView) findViewById(R.id.txtVacinado);
        campanha = (TextView) findViewById(R.id.txtCamp);
        data = (TextView) findViewById(R.id.txtData);
        local = (TextView) findViewById(R.id.txtLocal);
        proximadose = (TextView) findViewById(R.id.txtProxDose);

        Bundle posicao = getIntent().getExtras();

        if (posicao != null) {
            vacina = posicao.getString("vacina");
        }

        //CAMPANNHAS
        final ConsultaCampanha consCamp = new ConsultaCampanha(acCampanha.this);

        Thread pesquisaCamp = new Thread(new Runnable()
        {
            public void run()
            {
                resultCampanha = consCamp.getCampanha( vacina );
            }
        });
        pesquisaCamp.start();

        //DETALHES
        final ConsultaDetalhes consDet = new ConsultaDetalhes(acCampanha.this);

        Thread pesquisaDet = new Thread(new Runnable()
        {
            public void run()
            {
                resultVacinou = consDet.getDetalhes( "","" );
            }
        });
        pesquisaDet.start();
    }
}
