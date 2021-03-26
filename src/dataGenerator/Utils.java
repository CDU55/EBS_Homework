package dataGenerator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import dataGenerator.generators.PublicationsGenerator;
import dataGenerator.models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static int columnLength(int[][] matrix,int columnIndex)
    {
        if(columnIndex>=matrix[0].length || columnIndex<0)
        {
            return -1;
        }
        else
        {
            int elementsCount=0;
            for(int currentLineIndex=0;currentLineIndex<matrix.length;currentLineIndex++)
            {
                if(matrix[currentLineIndex][columnIndex]!=0)
                {
                    elementsCount++;
                }
            }
            return elementsCount;
        }
    }

    public static int lineLength(int[][] matrix,int lineIndex)
    {
        if(lineIndex>=matrix.length || lineIndex<0)
        {
            return -1;
        }
        else
        {
            int elementsCount=0;
            for(int currentColumnIndex=0;currentColumnIndex<matrix[0].length;currentColumnIndex++)
            {
                if(matrix[lineIndex][currentColumnIndex]!=0)
                {
                    elementsCount++;
                }
            }
            return elementsCount;
        }
    }

    public static int calculatePercentage(int total,int percentage)
    {
        if(percentage<0 || percentage>100 || total<0)
        {
            return -1;
        }
        if(percentage==0)
        {
            return 0;
        }
        float percentageNumber=((float)percentage)/100;
        float result=((float)total)*percentageNumber;
        if((int)result!=result)
        {
            return -1;
        }
        else
        {
            return (int)result;
        }
    }

    public static InputModel readInput(String path, PublicationsGenerator publicationsGenerator)
    {

        //creating a JSONParsing object
        JSONParser jsonParser = new JSONParser();
        InputModel model = new InputModel();

        try{
            //parsing the contents of the JSON file
            JSONObject obj = (JSONObject) jsonParser.parse(new FileReader(path));
            //publicationsNumber
            int publicationsNumber = ((Long)obj.get("publicationsNumber")).intValue();
            model.publicationsNumber = publicationsNumber;
            //subscriptionsNumber
            int subscriptionsNumber = ((Long)obj.get("subscriptionsNumber")).intValue();
            model.subscriptionsNumber = subscriptionsNumber;

            //creating the IntField object "stationid"
            JSONObject jstation = (JSONObject) obj.get("stationid");
            IntField station = new IntField();
            station.name = (String)jstation.get("name");
            station.frequency = ((Long)jstation.get("frequency")).intValue();
            station.equalityFrequency = ((Long)jstation.get("equalityFrequency")).intValue();
            station.MinValue = ((Long)jstation.get("minValue")).intValue();
            station.MaxValue = ((Long)jstation.get("maxValue")).intValue();
            station.operators = (List<String>)jstation.get("operators");
            model.fields = new ArrayList<Field>();
            model.fields.add(station);
            publicationsGenerator.stationIdField=station;

            //creating the StringField object "city"
            JSONObject jcity = (JSONObject) obj.get("city");
            StringField city = new StringField();
            city.name = (String)jcity.get("name");
            city.frequency = ((Long)jcity.get("frequency")).intValue();
            city.equalityFrequency = ((Long)jcity.get("equalityFrequency")).intValue();
            city.values = (ArrayList<String>)jcity.get("values");
            city.operators = (List<String>)jcity.get("operators");
            model.fields.add(city);
            publicationsGenerator.cityField=city;


            //creating the IntField object "temp"
            JSONObject jtemp = (JSONObject) obj.get("temp");
            IntField temp = new IntField();
            temp.name = (String)jtemp.get("name");
            temp.frequency = ((Long)jtemp.get("frequency")).intValue();
            temp.equalityFrequency = ((Long)jtemp.get("equalityFrequency")).intValue();
            temp.MinValue = ((Long)jtemp.get("minValue")).intValue();
            temp.MaxValue = ((Long)jtemp.get("maxValue")).intValue();
            temp.operators = (List<String>)jtemp.get("operators");
            model.fields.add(temp);
            publicationsGenerator.tempField=temp;

            //creating the DoubleField object "rain"
            JSONObject jrain = (JSONObject) obj.get("rain");
            DoubleField rain = new DoubleField();
            rain.name = (String)jrain.get("name");
            rain.frequency = ((Long)jrain.get("frequency")).intValue();
            rain.equalityFrequency = ((Long)jrain.get("equalityFrequency")).intValue();
            rain.MinValue = ((Number) jrain.get("minValue")).doubleValue();
            rain.MaxValue = ((Number) jrain.get("maxValue")).doubleValue();
            rain.operators = (List<String>)jrain.get("operators");
            model.fields.add(rain);
            publicationsGenerator.rainField=rain;

            //creating the IntField object "wind"
            JSONObject jwind = (JSONObject) obj.get("wind");
            IntField wind = new IntField();
            wind.name = (String)jwind.get("name");
            wind.frequency = ((Long)jwind.get("frequency")).intValue();
            wind.equalityFrequency = ((Long)jwind.get("equalityFrequency")).intValue();
            wind.MinValue = ((Long)jwind.get("minValue")).intValue();
            wind.MaxValue = ((Long)jwind.get("maxValue")).intValue();
            wind.operators = (List<String>)jwind.get("operators");
            model.fields.add(wind);
            publicationsGenerator.windField=wind;


            //creating the StringField object "direction"
            JSONObject jdirection = (JSONObject) obj.get("direction");
            StringField direction = new StringField();
            direction.name = (String)jdirection.get("name");
            direction.frequency = ((Long)jdirection.get("frequency")).intValue();
            direction.equalityFrequency = ((Long)jdirection.get("equalityFrequency")).intValue();
            direction.values = (ArrayList<String>)jdirection.get("values");
            direction.operators = (List<String>)jdirection.get("operators");
            model.fields.add(direction);
            publicationsGenerator.directionField=direction;

            //creating the DateField object "date"
            JSONObject jdate = (JSONObject) obj.get("date");
            DateField date = new DateField();
            date.name = (String)jdate.get("name");
            date.frequency = ((Long)jdate.get("frequency")).intValue();
            date.equalityFrequency = ((Long)jdate.get("equalityFrequency")).intValue();
            date.MinValue = (String)jdate.get("minValue");
            date.MaxValue = (String)jdate.get("maxValue");
            date.operators = (List<String>)jdate.get("operators");
            model.fields.add(date);
            publicationsGenerator.dateField=date;


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return model;
    }
    public static boolean writeOutput(String path, List<String> publications, List<String> subscriptions)
    {
        JSONObject jo=new JSONObject();
        jo.put("publications",publications);
        jo.put("subscriptions",subscriptions);
        try {
            FileWriter writer=new FileWriter(path);
            jo.writeJSONString(writer);
            writer.close();
        } catch (IOException e) {
           return false;
        }
        return true;
    }
}
