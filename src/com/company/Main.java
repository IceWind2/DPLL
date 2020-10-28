package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        int V = 1;
        HashMap<var, Integer> res = null;
        Set<subSet> disSub = new HashSet<>();

        while (true) {
            V += 1;
            HashMap<var, Integer> M = new HashMap<>();

            Vector<var> tmp = new Vector<>();
            for (int i = 1; i < V; i++) {
                tmp.clear();
                for (int j = 1; j <= k; j++) {
                    tmp.add(new var(i, V, j));
                }
                disSub.add(new subSet(tmp));
            }
            for (int i = 1; i < V; i++) {
                for (int j = i + 1; j < V; j++) {
                    for (int z = 1; z <= k; z++) {
                        tmp.clear();
                        tmp.add(var.neg(new var(i, j, z)));
                        tmp.add(var.neg(new var(j, V, z)));
                        tmp.add(var.neg(new var(i, V, z)));
                        disSub.add(new subSet(tmp));
                    }
                }
            }

            Set<subSet> disSub2 = new HashSet<>();
            for (subSet s : disSub) {
                disSub2.add(new subSet(s));
            }
            M = dpll.solve(disSub2, M);

            if (M == null) {
                V -= 1;
                break;
            } else {
                res = new HashMap<>(M);
            }
        }
        System.out.println(V);
        if (res != null) {
            for (Map.Entry<var, Integer> entry : res.entrySet()) {
                if ((entry.getValue() == 1 && entry.getKey().neg == 0) || (entry.getValue() == 0 && entry.getKey().neg == 1)) {
                    System.out.println(entry.getKey().i + "-" + entry.getKey().j + " " + entry.getKey().c);
                }
            }
        }
    }
}
