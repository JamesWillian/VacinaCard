package br.edu.fasb.vacinacard;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ConsultaVacinas {

    private String site = "http://gtx.net.br/vacinacard/consulta_vacinas.php";
    private URL url;
    private Activity contexto;

    public ConsultaVacinas(Activity contexto ) {
        this.contexto = contexto;
    }

    public List<Vacinas> getVacinas() {

        try {
            //PREPARA-SE PARA A BUSCA
            url = new URL(site);
//            Log.i(APPLOG, "URL: "+site);
            StringBuffer textoHTML = new StringBuffer();

            try {
                //REALIZA O TRATAMENTO DE BUSCA
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("GET");
                http.connect();

                InputStream dados = http.getInputStream();

                if (dados == null) {
                    //CASO VENHA NULO A REQUISIÇÃO ABORTA.
                    return new ArrayList<>();
                }

                //PREPARA-SE PARA REALIZAR A LEITURA DA PÁGINA
                BufferedReader reader = new BufferedReader(new InputStreamReader(dados));
                String linha;


                //LENDO A PÁGINA HTML
                while ((linha = reader.readLine()) != null){
                    textoHTML.append(linha);
                }

                if (textoHTML.length() == 0){
                    //RETORNA VAZIO CASO NÃO ENCONTRE NADA.
                    return new ArrayList<>();
                }

                if (http != null){
                    http.disconnect();//Desconectando da página...
                }

                if (reader != null){
                    reader.close();  //fechando arquivo de leitura.
                }

            } catch (IOException e) {
//                Log.e(APPLOG, e.getMessage());
                //     Toast.makeText(this.contexto, "Tivemos na requisição HTML", Toast.LENGTH_SHORT).show();
                return new ArrayList<>();
            }

            List<Vacinas> resultado = new ArrayList<>();

            try {
                //TRABALHANDO COM JSON CONVERANDO O ARQUIVO TEXTO PARA JSON
                JSONObject json = new JSONObject(textoHTML.toString());
//

//                Log.i(APPLOG, "Json recebido: " + json.toString());

                //pegando o resultado da pesquisa para varrer os registros.
                JSONArray lista = json.getJSONArray("Search");

                for ( int i = 0; i<lista.length(); i++ ) {
                    JSONObject object = lista.getJSONObject(i);
                    Vacinas v = new Vacinas(
                            object.getString("vacina"));

                    resultado.add(v);

                }

            } catch (JSONException e) {
//                Log.e(APPLOG, e.getMessage());
            }

            return resultado;

        } catch (MalformedURLException e) {
//            Log.e(APPLOG, e.getMessage());

            return new ArrayList<>();
        }

    }
}
