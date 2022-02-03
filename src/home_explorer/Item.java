package home_explorer;

import java.io.Serializable;

public interface Item extends Comparable<Item>, Serializable {
    String getName();
    Folder getPrev();
    boolean isFolder();
}
