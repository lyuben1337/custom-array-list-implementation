package net.broscorp.generics;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

public class MyCoolArrayList<T extends Number> implements MyCoolList<T> {

  private static final int DEFAULT_CAPACITY = 8;
  private T[] data;
  private int size = 0;

  /**
   * Constructs a MyCoolArrayList with the specified initial capacity.
   *
   * @param initialCapacity The initial capacity of the list. Must be non-negative.
   * @throws IllegalArgumentException if the initialCapacity is negative.
   */
  public MyCoolArrayList(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Initial capacity must be above or equal zero");
    }
    data = (T[]) Array.newInstance(Number.class, initialCapacity);
  }

  public MyCoolArrayList() {
    this(DEFAULT_CAPACITY);
  }

  @Override
  public void add(T o) {
    if (size == data.length) {
      ensureCapacity(size + 1);
    }
    data[size++] = o;
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("Out of range");
    }
    return data[index];
  }

  @Override
  public T remove(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("Out of range");
    }
    T removedElem = data[index];
    System.arraycopy(data, index + 1, data, index,size - index - 1);
    data[--size] = null;
    return removedElem;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public <R extends Number> MyCoolList<R> map(Function<T, R> function) {
    MyCoolList<R> mappedList = new MyCoolArrayList<>();

    for (int i = 0; i < size; i++) {
      R mappedElem = function.apply(data[i]);
      mappedList.add(mappedElem);
    }

    return mappedList;
  }

  @Override
  public Iterator<T> iterator() {
    return new MyCoolArrayListIterator();
  }

  private void ensureCapacity(int newCapacity) {
    int currentCapacity = data.length;

    if (newCapacity > currentCapacity) {
      T[] newData = (T[]) new Object[currentCapacity * 2];
      System.arraycopy(data, 0, newData, 0, size);
      data = newData;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    MyCoolArrayList<?> that = (MyCoolArrayList<?>) o;

    if (size != that.size) {
      return false;
    }
    for (int i = 0; i < size; i++) {
      if (!Objects.equals(data[i], that.data[i])) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 74321 * Objects.hash(size);

    for (int i = 0; i < size; i++) {
      result = result + (data[i] == null ? 0 : data[i].hashCode());
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < size; i++) {
      sb.append(data[i]);
      if (i < size - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }

  /**
   * 1. Статичний (static) Inner Class:
   *    - Внутрішній клас може бути визначений зі статичним модифікатором static.
   *    - Статичний Inner Class є асоційованим з контейнерним класом, а не з його інстанціями.
   *      Це означає, що статичний внутрішній клас не має доступу до нестатичних (нестатичних)
   *      членів контейнерного класу, і він може існувати навіть без створення об’єкта
   *      контейнерного класу.
   *    - Статичний Inner Class може мати тільки статичні члени.
   *    - Використовується, коли клас внутрішнього класу не залежить від інстанційного
   *      контейнерного класа і може бути використаний із зовнішнього класу.
   * 2. Звичайний (нестатичний) Внутрішній клас:
   *    - Внутрішній клас, який не має модифікатора static, є звичайним Inner Class.
   *    - Звичайний Inner Class асоційований з конкретною інстанцією контейнерного класу.
   *      Це означає, що для створення об'єкта звичайного Inner Class спочатку потрібно створити
   *      об'єкт контейнерного класу.
   *    - Звичайний Inner Class має доступ до всіх членів контейнерного класу, навіть до приватних.
   *    - Використовується, коли клас внутрішнього класу пов'язаний з об'єктами контейнерного класу
   *      і залежить від його стану або функціональності.
   */
  private class MyCoolArrayListIterator implements Iterator<T> {
    private int currentIndex = 0;

    @Override
    public boolean hasNext() {
      return currentIndex < size;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No more elements in the list.");
      }
      return data[currentIndex++];
    }
  }
}
