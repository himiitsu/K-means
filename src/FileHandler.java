import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.lang.String;

public class FileHandler {

    public static List<Combination> Reader(String path){

        List<Combination> container = new ArrayList<Combination>();
        Combination combination = new Combination();
        combination.factors = new ArrayList<Factors>();
        combination.characteristics = new ArrayList<String>();

        int i = 0;
        int count = 0;
        System.out.println("Файл:");

        try{
            FileInputStream fstream = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            //Чтение файла построчно
            while ((strLine = br.readLine()) != null){
                if(count == 0){
                    combination.numOfClusters = Integer.parseInt(strLine);
                    count++;
                    System.out.println("Количество кластеров: " + combination.numOfClusters);
                    continue;
                }

                /*if(count == 1){
                    String[] chars = strLine.split(",");
                    combination.characteristics.add(0, chars[0]);
                    combination.characteristics.add(0, chars[1]);
                    combination.characteristics.add(0, chars[2]);
                    combination.characteristics.add(0, chars[3]);
                    combination.characteristics.add(0, chars[4]);
                    continue;
                }*/

                String[] result = strLine.split(",");
                //Добавление значения в список
                combination.factors.add(i, new Factors(result[0], Integer.parseInt(result[1]), Double.parseDouble(result[2]),
                        Double.parseDouble(result[3]), Double.parseDouble(result[4]), Integer.parseInt(result[5]), 0));
                System.out.println(combination.factors.get(i).name + " " + combination.factors.get(i).temperature + " " + combination.factors.get(i).mass +
                        combination.factors.get(i).diameter + " " + combination.factors.get(i).shining + " " + combination.factors.get(i).hydrogen);
                i++;
            }
        }catch (IOException e){
            System.out.println("Ошибка");
        }
        container.add(combination);
        System.out.println();
        return container;
    }
}
