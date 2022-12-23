package day11;

import utils.InputFileReader;

import java.io.IOException;
import java.util.ArrayList;

public class Day11 {
    public static void main(String... args) throws IOException {
        ArrayList<String> lines = new InputFileReader("resources/day11-test.txt").getLines();
        ArrayList<Monkey> monkeys = new ArrayList<>();

        for(int monkey=0; monkey < (lines.size() + 1) / 7; monkey++) {
            String[] data = new String[7];
            for(int dataLine=0; dataLine < 7; dataLine++) {
                System.out.println("line " + (monkey * 7 + dataLine) + " " + lines.get(monkey * 7 + dataLine));
                data[dataLine] = lines.get(monkey * 7 + dataLine);

            }
            monkeys.add(new Monkey(data));
        }

        for(int round=0; round<20; round ++) {
            for(Monkey monkey: monkeys) {
                for(int i=0; i< monkey.getItems().size(); i++) {
                    Integer[] pairValue = monkey.getTargetMonkeyAndValue(i);
                    monkeys.get(pairValue[0]).addItem(pairValue[1]);
                }
                monkey.getItems().clear();
                monkey.setFirstRound(false);
            }
        }

        for(Monkey monkey: monkeys) {
            System.out.println("Monkey " + monkey.getId() + " : " + monkey.getItems() +
                    " Inspected Items " + (monkey.getInspectedItem() - monkey.getItems().size()));
        }
    }
}
