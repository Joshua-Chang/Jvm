package com.jvm.t;

import java.util.ArrayList;

public class Test {
    public int foo() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        int result = list.get(0);
        return result;
    }
}
