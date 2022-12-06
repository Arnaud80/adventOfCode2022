package day6;

import utils.InputFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day6 {
    public static void main(String[] args) throws IOException{
        ArrayList<String> lines = new InputFileReader("ressources/day6.txt").getLines();

        String message = lines.get(0);
        char[] charsMessage = message.toCharArray();
        int subroutineSize=14;
        int messageStart=0;

        while(messageStart == 0) {
            for(int i=0; i < charsMessage.length - subroutineSize; i++) {
                Boolean isSubroutine = false;
                HashSet<Character> characterSet = new HashSet<>();
                for(int j=0; j < subroutineSize; j++) {
                    if(!characterSet.add(charsMessage[i + j])) {
                        //i += j;
                        break;
                    } else {
                        if(j == (subroutineSize - 1)) {
                            messageStart = i + j;
                        }
                    }
                }
                if(messageStart != 0) break;
            }
        }
        System.out.println(messageStart + 1);
    }
}
