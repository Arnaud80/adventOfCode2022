package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Day1 {
    public static void main(String[] args) {
        File file = new File("ressources/day1.txt");
        FileReader fileReader;
        BufferedReader bufferedReader;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;

            System.out.println("Start loop");
            ArrayList<Elve> elves = new ArrayList<>();

            int elve=0;
            int calories=0;
            int i=0;
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println("#" + i++ + " " + line);
                if (line.equals("")) {
                    //System.out.println("day1.Elve " + elve + " : " + calories);
                    elves.add(new Elve(elve, calories));
                    calories = 0;
                    elve++;
                } else {
                    calories += Integer.parseInt(line);
                }
            }
            elves.add(new Elve(elve, calories));

            fileReader.close();
            Collections.sort(elves);
            System.out.println(elves);

            //System.out.println("Max : " + elves.stream().max(day1.Elve::compareTo));
            System.out.println("Max = " + elves.get(elves.size()-1).getCalories());
            int top3 = 0;

            for(i = elves.size() - 1; i > elves.size() - 4; i--) { top3+=elves.get(i).getCalories(); };
            System.out.println("Top3 = " + top3);
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
}
