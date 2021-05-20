package com.alura.dyno.math.graphics;

import com.alura.dyno.math.MathExtra;
import com.alura.dyno.math.linalg.AbstractFloatVector;

import org.jetbrains.annotations.NotNull;

public class Quaternion extends AbstractFloatVector<Quaternion> {
    public Quaternion() {
        super(4);
    }
    public Quaternion(float a, float b, float c, float d) {
        super(new float[]{a, b, c, d});
        normalize();
    }
    public Quaternion(@NotNull Quaternion origin) {
        super(origin.toArray());
    }

    public static Quaternion fromAxisAndAngle(Vector3 axis, float angle) {
        float cosHalf = (float) Math.cos(0.5f * Math.toRadians(angle));
        float sinHalf = (float) Math.sin(0.5f * Math.toRadians(angle));
        Vector3 newAxis = axis.clone().normalize().multiply(sinHalf);

        return new Quaternion(cosHalf, newAxis.x(), newAxis.y(), newAxis.z());
    }
    public static Quaternion fromEulerAngles(Vector3 eulerAngles) {
        float cosX = (float) Math.cos(Math.toRadians(0.5f * eulerAngles.x()));
        float sinX = (float) Math.sin(Math.toRadians(0.5f * eulerAngles.x()));
        float cosY = (float) Math.cos(Math.toRadians(0.5f * eulerAngles.y()));
        float sinY = (float) Math.sin(Math.toRadians(0.5f * eulerAngles.y()));
        float cosZ = (float) Math.cos(Math.toRadians(0.5f * eulerAngles.z()));
        float sinZ = (float) Math.sin(Math.toRadians(0.5f * eulerAngles.z()));

        return new Quaternion(
                cosX * cosY * cosZ + sinX * sinY * sinZ,
                sinX * cosY * cosZ - cosX * sinY * sinZ,
                cosX * sinY * cosZ + sinX * cosY * sinZ,
                cosX * cosY * sinZ - sinX * sinY * cosZ
        );
    }
    public static Quaternion fromToRotation(Vector3 from, Vector3 to) {
        float fromLength = from.norm2();
        float toLength = to.norm2();

        Vector3 axis = from.clone().cross(to);
        float angle = (float) Math.sqrt(fromLength * fromLength * toLength * toLength)
                + from.dotProduct(to);

        Quaternion q = new Quaternion(angle, axis.x(), axis.y(), axis.z());
        q.normalize();

        return q;
    }
    public static Quaternion multiply(Quaternion q_lhs, Quaternion q_rhs) {
        return q_lhs.clone().postMultiply(q_rhs);
    }

    public float w() {
        return getX_(0);
    }
    public float x() {
        return getX_(1);
    }
    public float y() {
        return getX_(2);
    }
    public float z() {
        return getX_(3);
    }

    public Quaternion preMultiply(@NotNull Quaternion q_lhs) {
        float newA = q_lhs.w() * w() - q_lhs.x() * x() - q_lhs.y() * y() - q_lhs.z() * z();
        float newB = q_lhs.w() * x() + q_lhs.x() * w() + q_lhs.y() * z() - q_lhs.z() * y();
        float newC = q_lhs.w() * y() - q_lhs.x() * z() + q_lhs.y() * w() + q_lhs.z() * x();
        float newD = q_lhs.w() * z() + q_lhs.x() * y() - q_lhs.y() * x() + q_lhs.z() * w();

        setX_(0, newA);
        setX_(1, newB);
        setX_(2, newC);
        setX_(3, newD);

        return this;
    }
    public Quaternion postMultiply(@NotNull Quaternion q_rhs) {
        float newA = w() * q_rhs.w() - x() * q_rhs.x() - y() * q_rhs.y() - z() * q_rhs.z();
        float newB = w() * q_rhs.x() + x() * q_rhs.w() + y() * q_rhs.z() - z() * q_rhs.y();
        float newC = w() * q_rhs.y() - x() * q_rhs.z() + y() * q_rhs.w() + z() * q_rhs.x();
        float newD = w() * q_rhs.z() + x() * q_rhs.y() - y() * q_rhs.x() + z() * q_rhs.w();

        setX_(0, newA);
        setX_(1, newB);
        setX_(2, newC);
        setX_(3, newD);

        return this;
    }

    public Vector3 getEulerAngles() {
        float f1 = 2.0f * (w() * x() + y() * z());
        float f2 = 1.0f - 2.0f * (x() * x() + y() * y());
        float f3 = MathExtra.clamp(2.0f * (w() * y() - z() * x()), -1.0f, 1.0f);
        float f4 = 2.0f * (w() * z() + x() * y());
        float f5 = 1.0f - 2.0f * (y() * y() + z() * z());

        return new Vector3(
                (float) Math.toDegrees(Math.atan2(f1, f2)),
                (float) Math.toDegrees(Math.asin(f3)),
                (float) Math.toDegrees(Math.atan2(f4, f5)));
    }
    public void setEulerAngles(Vector3 eulerAngles) {
        this.data = Quaternion.fromEulerAngles(eulerAngles).data;
    }

    @Override
    public Quaternion clone() {
        return new Quaternion(this);
    }
}
