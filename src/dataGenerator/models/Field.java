package dataGenerator.models;

import java.util.List;

public abstract class Field {

    public String name;
    public int frequency;
    public int equalityFrequency;
    public List<String> operators;

    public Field() {}
    public Field(String name, int frequency, int equalityFrequency, List<String> operators) {
        this.name = name;
        this.frequency = frequency;
        this.equalityFrequency = equalityFrequency;
        if(!operators.isEmpty()){
            for(int i = 0; i < operators.size(); i++){
                this.operators.set(i, operators.get(i));
            }
        }

    }

    public abstract String getRandomValue();

    public abstract String getRandomOperator();

    public abstract String getRandomOperatorNoEqual();
}
