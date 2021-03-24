package com.alura.dyno.math.graphics;

import android.opengl.Matrix;

import com.alura.dyno.math.linalg.Algebra;

public class GraphicMatrixFactory {
    public GraphicMatrix identity() {
        return new GraphicMatrix(new float[]{
                1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        });
    }
    public GraphicMatrix orthogonal(float width, float height, float near, float far) {
        float halfHeight = 0.5f * height;
        float halfWidth = 0.5f * width;

        return orthogonal(-halfWidth, halfWidth, -halfHeight, halfHeight, near, far);
    }
    public GraphicMatrix orthogonal(float left, float right, float bottom, float top, float near, float far) {
        if (left == right) {
            throw new IllegalArgumentException("left == right");
        }
        if (bottom == top) {
            throw new IllegalArgumentException("bottom == top");
        }
        if (near == far) {
            throw new IllegalArgumentException("near == far");
        }

        final float r_width  = 1.0f / (right - left);
        final float r_height = 1.0f / (top - bottom);
        final float r_depth  = 1.0f / (far - near);
        final float x =  2.0f * (r_width);
        final float y =  2.0f * (r_height);
        final float z = -2.0f * (r_depth);
        final float tx = -(right + left) * r_width;
        final float ty = -(top + bottom) * r_height;
        final float tz = -(far + near) * r_depth;

        float[] data = new float[]{
                x, 0, 0, tx,
                0, y, 0, ty,
                0, 0, z, tz,
                0, 0, 0, 1.0f};
        return new GraphicMatrix(data);
    }
    public GraphicMatrix lookAt(Vector3 eye, Vector3 center, Vector3 up) {
        Vector3 f = center.clone().minus(eye);
        f.normalize();

        Vector3 s = f.clone().cross(up);
        s.normalize();

        Vector3 u = s.clone().cross(f);

        float[] rm = new float[] {
                  s.x(),    u.x(),  -f.x(), -eye.x(),
                  s.y(),    u.y(),  -f.y(), -eye.y(),
                  s.z(),    u.z(),  -f.z(), -eye.z(),
                   0.0f,     0.0f,    0.0f,     1.0f};
        return new GraphicMatrix(rm);
    }

