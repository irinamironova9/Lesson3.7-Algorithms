package course3.lesson7_algorithms.StringList;

import course3.lesson7_algorithms.exception.ItemNotFoundException;
import course3.lesson7_algorithms.exception.ListIsEmptyException;
import course3.lesson7_algorithms.exception.NoSpaceLeftException;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private final String[] strings;
    private int size;

    public StringListImpl() {
        this.strings = new String[10];
    }

    public StringListImpl(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException();
        }
        this.strings = new String[length];
    }

    @Override
    public String add(String item) {
        return add(size, item);
    }

    @Override
    public String add(int index, String item) {
        if (index > size) {
            throw new IllegalArgumentException();
        }
        validateItem(item);
        checkNotFull();
        if (index < size) {
            System.arraycopy(strings, index, strings, index + 1, size - index);
        }
        strings[index] = item;
        size++;
        return strings[index];
    }

    @Override
    public String set(int index, String item) {
        validateValues(index, item);
        checkNotFull();
        strings[index] = item;
        return strings[index];
    }

    @Override
    public String remove(String item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new ItemNotFoundException();
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String copy = strings[index];
        System.arraycopy(strings, index + 1, strings, index, size - 1 - index);
        strings[size - 1] = null;
        size--;
        return copy;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        validateItem(item);
        for (int i = 0; i < size; i++) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        validateItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return strings[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException();
        }
        if (this.isEmpty() || otherList.isEmpty() || size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!strings[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        checkNotEmpty();
        for (int i = 0; i < size; i++) {
            strings[i] = null;
        }
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(strings, size);
    }

    private void validateValues(int index, String item) {
        validateIndex(index);
        validateItem(item);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNotFull() {
        if (size == strings.length) {
            throw new NoSpaceLeftException();
        }
    }

    private void checkNotEmpty() {
        if (size == 0) {
            throw new ListIsEmptyException();
        }
    }
}
