package developmental.warlocks.GL;

/**
 * Created by Scott on 5/01/14.
 */


import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import Tools.Vector;

/**
 * A 2D rectangular mesh. Can be drawn textured or untextured.
 * This version is modified from the original Grid.java (found in
 * the SpriteText package in the APIDemos Android sample) to support hardware
 * vertex buffers.
 */
public class Grid {
    String s = "    void main (void)    {if(gl_Colorgl_FragColor  gl_FragColor = vec4(0.0, 1.0, 0.0, 1.0);    }";
    private FloatBuffer mFloatVertexBuffer;
    private FloatBuffer mFloatTexCoordBuffer;
    private FloatBuffer mFloatColorBuffer;
    private IntBuffer mFixedVertexBuffer;
    private IntBuffer mFixedTexCoordBuffer;
    private IntBuffer mFixedColorBuffer;
    private CharBuffer mIndexBuffer;
    private Buffer mVertexBuffer;
    private Buffer mTexCoordBuffer;
    private Buffer mColorBuffer;
    private int mCoordinateSize;
    private int mCoordinateType;
    private int mW;
    private int mH;
    private int mIndexCount;
    private boolean mUseHardwareBuffers;
    private int mVertBufferIndex;
    private int mIndexBufferIndex;
    private int mTextureCoordBufferIndex;
    private int mColorBufferIndex;

    public Grid(int vertsAcross, int vertsDown, boolean useFixedPoint) {
        if (vertsAcross < 0 || vertsAcross >= 65536) {
            throw new IllegalArgumentException("vertsAcross");
        }
        if (vertsDown < 0 || vertsDown >= 65536) {
            throw new IllegalArgumentException("vertsDown");
        }
        if (vertsAcross * vertsDown >= 65536) {
            throw new IllegalArgumentException("vertsAcross * vertsDown >= 65536");
        }

        mUseHardwareBuffers = false;

        mW = vertsAcross;
        mH = vertsDown;
        int size = vertsAcross * vertsDown;
        final int FLOAT_SIZE = 4;
        final int FIXED_SIZE = 4;
        final int CHAR_SIZE = 2;

        if (useFixedPoint) {
            mFixedVertexBuffer = ByteBuffer.allocateDirect(FIXED_SIZE * size * 3)
                    .order(ByteOrder.nativeOrder()).asIntBuffer();

            mFixedTexCoordBuffer = ByteBuffer.allocateDirect(FIXED_SIZE * size * 2)
                    .order(ByteOrder.nativeOrder()).asIntBuffer();
            mFixedColorBuffer = ByteBuffer.allocateDirect(FIXED_SIZE * size * 4)
                    .order(ByteOrder.nativeOrder()).asIntBuffer();

            mVertexBuffer = mFixedVertexBuffer;
            mTexCoordBuffer = mFixedTexCoordBuffer;
            mColorBuffer = mFixedColorBuffer;
            mCoordinateSize = FIXED_SIZE;
            mCoordinateType = GL10.GL_FIXED;

        } else {
            mFloatVertexBuffer = ByteBuffer.allocateDirect(FLOAT_SIZE * size * 3)
                    .order(ByteOrder.nativeOrder()).asFloatBuffer();
            mFloatTexCoordBuffer = ByteBuffer.allocateDirect(FLOAT_SIZE * size * 2)
                    .order(ByteOrder.nativeOrder()).asFloatBuffer();

            mFloatColorBuffer = ByteBuffer.allocateDirect(FLOAT_SIZE * size * 4)
                    .order(ByteOrder.nativeOrder()).asFloatBuffer();


            mVertexBuffer = mFloatVertexBuffer;
            mTexCoordBuffer = mFloatTexCoordBuffer;
            mColorBuffer = mFloatColorBuffer;
            mCoordinateSize = FLOAT_SIZE;
            mCoordinateType = GL10.GL_FLOAT;
        }


        int quadW = mW - 1;
        int quadH = mH - 1;
        int quadCount = quadW * quadH;
        int indexCount = quadCount * 6;
        mIndexCount = indexCount;
        mIndexBuffer = ByteBuffer.allocateDirect(CHAR_SIZE * indexCount)
                .order(ByteOrder.nativeOrder()).asCharBuffer();

        /*
         * Initialize triangle list mesh.
         *
         *     [0]-----[  1] ...
         *      |    /   |
         *      |   /    |
         *      |  /     |
         *     [w]-----[w+1] ...
         *      |       |
         *
         */

        {
            int i = 0;
            for (int y = 0; y < quadH; y++) {
                for (int x = 0; x < quadW; x++) {
                    char a = (char) (y * mW + x);
                    char b = (char) (y * mW + x + 1);
                    char c = (char) ((y + 1) * mW + x);
                    char d = (char) ((y + 1) * mW + x + 1);

                    mIndexBuffer.put(i++, a);
                    mIndexBuffer.put(i++, b);
                    mIndexBuffer.put(i++, c);

                    mIndexBuffer.put(i++, b);
                    mIndexBuffer.put(i++, c);
                    mIndexBuffer.put(i++, d);
                }
            }
        }

        mVertBufferIndex = 0;
    }

