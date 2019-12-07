import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kmeans {

    public Cluster[] startPosition(List<Combination> container, Cluster[] cluster){

        Random random = new Random();

        //cluster[] cluster = new cluster[container.get(0).numOfClusters];

        /*for(int i = 0; i < container.get(0).numOfClusters; i++){

            //cluster[i] = new cluster(i, random.nextInt(30000), random.nextInt(80),
                    //random.nextInt(600), random.nextInt(14000), random.nextInt(4));
            //System.out.println(cluster[i].temperature + " " + cluster[i].mass + " " + cluster[i].diameter + " " + cluster[i].shining
            //+ " " + cluster[i].hydrogen);

        }*/

        Double max[] = new Double[5];
        max[0] = (double)container.get(0).factors.get(0).temperature;
        max[1] = container.get(0).factors.get(0).mass;
        max[2] = container.get(0).factors.get(0).diameter;
        max[3] = container.get(0).factors.get(0).shining;
        max[4] = (double)container.get(0).factors.get(0).hydrogen;

        for(int i = 0; i < 5 ; i++) {
            for (int j = 0; j < container.get(0).factors.size(); j++) {
                if(max[0] < container.get(0).factors.get(j).temperature){
                    max[0] = container.get(0).factors.get(j).temperature;
                }
                if(max[1] < container.get(0).factors.get(j).mass){
                    max[1] = container.get(0).factors.get(j).mass;
                }
                if(max[2] < container.get(0).factors.get(j).diameter){
                    max[2] = container.get(0).factors.get(j).diameter;
                }
                if(max[3] < container.get(0).factors.get(j).shining){
                    max[3] = container.get(0).factors.get(j).shining;
                }
                if(max[4] < (double)container.get(0).factors.get(j).hydrogen){
                    max[4] = (double)container.get(0).factors.get(j).hydrogen;
                }
            }
        }

        for (int i = 0; i < container.get(0).factors.size(); i++) {
            container.get(0).factors.get(i).ratioTemperature(container.get(0).factors.get(i).temperature, max[0]);
            container.get(0).factors.get(i).ratioMass(container.get(0).factors.get(i).mass, max[1]);
            container.get(0).factors.get(i).ratioDiameter(container.get(0).factors.get(i).diameter, max[2]);
            container.get(0).factors.get(i).ratioShining(container.get(0).factors.get(i).shining, max[3]);
            container.get(0).factors.get(i).ratioHydrogen(container.get(0).factors.get(i).hydrogen, (int)Math.round(max[4]));
        }

        for (int i = 0; i < container.get(0).factors.size(); i++) {
            //System.out.println(container.get(0).factors.get(i).diameter);
        }

        int k = container.get(0).numOfClusters;
        int size = container.get(0).factors.size();
        int step = size/k;
        //System.out.println(step);
        int stepper = 0;

        for (int i = 0; i < k; i++, stepper += step){

            //System.out.println(stepper);
            cluster[i] = new Cluster(i, container.get(0).factors.get(stepper).temperature,
                    container.get(0).factors.get(stepper).mass, container.get(0).factors.get(stepper).diameter,
                    container.get(0).factors.get(stepper).shining, container.get(0).factors.get(stepper).hydrogen);
        }

        /*cluster[0] = new cluster(0, 22000, 10, 2.6796, 163.77, 2);
        cluster[1] = new cluster(1, 4666,1.47,9.3,30.835, 0);
        cluster[2] = new cluster(2, 3580, 0.15,0.36,0.0013,0);
        cluster[3] = new cluster(3,8720,2.028,1.8467,14.298,3);
        cluster[4] = new cluster(4, 3850,0.62,0.76,0.026,0);*/
        return cluster;
    }

    public Cluster[] euclidianDistance(List<Combination> container, Cluster[] cluster){

        Double[][] distance = new Double[container.get(0).factors.size()][container.get(0).numOfClusters];
        double min;

        for(int i = 0; i < container.get(0).factors.size(); i++){
            for(int j = 0; j < container.get(0).numOfClusters; j++){

                distance[i][j] = Math.sqrt(Math.pow(cluster[j].temperature - container.get(0).factors.get(i).temperature, 2) +
                        Math.pow(cluster[j].mass - container.get(0).factors.get(i).mass, 2) +
                        Math.pow(cluster[j].diameter - container.get(0).factors.get(i).diameter, 2) +
                        Math.pow(cluster[j].shining - container.get(0).factors.get(i).shining, 2) +
                        Math.pow(cluster[j].hydrogen - container.get(0).factors.get(i).hydrogen, 2));
                //System.out.println(container.get(0).factors.get(i).temperature);
            }

           //System.out.println(distance[i][0]);
        }

        for(int i = 0; i < container.get(0).factors.size(); i++) {
            min = distance[i][0];

            for (int j = 0; j < container.get(0).numOfClusters - 1; j++) {

                if(min > distance[i][j]){
                    min = distance[i][j];
                    container.get(0).factors.get(i).cluster = j;
                   // System.out.println(distance[i][j] + " " + distance[i][j+1]);
                }
                //System.out.println(min);
            }
            //System.out.println();
            //System.out.println(container.get(0).factors.get(i).cluster);
        }

        return  cluster;
    }

    public Cluster[] clustersChanging(List<Combination> container, Cluster[] cluster) {

        Double[] newTemperature = new Double[container.get(0).numOfClusters];
        Double[] newMass = new Double[container.get(0).numOfClusters];
        Double[] newDiameter = new Double[container.get(0).numOfClusters];
        Double[] newShining = new Double[container.get(0).numOfClusters];
        Integer[] newHydrogen = new Integer[container.get(0).numOfClusters];
        Integer[] count = new Integer[container.get(0).numOfClusters];

        for(int i = 0; i < container.get(0).numOfClusters; i++) {
            newTemperature[i] = 0d;
            newMass[i] = 0d;
            newDiameter[i] = 0d;
            newShining[i] = 0d;
            newHydrogen[i] = 0;
            count[i] = 1;
        }

        for(int i = 0; i < container.get(0).numOfClusters; i++) {
            for (int j = 0; j < container.get(0).factors.size(); j++) {

                if(container.get(0).factors.get(j).cluster == i){
                   newTemperature[i] += container.get(0).factors.get(j).temperature;
                   newMass[i] += container.get(0).factors.get(j).mass;
                   newDiameter[i] += container.get(0).factors.get(j).diameter;
                   newShining[i] += container.get(0).factors.get(j).shining;
                   newHydrogen[i] += container.get(0).factors.get(j).hydrogen;
                   count[i]++;
                }
            }
            //System.out.println(i + " " + newTemperature[i]);
        }

        for(int i = 0; i < container.get(0).numOfClusters; i++) {
            cluster[i].temperature = newTemperature[i]/count[i];
            cluster[i].mass = newMass[i]/count[i];
            cluster[i].diameter = newDiameter[i]/count[i];
            cluster[i].shining = newShining[i]/count[i];
            cluster[i].hydrogen = newHydrogen[i]/count[i];
            //System.out.println();
        }

        return cluster;
    }
}
