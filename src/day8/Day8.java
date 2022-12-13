package day8;

import utils.InputFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day8 {
    public static void main(String... args) throws IOException {
        ArrayList<String> lines = new InputFileReader("resources/day8.txt").getLines();
        Integer width = lines.get(0).length();
        Integer length = lines.size();
        Integer[][] tableTree = new Integer[width][length];

        // Fill the tableTree
        for(int y=0; y<length; y++) {
            for(int x=0; x<width; x++) {
                tableTree[x][y] = lines.get(y).charAt(x)-48; // '0' = 48
            }
        }

        // Count the number of visible trees
        Integer visibles = width * 2 + length * 2 - 4;
        ArrayList<Integer> scoresPost = new ArrayList<>();
        for(int y=1; y<length-1; y++) {
            for(int x=1; x<width-1; x++) {
                Boolean westVisible=true; int westDistance=0;
                Boolean eastVisible=true; int eastDistance=0;
                Boolean northVisible=true; int northDistance=0;
                Boolean southVisible=true; int southDistance=0;
                for(int j=x-1; j>=0; j--) { // Check on the Left
                    westDistance++;
                    if (tableTree[j][y] >= tableTree[x][y]) {
                        westVisible = false;
                        break;
                    }
                }
                for(int j=x+1; j<width; j++) { // Check on the Right
                    eastDistance++;
                    if(tableTree[j][y] >= tableTree[x][y]) {
                        eastVisible=false;
                        break;
                    }
                }

                for(int j=y-1; j>=0; j--) { // Check on the Top
                    southDistance++;
                    if(tableTree[x][j] >= tableTree[x][y]) {
                        northVisible=false;
                        break;
                    }
                }

                for(int j=y+1; j<length; j++) { // Check on the Bottom
                    northDistance++;
                    if(tableTree[x][j] >= tableTree[x][y])
                    {
                        southVisible=false;
                        break;
                    }
                }

                if(westVisible || eastVisible || northVisible || southVisible) visibles++;
                scoresPost.add(westDistance * eastDistance * northDistance * southDistance);
            }
        }

        Collections.sort(scoresPost);
        System.out.println("Total visibles : " + visibles);
        System.out.println("Best score post : " + scoresPost.get(scoresPost.size()-1));
    }
}
