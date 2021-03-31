package com.alura.dyno.math.graphics;

public class Quaternion extends FloatVector<Quaternion> {
    public Quaternion() {
        super(4);
    }
    public Quaternion(float a, float b, float c, float d) {
        super(new float[]{a, b, c, d});
    }
    public Quaternion(Quaternion origin) {
        super(origin.toArray());
    }

    public static Quaternion fromAxisAndAngle(Vector3 axis, float angle) {
        float cosHalf = (float) Math.cos(0.5f * Math.toRadians(angle));
        float sinHalf = (float) Math.sin(0.5f * Math.toRadians(angle));
        Vector3 newAxis = axis.clone().multiply(sinHalf);

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
                cosX * cosY * cosZ - sinX * sinY * sinZ,
                sinX * sinY * cosZ + cosX * cosY * sinZ,
                sinX * cosY * cosZ + cosX * sinY * sinZ,
                cosX * sinY * cosZ - sinX * cosY * sinZ
        );
    }
    public static Quaternion fromToRotation(Vector3 from, Vector3 to) {
        float fromLength = from.length();
        float toLength = to.length();

        Vector3 axis = from.clone().cross(to);
        float angle = (float) Math.sqrt(fromLength * fromLength * toLength * toLength)
                + from.dotProduct(to);

        Quaternion q = new Quaternion(angle, axis.x(), axis.y(), axis.z());
        q.normalize();

        return q;
    }

    public static Quaternion multiply(Quaternion q_lhs, Quaternion q_rhs) {
        float newA = q_lhs.a() * q_rhs.a() - q_lhs.b() * q_rhs.b() - q_lhs.c() * q_rhs.c() - q_lhs.d() * q_rhs.d();
        float newB = q_lhs.a() * q_rhs.b() + q_lhs.b() * q_rhs.a() + q_lhs.c() * q_rhs.d() - q_lhs.d() * q_rhs.c();
        float newC = q_lhs.a() * q_rhs.c() - q_lhs.b() * q_rhs.d() + q_lhs.c() * q_rhs.a() + q_lhs.d() * q_rhs.b();
        float newD = q_lhs.a() * q_rhs.d() + q_lhs.b() * q_rhs.c() - q_lhs.c() * q_rhs.b() + q_lhs.d() * q_rhs.a();

        return new Quaternion(newA, newB, newC, newD);
    }

    public float a() {
        return getX_(0);
    }
    public float b() {
        return getX_(1);
    }
    public float c() {
        return getX_(2);
    }
    public float d() {
        return getX_(3);
    }

    public Quaternion multiply(Quaternion q_lhs) {
        float newA = q_lhs.a() * a() - q_lhs.b() * b() - q_lhs.c() * c() - q_lhs.d() * d();
        float newB = q_lhs.a() * b() + q_lhs.b() * a() + q_lhs.c() * d() - q_lhs.d() * c();
        float newC = q_lhs.a() * c() - q_lhs.b() * d() + q_lhs.c() * a() + q_lhs.d() * b();
        float newD = q_lhs.a() * d() + q_lhs.b() * c() - q_lhs.c() * b() + q_lhs.d() * a();

        setX_(0, newA);
        setX_(1, newB);
        setX_(2, newC);
        setX_(3, newD);

        return this;
    }

    @Override public Quaternion clone() {
        return new Quaternion(this);
    }

}
