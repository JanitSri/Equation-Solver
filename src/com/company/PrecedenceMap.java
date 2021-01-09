package com.company;

import java.util.HashMap;
import java.util.Map;

public class PrecedenceMap {
    /*
    * These maps represent precedence and associativity. If the precedence is greater inside the stack
    * than outside the stack, it signifies a left to right association (vice-versa for right to left association).
    * */

    // precedence of operator outside of the stack
    static final Map<Character, Integer> OUTSIDE_STACK_PRECEDENCE = new HashMap<>(){{
        put('+', 1);
        put('-', 1);
        put('*', 3);
        put('/', 3);
        put('^', 6);
        put('(', 7);
        put(')', 0);
        put('[', 7);
        put(']', 0);
    }};

    // precedence of operator inside of the stack
    static final Map<Character, Integer> INSIDE_STACK_PRECEDENCE = new HashMap<>(){{
        put('+', 2);
        put('-', 2);
        put('*', 4);
        put('/', 4);
        put('^', 5);
        put('(', 0);
        put(')', null);
        put('[', 0);
        put(']', null);
    }};
}