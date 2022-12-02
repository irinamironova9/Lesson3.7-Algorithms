package course3.lesson7_algorithms.IntegerList;

import course3.lesson7_algorithms.StringList.StringList;
import course3.lesson7_algorithms.StringList.StringListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static course3.lesson7_algorithms.StringList.StringListImplTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {

    private IntegerList out;

    @BeforeEach
    void setUp() {
        out = new IntegerListImpl(10);
        out.add(3);
        out.add(8);
        out.add(4);
        assertThat(out.size()).isEqualTo(3);
    }

    @Test
    void add() {
        String actual = out.add(S_4);
        assertEquals(S_4, actual);
        assertEquals(S_4, out.get(3));
        assertThat(out.size()).isEqualTo(4);
    }

    @Test
    void addIndex() {
        String actual = out.add(1, S_4);
        assertEquals(S_4, actual);
        assertEquals(S_4, out.get(1));
        assertThat(out.size()).isEqualTo(4);
    }

    @Test
    void set() {
        assertThat(out.get(1)).isEqualTo(S_2);
        String actual = out.set(1, S_4);
        assertEquals(S_4, actual);
        assertEquals(S_4, out.get(1));
        assertThat(out.size()).isEqualTo(3);
    }

    @Test
    void remove() {
        String actual = out.remove(S_2);
        assertEquals(S_2, actual);
        assertThat(out.size()).isEqualTo(2);
        assertEquals(S_3, out.get(1));
    }

    @Test
    void removeIndex() {
        String actual = out.remove(1);
        assertEquals(S_2, actual);
        assertThat(out.size()).isEqualTo(2);
        assertEquals(S_3, out.get(1));
    }

    @Test
    void contains() {
        assertTrue(out.contains(S_1));
        assertFalse(out.contains("test"));
    }

    @Test
    void indexOf() {
        assertThat(out.indexOf(S_1)).isEqualTo(0);
        assertThat(out.indexOf(S_4)).isEqualTo(0);
        assertThat(out.indexOf("test")).isEqualTo(-1);
    }

    @Test
    void lastIndexOf() {
        out.add(S_4);
        assertThat(out.lastIndexOf(S_1)).isEqualTo(3);
        assertThat(out.lastIndexOf(S_4)).isEqualTo(3);
        assertThat(out.lastIndexOf("test")).isEqualTo(-1);
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

        StringList test2 = new StringListImpl(10);
        test2.add(S_1);
        assertFalse(out.equals(test2));

        StringList test3 = new StringListImpl(10);
        assertFalse(out.equals(test3));

        StringList test4 = new StringListImpl(5);
        test1.add(S_1);
        test1.add(S_2);
        test1.add(S_3);
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
}