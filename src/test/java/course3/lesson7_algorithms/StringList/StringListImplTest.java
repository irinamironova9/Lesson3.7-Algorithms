package course3.lesson7_algorithms.StringList;

import course3.lesson7_algorithms.exception.ItemNotFoundException;
import course3.lesson7_algorithms.exception.ListIsEmptyException;
import course3.lesson7_algorithms.exception.NoSpaceLeftException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static course3.lesson7_algorithms.StringList.StringListImplTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {

    StringList out;

    @BeforeEach
    void setUp() {
        out = new StringListImpl();
        out.add(S_1);
        out.add(S_2);
        out.add(S_3);
        assertEquals(out.size(), 3);
        assertArrayEquals(EXPECTED, out.toArray());
    }

    @Test
    void add() {
        assertEquals(S_4, out.add(S_4));
        assertEquals(S_4, out.get(3));
        assertEquals(out.size(), 4);
    }

    @Test
    void addIndex() {
        assertEquals(S_4, out.add(1, S_4));
        assertEquals(S_1, out.get(0));
        assertEquals(S_4, out.get(1));
        assertEquals(S_2, out.get(2));
        assertEquals(S_3, out.get(3));
        assertEquals(out.size(), 4);
    }

    @Test
    void set() {
        assertEquals(S_2, out.get(1));
        assertEquals(S_4, out.set(1, S_4));
        assertEquals(S_4, out.get(1));
        assertEquals(out.size(), 3);
    }

    @Test
    void remove() {
        assertEquals(S_2, out.remove(S_2));
        assertEquals(out.size(), 2);
        assertEquals(S_3, out.get(1));
    }

    @Test
    void removeIndex() {
        assertEquals(S_2, out.remove(1));
        assertEquals(out.size(), 2);
        assertEquals(S_3, out.get(1));
    }

    @Test
    void contains() {
        assertTrue(out.contains(S_1));
        assertFalse(out.contains("test"));
    }

    @Test
    void indexOf() {
        assertEquals(out.indexOf(S_1), 0);
        assertEquals(out.indexOf(S_2), 1);
        assertEquals(out.indexOf(S_3), 2);
        assertEquals(out.indexOf(S_4), 0);
        assertEquals(out.indexOf("test"), -1);
    }

    @Test
    void lastIndexOf() {
        out.add(S_4);
        assertEquals(out.lastIndexOf(S_1), 3);
        assertEquals(out.lastIndexOf(S_2), 1);
        assertEquals(out.lastIndexOf(S_3), 2);
        assertEquals(out.lastIndexOf(S_4), 3);
        assertEquals(out.lastIndexOf("test"), -1);
    }

    @Test
    void get() {
        assertThat(out.get(0)).isEqualTo(S_1);
    }

    @Test
    void equalsList() {
        StringList test1 = new StringListImpl(10);
        test1.add(S_1);
        test1.add(S_2);
        test1.add(S_3);
        assertTrue(out.equals(test1));

        StringList test2 = new StringListImpl(5);
        test2.add(S_1);
        test2.add(S_2);
        test2.add(S_3);
        assertTrue(out.equals(test2));

        StringList test3 = new StringListImpl(10);
        test3.add(S_1);
        assertFalse(out.equals(test3));

        StringList test4 = new StringListImpl(10);
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
        StringList test = new StringListImpl(1);
        test.add(S_1);
        assertThrows(NoSpaceLeftException.class, () -> test.add(S_2));
    }

    @Test
    void throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> out.remove("test"));
    }

    @Test
    void throwsListIsEmptyException() {
        StringList test = new StringListImpl(5);
        assertThrows(ListIsEmptyException.class, test::clear);
    }

    @Test
    void throwsIllegalArgumentExceptionWhenValuesAreNullOrUnacceptable() {
        assertThrows(IllegalArgumentException.class, () -> out.set(-1, "test"));
        assertThrows(IllegalArgumentException.class, () -> out.set(3, "test"));
        assertThrows(IllegalArgumentException.class, () -> out.set(0, null));
        assertThrows(IllegalArgumentException.class, () -> out.equals(null));
    }
}