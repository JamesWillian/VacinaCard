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

public class ConsultaCampanha {

    private String site = "http://gtx.net.br/vacinacard/consulta_camp.php?";
    private URL url;
    private Activity contexto;
    private StringBuilder parametros = new StringBuilder();

    public ConsultaCampanha(Activity contexto ) {
        this.contexto = contexto;
    }

    public List<Campanha> getCampanha(String vacina ) {
        parametros.append( "&vacina="+ URLEncoder.encode(vacina));

        String site = this.site + parametros.toString();

        try {
            url = new URL(site);
//            Log.i(APPLOG, "URL: "+site);
            StringBuffer textoHTML = new StringBuffer();

            try {
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("GET");
                http.connect();

                InputStream dados = http.getInputStream();

                if (dados == null) {
                    return new ArrayList<>();
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(dados));
                String linha;


                while ((linha = reader.readLine()) != null){
                    textoHTML.append(linha);
                }

                if (textoHTML.length() == 0){
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
                return new ArrayList<>();
            }

            List<Campanha> resultado = new ArrayList<>();

            try {
                JSONObject json = new JSONObject(textoHTML.toString());

//                Log.i(APPLOG, "Json recebido: " + json.toString());

                //pegando o resultado da pesquisa para varrer os registros.
                JSONArray lista = json.getJSONArray("Search");

                for ( int i = 0; i<lista.length(); i++ ) {
                    JSONObject object = lista.getJSONObject(i);
                    Campanha camp = new Campanha(
                            object.getString("campanha"),
                            object.getString("ano"));

                    resultado.add(camp);

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
