package day5;

import utils.InputFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Day5 {
    public static void main(String[] args) throws IOException{
        ArrayList<String> lines = new InputFileReader("resources/day5.txt").getLines();

        int qtyOfStack=9;
        HashMap<Integer, Integer> posCharToStack = new HashMap<>();
        posCharToStack.put(1,0);
        posCharToStack.put(5,1);
        posCharToStack.put(9,2);
        posCharToStack.put(13,3);
        posCharToStack.put(17,4);
        posCharToStack.put(21,5);
        posCharToStack.put(25,6);
        posCharToStack.put(29,7);
        posCharToStack.put(33,8);
        //ArrayList<Character>[] stacks = new ArrayList[qtyOfStack];
        Stack<Character>[] stacks = new Stack[qtyOfStack];
        ArrayList<String> orders = new ArrayList<>();

        // ArrayList initialization
        for(int x=0; x<qtyOfStack; x++) {
            //stacks[x] = new ArrayList<Character>();
            stacks[x] = new Stack<Character>();
        }

        int inputPart=0; // 0 : Stacks part, 1 : Orders part
        for(int i=0 ; i<lines.size() ; i++) { // Part separator, we skip the line and move to orders part
            if(lines.get(i).equals("")) {
                inputPart++; i++;
            }

            if(inputPart == 0) { // Stack part
                char[] crateLineChar = lines.get(i).toCharArray();
                for(int j=0; j<crateLineChar.length; j++) {
                    if(crateLineChar[j]>='A' && crateLineChar[j]<='Z') {
                        int stackNumber = posCharToStack.get(j);
                        stacks[stackNumber].push(crateLineChar[j]);
                    };
                }
            } else orders.add(lines.get(i));
        }

        // Invert Stacks
        for(int i = 0; i< qtyOfStack; i++) {
            Stack<Character> invertedStacks = new Stack();
            while(!stacks[i].isEmpty()) invertedStacks.push(stacks[i].pop());
            stacks[i] = invertedStacks;
        }

        // Process Movement
        for(int i = 0; i < orders.size(); i++) {
            String fragmentedOrder[] = orders.get(i).split(" ");
            int nbCrate = Integer.valueOf(fragmentedOrder[1]);
            int from = Integer.valueOf(fragmentedOrder[3]) - 1;
            int to =  Integer.valueOf(fragmentedOrder[5]) - 1;
            Stack<Character> tempStack = new Stack();
            for(int j = 0; j < nbCrate; j++) {
                tempStack.push(stacks[from].pop());
            }
            while (!tempStack.isEmpty()) stacks[to].push(tempStack.pop());
        }

        System.out.print("crate ends up on top : ");
        for(int i = 0; i < qtyOfStack; i++) {
            System.out.print(stacks[i].peek());
        }
    }
}
