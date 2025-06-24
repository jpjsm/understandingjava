package com.jpjofre.learning;

public interface IList<T> {
    int Add(T item);
    void Clear();
    boolean Contains(T item);
    int IndexOf(T item);
    void InsertAt(int pos, T item);
    T ItemAt(int pos);
    T Remove();
    T RemoveAt(int pos);
}