    public GraphicMatrix translation(Vector3 delta) {
        return new GraphicMatrix(new float[] {
                1.0f, 0.0f, 0.0f, delta.x(),
                0.0f, 1.0f, 0.0f, delta.y(),
                0.0f, 0.0f, 1.0f, delta.z(),
                0.0f, 0.0f, 0.0f,      1.0f
        });
    }
    public GraphicMatrix scale(Vector3 factor) {
        return new GraphicMatrix(new float[] {
                factor.x(), 0.0f, 0.0f, 0.0f,
                0.0f, factor.y(), 0.0f, 0.0f,
                0.0f, 0.0f, factor.z(), 0.0f,
                0.0f, 0.0f,       0.0f, 1.0f
        });
    }
    public GraphicMatrix rotateEuler(Vector3 euler) {
        GraphicMatrix rotX = rotate(new Vector3(1.0f, 0.0f, 0.0f), euler.x());
        GraphicMatrix rotY = rotate(new Vector3(0.0f, 1.0f, 0.0f), euler.y());
        GraphicMatrix rotZ = rotate(new Vector3(0.0f, 0.0f, 1.0f), euler.z());
        return rotZ.postMultiply(rotX).postMultiply(rotY);
    }
    public GraphicMatrix rotate(Vector3 axis, float angle) {
        float rad = angle * (float) (Math.PI / 180.0f);
        float s = (float) Math.sin(rad);
        float c = (float) Math.cos(rad);

        if (axis.x() == 1.0f && axis.y() == 0.0f && axis.z() == 0.0f) {
            return new GraphicMatrix(new float[] {
                    1.0f, 0.0f, 0.0f, 0.0f,
                    0.0f,    c,   -s, 0.0f,
                    0.0f,    s,    c, 0.0f,
                    0.0f, 0.0f, 0.0f, 1.0f
            });
        }
        else if (axis.x() == 0.0f && axis.y() == 1.0f && axis.z() == 0.0f) {
            return new GraphicMatrix(new float[] {
                       c, 0.0f,   s, 0.0f,
                    0.0f, 1.0f, 0.0f, 0.0f,
                    -s, 0.0f,    c, 0.0f,
                    0.0f, 0.0f, 0.0f, 1.0f
            });
        }
        else if (axis.x() == 0.0f && axis.y() == 0.0f && axis.z() == 1.0f) {
            return new GraphicMatrix(new float[] {
                       c,   -s, 0.0f, 0.0f,
                       s,    c, 0.0f, 0.0f,
                    0.0f, 0.0f, 1.0f, 0.0f,
                    0.0f, 0.0f, 0.0f, 1.0f
            });
        }
        else {
            Vector3 an = axis.clone().normalize();
            float nc = 1.0f - c;
            float xy = an.x() * an.y();
            float yz = an.y() * an.z();
            float zx = an.z() * an.x();
            float xs = an.x() * s;
            float ys = an.y() * s;
            float zs = an.z() * s;

            return new GraphicMatrix(new float[] {
                an.x() * an.x() * nc + c,           xy * nc - zs,          zx * nc + ys, 0.0f,
                            xy * nc + zs, an.y() * an.y()*nc + c,          yz * nc - xs, 0.0f,
                            zx * nc - ys,           yz * nc + xs, an.z()*an.z()*nc +  c, 0.0f,
                                    0.0f,                   0.0f,                  0.0f, 1.0f
            });
        }
    }

    public GraphicMatrix frustum(float left, float right, float bottom, float top, float near, float far) {
        if (left == right) {
            throw new IllegalArgumentException("left == right");
        }
        if (top == bottom) {
            throw new IllegalArgumentException("top == bottom");
        }
        if (near == far) {
            throw new IllegalArgumentException("near == far");
        }
        if (near <= 0.0f) {
            throw new IllegalArgumentException("near <= 0.0f");
        }
        if (far <= 0.0f) {
            throw new IllegalArgumentException("far <= 0.0f");
        }
        final float r_width  = 1.0f / (right - left);
        final float r_height = 1.0f / (top - bottom);
        final float r_depth  = 1.0f / (near - far);
        final float x = 2.0f * (near * r_width);
        final float y = 2.0f * (near * r_height);
        final float A = (right + left) * r_width;
        final float B = (top + bottom) * r_height;
        final float C = (far + near) * r_depth;
        final float D = 2.0f * (far * near * r_depth);

        float[] m = new float[16];
        m[0] = x;
        m[5] = y;
        m[8] = A;
        m[ 9] = B;
        m[10] = C;
        m[14] = D;
        m[11] = -1.0f;
        m[ 1] = 0.0f;
        m[ 2] = 0.0f;
        m[ 3] = 0.0f;
        m[ 4] = 0.0f;
        m[ 6] = 0.0f;
        m[ 7] = 0.0f;
        m[12] = 0.0f;
        m[13] = 0.0f;
        m[15] = 0.0f;

        return new GraphicMatrix(m);
    }
    public GraphicMatrix perspective(float fov, float aspect, float near, float far) {
        float f = 1.0f / (float) Math.tan(fov * (Math.PI / 360.0));
        float rangeReciprocal = 1.0f / (near - far);

        float[] m = new float[]{
            f / aspect, 0.0f,                           0.0f,                                0.0f,
                  0.0f,    f,                           0.0f,                                0.0f,
                  0.0f, 0.0f, (far + near) * rangeReciprocal, 2.0f * far * near * rangeReciprocal,
                  0.0f, 0.0f,                          -1.0f,                                2.0f
        };

        return new GraphicMatrix(m);
    }
}

