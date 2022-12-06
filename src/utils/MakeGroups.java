package utils;

import java.util.ArrayList;

public class MakeGroups {
    public static ArrayList<String[]> makeGroups(ArrayList<String> lines, int groupSize) {
        ArrayList<String[]> groups = new ArrayList<>();
        int count=0;
        String group[] = new String[groupSize];
        for(int i=0; i < lines.size(); i++) {
            group[i % groupSize] = lines.get(i);
            if(i > 0 && i % groupSize == groupSize - 1) {
                groups.add(group);
                group = new String[groupSize];
            }
        }
        return groups;
    }
}
