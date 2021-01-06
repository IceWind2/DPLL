package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class dpll {
    private static void eliminate_pure(Set<subSet> S, var l) {
        for (subSet e : S) {
            e.set.remove(l);
        }
    }

    private static void unit_propagate(Set<subSet> S, var l) {
        S.removeIf(e -> e.set.contains(l));
        eliminate_pure(S, var.neg(l));
    }

    public static HashMap<var, Integer> solve(Set<subSet> disSub, HashMap<var, Integer> M) {
        boolean fl = true;
        while (fl) {
            fl = false;

            if (disSub.size() == 0) {
                return M;
            }
            for (subSet e : disSub) {
                if (e.set.size() == 0) {
                    return null;
                }
            }

            //unit propagate
            Set<var> tmp = new HashSet<>();
            for (subSet e : disSub) {
                if (e.set.size() == 1) {
                    tmp.add(e.set.get(0));
                }
            }
            for (var l : tmp) {
                fl = true;
                dpll.unit_propagate(disSub, l);
                M.put(l, 1);
            }

            //eliminate pure literal
            tmp.clear();
            for (subSet e : disSub) {
                tmp.addAll(e.set);
            }
            Set<subSet> tmp2 = new HashSet<>();
            for (var l : tmp) {
                if (!tmp.contains(var.neg(l))) {
                    fl = true;
                    M.put(l, 1);
                    for (subSet e : disSub) {
                        if (e.set.contains(l)) {
                            tmp2.add(e);
                        }
                    }
                }
            }
            for (subSet s : tmp2) {
                disSub.remove(s);
            }
        }
        //guessing literal
        var t = null;
        for (subSet e : disSub) {
            for (var l : e.set) {
                t = new var(l);
                break;
            }
            if (t != null) {
                break;
            }
        }
        if (t == null) return null;
        Set<subSet> _disSub = new HashSet<>();
        for (subSet e : disSub) {
            _disSub.add(new subSet(e));
        }
        HashMap<var, Integer> _M = new HashMap<>();
        for (Map.Entry<var, Integer> entry : M.entrySet()) {
            _M.put(entry.getKey(), entry.getValue());
        }
        _disSub.add(new subSet(t));
        _M.put(t, 1);
        _M = solve(_disSub, _M);
        if (_M != null) {
            return _M;
        }
        else {
            _M = new HashMap<>();
            for (Map.Entry<var, Integer> entry : M.entrySet()) {
                _M.put(entry.getKey(), entry.getValue());
            }
            _M.put(t, 0);
            _disSub = new HashSet<>();
            for (subSet e : disSub) {
                _disSub.add(new subSet(e));
            }
            _disSub.add(new subSet(var.neg(t)));
            return solve(_disSub, _M);
        }
    }
}
