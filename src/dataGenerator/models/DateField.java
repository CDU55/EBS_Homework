package dataGenerator.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateField extends Field{

    public String MinValue;
    public String MaxValue;

    public String getRandomValue(){
        String[] minval = MinValue.split("/");
        String[] maxval = MaxValue.split("/");



        LocalDate start = LocalDate.of(Integer.parseInt(minval[2]), Integer.parseInt(minval[1]), Integer.parseInt(minval[0]));
        LocalDate end = LocalDate.of(Integer.parseInt(maxval[2]), Integer.parseInt(maxval[1]), Integer.parseInt(maxval[0]));

        long days = DAYS.between(start, end);

        LocalDate random = start.plusDays(ThreadLocalRandom.current().nextInt((int) (days+1)));


        return String.valueOf(random);
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
