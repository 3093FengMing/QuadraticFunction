package me.fengming;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Function;

public class Window {
    Frame frame = new Frame();
    Canvas canv = new Canvas();
    TextField edit = new TextField();
    int height = 1000, wight = 1000;
    Expr exp = new Expr("x^2+0x+0");
    ArrayList<Function<Integer, Integer>> functions = new ArrayList<>();
    private class Canvas extends java.awt.Canvas {
        @Override
        public void paint(Graphics g1) {
            Graphics2D g = (Graphics2D) g1;

            g.drawLine(wight/2, 0, wight/2, height);
            g.drawLine(0, height/2, wight, height/2);
            if (functions.isEmpty()) return;

            ArrayList<Integer> xc = new ArrayList<>();
            ArrayList<Integer> yc = new ArrayList<>();
            for (int x = -100; x < 100; x++) {
                int x2 = exp.add(x);
                int tx = functions.get(0).apply(x) + functions.get(1).apply(x) + functions.get(2).apply(x);
                int gx = functions.get(0).apply(x2) + functions.get(1).apply(x2) + functions.get(2).apply(x2);
                for (int y = -100; y < 100; ++y) {
                    if (y == tx || y == gx) {
                        xc.add(x * 12);
                        yc.add(y * 12);
                    }
                }
            }

            for (int i = 0; i < xc.size(); i++) {
                if (i == xc.size() - 1) {
                    break;
                }
                g.setColor(Color.RED);
                // g.drawString("", wight/2 + xc.get(i), height/2 - yc.get(i));
                g.drawLine(wight/2 + xc.get(i), height/2 - yc.get(i), wight/2 + xc.get(i + 1), height/2 - yc.get(i + 1));
            }
        }
    }

    public void init() {
        edit.addActionListener(e -> {
            String expr = ((TextField)e.getSource()).getText();
            expr = expr.substring(2).replace("x^2", "p").replace("x", "x^1").replace("p", "x^2");
            exp = new Expr(expr);
            exp.preform();
            functions = exp.getFunctions();
            canv.repaint();
        });

        Box b = Box.createHorizontalBox();
        b.add(edit);
        Dimension d = new Dimension(wight, height);
        canv.setPreferredSize(d);
        frame.add(canv);
        frame.add(b, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}
