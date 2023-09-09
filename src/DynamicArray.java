import java.lang.reflect.Array;

public class DynamicArray<T> {

    private T[] array;
    // How many elements are in the array.
    private int size;
    private Class<T> clazz;

    public DynamicArray(Class<T> clazz) {

        this.clazz = clazz;
        array = (T[]) Array.newInstance(clazz, 10);
        size = 0;

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = element;
                size++;
                if (size > array.length*0.8) growArray();
                break;
            }
        }
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) throw new IllegalArgumentException("Array Index out of Bounds!");
        if (isEmpty()) add(element);
        else {
            // Traverse through the array starting at the back.
            // Keep copying elements to the index immediately to their right until empty slot to insert element at is found.
            int i = size-1;
            while (i >= index) array[i+1] = array[i--];
            array[++i] = element;
            size++;
            if (size > array.length*0.8) growArray();
            // size = 1
            // i = 0
            // index = 0
            // i == index therefore array[1] = 17
            // i = -1
            // array[1]
            //

        }
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Array Index out of Bounds!");
        array[index] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= array.length) throw new IllegalArgumentException("Index Out of Bounds!");
        return array[index];
    }

    // Create a new array with double the length as the former array and call copyToGrownArray to copy elements over.
    private void growArray() {
        copyToNewArray((T[]) Array.newInstance(clazz, array.length*2));
    }

    // Copy contents from current array to new array and sets previous array to new array.
    private void copyToNewArray(T[] grownArray) {
        for (int i = 0; i < array.length; i++) {
            grownArray[i] = array[i];
        }
        array = grownArray;
    }

    //
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Array Index out of Bounds!");
        T element = array[index];
        array[index] = null;
        size--;
        return element;
    }


}
