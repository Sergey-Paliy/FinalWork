// Файл Counter.java
public class Counter implements AutoCloseable {
    private int count = 0;

    public void add() {
        count++;
    }

    @Override
    public void close() throws Exception {
        if (count == 0) {
            throw new Exception("Ресурс не использован");
        }
    }
}

