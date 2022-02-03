package home_explorer;

import java.util.ArrayList;
import java.util.List;

public class Folder implements Item {
    private String name;
    private Folder prev;
    private List<Item> contents;

    public Folder(String name, Folder prev){
        this.name = name;
        this.prev = prev;
        this.contents = new ArrayList<>();
    }

    public void add(Item item){
        contents.add(item);
    }

    public String getPath(){
        if(prev == null){
            return "/";
        }
        return prev.getPath() + name + "/";
    }

    public List<Item> getContents() {
        return contents;
    }

    public Item getItemByName(String name){
        if(name.equals("..")){
            return prev;
        }
        for(Item item : contents){
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Folder getPrev() {
        return prev;
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    @Override
    public int compareTo(Item o) {
        return name.compareTo(o.getName());
    }
}
