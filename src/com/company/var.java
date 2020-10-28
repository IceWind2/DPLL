package com.company;

public class var {
    public int i = -1, j = -1, c = -1;
    public int neg = 0;

    public var() {}

    public var(int I, int J, int C) {
        i = I;
        j = J;
        c = C;
    }

    public var(var a) {
        this.i = a.i;
        this.j = a.j;
        this.c = a.c;
        this.neg = a.neg;
    }

    public static var neg(var a) {
        var b = new var(a);
        b.neg = 1 - a.neg;
        return b;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final var a = (var) obj;
        if (this.i == a.i && this.j == a.j && this.c == a.c && this.neg == a.neg) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.i;
        hash = 37 * hash + this.j;
        hash = 37 * hash + this.c;
        hash = 37 * hash + this.neg;
        return hash;
    }
}
