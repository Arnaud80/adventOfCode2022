package day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Day2 {
    public static void main(String[] args) {
        try {
            File file = new File("resources/day2.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            ArrayList<String> lines = new ArrayList<>();

            System.out.println("Start loop");

            while ((line = bufferedReader.readLine()) != null) {
                if(line.equals("")) {

                } else {
                    lines.add(line);
                }
            }
            fileReader.close();

            Integer result = lines.stream().mapToInt(l -> calculatePart1(l)).sum();
            System.out.println("total score Part 1 = " + result);

            result = lines.stream().mapToInt(l -> calculatePart2(l)).sum();
            System.out.println("total score Part 2 = " + result);
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public static Integer calculatePart1(String line) {
        int score=0;

        switch(line) {
            case "A X":
                score+=(1 + 3);
                break;
            case "A Y":
                score+=(2 + 6);
                break;
            case "A Z":
                score+=(3 + 0);
                break;
            case "B X":
                score+=(1 + 0);
                break;
            case "B Y":
                score+=(2 + 3);
                break;
            case "B Z":
                score+=(3 + 6);
                break;
            case "C X":
                score+=(1 + 6);
                break;
            case "C Y":
                score+=(2 + 0);
                break;
            case "C Z":
                score+=(3 + 3);
                break;
        }

        //System.out.println(score);

      return score;
    };

    public static Integer calculatePart2(String line) {
        int score=0;

        switch(line) {
            case "A X":
                score+=(3 + 0);
                break;
            case "B X":
                score+=(1 + 0);
                break;
            case "C X":
                score+=(2 + 0);
                break;
            case "A Y":
                score+=(1 + 3);
                break;
            case "B Y":
                score+=(2 + 3);
                break;
            case "C Y":
                score+=(3 + 3);
                break;
            case "A Z":
                score+=(2 + 6);
                break;
            case "B Z":
                score+=(3 + 6);
                break;
            case "C Z":
                score+=(1 + 6);
                break;
        }

        //System.out.println(score);

        return score;
    };
}
