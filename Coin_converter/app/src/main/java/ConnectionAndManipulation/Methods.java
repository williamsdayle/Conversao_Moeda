package ConnectionAndManipulation;

import java.util.ArrayList;
import java.util.List;

import Model.Coin;

public class Methods {

    public boolean isValueValid(String valor){

        return valor.matches("[0-9]{0,6}[.]{0,1}[0-9]{0,7}");
    }

    public String changeCodeCoin(String moeda){
        switch (moeda){
            case "Dolar":
                return "USD";
            case "Real":
                return "BRL";
            case "Euro":
                return "EUR";
            case "Franco Suiço":
                return "CHF";
            case "Rupia Indiana":
                return "INR";
            case "Bitcoin":
                return "BTC";
            default:
                return "";
        }

    }

    public List<Coin> calculateValues(List<Coin> coins, double value){
        for(Coin coin : coins){
            coin.setValue(coin.getValue() * value);
        }
        return coins;
    }


    public List<Coin> getNameAndValues(String valores){
        List<Coin> listaDeMoedas = new ArrayList();
        String[] splitted = valores.split("[{]");
        String lastValue = splitted[1].replace("}","");
        splitted = lastValue.split(",");
        for (String values: splitted){
            String[] valuesSplitted = values.split(":");
            Coin coin = new Coin(valuesSplitted[0], Double.parseDouble(valuesSplitted[1]));
            listaDeMoedas.add(coin);
        }
        return listaDeMoedas;

    }

}
