import java.util.ArrayList;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {

        List<Combination> container = new ArrayList<Combination>();
        FileHandler fileHandler = new FileHandler();

        container = fileHandler.Reader("C:\\Users\\Mig\\Desktop\\kmeans.txt");


        Cluster[] cluster = new Cluster[container.get(0).numOfClusters];

        //System.out.println(container.get(0).factors.get(0).name + " " + container.get(0).numOfClusters);

        Kmeans kmeans = new Kmeans();
        kmeans.startPosition(container, cluster);

        //for(int i = 0; i < container.get(0).factors.size(); i++) {

            //kmeans.euclidianDistance(container, cluster);
            //System.out.println(container.get(0).factors.get(i).cluster);
        //}

        for(int i = 0; i < 10; i++){
            kmeans.euclidianDistance(container, cluster);
            kmeans.clustersChanging(container, cluster);
            //System.out.println(container.get(0).factors.get(i).cluster);
           // System.out.println();
        }

        int cluster1 = 0, cluster2 = 0, cluster3 = 0, cluster4 = 0, cluster5 = 0, cluster6 = 0;

        for(int i = 0; i < container.get(0).factors.size(); i++){
            if(container.get(0).factors.get(i).cluster == 0) cluster1++;
            if(container.get(0).factors.get(i).cluster == 1) cluster2++;
            if(container.get(0).factors.get(i).cluster == 2) cluster3++;
            if(container.get(0).factors.get(i).cluster == 3) cluster4++;
            if(container.get(0).factors.get(i).cluster == 4) cluster5++;
            if(container.get(0).factors.get(i).cluster == 5) cluster6++;
        }

        for(int i = 0; i < container.get(0).factors.size(); i++) {
            System.out.println(container.get(0).factors.get(i).name + " " + container.get(0).factors.get(i).cluster);
        }

        System.out.println("\nКластер 1: " + cluster1 + "\nКластер 2: " + cluster2 + "\nКластер 3: " +
                cluster3 + "\nКластер 4: " + cluster4 + "\nКластер 5: " + cluster5);
    }
}
