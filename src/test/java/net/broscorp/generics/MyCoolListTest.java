package net.broscorp.generics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyCoolListTest {

  private MyCoolList<Integer> list;

  @BeforeEach
  void setUp() {
    list = new MyCoolArrayList<>();

    list.add(1);
    list.add(2);
    list.add(3);
  }

  @Test
  void testAddAndGet() {
    assertEquals(1, list.get(0));
    assertEquals(2, list.get(1));
    assertEquals(3, list.get(2));
  }

  @Test
  void testRemove() {
    assertEquals(2, list.remove(1));
    assertEquals(1, list.get(0));
    assertEquals(3, list.get(1));
    assertEquals(2, list.size());
  }

  @Test
  void testSize() {
    assertEquals(3, list.size());

    list.add(1);
    list.add(2);

    assertEquals(5, list.size());
  }

  @Test
  void testMap() {
    MyCoolList<Double> mappedList = list.map(elem -> (double) elem + 0.33);

    assertEquals(1.33, mappedList.get(0));
    assertEquals(2.33, mappedList.get(1));
    assertEquals(3.33, mappedList.get(2));
    assertEquals(3, mappedList.size());
  }

  @Test
  void testInvalidIndex() {
    list = new MyCoolArrayList<>();

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));

    list.add(1);
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));

    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
  }

  @Test
  public void testIterator() {
    // Test using for-each loop
    int sumForEach = 0;
    for (Integer element : list) {
      sumForEach += element;
    }
    assertEquals(6, sumForEach);

    // Test using explicit iterator
    Iterator<Integer> iterator = list.iterator();
    int sumIterator = 0;
    while (iterator.hasNext()) {
      sumIterator += iterator.next();
    }
    assertEquals(6, sumIterator);
  }

  @Test
  public void testEqualsWithSameLists() {
    MyCoolList<Integer> list1 = new MyCoolArrayList<>();
    list1.add(1);
    list1.add(2);
    list1.add(3);

    assertTrue(list.equals(list1));
    assertTrue(list1.equals(list));
  }

  @Test
  public void testEqualsWithDifferentListsSameSize() {
    MyCoolList<Integer> list1 = new MyCoolArrayList<>();
    list1.add(3);
    list1.add(3);
    list1.add(3);

    assertFalse(list.equals(list1));
  }

  @Test
  public void testEqualsWithDifferentListsDifferentSize() {
    MyCoolList<Integer> list1 = new MyCoolArrayList<>();

    list1.add(1);
    list1.add(2);
    assertFalse(list.equals(list1));

    list.add(4);
    list.add(1);
    assertFalse(list.equals(list1));
  }

  @Test
  public void testHashCode() {
    MyCoolArrayList<Integer> list1 = new MyCoolArrayList<>();
    list1.add(1);
    list1.add(2);
    list1.add(3);

    assertEquals(list1.hashCode(), list.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("[1, 2, 3]", list.toString());

    // Test with empty list
    MyCoolArrayList<Integer> emptyList = new MyCoolArrayList<>();
    assertEquals("[]", emptyList.toString());
  }
}

