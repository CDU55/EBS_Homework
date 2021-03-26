package dataGenerator.models;

import java.util.*;

public class DoubleField extends Field{

    public double MinValue;
    public double MaxValue;

    public String getRandomValue(){
        Random rand = new Random();

        double index_random = MinValue + rand.nextDouble() * (MaxValue - MinValue);

        return String.valueOf(index_random);
    }

    public String getRandomOperator(){
        Random rand = new Random();
        int upperbound = operators.size();

        int index_random = rand.nextInt(upperbound);

        return operators.get(index_random);
    }

    public String getRandomOperatorNoEqual(){
        ArrayList<String> temp = new ArrayList<>();
        for(int i=1; i < operators.size(); i++){
            temp.add(operators.get(i));
        }
        Random rand = new Random();
        int upperbound = temp.size();

        int index_random = rand.nextInt(upperbound);

        return temp.get(index_random);
    }

    @Override
    public String toString(){
        String result = "Name: " + name + "\n" +
                "frequency: " + frequency + "\n" +
                "equalityFrequency:" + equalityFrequency + "\n" +
                "MinValue: " + MinValue + "\n" +
                "MaxValue: " + MaxValue ;
        result += "\n" + "operators: ";
        for(int i=0; i < operators.size(); i++){
            result += operators.get(i);
            result += " ";
        }
        result += "\n";
        return result;
    }

}
