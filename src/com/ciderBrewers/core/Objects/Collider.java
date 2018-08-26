package com.ciderBrewers.core.Objects;

public class Collider {
    public float x;
    public float y;
    public float left;
    public float right;
    public float up;
    public float down;

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
}
