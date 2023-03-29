package me.fengming;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Expr {
    private List<String> group = new ArrayList<>();
    private final ArrayList<Function<Integer, Integer>> functions = new ArrayList<>();
    private final String expr;
    public Expr(String expr) {
        this.expr = expr;
    }
    public ArrayList<Function<Integer, Integer>> getFunctions() {
        return functions;
    }
    private int a, b, c;

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public void preform() {
        group = makeGroup(expr);

        a = toNumber(0);
        b = toNumber(1);
        c = toNumber(2);
        for (int i = 0; i < group.size(); i++) {
            String str = group.get(i);
            if (str.equals("")) {
                if (i == 0) {
                    functions.add(x -> x * x);
                } else if (i == 1) {
                    functions.add(x -> x);
                } else if (i == 2) {
                    functions.add(x -> 0);
                }
            } else {
                if (i == 0) {
                    functions.add(x -> x * x * a);
                } else if (i == 1) {
                    functions.add(x -> x * b);
                } else if (i == 2) {
                    functions.add(x -> c);
                }
            }
        }
        // y = 2p+3x-4
    }

    private List<String> makeGroup(String e) {
        return List.of(e.split("x\\^\\d"));
    }
    private int toNumber(int index) {
        String s = group.get(index);
        if (s.equals("-")) return -1;
        if (s.equals("+")) return 1;
        if (s.equals("")) return 1;
        return Integer.parseInt(s);
    }

    public int add(int x) {
        return (-b/a) - x;
    }
}
