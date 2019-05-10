package ConnectionAndManipulation;


import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HTTPService extends AsyncTask<Void, Void, StringBuilder>{

    private final String moedaEscolhida;

    public HTTPService(String moedaEscolhida) {
        this.moedaEscolhida = moedaEscolhida;
    }

    @Override
    protected StringBuilder doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL("https://free.currconv.com/api/v7/convert?q="
                    +moedaEscolhida+"_USD,"
                    +moedaEscolhida+"_INR,"
                    +moedaEscolhida+"_BRL,"
                    +moedaEscolhida+"_CHF,"
                    +moedaEscolhida+"_BTC,"
                    +moedaEscolhida+"_EUR&compact=ultra&apiKey=b2e89e7541ea8a61ad3b");
            createConnection(url).connect();
            Scanner leitor = new Scanner(url.openStream());
            while(leitor.hasNext()){
                resposta.append(leitor.next());
            }

        } catch (MalformedURLException e) {
            Toast.makeText(null, "Sem conexão",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(null, "Erro de entrada e saída",Toast.LENGTH_LONG).show();
        }
        return resposta;
    }

    private HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept","application/json");
        connection.setConnectTimeout(5000);
        return connection;

    }


}
