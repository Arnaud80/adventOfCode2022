package utils;

import java.util.ArrayList;
import java.util.HashMap;

public class TreeNode{
    private TreeNode parent;
    private String type;
    private String name;
    private Integer size;
    private ArrayList<TreeNode> children;
    private int level = 0;

    public TreeNode(String rootName) {
        parent = null;
        type = "folder";
        name = rootName;
        size = 0;
        children = new ArrayList<>();
    }

    public TreeNode(String nodeName, TreeNode parent) {
        this.parent = parent;
        this.type = "folder";
        this.name = nodeName;
        this.size = 0;
        children = new ArrayList<>();
        this.level = this.parent.level + 1;
    }

    public TreeNode(String nodeName, Integer size, TreeNode parent) {
        this.parent = parent;
        this.type = "file";
        this.name = nodeName;
        this.size = size;
        children = new ArrayList<>();
        this.level = this.parent.level + 1;
    }

    public void addFolder(String nodeName) {
        children.add(new TreeNode(nodeName, this));
    }

    public void addFile(String nodeName, Integer size) {
        children.add(new TreeNode(nodeName, size,this));
    }

    public TreeNode search(String nodeName) {
        if(name.equals(nodeName)) return this;
        else {
            for(int i=0; i<children.size(); i++) {
                TreeNode node = search(nodeName);
                if(node!=null) return node;
                else return null;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    public TreeNode getChildByName(String name) {
        return children.stream().filter((node) -> node.name.equals(name)).findFirst().get();
    }

    public void setChildren(ArrayList<TreeNode> children) {
        this.children = children;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void printArbo() {
        String str="";
        for(int i=0; i< level; i++) str+="-";

        str+=" " + name + " (" + type;
        if(type.equals("file")) str+=", " + size;
        str+=")";
        System.out.println(str);
        for(int i=0; i< children.size(); i++) {
            children.get(i).printArbo();
        }
    }

    public Integer getFolderSize() {
        int totalSize=0;
        for(int i=0; i< children.size(); i++) {
            if(children.get(i).type.equals("file")) totalSize+=children.get(i).getSize();
            else totalSize+=children.get(i).getFolderSize();
        }
        return totalSize;
    }
    public Integer sumFolderAtMost(Integer value) {
        int sum=0;
        if(getFolderSize()<=value) sum+=getFolderSize();

        for(int i=0; i< children.size(); i++) {
            if(children.get(i).type.equals("folder")) sum+=children.get(i).sumFolderAtMost(value);
        }
        return sum;
    }

    public HashMap<TreeNode, Integer> folderListWithMax(Integer value) {
        HashMap<TreeNode, Integer> folderList=new HashMap<>();

        if(getFolderSize()>=value) folderList.put(this, getFolderSize());//System.out.println("Folder " + name + " : " + getFolderSize() );

        for(int i=0; i< children.size(); i++) {
            if(children.get(i).type.equals("folder")) folderList.putAll(children.get(i).folderListWithMax(value));
        }

        return folderList;
    }
}
