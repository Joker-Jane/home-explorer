package home_explorer;

import home_explorer.exception.InvalidCommandException;
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

    public void commandParser(String command){
        String[] tokens = command.split(" ");
        try {
            switch (tokens[0]){
                case "cd":      checkCommand(tokens, new int[]{2}); cd(tokens[1]); break;
                case "pwd":     checkCommand(tokens, new int[]{1}); pwd(); break;
                case "ls":      checkCommand(tokens, new int[]{1}); ls(); break;
                case "mkdir":   checkCommand(tokens, new int[]{2}); mkdir(tokens[1]); break;
                case "touch":   checkCommand(tokens, new int[]{2}); touch(tokens[1]); break;
                case "save":    checkCommand(tokens, new int[]{1}); save(); break;
                case "exit":    checkCommand(tokens, new int[]{1}); System.exit(0); break;
                default:        throw new InvalidCommandException("Unknown command.");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void checkCommand(String[] tokens, int[] len){
        boolean valid = false;
        for (int j : len) {
            if (tokens.length == j) {
                valid = true;
                break;
            }
        }
        if(!valid){
            throw new InvalidCommandException("Invalid command usage.");
        }
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

    private void cd(String path){
        try {
            current = (Folder) pathParser(path);
        }catch (ClassCastException e){
            throw new ItemException("Item '" + pathParser(path).getName() + "' is not a folder.");
        }
    }

    private void ls(){
        StringBuffer sb = new StringBuffer();
        for(Item item : current.getContents()){
            sb.append(item.getName()).append(" ");
        }
        System.out.println(sb);
    }

    private void pwd(){
        System.out.println(current.getPath());
    }

    private void mkdir(String name) {
        Folder folder = new Folder(name, current);
        current.add(folder);
    }

    private void touch(String name){
        File file = new File(name, current);
        current.add(file);
        addToFileList(file);
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

    private void save(){
        System.out.println(Serializer.objectToString(this));
    }

    private void load(String str){
        // TODO
    }

    private void searchByName(Item item, String name){
        // TODO
    }
}
