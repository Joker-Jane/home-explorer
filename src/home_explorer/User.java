package home_explorer;

import home_explorer.exception.InvalidCommandException;
import home_explorer.exception.ItemException;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    ArrayList<Explorer> explorers = new ArrayList<>();
    Explorer cur;

    public User(){
        Explorer def = new Explorer();
        explorers.add(def);
        cur = def;
    }

    public void command(String command){
        String[] tokens = command.split(" ");
        try {
            switch (tokens[0]){
                case "cd":      checkCommand(tokens, new int[]{2}); cur.cd(tokens[1]); break;
                case "pwd":     checkCommand(tokens, new int[]{1}); cur.pwd(); break;
                case "ls":      checkCommand(tokens, new int[]{1}); cur.ls(); break;
                case "mkdir":   checkCommand(tokens, new int[]{2}); cur.mkdir(tokens[1]); break;
                case "touch":   checkCommand(tokens, new int[]{2}); cur.touch(tokens[1]); break;
                case "save":    checkCommand(tokens, new int[]{1}); save(); break;
                case "load":    checkCommand(tokens, new int[]{2}); load(tokens[1]); break;
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

    private void save(){
        System.out.println(Serializer.objectToString(explorers));
    }

    @SuppressWarnings("unchecked")
    private void load(String str){
        str = str.trim();
        try {
            explorers = (ArrayList<Explorer>) Serializer.stringToObject(str);
        }catch (Exception e){
            throw new ItemException("Invalid load string");
        }
        cur = explorers.get(0);
    }
}
