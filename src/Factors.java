
public class Factors {

    String name;
    double temperature;
    double mass;
    double diameter;
    double shining;
    int hydrogen;
    int cluster;

    public Factors(String name, double temperature, double mass, double diameter, double shining, int hydrogen, int cluster){
        this.name = name;
        this.temperature = temperature;
        this.mass = mass;
        this.diameter = diameter;
        this.shining = shining;
        this.hydrogen = hydrogen;
        this.cluster = cluster;
    }

    public void ratioTemperature(double temperature, double max){
        this.temperature = temperature/max;
    }
    public void ratioMass(double mass, double max){
        this.mass = mass/max;
    }
    public void ratioDiameter(double diameter, double max){
        this.diameter = diameter/max;
    }
    public void ratioShining(double shining, double max){
        this.shining = shining/max;
    }
    public void ratioHydrogen(int temperature, int max){
        this.hydrogen = temperature/max;
    }
}