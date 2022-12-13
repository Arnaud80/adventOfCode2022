package day9;

import utils.Coordinate;
import utils.InputFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day9 {
    public static void main(String... args)  throws IOException {
        ArrayList<String> lines = new InputFileReader("resources/day9.txt").getLines();

        ArrayList<Coordinate> headPath = new ArrayList<>();
        ArrayList<Coordinate> shortTailPath = new ArrayList<>();
        Set<Coordinate> uniqueShortTailPos = new HashSet<>();
        Set<Coordinate> uniqueLongTailPos = new HashSet<>();

        int tailSize=9;

        Coordinate headPos = new Coordinate(100,100);
        Coordinate shortTailPos = new Coordinate(100,100);
        Coordinate[] knotsTail = new Coordinate[tailSize];
        for(int i=0; i<tailSize; i++) knotsTail[i] = new Coordinate(100, 100);

        headPath.add(new Coordinate(headPos));
        shortTailPath.add(new Coordinate(shortTailPos));
        uniqueShortTailPos.add(new Coordinate(shortTailPos));
        uniqueLongTailPos.add(new Coordinate(knotsTail[tailSize - 1]));

        for(String line: lines) {
            String direction = line.split(" ")[0];
            Integer distance = Integer.valueOf(line.split(" ")[1]);

            switch (direction) {
                case "R" :
                    for(int i=0; i< distance; i++) {
                        Coordinate oldHeadPos = new Coordinate(headPos);
                        headPos.setX(headPos.getX() + 1);
                        headPath.add(new Coordinate(headPos));

                        shortTailPos = calculateShortTailPos(headPos, shortTailPos);
                        knotsTail = calculateLongTailPos(headPos, knotsTail);

                        shortTailPath.add(new Coordinate(shortTailPos));
                        uniqueShortTailPos.add(new Coordinate(shortTailPos));
                        uniqueLongTailPos.add(new Coordinate(knotsTail[tailSize-1]));
                    }
                    break;
                case "L" :
                    for(int i=0; i< distance; i++) {
                        Coordinate oldHeadPos = new Coordinate(headPos);
                        headPos.setX(headPos.getX() - 1);
                        headPath.add(new Coordinate(headPos));

                        shortTailPos = calculateShortTailPos(headPos, shortTailPos);
                        knotsTail = calculateLongTailPos(headPos, knotsTail);

                        shortTailPath.add(new Coordinate(shortTailPos));
                        uniqueShortTailPos.add(new Coordinate(shortTailPos));
                        uniqueLongTailPos.add(new Coordinate(knotsTail[tailSize-1]));
                    }
                    break;
                case "U" :
                    for(int i=0; i< distance; i++) {
                        Coordinate oldHeadPos = new Coordinate(headPos);
                        headPos.setY(headPos.getY() - 1);
                        headPath.add(new Coordinate(headPos));

                        shortTailPos = calculateShortTailPos(headPos, shortTailPos);
                        knotsTail = calculateLongTailPos(headPos, knotsTail);

                        shortTailPath.add(new Coordinate(shortTailPos));
                        uniqueShortTailPos.add(new Coordinate(shortTailPos));
                        uniqueLongTailPos.add(new Coordinate(knotsTail[tailSize-1]));
                    }
                    break;
                case "D" :
                    for(int i=0; i< distance; i++) {
                        Coordinate oldHeadPos = new Coordinate(headPos);
                        headPos.setY(headPos.getY() + 1);
                        headPath.add(new Coordinate(headPos));

                        shortTailPos = calculateShortTailPos(headPos, shortTailPos);
                        knotsTail = calculateLongTailPos(headPos, knotsTail);

                        shortTailPath.add(new Coordinate(shortTailPos));
                        uniqueShortTailPos.add(new Coordinate(shortTailPos));
                        uniqueLongTailPos.add(new Coordinate(knotsTail[tailSize-1]));
                    }
                    break;
            }
        }

        System.out.println("Nb Tail unique position : " + uniqueShortTailPos.size());
        System.out.println("Nb Long Tail unique position : " + uniqueLongTailPos.size());

    }

    private static Coordinate calculateShortTailPos(Coordinate newHeadPos, Coordinate tailPos) {
        int distY = newHeadPos.getY() - tailPos.getY();
        int distX = newHeadPos.getX() - tailPos.getX();

        if(Math.abs(distX)<=1 && Math.abs(distY)<=1) { // Distance from Tail & Queue acceptable
            return new Coordinate(tailPos);
        } else if(Math.abs(distX)>1) { // Move on the same row
            if(distX > 0) {
                return new Coordinate(newHeadPos.getX() - 1, newHeadPos.getY());
            } else {
                return new Coordinate(newHeadPos.getX() + 1, newHeadPos.getY());
            }
        } else { // Move on the same column
            if(distY > 0) {
                return new Coordinate(newHeadPos.getX(), newHeadPos.getY() - 1);
            } else {
                return new Coordinate(newHeadPos.getX(), newHeadPos.getY() + 1);
            }
        }
    }

    private static Coordinate[] calculateLongTailPos(Coordinate newHeadPos, Coordinate[] tailPos) {
        Coordinate[] newTail = new Coordinate[tailPos.length];

        newTail[0] = new Coordinate(calculateShortTailPos(newHeadPos, tailPos[0]));
        for (int i = 1; i < tailPos.length; i++) {
            newTail[i] = new Coordinate(calculateShortTailPos(newTail[i-1], tailPos[i]));
        }

        return newTail;
    }
}
