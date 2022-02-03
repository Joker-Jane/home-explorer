package home_explorer;

import java.io.*;
import java.util.Base64;

public class Serializer {
    public static String objectToString(Object obj){
        if(obj == null){
            return null;
        }
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(obj);
            return Base64.getEncoder().encodeToString(byteOut.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object stringToObject(String str){
        if(str == null){
            return null;
        }
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(Base64.getDecoder().decode(str));
            ObjectInputStream objIn = new ObjectInputStream(byteIn);
            return objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
