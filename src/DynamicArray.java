import java.util.Arrays;

public class DynamicArray<T> {

    private int size;
    private Object[] elements;

    public DynamicArray(Class<T> val){
        this.size = 0;
        this.elements = new Object[0];
    }

    public int size(){
        return this.size;
    }

    public void add(T val){
        size++;
        elements = Arrays.copyOf(elements, size);
        elements[size-1] = val;
    }

    public T remove(int i){
        if(i<0||i>=size) throw new IllegalArgumentException("Array Index out of Bounds!");
        int cnt = size-i-1;
        T r = (T) elements[i];
        System.arraycopy(elements, i+1, elements, i, cnt);
//        elements[size] = null;
        size--;
        return r;
    }

    public T remove(Object o){
        for (int i = 0; i < size; i++){
            if(elements[i].equals(o)){
                T r = (T) elements[i];
                size--;
                System.arraycopy(elements, i+1, elements, i, size-i);
                return r;
            }
        }
//        for (Object i : elements){
//            if(o.equals(i)){
//                size--;
//                System.arraycopy(elements, )
//                return (T) i;
//            }
//        }
        return null;
    }

    public T removeAll(Object o){
        for (Object i : elements){
            if(o.equals(i)) remove(i);
        }
        return (T)o;
    }

    public boolean isEmpty(){
        return (size==0);
    }

    public T get(int i){
        if(i<0||i>=size) return null;
//        System.out.println(this.size);
        return (T) elements[i];
    }

    public void add(int i, T var){
        if(i<0||i>size) throw new IllegalArgumentException("Array Index out of Bounds!");

        enlarge();
//        elements = Arrays.copyOf(elements, size);
        System.arraycopy(elements, i, elements, i+1, size-i);
        elements[i] = var;
        size++;
    }

    public void set(int i, T var){
        if(i < 0 || i>=size) throw new IllegalArgumentException("Array Index out of Bounds!");
        elements[i] = var;
    }

    public boolean contains(Object o) {
        for (Object i : elements){
            if(i.equals(o))return true;
        }
        return false;
    }

    public void clear(){
        size=0;
        elements = Arrays.copyOf(elements, 0);
    }

    private void enlarge(){
        Object[] newElements = new Object[size+1];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
