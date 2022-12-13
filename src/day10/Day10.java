package day10;

import utils.InputFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Day10 {
    public static void main(String[] args) throws Exception {
        ArrayList<String> lines = new InputFileReader("resources/day10.txt").getLines();

        ExecutorService service = null;
        Integer sum = 1;
        Integer cycle = 0;
        ArrayList<Callable<Integer>> instructions = new ArrayList<>();


        service = Executors.newSingleThreadExecutor();
        for(int i=0; i<lines.size(); i++) {
            String instruction = lines.get(i).split(" ")[0];
            if(instruction.equals("addx")) {
                Integer addValue = Integer.valueOf(lines.get(i).split(" ")[1]);

                instructions.add(() -> {
                    return (0);
                });
                instructions.add(() -> {
                    return (addValue);
                });
            } else {
                instructions.add(() -> {
                    return (0);
                });
            }
        }

        try {
            service = Executors.newSingleThreadExecutor();
            List<Future<Integer>> results = service.invokeAll(instructions);

            ArrayList<Integer> cyclesResults = new ArrayList<>();

            for(Future<Integer> result : results) {
                sum += result.get();
                cyclesResults.add(sum);
                System.out.println("On cycle " + ++cycle + " adding " + result.get() + ", sum = " + sum);
            }

            int total = 0;
            for(int i:List.of(20, 60, 100, 140, 180, 220)) {
                total += i * cyclesResults.get(i - 2);
                System.out.println("On cycle " + i + " signal strength = " + i * cyclesResults.get(i - 2)); // Pourquoi -2 ?
            }
            System.out.println("On total signal strength = " + total);
        } finally {
            if(service!=null) service.shutdown();
        }
    }
}
