package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class StringListImpl implements StringList {
    private int size;
    private String[] strings;
    private boolean result = false;


    public StringListImpl(int size) {
        this.size = size;
        this.strings = new String[size];
    }

    @Override
    public String add(String item) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] == null) {
                strings[i] = item;
                return item;
            }
        }
        throw new IndexOutOfBoundsException("В массиве нет свободной ячейки воспользуйтесь методом addPlus");
    }


    public String addPlus(String item) {
        try {
            add(item);
        } catch (IndexOutOfBoundsException e) {
            strings = refactorArrayAdd(strings, item);
        }

        return item;
    }

    private String[] refactorArrayAdd(String[] strings, String item) {
        String[] array = Arrays.copyOf(strings, strings.length + 1);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = item;
            }
        }
        return array;
    }

    @Override
    public String add(int index, String item) {
        checkIndexOutOfBoundsException(index);
        this.strings[index] = item;
        return item;
    }

    @Override
    public String set(int index, String item) {
        item = add(index, item);
//        checkIndexOutOfBoundsException(index);
//        this.strings[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(item)) {
                strings[i] = null;
                return item;
            }
        }
        throw new IllegalArgumentException(item + " элемент отсутствует в списке");
    }

    public String removeMinus(String item) {
        remove(item);
        strings = refactorArrayRemove(item);
        return item;
    }

    private String[] refactorArrayRemove(String item) {
        List<String> arrayList = new ArrayList<>(Arrays.stream(strings).toList());
        arrayList.removeIf(Objects::isNull);
        String[] array = new String[arrayList.size()];
        arrayList.toArray(array);
        return array;
    }

    @Override

    public String remove(int index) {
        checkIndexOutOfBoundsException(index);
        String item = strings[index];
        strings[index] = null;
        return item;
    }

    @Override
    public boolean contains(String item) {
        if (item != null || !item.isEmpty() || item.isBlank()) {
            for (int i = 0; i < strings.length; i++) {
                if (strings[i].equalsIgnoreCase(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = strings.length - 1; i > 0; i--) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndexOutOfBoundsException(index);
        return strings[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList != null || otherList.equals(strings)) {
            return true;
        }
        throw new NullPointerException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < strings.length; i++) {
            if (!strings[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        Arrays.fill(strings, null);
    }

    @Override
    public String[] toArray() {
        return new String[0];
    }

    @Override
    public String toString() {
        return Arrays.toString(strings);
    }

    private void checkIndexOutOfBoundsException(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("Индекс за пределами массива");
        }
    }
}
