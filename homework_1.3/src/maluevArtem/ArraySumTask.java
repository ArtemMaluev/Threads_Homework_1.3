package maluevArtem;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ArraySumTask extends RecursiveTask<Integer> {

    private final int[] array;

    public ArraySumTask(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 2) {
            return Arrays.stream(array).sum();
        }
        ArraySumTask task1 = new ArraySumTask(Arrays.copyOfRange(array, 0, array.length / 2));
        ArraySumTask task2 = new ArraySumTask(Arrays.copyOfRange(array, array.length / 2, array.length));
        invokeAll(task1, task2);
        return task1.join() + task2.join();
    }
}
