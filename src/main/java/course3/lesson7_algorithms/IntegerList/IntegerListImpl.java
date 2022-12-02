package course3.lesson7_algorithms.IntegerList;

import course3.lesson7_algorithms.exception.ItemNotFoundException;
import course3.lesson7_algorithms.exception.ListIsEmptyException;
import course3.lesson7_algorithms.exception.NoSpaceLeftException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private final Integer[] integers;
    private int size;

    public IntegerListImpl() {
        this.integers = new Integer[10];
    }

    public IntegerListImpl(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException();
        }
        this.integers = new Integer[length];
    }

    @Override
    public Integer add(Integer item) {
        return add(size, item);
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index > size) {
            throw new IllegalArgumentException();
        }
        validateItem(item);
        checkNotFull();
        if (index < size) {
            System.arraycopy(integers, index, integers, index + 1, size - index);
        }
        integers[index] = item;
        size++;
        return integers[index];
    }

    @Override
    public Integer set(int index, Integer item) {
        validateValues(index, item);
        checkNotFull();
        integers[index] = item;
        return integers[index];
    }

    @Override
    public Integer remove(Integer item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new ItemNotFoundException();
        }
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer copy = integers[index];
        System.arraycopy(integers, index + 1, integers, index, size - 1 - index);
        integers[size - 1] = null;
        size--;
        return copy;
    }

    @Override
    public boolean contains(Integer item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        validateItem(item);
        for (int i = 0; i < size; i++) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public int sortAndBinarySearchIndexOf(Integer item) {
        return binarySearch(item);
    }

    @Override
    public int lastIndexOf(Integer item) {
        validateItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return integers[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException();
        }
        if (this.isEmpty() || otherList.isEmpty() || size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!integers[i].equals(otherList.get(i))) {
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
            integers[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(integers, size);
    }

    private void validateValues(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNotFull() {
        if (size == integers.length) {
            throw new NoSpaceLeftException();
        }
    }

    private void checkNotEmpty() {
        if (size == 0) {
            throw new ListIsEmptyException();
        }
    }

    private int binarySearch(Integer item) {
        validateItem(item);
        sort();
        int min = 0;
        int max = size - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (integers[mid].equals(item)) {
                return mid;
            }
            if (item < integers[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    private void sort() {
        for (int i = 0; i < size; i++) {
            int tmp = integers[i];
            int j = i;
            while (j > 0 && integers[j - 1] >= tmp) {
                integers[j] = integers[j - 1];
                j--;
            }
            integers[j] = tmp;
        }
    }
}
