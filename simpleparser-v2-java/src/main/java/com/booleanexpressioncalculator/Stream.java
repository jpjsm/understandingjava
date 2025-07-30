package com.booleanexpressioncalculator;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Stream<T> {
    private Deque<T> elements = new LinkedList<>();

    // Add to the end of the list
    public void Append(T element){
        elements.addLast(element);
    }

    // Add to the beginning of the list, make this element 
    // the first of the list.
    public void Push(T element){
        elements.addFirst(element);
    }

    // Remove the first element of the list.
    public T Get() throws NoSuchElementException {
        return elements.pop();
    }

    public Boolean EOS(){
        return elements.isEmpty();
    }
}
