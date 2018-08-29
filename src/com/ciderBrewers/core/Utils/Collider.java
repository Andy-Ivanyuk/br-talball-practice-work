package com.ciderBrewers.core.Utils;

public class Collider {
    public float x;
    public float y;
    public float left;
    public float right;
    public float up;
    public float down;

    private static final int OUT_LEFT = 1;
    private static final int OUT_TOP = 2;
    private static final int OUT_RIGHT = 4;
    private static final int OUT_BOTTOM = 8;

    public Collider(float x, float y, float left, float up, float right, float down) {
        this.x = x;
        this.y = y;
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    public float getWidth() {
        return left + right;
    }

    public float getHeight() {
        return up + down;
    }

    public boolean intersects(Collider r) {
        float tw = getWidth();
        float th = getHeight();
        float rw = r.getWidth();
        float rh = r.getHeight();
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        float tx = this.x - left;
        float ty = this.y - up;
        float rx = r.x - r.left;
        float ry = r.y - r.up;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    public boolean intersectsLine(double x1, double y1, double x2, double y2) {
        int out1, out2;
        if ((out2 = outcode(x2, y2)) == 0) {
            return true;
        }
        while ((out1 = outcode(x1, y1)) != 0) {
            if ((out1 & out2) != 0) {
                return false;
            }
            if ((out1 & (OUT_LEFT | OUT_RIGHT)) != 0) {
                double lx = x - left;
                if ((out1 & OUT_RIGHT) != 0) {
                    lx += getWidth();
                }
                y1 = y1 + (lx - x1) * (y2 - y1) / (x2 - x1);
                x1 = lx;
            } else {
                double ly = y - up;
                if ((out1 & OUT_BOTTOM) != 0) {
                    ly += getHeight();
                }
                x1 = x1 + (ly - y1) * (x2 - x1) / (y2 - y1);
                y1 = ly;
            }
        }
        return true;
    }

    private int outcode(double x, double y) {
        /*
         * Note on casts to double below.  If the arithmetic of
         * x+w or y+h is done in float, then some bits may be
         * lost if the binary exponents of x/y and w/h are not
         * similar.  By converting to double before the addition
         * we force the addition to be carried out in double to
         * avoid rounding error in the comparison.
         *
         * See bug 4320890 for problems that this inaccuracy causes.
         */
        int out = 0;
        if (getWidth() <= 0) {
            out |= OUT_LEFT | OUT_RIGHT;
        } else if (x < this.x - left) {
            out |= OUT_LEFT;
        } else if (x > this.x - left + (double) getWidth()) {
            out |= OUT_RIGHT;
        }
        if (getHeight() <= 0) {
            out |= OUT_TOP | OUT_BOTTOM;
        } else if (y < this.y - up) {
            out |= OUT_TOP;
        } else if (y > this.y - up + (double) getHeight()) {
            out |= OUT_BOTTOM;
        }
        return out;
    }
}
