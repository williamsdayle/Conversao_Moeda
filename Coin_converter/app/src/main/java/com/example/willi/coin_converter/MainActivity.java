package com.example.willi.coin_converter;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.List;
import java.util.concurrent.ExecutionException;
import ConnectionAndManipulation.HTTPService;
import Model.Coin;
import ConnectionAndManipulation.Methods;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner spMoeda = (Spinner)findViewById(R.id.moedasTroca);
        final EditText etValor = (EditText) findViewById(R.id.valor_moeda);
        final Button btTrocarMoeda = (Button) findViewById(R.id.btn_exchange);
        final Methods metodos = new Methods();



        btTrocarMoeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verificaConexao(getApplicationContext())) {
                    if (!etValor.getText().toString().isEmpty() && !spMoeda.getSelectedItem().toString().isEmpty()) {
                        if (metodos.isValueValid(etValor.getText().toString())) {
                            final HTTPService service = new HTTPService(metodos.changeCodeCoin(spMoeda.getSelectedItem().toString()));
                            try {
                                StringBuilder valuesFromJson = service.execute().get();
                                List<Coin> nomesValores = metodos.getNameAndValues(valuesFromJson.toString());
                                List<Coin> valoresAtualizados = metodos.calculateValues(nomesValores, Double.parseDouble(etValor.getText().toString()));
                                adicionarListaToListView(valoresAtualizados);


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Digite um valor Válido.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Digite um valor a ser trocado e selecione uma moeda de troca.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Sem conexão!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void adicionarListaToListView(List<Coin> coins){
        final ListView lvMoedas = (ListView) findViewById(R.id.lista_moedas);
        ArrayAdapter<Coin> adapter = new ArrayAdapter<Coin>(this,
                android.R.layout.simple_list_item_1, coins);
        lvMoedas.setAdapter(adapter);
    }

    public boolean verificaConexao(Context contexto){

        ConnectivityManager conectManager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo informacaoNet = conectManager.getActiveNetworkInfo();
        if (informacaoNet != null && informacaoNet.isConnectedOrConnecting() && informacaoNet.isAvailable())
            return true;
        else
            return false;
    }


}
