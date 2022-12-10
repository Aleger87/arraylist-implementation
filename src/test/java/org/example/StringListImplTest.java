package org.example;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StringListImplTest {
    private  StringListImpl stringListImpl = new StringListImpl(3);
    private  String[] stringList = new String[3];
    private boolean ifResult = false;

    @BeforeEach
    public  void setUp() {
        stringList[0] = "test";
        stringList[1] = "test1";
        stringList[2] = "test2";

        stringListImpl.add("test");
        stringListImpl.add("test1");
        stringListImpl.add("test2");
    }

    @Test
    void add() {
       Assertions.assertEquals(stringListImpl.toString(), Arrays.toString(stringList));
    }

    @Test
    void addPlus() {
        String string = "Newt_test";
        String[] strings = Arrays.copyOf(stringList, stringList.length + 1);
        for (int i = 0; i < strings.length; i++) {
            if (strings[i]==null) {
                strings[i] = string;
            }
        }
        stringListImpl.addPlus(string);
        Assertions.assertEquals(stringListImpl.toString(),Arrays.toString(strings));
    }

    @Test
    void testAdd() {
        int index = 0;
        String string = "test4";
        stringListImpl.add(index, string);
        stringList[index] = string;
        Assertions.assertEquals(stringListImpl.toString(), Arrays.toString(stringList));
    }

    @Test()
    void testAddIndexOutOfBoundsException() {
        int index = 3;
        String string = "test4";
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                stringListImpl.add(index, string);
        });

    }

    @Test
    void set() {
        testAdd();
    }

    @Test()
    void testSetIndexOutOfBoundsException() {
        testAddIndexOutOfBoundsException();
    }

    @Test
    void remove() {
        String string = "test1";
        for (int i = 0; i < stringList.length; i++) {
            if (stringList[i].equals(string)) {
                stringList[i] = null;
            }
        }
        stringListImpl.remove(string);
        Assertions.assertEquals(stringListImpl.toString(), Arrays.toString(stringList));
    }

    @Test
    void testRemoveIllegalArgumentException() {
        String string = "test4";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stringListImpl.remove(string);
        });
    }

    @Test
    void testRemove() {
        int index = 0;
        stringList[index] = null;
        stringListImpl.remove(0);
        Assertions.assertEquals(stringListImpl.toString(), Arrays.toString(stringList));

    }

    @Test
    void removeMinus() {
        String string = "test";
        for (int i = 0; i < stringList.length; i++) {
            if (stringList[i] == string) {
                stringList[i] = null;
            }
        }
        List<String> arrayList = new ArrayList<>(Arrays.stream(stringList).toList());
        arrayList.removeIf(Objects::isNull);
       String[] stringListNew = new String[arrayList.size()];
        arrayList.toArray(stringListNew);
        stringListImpl.removeMinus(string);
        Assertions.assertEquals(stringListImpl.toString(), Arrays.toString(stringListNew));

    }

    @Test
    void testRemoveIndexOutOfBoundsException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            stringListImpl.remove(5);
        });

    }

    @ParameterizedTest
    @MethodSource("parametersForTests")
    void contains(String string) {
        boolean expected = stringListImpl.contains(string);
        Assertions.assertTrue(expected);
    }

    @ParameterizedTest
    @MethodSource("parametersForTests")
    void indexOf(String string) {
        Integer actualIndex= -1;
        for (int i = 0; i < stringList.length; i++) {
            if (stringList[i].equals(string)) {
                actualIndex = i;
                return;
            }
        }
        Integer expectedIndex = stringListImpl.indexOf(string);

        Assertions.assertEquals(actualIndex, expectedIndex);

    }

    @ParameterizedTest
    @MethodSource("parametersForTests")
    void lastIndexOf(String string) {
        Integer actualIndex= -1;
        for (int i = stringList.length-1; i >= 0; i--) {
            if (stringList[i].equals(string)) {
                actualIndex = i;
                return;
            }
        }
        Integer expectedIndex = stringListImpl.lastIndexOf(string);

        Assertions.assertEquals(actualIndex, expectedIndex);
    }

    @Test
    void get() {
        int index = 0;
        String actual = stringList[index];
        String expected = stringListImpl.get(index);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void testGetIndexOutOfBoundsException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            stringListImpl.get(5);
        });
    }

    @Test
    void testEqualsIllegalArgumentException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            stringListImpl.equals(null);
        });
    }
    @Test
    void testEqualsTrue() {
        StringListImpl otherList = new StringListImpl(3);
       Assertions.assertTrue(stringListImpl.equals(otherList));
    }
    @Test
    void testEqualsFalse() {
        Assertions.assertFalse(stringListImpl.equals(stringList));
    }

    @Test
    void size() {
        int actual = stringList.length;
        int expected = stringListImpl.size();
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void isEmptyFalse() {
        Assertions.assertFalse(stringListImpl.isEmpty());
    }

    @Test
    void isEmptyTrue() {
        StringListImpl stringListImplTrue = new StringListImpl(3);
        stringListImplTrue.add("");
        stringListImplTrue.add("");
        stringListImplTrue.add("");
        Assertions.assertTrue(stringListImplTrue.isEmpty());
    }

    @Test
    void clear() {
        Arrays.fill(stringList, null);
        stringListImpl.clear();
        Assertions.assertEquals(Arrays.toString(stringList), stringListImpl.toString());

    }

    @Test
    void toArray() {
        String[] stringsActual = new String[0];
        String[] stringExpected = stringListImpl.toArray();
        Assertions.assertEquals(Arrays.toString(stringsActual), Arrays.toString(stringExpected));

    }

    public static Stream<Arguments> parametersForTests() {
        return Stream.of(
                Arguments.of("Test"),
                Arguments.of("TEST"),
                Arguments.of("teST")
        );
    }
}