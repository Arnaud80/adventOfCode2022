package day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day3 {
    public static void main(String[] args) {
        try {
            File file = new File("resources/day3.txt");
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

            ArrayList<String[]> groups = new ArrayList<>();
            int count=0;
            String bags[] = new String[3];
            for(int i=0; i < lines.size(); i++) {
                bags[i%3] = lines.get(i);
                if(i > 0 && i%3 == 2) {
                    groups.add(bags);
                    bags = new String[3];
                }
            }

            result = groups.stream().mapToInt(group -> calculatePart2(group)).sum();
            System.out.println("total score Part 2 = " + result);
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public static Integer calculatePart1(String line) {
        String part1 = line.substring(0,line.length()/2);
        String part2 = line.substring(line.length()/2,line.length());
        Set<Integer> setOfChar = new HashSet<>();

        part1.chars().forEach(i -> setOfChar.add(i));

        OptionalInt item = part2.chars().filter(c -> setOfChar.contains(c)).findFirst();
        //OptionalInt item = line.chars().filter(c -> (!setOfChar.add(c))).reduce((s, c) -> c);
        //Character c = (char)item.getAsInt();

        //System.out.println(part1 + " / " + part2 + " : " + (char)item.getAsInt() + " as priority " + getPriority(item.getAsInt()));
        return getPriority(item.getAsInt());
    };

    public static Integer getPriority(Integer i) {
        if(i>96) return i - 96;
        else return i - 64 + 26;
    }

    public static Integer calculatePart2(String[] group) {
        OptionalInt priority = group[0].chars().filter(
                    c -> group[1].contains(Character.valueOf((char)c).toString()) &&
                         group[2].contains(Character.valueOf((char)c).toString())).findFirst();
        //System.out.println((char)priority.getAsInt());
        return getPriority(priority.getAsInt());
    };
}
