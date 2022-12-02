package course3.lesson7_algorithms.IntegerList;

import course3.lesson7_algorithms.StringList.StringList;
import course3.lesson7_algorithms.StringList.StringListImpl;
import course3.lesson7_algorithms.exception.ItemNotFoundException;
import course3.lesson7_algorithms.exception.ListIsEmptyException;
import course3.lesson7_algorithms.exception.NoSpaceLeftException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {

    private static final Integer[] EXPECTED = new Integer[] {3, 8, 4};

    private IntegerListImpl out;

    @BeforeEach
    void setUp() {
        out = new IntegerListImpl();
        out.add(3);
        out.add(8);
        out.add(4);
        assertEquals(out.size(), 3);
        assertArrayEquals(EXPECTED, out.toArray());
    }

    @Test
    void add() {
        assertEquals(5, out.add(5));
        assertEquals(5, out.get(3));
        assertEquals(out.size(), 4);
    }

    @Test
    void addIndex() {
        assertEquals(5, out.add(1, 5));
        assertEquals(3, out.get(0));
        assertEquals(5, out.get(1));
        assertEquals(8, out.get(2));
        assertEquals(4, out.get(3));
        assertEquals(out.size(), 4);
    }

    @Test
    void set() {
        assertEquals(8, out.get(1));
        assertEquals(5, out.set(1, 5));
        assertEquals(5, out.get(1));
        assertEquals(out.size(), 3);
    }

    @Test
    void remove() {
        assertEquals(3, out.remove((Integer) 3));
        assertEquals(out.size(), 2);
        assertEquals(8, out.get(0));
    }

    @Test
    void removeIndex() {
        assertEquals(3, out.remove(0));
        assertEquals(out.size(), 2);
        assertEquals(8, out.get(0));
    }

    @Test
    void contains() {
        assertTrue(out.contains(4));
        assertFalse(out.contains(7));
    }

    @Test
    void indexOf() {
        out.add(3);
        assertEquals(out.indexOf(3), 0);
        assertEquals(out.indexOf(8), 1);
        assertEquals(out.indexOf(4), 2);
        assertEquals(out.indexOf(7), -1);
    }

    @Test
    void sortAndBinarySearchIndexOf() {
        assertEquals(out.sortAndBinarySearchIndexOf(3), 0);
        assertEquals(out.sortAndBinarySearchIndexOf(4), 1);
        assertEquals(out.sortAndBinarySearchIndexOf(8), 2);
    }

    @Test
    void lastIndexOf() {
        out.add(3);
        assertEquals(out.lastIndexOf(3), 3);
        assertEquals(out.lastIndexOf(8), 1);
        assertEquals(out.lastIndexOf(4), 2);
        assertEquals(out.lastIndexOf(7), -1);
    }

    @Test
    void get() {
        assertThat(out.get(0)).isEqualTo(3);
    }

    @Test
    void equalsList() {
        IntegerList test1 = new IntegerListImpl(10);
        test1.add(3);
        test1.add(8);
        test1.add(4);
        assertTrue(out.equals(test1));

        IntegerList test2 = new IntegerListImpl(5);
        test2.add(3);
        test2.add(8);
        test2.add(4);
        assertTrue(out.equals(test2));

        IntegerList test3 = new IntegerListImpl(10);
        test3.add(3);
        assertFalse(out.equals(test3));

        IntegerList test4 = new IntegerListImpl(10);
        assertFalse(out.equals(test4));
    }

    @Test
    void size() {
        assertEquals(out.size(), 3);
    }

    @Test
    void isEmpty() {
        StringList test = new StringListImpl(10);
        assertTrue(test.isEmpty());
        assertFalse(out.isEmpty());
    }

    @Test
    void clear() {
        assertFalse(out.isEmpty());
        out.clear();
        assertTrue(out.isEmpty());
    }

    @Test
    void toArray() {
        assertArrayEquals(EXPECTED, out.toArray());
    }

    @Test
    void throwsNoSpaceLeftExceptionWhenListIsFull() {
        IntegerList test = new IntegerListImpl(1);
        test.add(1);
        assertThrows(NoSpaceLeftException.class, () -> test.add(2));
    }

    @Test
    void throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> out.remove((Integer) 7));
    }

    @Test
    void throwsListIsEmptyException() {
        StringList test = new StringListImpl(5);
        assertThrows(ListIsEmptyException.class, test::clear);
    }

    @Test
    void throwsIllegalArgumentExceptionWhenValuesAreNullOrUnacceptable() {
        assertThrows(IllegalArgumentException.class, () -> out.set(-1, 5));
        assertThrows(IllegalArgumentException.class, () -> out.set(3, 5));
        assertThrows(IllegalArgumentException.class, () -> out.set(0, null));
        assertThrows(IllegalArgumentException.class, () -> out.equals(null));
    }
}