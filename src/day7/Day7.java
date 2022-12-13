package day7;

import utils.InputFileReader;
import utils.TreeNode;

import java.io.IOException;
import java.util.*;

public class Day7 {
    public static void main(String... args) throws IOException {
        ArrayList<String> lines = new InputFileReader("resources/day7.txt").getLines();
        TreeNode tree = new TreeNode("/");

        TreeNode currentFolder = tree;

        for(int i=0; i<lines.size(); i++) {
            String[] splitLine = lines.get(i).split(" ");
            switch (splitLine[0]) {
                case "$": // Command line
                    switch (splitLine[1]) {
                        case "cd" : // Change directory command
                            switch (splitLine[2]) {
                                case "/" :
                                    break;
                                case ".." :
                                    currentFolder = currentFolder.getParent();
                                    break;
                                default :
                                    currentFolder = currentFolder.getChildByName(splitLine[2]);
                                    break;
                            }

                            break;
                        case "ls" : // List directory content
                            break;
                    }
                    break;
                default: // stdout
                    switch (splitLine[0]) {
                        case "dir" : // Directory
                            if(!splitLine[1].equals("/")) currentFolder.addFolder(splitLine[1]);
                            break;
                        default : // File
                            currentFolder.addFile(splitLine[1], Integer.valueOf(splitLine[0]));
                            break;
                    }
                    break;
            }
        }
        tree.printArbo();
        System.out.println("Size / : " + tree.getFolderSize());
        System.out.println("Sum folder at most 100000 : " + tree.sumFolderAtMost(100000));

        int totalSpaceDisk = 70000000;
        int minFreeSpaceAllowed = 30000000;
        int spaceDiskToFree = minFreeSpaceAllowed - (totalSpaceDisk - tree.getFolderSize());

        System.out.println("spaceDiskToFree = " + spaceDiskToFree);
        tree.folderListWithMax(spaceDiskToFree);
        HashMap<TreeNode, Integer> candidateList = tree.folderListWithMax(spaceDiskToFree);

        Map sortedMap = sortByValues(candidateList);

        System.out.println("folderList = " + sortedMap.values());
    }
    //Method for sorting the TreeMap based on values
    public static <K, V extends Comparable<V>> Map<K, V>
    sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                new Comparator<K>() {
                    public int compare(K k1, K k2) {
                        int compare =
                                map.get(k1).compareTo(map.get(k2));
                        if (compare == 0)
                            return 1;
                        else
                            return compare;
                    }
                };

        Map<K, V> sortedByValues =
                new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
}
