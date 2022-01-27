import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TodoList implements Iterable<String> {

    private List<String> primary = new ArrayList<>();
    private List<String> secondary = new ArrayList<>();


    public TodoList addPrimary(String task) {
        primary.add(task);
        return this;
    }

    public TodoList addSecondary(String task) {
        secondary.add(task);
        return this;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            boolean isPrimary = true;
            int nextIndex = 0;

            @Override
            public boolean hasNext() {
                if (isPrimary) {
                    if (nextIndex < primary.size()) {
                        return true;
                    } else {
                        return !secondary.isEmpty();
                    }
                } else {
                    return nextIndex < secondary.size();
                }
            }

            @Override
            public String next() {
                if (isPrimary) {
                    if (nextIndex < primary.size()) {
                        String task = primary.get(nextIndex);
                        nextIndex++;
                        return task;
                    } else {
                        isPrimary = false;
                        String task = secondary.get(0);
                        nextIndex = 1;
                        return task;
                    }
                } else {
                    String task = secondary.get(nextIndex);
                    nextIndex++;
                    return task;
                }
            }
        };
    }
}
