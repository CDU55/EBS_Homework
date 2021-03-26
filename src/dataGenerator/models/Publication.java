package dataGenerator.models;

import java.util.Date;

public class Publication {

    public int stationId;
    public String city;
    public int temp;
    public double rain;
    public int wind;
    public String direction;
    public Date date;

    @Override
    public String toString() {
        return "Publication{" +
                "stationId=" + stationId +
                ", city='" + city + '\'' +
                ", temp=" + temp +
                ", rain=" + rain +
                ", wind=" + wind +
                ", direction='" + direction + '\'' +
                ", date=" + date +
                '}';
    }
}
