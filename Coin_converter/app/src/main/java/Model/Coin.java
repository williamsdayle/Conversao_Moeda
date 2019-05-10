package Model;

public class Coin {

    private String name;
    private double value;

    public Coin(){}

    public Coin(String name, double value){
        this.name = name;
        this.value = value;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setValue(double value){
        this.value = value;
    }

    public double getValue(){
        return this.value;
    }


    @Override
    public String toString() {
        String[] valor = name.split("_");
        String valueName = valor[1];
        if(valueName.equals("EUR\"")){
            return "Euro: "+String.format("%.2f", value)+" €";
        }
        if(valueName.equals("INR\"")){
            return "Rupia Indiana: "+String.format("%.2f", value)+" ₹";
        }
        if(valueName.equals("CHF\"")){
            return "Franco Suiço: "+String.format("%.2f", value)+" SFr";
        }
        if(valueName.equals("BTC\"")){
            return "Bitcoin: "+String.format("%.6f", value) + " B";
        }
        if(valueName.equals("BRL\"")){
            return "Real: "+String.format("%.2f", value)+" R$";
        }
        if(valueName.equals("USD\"")){
            return "Dolar: "+String.format("%.2f", value)+" $";
        }
        else
            return "";
    }


}
