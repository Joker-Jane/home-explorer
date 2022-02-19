package home_explorer;

import home_explorer.exception.ItemException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Explorer implements Serializable {
    private final Folder root;
    private Folder current;
    private List<File> files = new ArrayList<>();

    public Explorer(){
        this.root = new Folder("root", null);
        this.current = root;
    }

    protected void cd(String path){
        try {
            current = (Folder) pathParser(path);
        }catch (ClassCastException e){
            throw new ItemException("Item '" + pathParser(path).getName() + "' is not a folder.");
        }
    }

    protected void ls(){
        StringBuffer sb = new StringBuffer();
        for(Item item : current.getContents()){
            sb.append(item.getName()).append(" ");
        }
        System.out.println(sb);
    }

    protected void pwd(){
        System.out.println(current.getPath());
    }

    protected void mkdir(String name) {
        Folder folder = new Folder(name, current);
        current.add(folder);
    }

    protected void touch(String name){
        File file = new File(name, current);
        current.add(file);
        addToFileList(file);
    }

    private Item pathParser(String path){
        String[] paths = path.split("/");
        Folder cur = current;
        int i = 0;
        if(paths.length == 0 || paths[0].equals("")){
            cur = root;
            i++;
        }
        for(; i < paths.length; i++){
            Item next;
            next = cur.getItemByName(paths[i]);
            if(next == null){
                throw new ItemException("Item '" + paths[i] + "' does not exist.");
            }
            if(!next.isFolder()){
                if(i != paths.length - 1){
                    throw new ItemException("Item '" + paths[i] + "' is not a folder.");
                }
                return next;
            }
            cur = (Folder) next;
        }
        return cur;
    }

    private void addToFileList(File file){
        int left = 0;
        int right = files.size() - 1;
        while(left < right){
            int mid = (left + right) / 2;
            if(files.get(mid).compareTo(file) == 0){
                left = mid;
                break;
            }else if(files.get(mid).compareTo(file) > 0){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        if(left == files.size() - 1){
            left++;
        }
        files.add(left, file);
    }

    private void searchByName(Item item, String name){
        // TODO
    }
}
