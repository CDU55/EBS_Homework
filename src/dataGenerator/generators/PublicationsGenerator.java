package dataGenerator.generators;

import dataGenerator.models.Field;
import dataGenerator.models.Publication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PublicationsGenerator {

    public Field stationIdField;
    public Field cityField;
    public Field tempField;
    public Field rainField;
    public Field windField;
    public Field directionField;
    public Field dateField;

    public List<Publication> generatePublications(int publicationsCount)
    {
        List<Publication> result=new ArrayList<Publication>();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        for(int publicationIndex=0;publicationIndex<publicationsCount;publicationIndex++)
        {
            Publication currentPublication=new Publication();
            currentPublication.stationId=Integer.parseInt(stationIdField.getRandomValue());
            currentPublication.city=cityField.getRandomValue();
            currentPublication.temp=Integer.parseInt(tempField.getRandomValue());
            currentPublication.rain=Double.parseDouble(rainField.getRandomValue());
            currentPublication.wind=Integer.parseInt(windField.getRandomValue());
            currentPublication.direction=directionField.getRandomValue();
            try {
                currentPublication.date=dateFormat.parse(dateField.getRandomValue());
            } catch (ParseException e) {
                return null;
            }
            result.add(currentPublication);
        }
        return result;
    }
}
