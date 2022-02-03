package home_explorer;

public class File implements Item {
    private String name;
    private Folder prev;

    public File(String name, Folder prev){
        this.name = name;
        this.prev = prev;
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
        return false;
    }

    @Override
    public int compareTo(Item o) {
        return name.compareTo(o.getName());
    }
}
