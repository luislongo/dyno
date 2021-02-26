package com.alura.dyno.math.graphics;

import android.graphics.RectF;
import android.opengl.GLES10;
import android.opengl.Matrix;

public class GraphicMatrixFactory {
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

        final float r_width = 1.0f / (right - left);
        final float r_height = 1.0f / (top - bottom);
        final float r_depth = 1.0f / (far - near);
        final float x = 2.0f * (r_width);
        final float y = 2.0f * (r_height);
        final float z = -2.0f * (r_depth);
        final float tx = -(right + left) * r_width;
        final float ty = -(top + bottom) * r_height;
        final float tz = -(far + near) * r_depth;

        return new GraphicMatrix(new float[]
                {
                         x,  0,  0, tx,
                         0,  y,  0, ty,
                         0,  0,  z, tz,
                         0,  0,  0,  1
                });
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
        final float r_width = 1.0f / (right - left);
        final float r_height = 1.0f / (top - bottom);
        final float r_depth = 1.0f / (near - far);
        final float x = 2.0f * (near * r_width);
        final float y = 2.0f * (near * r_height);
        final float A = (right + left) * r_width;
        final float B = (top + bottom) * r_height;
        final float C = (far + near) * r_depth;
        final float D = 2.0f * (far * near * r_depth);

        return new GraphicMatrix(new float[]{
                x, 0, A, 0,
                0, y, B, 0,
                0, 0, C, D,
                0, 0, -1.0f, 0
        });
    }
    public GraphicMatrix perspective(float fov, float aspect, float near, float far) {
        float f = 1.0f / (float) Math.tan(fov * (Math.PI / 360.0));
        float rangeReciprocal = 1.0f / (near - far);

        return new GraphicMatrix(new float[]{
                f / aspect, 0.0f,                           0.0f, 0.0f,
                      0.0f,    f,                           0.0f, 0.0f,
                      0.0f, 0.0f, (far + near) * rangeReciprocal, 2.0f * far * near * rangeReciprocal,
                      0.0f, 0.0f,                          -1.0f, 0.0f
        });
    }
    public GraphicMatrix lookAt(Vector3 eye, Vector3 center, Vector3 up) {
        Vector3 f = center.clone().minus(eye).normalize();
        Vector3 s = f.clone().cross(up).normalize();
        Vector3 u = s.clone().cross(f);

        return new GraphicMatrix(new float[]{
           s.x(),  s.y(),  s.z(),  s.x() + eye.x(),
           u.x(),  u.y(),  u.z(),  u.y() + eye.y(),
          -f.x(), -f.y(), -f.z(), -f.z() + eye.z(),
            0.0f,   0.0f,   0.0f, 1.0f
        });
    }
    public GraphicMatrix identity() {
        return new GraphicMatrix(new float[]{
                1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        });
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
        Vector3 rad = euler.clone().multiply((float) Math.PI / 180.0f);
        float cx = (float) Math.cos(rad.x());
        float sx = (float) Math.sin(rad.x());
        float cy = (float) Math.cos(rad.y());
        float sy = (float) Math.sin(rad.y());
        float cz = (float) Math.cos(rad.z());
        float sz = (float) Math.sin(rad.z());
        float cxsy = cx * sy;
        float sxsy = sx * sy;

        return new GraphicMatrix(new float[] {
                 cy * cz,  cxsy * cz + cx * sz, -sxsy * cz + sx * sz, 0.0f,
                -cy * sz, -cxsy * sz + cx * cz,  sxsy * sz + sx * cz, 0.0f,
                      sy,             -sx * cy,              cx * cy, 0.0f,
                    0.0f,                 0.0f,                 0.0f, 1.0f
        });
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
                       c, 0.0f,    s, 0.0f,
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


}

