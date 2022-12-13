package day4;

import utils.InputFileReader;

import java.io.IOException;
import java.util.ArrayList;

public class Day4 {
    public static void main(String[] args) throws IOException{
        ArrayList<String> lines = new InputFileReader("resources/day4.txt").getLines();

        System.out.println("FullOverlap : " + lines.stream().filter(line -> fullOverlap(line)).count());
        System.out.println("Overlap : " + lines.stream().filter(line -> overlap(line)).count());
    }

    private static boolean fullOverlap(String line) {
        String ElvePair[] = line.split(",");
        String ElveRange1[] = ElvePair[0].split("-");
        String ElveRange2[] = ElvePair[1].split("-");

        Integer ElveStart1 = Integer.valueOf(ElveRange1[0]);
        Integer ElveEnd1 = Integer.valueOf(ElveRange1[1]);

        Integer ElveStart2 = Integer.valueOf(ElveRange2[0]);
        Integer ElveEnd2 = Integer.valueOf(ElveRange2[1]);

        if(ElveStart1 <= ElveStart2) {
            if(ElveEnd2 <= ElveEnd1) return true;
        } else {
            if(ElveEnd1 <= ElveEnd2) return true;
        }
        if(ElveStart2 <= ElveStart1) {
            if(ElveEnd1 <= ElveEnd2) return true;
        } else {
            if(ElveEnd2 <= ElveEnd1) return true;
        }

        return false;
    }

    private static boolean overlap(String line) {
        String ElvePair[] = line.split(",");
        String ElveRange1[] = ElvePair[0].split("-");
        String ElveRange2[] = ElvePair[1].split("-");

        Integer ElveStart1 = Integer.valueOf(ElveRange1[0]);
        Integer ElveEnd1 = Integer.valueOf(ElveRange1[1]);

        Integer ElveStart2 = Integer.valueOf(ElveRange2[0]);
        Integer ElveEnd2 = Integer.valueOf(ElveRange2[1]);

        if(ElveStart2 >= ElveStart1 && ElveStart2 <= ElveEnd1) return true;
        if(ElveStart1 >= ElveStart2 && ElveStart1 <= ElveEnd2) return true;

        return false;
    }
}
