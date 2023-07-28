package net.broscorp.generics;

import java.util.function.Function;

public interface MyCoolList<T extends Number> extends Iterable<T> {
    void add(T element);

    T remove(int index);

    T get(int index);

    int size();

    <R extends Number> MyCoolList<R> map(Function<T, R> function);
}
