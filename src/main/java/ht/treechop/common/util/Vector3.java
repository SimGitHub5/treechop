package ht.treechop.common.util;

import com.mojang.math.Vector3d;
import com.mojang.math.Vector3f;
import net.minecraft.core.Position;

public class Vector3 implements Position {

    private final double x;
    private final double y;
    private final double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector3f vector3f) {
        this(vector3f.x(), vector3f.y(), vector3f.z());
    }

    public Vector3(Position position) {
        this(position.x(), position.y(), position.z());
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public Vector3 withX(double x) {
        return new Vector3(x, this.y(), this.z());
    }

    public Vector3 withX(Number x) {
        return withX(x.doubleValue());
    }

    public Vector3 withY(double y) {
        return new Vector3(this.x(), y, this.z());
    }

    public Vector3 withY(Number y) {
        return withY(y.doubleValue());
    }

    public Vector3 withZ(double z) {
        return new Vector3(this.x(), this.y(), z);
    }

    public Vector3 withZ(Number z) {
        return withZ(z.doubleValue());
    }

    public Vector3 scale(double factor) {
        return new Vector3(this.x() * factor, this.y() * factor, this.z() * factor);
    }

    public Vector3 scale(Number factor) {
        return scale(factor.doubleValue());
    }

    public Vector3 add(Vector3 vector3) {
        return new Vector3(
                this.x() + vector3.x(),
                this.y() + vector3.y(),
                this.z() + vector3.z()
        );
    }

    public Vector3 add(double amount) {
        return new Vector3(
                this.x() + amount,
                this.y() + amount,
                this.z() + amount
        );
    }

    public Vector3 add(Number amount) {
        return add(amount.doubleValue());
    }

    public Vector3f asVector3f() {
        return new Vector3f((float)this.x(), (float)this.y(), (float)this.z());
    }

    public Vector3d asVector3d() {
        return new Vector3d(this.x(), this.y(), this.z());
    }

}
