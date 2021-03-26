package dataGenerator.models;

import java.util.ArrayList;
import java.util.Random;

public class StringField extends Field{

    public ArrayList<String> values;

    public String getRandomValue(){
        Random rand = new Random();
        int upperbound = values.size();

        int index_random = rand.nextInt(upperbound);

        return values.get(index_random);

    }

    public String getRandomOperator(){
        Random rand = new Random();
        int upperbound = operators.size();

        int index_random = rand.nextInt(upperbound);

        return operators.get(index_random);
    }

    public String getRandomOperatorNoEqual(){
        return operators.get(1);
    }

    @Override
    public String toString(){
        String result = "Name: " + name + "\n" +
                "frequency: " + frequency + "\n" +
                "equalityFrequency:" + equalityFrequency + "\n" +
                "values: ";
        for(int i=0; i < values.size(); i++){
            result += values.get(i);
            result += " ";
        }
        result += "\n" + "operators: ";
        for(int i=0; i < operators.size(); i++){
            result += operators.get(i);
            result += " ";
        }
        result += "\n";
        return result;
    }

}