    public static void beginDrawing(GL10 gl, boolean useTexture, boolean useColor) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        if (useTexture) {
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            gl.glEnable(GL10.GL_TEXTURE_2D);
        } else {
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            gl.glDisable(GL10.GL_TEXTURE_2D);
        }

        if (useColor) {
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        } else {
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        }
    }

    public static void endDrawing(GL10 gl) {
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    public static ArrayList<Grid> createSingleLineGrid(Vector size, int numberOfSprites) {
        ArrayList<Grid> g = new ArrayList<Grid>();
        for (int i = 0; i < numberOfSprites; i++) {
            Grid backgroundGrid = new Grid(2, 2, false);
            backgroundGrid.set(0, 0, -0.5f * size.x / 2, -size.y / 2, 0.0f, 1f / numberOfSprites * i, 1.0f, null);
            backgroundGrid.set(1, 0, size.x / 2, -size.y / 2, 0.0f, 1f / numberOfSprites * (i + 1), 1.0f, null);
            backgroundGrid.set(0, 1, -0.5f * size.x / 2, size.y / 2, 0.0f, 1f / numberOfSprites * i, 0.0f, null);
            backgroundGrid.set(1, 1, size.x / 2, size.y / 2, 0.0f, 1f / numberOfSprites * (i + 1), 0.0f, null);
            g.add(backgroundGrid);
        }
        return g;
    }

    public static ArrayList<Grid> FramesTail(Vector size) {

        ArrayList<Grid> mGrid = new ArrayList<Grid>();
        for (int i = 0; i < 4; i++) {
            Grid backgroundGrid = new Grid(2, 2, false);
            backgroundGrid.set(0, 0, -1.5f * size.x, -size.y / 2, 0.0f, 0.25f * i, 1.0f, null);
            backgroundGrid.set(1, 0, size.x / 2, -size.y / 2, 0.0f, 0.25f * (i + 1), 1.0f, null);
            backgroundGrid.set(0, 1, -1.5f * size.x, size.y / 2, 0.0f, 0.25f * i, 0.0f, null);
            backgroundGrid.set(1, 1, size.x / 2, size.y / 2, 0.0f, 0.25f * (i + 1), 0.0f, null);
            mGrid.add(backgroundGrid);
        }
        return mGrid;

    }

    public static ArrayList<Grid> EffectGrid(Vector size, float framecount) {
        ArrayList<Grid> mGrid = new ArrayList<Grid>();
        for (float i = 0; i < framecount; i++) {
            Grid backgroundGrid = new Grid(2, 2, false);
            backgroundGrid.set(0, 0, 0, 0, 0.0f, 1 / framecount * i, 1.0f, null);
            backgroundGrid.set(1, 0, size.x, 0, 0.0f, 1 / framecount * (i + 1), 1.0f, null);
            backgroundGrid.set(0, 1, 0, size.y, 0.0f, 1 / framecount * i, 0.0f, null);
            backgroundGrid.set(1, 1, size.x, size.y, 0.0f, 1 / framecount * (i + 1), 0.0f, null);
            mGrid.add(backgroundGrid);
        }
        return mGrid;
    }

    public static Grid shadowGridGenerateProjectile(Vector size) {
        Grid backgroundGrid = new Grid(2, 2, false);
        backgroundGrid.set(0, 0, -size.x / 2, -size.y / 2, 0.0f, 0, 1.0f, null);
        backgroundGrid.set(1, 0, size.x / 2, -size.y / 2, 0.0f, 1, 1.0f, null);
        backgroundGrid.set(0, 1, -size.x / 2, size.y / 2, 0.0f, 0, 0.0f, null);
        backgroundGrid.set(1, 1, size.x / 2, size.y / 2, 0.0f, 1, 0.0f, null);
        return backgroundGrid;
    }

    public static Grid shadowGridGenerateObject(Vector size) {
        Grid backgroundGrid = new Grid(2, 2, false);
        backgroundGrid.set(0, 0, 0, 0, 0.0f, 0, 1.0f, null);
        backgroundGrid.set(1, 0, size.x, 0, 0.0f, 1, 1.0f, null);
        backgroundGrid.set(0, 1, 0, size.y, 0.0f, 0, 0.0f, null);
        backgroundGrid.set(1, 1, size.x, size.y, 0.0f, 1, 0.0f, null);
        return backgroundGrid;
    }

    public static ArrayList<Grid> LightningLineGrid(float Range) {
        ArrayList<Grid> g = new ArrayList<Grid>();
        Grid backgroundGrid = new Grid(2, 2, false);
        backgroundGrid.set(0, 0, 0, -30, 0.0f, 0.0f, 1.0f, null);
        backgroundGrid.set(1, 0, Range, -30, 0.0f, 1.0f, 1.0f, null);
        backgroundGrid.set(0, 1, 0, 30, 0.0f, 0.0f, 0.0f, null);
        backgroundGrid.set(1, 1, Range, 30, 0.0f, 1.0f, 0.0f, null);
        g.add(backgroundGrid);
        return g;
    }

    public static Grid LinkLineGrid(float Range, float offset, float radius) {

        Grid backgroundGrid = new Grid(2, 2, false);

        backgroundGrid.set(0, 0, 0, -radius / 2, 0.0f, 0.0f + (offset % 100) * 0.01f, 1.0f, null);
        backgroundGrid.set(1, 0, Range, -radius / 2, 0.0f, 0.5f + (offset % 100) * 0.01f, 1.0f, null);
        backgroundGrid.set(0, 1, 0, radius / 2, 0.0f, 0.0f + (offset % 100) * 0.01f, 0.0f, null);
        backgroundGrid.set(1, 1, Range, radius / 2, 0.0f, 0.5f + (offset % 100) * 0.01f, 0.0f, null);

        return backgroundGrid;
    }

    public void set(int i, int j, float x, float y, float z, float u, float v, float[] color) {
        if (i < 0 || i >= mW) {
            throw new IllegalArgumentException("i");
        }
        if (j < 0 || j >= mH) {
            throw new IllegalArgumentException("j");
        }

        final int index = mW * j + i;

        final int posIndex = index * 3;
        final int texIndex = index * 2;
        final int colorIndex = index * 4;

        if (mCoordinateType == GL10.GL_FLOAT) {
            mFloatVertexBuffer.put(posIndex, x);
            mFloatVertexBuffer.put(posIndex + 1, y);
            mFloatVertexBuffer.put(posIndex + 2, z);

            mFloatTexCoordBuffer.put(texIndex, u);
            mFloatTexCoordBuffer.put(texIndex + 1, v);

            if (color != null) {
                mFloatColorBuffer.put(colorIndex, color[0]);
                mFloatColorBuffer.put(colorIndex + 1, color[1]);
                mFloatColorBuffer.put(colorIndex + 2, color[2]);
                mFloatColorBuffer.put(colorIndex + 3, color[3]);
            }
        } else {
            mFixedVertexBuffer.put(posIndex, (int) (x * (1 << 16)));
            mFixedVertexBuffer.put(posIndex + 1, (int) (y * (1 << 16)));
            mFixedVertexBuffer.put(posIndex + 2, (int) (z * (1 << 16)));

            mFixedTexCoordBuffer.put(texIndex, (int) (u * (1 << 16)));
            mFixedTexCoordBuffer.put(texIndex + 1, (int) (v * (1 << 16)));

            if (color != null) {
                mFixedColorBuffer.put(colorIndex, (int) (color[0] * (1 << 16)));
                mFixedColorBuffer.put(colorIndex + 1, (int) (color[1] * (1 << 16)));
                mFixedColorBuffer.put(colorIndex + 2, (int) (color[2] * (1 << 16)));
                mFixedColorBuffer.put(colorIndex + 3, (int) (color[3] * (1 << 16)));
            }
        }
    }

    public void draw(GL10 gl, boolean useTexture, boolean useColor) {
        if (!mUseHardwareBuffers) {
            gl.glVertexPointer(3, mCoordinateType, 0, mVertexBuffer);


//            if(!first)
//            {
//            float texture1[] = {
//                    0.0f, 1.0f,0,
//                    0.0f, 0.0f,0,
//                    0.25f, 1.0f,0,
//                    0.25f, 0.0f,0
//            };
//
//            mFloatTexCoordBuffer.put(texture1);
//                first = true;
//            }
            if (useTexture) {
                gl.glTexCoordPointer(2, mCoordinateType, 0, mTexCoordBuffer);
            }

            if (useColor) {
                gl.glColorPointer(4, mCoordinateType, 0, mColorBuffer);
            }


            gl.glDrawElements(GL10.GL_TRIANGLES, mIndexCount,
                    GL10.GL_UNSIGNED_SHORT, mIndexBuffer);
        } else {
            GL11 gl11 = (GL11) gl;
            // draw using hardware buffers
            gl11.glBindBuffer(GL11.GL_ARRAY_BUFFER, mVertBufferIndex);
            gl11.glVertexPointer(3, mCoordinateType, 0, 0);

            if (useTexture) {
                gl11.glBindBuffer(GL11.GL_ARRAY_BUFFER, mTextureCoordBufferIndex);
                gl11.glTexCoordPointer(2, mCoordinateType, 0, 0);
            }

            if (useColor) {
                gl11.glBindBuffer(GL11.GL_ARRAY_BUFFER, mColorBufferIndex);
                gl11.glColorPointer(4, mCoordinateType, 0, 0);
            }
            gl11.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER, mIndexBufferIndex);

            gl11.glDrawElements(GL11.GL_TRIANGLES, mIndexCount,
                    GL11.GL_UNSIGNED_SHORT, 0);

            gl11.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);
            gl11.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER, 0);

        }
    }

    public boolean usingHardwareBuffers() {
        return mUseHardwareBuffers;
    }

    /**
     * When the OpenGL ES device is lost, GL handles become invalidated.
     * In that case, we just want to "forget" the old handles (without
     * explicitly deleting them) and make new ones.
     */
    public void invalidateHardwareBuffers() {
        mVertBufferIndex = 0;
        mIndexBufferIndex = 0;
        mTextureCoordBufferIndex = 0;
        mColorBufferIndex = 0;
        mUseHardwareBuffers = false;
    }

    /**
     * Deletes the hardware buffers allocated by this object (if any).
     */
    public void releaseHardwareBuffers(GL10 gl) {
        if (mUseHardwareBuffers) {
            if (gl instanceof GL11) {
                GL11 gl11 = (GL11) gl;
                int[] buffer = new int[1];
                buffer[0] = mVertBufferIndex;
                gl11.glDeleteBuffers(1, buffer, 0);

                buffer[0] = mTextureCoordBufferIndex;
                gl11.glDeleteBuffers(1, buffer, 0);

                buffer[0] = mColorBufferIndex;
                gl11.glDeleteBuffers(1, buffer, 0);

                buffer[0] = mIndexBufferIndex;
                gl11.glDeleteBuffers(1, buffer, 0);
            }

            invalidateHardwareBuffers();
        }
    }

    /**
     * Allocates hardware buffers on the graphics card and fills them with
     * data if a buffer has not already been previously allocated.  Note that
     * this function uses the GL_OES_vertex_buffer_object extension, which is
     * not guaranteed to be supported on every device.
     *
     * @param gl A pointer to the OpenGL ES context.
     */
    public void generateHardwareBuffers(GL10 gl) {
        if (!mUseHardwareBuffers) {
            if (gl instanceof GL11) {
                GL11 gl11 = (GL11) gl;
                int[] buffer = new int[1];

                // Allocate and fill the vertex buffer.
                gl11.glGenBuffers(1, buffer, 0);
                mVertBufferIndex = buffer[0];
                gl11.glBindBuffer(GL11.GL_ARRAY_BUFFER, mVertBufferIndex);
                final int vertexSize = mVertexBuffer.capacity() * mCoordinateSize;
                gl11.glBufferData(GL11.GL_ARRAY_BUFFER, vertexSize,
                        mVertexBuffer, GL11.GL_STATIC_DRAW);

                // Allocate and fill the texture coordinate buffer.
                gl11.glGenBuffers(1, buffer, 0);
                mTextureCoordBufferIndex = buffer[0];
                gl11.glBindBuffer(GL11.GL_ARRAY_BUFFER,
                        mTextureCoordBufferIndex);
                final int texCoordSize =
                        mTexCoordBuffer.capacity() * mCoordinateSize;
                gl11.glBufferData(GL11.GL_ARRAY_BUFFER, texCoordSize,
                        mTexCoordBuffer, GL11.GL_STATIC_DRAW);

                // Allocate and fill the color buffer.
                gl11.glGenBuffers(1, buffer, 0);
                mColorBufferIndex = buffer[0];
                gl11.glBindBuffer(GL11.GL_ARRAY_BUFFER,
                        mColorBufferIndex);
                final int colorSize =
                        mColorBuffer.capacity() * mCoordinateSize;
                gl11.glBufferData(GL11.GL_ARRAY_BUFFER, colorSize,
                        mColorBuffer, GL11.GL_STATIC_DRAW);

                // Unbind the array buffer.
                gl11.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);

                // Allocate and fill the index buffer.
                gl11.glGenBuffers(1, buffer, 0);
                mIndexBufferIndex = buffer[0];
                gl11.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER,
                        mIndexBufferIndex);
                // A char is 2 bytes.
                final int indexSize = mIndexBuffer.capacity() * 2;
                gl11.glBufferData(GL11.GL_ELEMENT_ARRAY_BUFFER, indexSize, mIndexBuffer,
                        GL11.GL_STATIC_DRAW);

                // Unbind the element array buffer.
                gl11.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER, 0);

                mUseHardwareBuffers = true;
                assert mVertBufferIndex != 0;
                assert mTextureCoordBufferIndex != 0;
                assert mIndexBufferIndex != 0;
                assert gl11.glGetError() == 0;

            }
        }
    }

    // These functions exposed to patch Grid info into native code.
    public final int getVertexBuffer() {
        return mVertBufferIndex;
    }

    public final int getTextureBuffer() {
        return mTextureCoordBufferIndex;
    }

    public final int getIndexBuffer() {
        return mIndexBufferIndex;
    }

    public final int getColorBuffer() {
        return mColorBufferIndex;
    }

    public final int getIndexCount() {
        return mIndexCount;
    }

    public boolean getFixedPoint() {
        return (mCoordinateType == GL10.GL_FIXED);
    }
}