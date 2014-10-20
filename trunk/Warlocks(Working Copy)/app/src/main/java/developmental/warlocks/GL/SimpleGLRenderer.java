package developmental.warlocks.GL;
/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import android.view.MotionEvent;

import com.developmental.warlocks.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;

import Actors.BlockEnemy;
import Actors.Player;
import HUD.PopupText;
import Input.Finger;
import Platform.EllipticalPlatform;
import Tools.Vector;
import World.Level;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.NewHeirarchy.Renderable;
import developmental.warlocks.GL.NewHeirarchy.glButton;
import developmental.warlocks.GL.NewHeirarchy.glHealthBar;
import developmental.warlocks.GL.NewHeirarchy.glParticle;
import developmental.warlocks.GL.NewHeirarchy.glText;
import developmental.warlocks.Global;

/**
 * An OpenGL ES renderer based on the GLSurfaceView rendering framework.  This
 * class is responsible for drawing a list of renderables to the screen every
 * frame.  It also manages loading of textures and (when VBOs are used) the
 * allocation of vertex buffer objects.
 */
public class SimpleGLRenderer implements mGLSurfaceView.Renderer {
    private static final String TAG = SimpleGLRenderer.class.getSimpleName();
    public static List<GameObject> gameObjects = new ArrayList<GameObject>();


    public enum Screen{Shop,Game}

    public static Screen screen = Screen.Game;
    public static List<glParticle> Particles = new ArrayList<glParticle>();
    public static GameObject archie;
    public static List<GameObject> players = new ArrayList<GameObject>();
    public static Level l;
    public static int objects = 0;
    public static int particles = 0;
    public static Point size, trueSize;
    public static Finger finger = new Finger();
    public static List<PopupText> popupTexts = new ArrayList<PopupText>();

    public static void addObject(GameObject obj) {
        gameObjects.add(obj);
        gameObjects.get(gameObjects.size() - 1).id = objects++;
    }

    public static void addParticle(glParticle obj) {
        Particles.add(obj);
        Particles.get(Particles.size() - 1).id = particles++;
    }

    public static void delObject(int id) {
        for (int x = 0; x < gameObjects.size(); x++)
            if (gameObjects.get(x).id == id) {

                gameObjects.remove(x);


                return;
            }


    }

    public static void delParticle(int id) {
        for (int x = 0; x < Particles.size(); x++)
            if (Particles.get(x).id == id) {

                Particles.remove(x);
                return;
            }


    }
    public boolean onTouchEvent(MotionEvent event) {

        finger.Update(event);
        return true;
    }





//
//    public SimpleGLRenderer(Context context, Point _size) {
//        super(context);
//
//        this.Load();
//        //gameThread.startShop();
//    }
    public static void SetLevelShape(Level.LevelShape _l)
    {
        lShape = _l;
        l= new Level(lShape);
    }
    public static Level.LevelShape lShape;

    public void Load() {




        l = new Level(Level.LevelShape.Ellipse);
        l.iceplatform = new EllipticalPlatform(GameObject.PositiononEllipse(30).add(new Vector(Global.WORLD_BOUND_SIZE.x/2,Global.WORLD_BOUND_SIZE.y/2)),
                new Vector(900,450), R.drawable.platform_ice);

        gameObjects = new ArrayList<GameObject>();
        Log.d("INET", "PLAYER NO." + Global.playerno);



    }

    public static void MakePlayers()
    {
        if (gameObjects.size() == 0) {
            // load sprite sheet
            if (Global.Multiplayer) {

                int a = 360 / Global.Players;
                players = new ArrayList<GameObject>();

                for (int x = 0; x < Global.Players; x++) {
                    Player p = new Player( GameObject.PositiononEllipse(a * x + 45),Global.spellList);
                    players.add(p);
                    addObject(p);
                    Log.d("INET", "PLAYER CREATED " + Global.playerno + " " + Global.Players);

                }

                archie = players.get(Global.playerno);

            } else {
                // playerno=0;
                players = new ArrayList<GameObject>();
                Player p = new Player(GameObject.PositiononEllipse(45),Global.spellList);
                players.add(p);
                addObject(p);
                p = new BlockEnemy( GameObject.PositiononEllipse(100),Global.spellList);
                players.add(p);
                addObject(p);
                p = new BlockEnemy( GameObject.PositiononEllipse(200),Global.spellList);
                players.add(p);
                addObject(p);
                p = new BlockEnemy( GameObject.PositiononEllipse(70),Global.spellList);
                players.add(p);
                addObject(p);
                p = new BlockEnemy( GameObject.PositiononEllipse(300),Global.spellList);
                players.add(p);
                addObject(p);
                archie = players.get(0);
//                addObject(new Block(3399, 750));
//                addObject(new Block(3300, 950));
//                addObject(new Block(3300, 1150));
//                addObject(new Block(3300, 1450));
//                addObject(new Block(3300, 1650));
            }

        }

    }
    private glText textRenderer;
    // Specifies the format our textures should be converted to upon load.
    private static BitmapFactory.Options sBitmapOptions
            = new BitmapFactory.Options();
    public static glHealthBar archieManaBar;
    // An array of things to draw every frame.
    private Renderable[] mSprites;
    // Pre-allocated arrays to use at runtime so that allocation during the
    // test can be avoided.
    private int[] mTextureNameWorkspace;
    private int[] mCropWorkspace;
    // A reference to the application context.
    private Context mContext;
    // Determines the use of vertex arrays.
    private boolean mUseVerts;
    // Determines the use of vertex buffer objects.
    private boolean mUseHardwareBuffers;
    final String vertexShader =
            "uniform mat4 u_MVPMatrix;      \n"     // A constant representing the combined model/view/projection matrix.

                    + "attribute vec4 a_Position;     \n"     // Per-vertex position information we will pass in.
                    + "attribute vec4 a_Color;        \n"     // Per-vertex color information we will pass in.

                    + "varying vec4 v_Color;          \n"     // This will be passed into the fragment shader.

                    + "void main()                    \n"     // The entry point for our vertex shader.
                    + "{                              \n"
                    + "   v_Color = a_Color;          \n"     // Pass the color through to the fragment shader.
                    // It will be interpolated across the triangle.
                    + "   gl_Position = u_MVPMatrix   \n"     // gl_Position is a special variable used to store the final position.
                    + "               * a_Position;   \n"     // Multiply the vertex by the matrix to get the final point in
                    + "}                              \n";    // normalized screen coordinates.
    final String fragmentShader =
            "precision mediump float;       \n"     // Set the default precision to medium. We don't need as high of a
                    // precision in the fragment shader.
                    + "varying vec4 v_Color;          \n"     // This is the color from the vertex shader interpolated across the
                    // triangle per fragment.
                    + "void main()                    \n"     // The entry point for our fragment shader.
                    + "{                              \n"
                    + "   gl_FragColor = vec4(0.5,0.2,0.5,0.0);    \n"     // Pass the color directly through the pipeline.
                    + "}                              \n";
    public int createProgram(String vertexSource, String fragmentSource) {
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
        int pixelShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);

        int program = GLES20.glCreateProgram();
        if (program != 0) {
            GLES20.glAttachShader(program, vertexShader);

            GLES20.glAttachShader(program, pixelShader);

            GLES20.glLinkProgram(program);
            int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
            if (linkStatus[0] != GLES20.GL_TRUE) {
                Log.e("SHADERS", "Could not link program: ");
                Log.e("SHADERS", GLES20.glGetProgramInfoLog(program));
                GLES20.glDeleteProgram(program);
                program = 0;
            }
            else
            {
                Log.e("SHADERS", "LINK SUCCESSFUL!");

            }
        }
        return program;
    }
    //strSource, shader source
//iType, type of shader we are trying to load,
//Vertex Shader or Fragment shader
    public static int LoadShader(String strSource, int iType)
    {
        int[] compiled = new int[1];
        int iShader = GLES20.glCreateShader(iType);
        GLES20.glShaderSource(iShader, strSource);
        GLES20.glCompileShader(iShader);
        GLES20.glGetShaderiv(iShader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0)
        {
            Log.d("Load Shader Failed", "Compilation\n"+GLES20.glGetShaderInfoLog(iShader));
            return 0;
        }
        return iShader;
    }
    public static int LoadProgram(String strVSource, String strFSource)
    {
        int iVShader;
        int iFShader;
        int iProgId;
        int[] link = new int[1];

        iVShader = LoadShader(strVSource, GLES20.GL_VERTEX_SHADER);
        if (iVShader == 0)
        {
            Log.d("Load Program", "Vertex Shader Failed");
            return 0;
        }
        iFShader = LoadShader(strFSource, GLES20.GL_FRAGMENT_SHADER);
        if(iFShader == 0)
        {
            Log.d("Load Program", "Fragment Shader Failed");
            return 0;
        }

        iProgId = GLES20.glCreateProgram();
        //attache shaders to program
        GLES20.glAttachShader(iProgId, iVShader);
        GLES20.glAttachShader(iProgId, iFShader);
        //link program
        GLES20.glLinkProgram(iProgId);
        //get the link status
        GLES20.glGetProgramiv(iProgId, GLES20.GL_LINK_STATUS, link, 0);
        if (link[0] <= 0)
        {
            Log.d("Load Program", "Linking Failed");
            return 0;
        }
//delete the shaders, since we have already loaded
        GLES20.glDeleteShader(iVShader);
        GLES20.glDeleteShader(iFShader);
        return iProgId;
    }
    private int loadShader(int shaderType, String source) {
        int shader = GLES20.glCreateShader(shaderType);
        if (shader != 0) {
            GLES20.glShaderSource(shader, source);
            GLES20.glCompileShader(shader);
            int[] compiled = new int[1];
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
            if (compiled[0] == 0) {
                Log.e("SHADERS", "Could not compile shader "+(shaderType==GLES20.GL_VERTEX_SHADER?"Vertex":"Fragment" )+ shaderType + ":");
                Log.e("SHADERS", GLES20.glGetShaderInfoLog(shader));
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }
    public static SimpleGLRenderer simpleGLRenderer;
    public SimpleGLRenderer(Context context,Point size) {
      Load();
        // Pre-allocate and store these objects so we can use them at runtime
        // without allocating memory mid-frame.
        mTextureNameWorkspace = new int[1];
        mCropWorkspace = new int[4];
        this.size = size;
        // Set our bitmaps to 16-bit, 565 format.
        sBitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;

        mContext = context;
    }

    public int[] getConfigSpec() {
        // We don't need a depth buffer, and don't care about our
        // color depth.
        int[] configSpec = { EGL10.EGL_DEPTH_SIZE, 0, EGL10.EGL_NONE };
        return configSpec;
    }

    public void setSprites(Renderable[] sprites) {
        mSprites = sprites;
    }
    public static ArrayList<glButton> buttons = new ArrayList<glButton>();
    /**
     * Changes the vertex mode used for drawing.
     * @param useVerts  Specifies whether to use a vertex array.  If false, the
     *     DrawTexture extension is used.
     * @param useHardwareBuffers  Specifies whether to store vertex arrays in
     *     main memory or on the graphics card.  Ignored if useVerts is false.
     */
    public void setVertMode(boolean useVerts, boolean useHardwareBuffers) {
        mUseVerts = useVerts;
        mUseHardwareBuffers = useVerts ? useHardwareBuffers : false;
    }

    /** Draws the sprites. */
    public void drawFrame(GL10 gl) {
        if (mSprites != null) {
            gl.glClearColor(0,0,0,0.2f);
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            gl.glMatrixMode(GL10.GL_MODELVIEW);

            if (mUseVerts) {
                Grid.beginDrawing(gl, true, false);
            }

            float offsetX = (archie.bounds.Center.x - Global.size.x / 2-archie.size.x/2), offsetY = Global.WORLD_BOUND_SIZE.y - archie.bounds.Center.y - Global.size.y / 2-Global.healthBarHeight*2 - Global.ButtonSize;
            RectF bds = new RectF(archie.bounds.Center.x-Global.size.x/2,archie.bounds.Center.y-Global.size.y/2,archie.bounds.Center.x+Global.size.x/2,archie.bounds.Center.y+Global.size.y/2);
           mSprites[0].draw(gl, offsetX, offsetY, false);
           mSprites[1].draw(gl, offsetX,offsetY, false);
//
           mSprites[2].draw(gl, offsetX,offsetY-mSprites[2].position.y+mSprites[2].size.y/2, false);
            for(int y = 0; y<SimpleGLRenderer.Particles.size(); y++)
            {
                glParticle j =   SimpleGLRenderer.Particles.get(y);
                if(j.Within(bds))
                    j.draw(gl, offsetX,offsetY , false);
            }


            for (int x = 0; x < gameObjects.size(); x++) {
                GameObject j = gameObjects.get(x);
                if(j.Within(bds))
                j.draw(gl, offsetX,offsetY, false);

            }
            for(int y = 0; y<SimpleGLRenderer.popupTexts.size(); y++)
            {
                PopupText j =   SimpleGLRenderer.popupTexts.get(y);
             j.draw(textRenderer, gl, offsetX, offsetY, false);

            }
            for(int y = 0; y<players.size(); y++)
            {
                GameObject g = players.get(y);
             textRenderer.draw("PLAYER " + (y+1)+ ": "+ g.health+ "/" + g.maxhealth,gl,0,Global.size.y-(60*y));
            }
            for (int i = 0; i < buttons.size(); i++) {
                glButton s = buttons.get(i);
                archie.Spells[i].loadResouce();
                s.spellResource = archie.Spells[i].texture;
                s.draw(gl, 0, 0, true);

            }

            SimpleGLRenderer.archieHealthBar.draw(gl,0,0,true);
            if(!l.iceplatform.Within(archie.bounds.Center))
            SimpleGLRenderer.archieManaBar.draw(gl,0,0,true);
            if (mUseVerts) {
                Grid.endDrawing(gl);
            }


        }
    }
    public static  glHealthBar archieHealthBar;
    /* Called when the size of the window changes. */
    public void sizeChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);

        /*
         * Set our projection matrix. This doesn't have to be done each time we
         * draw, but usually a new projection needs to be set when the viewport
         * is resized.
         */
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0.0f, width, 0.0f, height, 0.0f, 1.0f);

        gl.glShadeModel(GL10.GL_FLAT);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glColor4x(0x10000, 0x10000, 0x10000, 0x10000);
        gl.glEnable(GL10.GL_TEXTURE_2D);
    }

    /**
     * Called whenever the surface is created.  This happens at startup, and
     * may be called again at runtime if the device context is lost (the screen
     * goes to sleep, etc).  This function must fill the contents of vram with
     * texture data and (when using VBOs) hardware vertex arrays.
     */
    public void surfaceCreated(GL10 gl) {
         /*
         * Some one-time OpenGL initialization can be made here probably based
         * on features of this particular context
         */
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

        gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        gl.glShadeModel(GL10.GL_FLAT);
        gl.glDisable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        /*
         * By default, OpenGL enables features that improve quality but reduce
         * performance. One might want to tweak that especially on software
         * renderer.
         */
        gl.glDisable(GL10.GL_DITHER);
        gl.glDisable(GL10.GL_LIGHTING);

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        if (mSprites != null) {

            // If we are using hardware buffers and the screen lost context
            // then the buffer indexes that we recorded previously are now
            // invalid.  Forget them here and recreate them below.
            if (mUseHardwareBuffers) {
                for (int x = 0; x < mSprites.length; x++) {
                    // Ditch old buffer indexes.
                    for( Grid g:mSprites[x].getGrid())
                    g.invalidateHardwareBuffers();
                }
            }
            Global.resources.put(R.drawable.charsheet,loadBitmap(mContext, gl, R.drawable.charsheet));
            Global.resources.put(R.drawable.charsheetedit,loadBitmap(mContext, gl, R.drawable.charsheetedit));
            Global.resources.put(R.drawable.charsheetedit2,loadBitmap(mContext, gl, R.drawable.charsheetedit2));
            Global.resources.put(R.drawable.charsheetedit4,loadBitmap(mContext, gl, R.drawable.charsheetedit4));
            Global.resources.put(R.drawable.button_meteor,loadBitmap(mContext, gl, R.drawable.button_meteor));
            Global.resources.put(R.drawable.spell_fireball,loadBitmap(mContext, gl, R.drawable.spell_fireball));
            Global.resources.put(R.drawable.button_fireball,loadBitmap(mContext, gl, R.drawable.button_fireball));
            Global.resources.put(R.drawable.effect_ice,loadBitmap(mContext, gl, R.drawable.effect_ice));
            Global.resources.put(R.drawable.button_ice,loadBitmap(mContext, gl, R.drawable.button_ice));
            Global.resources.put(R.drawable.backgroundlava,loadBitmap(mContext, gl, R.drawable.backgroundlava));
            Global.resources.put(R.drawable.backgroundlava2,loadBitmap(mContext, gl, R.drawable.backgroundlava2));
            Global.resources.put(R.drawable.platform_main,loadBitmap(mContext, gl, R.drawable.platform_main));
            Global.resources.put(R.drawable.spell_boundsircle,loadBitmap(mContext, gl, R.drawable.spell_boundsircle));
            Global.resources.put(R.drawable.spell_lightning,loadBitmap(mContext, gl, R.drawable.spell_lightning));
            Global.resources.put(R.drawable.spell_gravity,loadBitmap(mContext, gl, R.drawable.spell_gravity));
            Global.resources.put(R.drawable.spell_gravity2,loadBitmap(mContext, gl, R.drawable.spell_gravity2));
            Global.resources.put(R.drawable.button_gravity,loadBitmap(mContext, gl, R.drawable.button_gravity));
            Global.resources.put(R.drawable.button_lightning,loadBitmap(mContext, gl, R.drawable.button_lightning));
            Global.resources.put(R.drawable.button_firespray,loadBitmap(mContext, gl, R.drawable.button_firespray));
            Global.resources.put(R.drawable.spell_boomerang,loadBitmap(mContext, gl, R.drawable.spell_boomerang));
            Global.resources.put(R.drawable.spell_iceball,loadBitmap(mContext, gl, R.drawable.spell_iceball));
            Global.resources.put(R.drawable.spell_meteor,loadBitmap(mContext, gl, R.drawable.spell_meteor));
            Global.resources.put(R.drawable.healthbar,loadBitmap(mContext, gl, R.drawable.healthbar));
            Global.resources.put(R.drawable.font,loadBitmap(mContext, gl, R.drawable.font));
            Global.resources.put(R.drawable.button_boomerang,loadBitmap(mContext, gl, R.drawable.button_boomerang));
            Global.resources.put(R.drawable.effect_shield,loadBitmap(mContext, gl, R.drawable.effect_shield));
            Global.resources.put(R.drawable.button_shield,loadBitmap(mContext, gl, R.drawable.button_shield));
            Global.resources.put(R.drawable.button_explosion,loadBitmap(mContext, gl, R.drawable.button_explosion));
            Global.resources.put(R.drawable.effect_explode,loadBitmap(mContext, gl, R.drawable.effect_explode));
            Global.resources.put(R.drawable.effect_burn,loadBitmap(mContext, gl, R.drawable.effect_burn));


            // Load our texture and set its texture name on all sprites.

            // To keep this sample simple we will assume that sprites that share
            // the same texture are grouped together in our sprite list. A real
            // app would probably have another level of texture management,
            // like a texture hash.

            int lastLoadedResource = -1;
            int lastTextureId = -1;

            for (int x = 0; x < mSprites.length; x++) {
                int resource = mSprites[x].getResourceId();
                if (resource != lastLoadedResource) {
                    lastTextureId = loadBitmap(mContext, gl, resource);
                    lastLoadedResource = resource;
                }
                mSprites[x].setTextureName(lastTextureId);
                if (mUseHardwareBuffers) {
                    for(Grid currentGrid:mSprites[x].getGrid())
                    if (!currentGrid.usingHardwareBuffers()) {
                        currentGrid.generateHardwareBuffers(gl);
                    }

                }
            this.textRenderer = new glText(R.drawable.font,70,70);
//                GLES20.glUseProgram(this.createProgram(this.vertexShader, this.fragmentShader));
            }
        }
    }

    /**
     * Called when the rendering thread shuts down.  This is a good place to
     * release OpenGL ES resources.
     * @param gl
     */
    public void shutdown(GL10 gl) {
        if (mSprites != null) {

            int lastFreedResource = -1;
            int[] textureToDelete = new int[1];

            for (int x = 0; x < mSprites.length; x++) {
                int resource = mSprites[x].getResourceId();
                if (resource != lastFreedResource) {
                    textureToDelete[0] = mSprites[x].getTextureName();
                    gl.glDeleteTextures(1, textureToDelete, 0);
                    mSprites[x].setTextureName(0);
                }
                if (mUseHardwareBuffers) {
                    for(Grid g: mSprites[x].getGrid())
                   g.releaseHardwareBuffers(gl);
                }
            }
        }
    }

    /**
     * Loads a bitmap into OpenGL and sets up the common parameters for
     * 2D texture maps.
     */
    protected int loadBitmap(Context context, GL10 gl, int resourceId) {
        int textureName = -1;
        if (context != null && gl != null) {
            gl.glGenTextures(1, mTextureNameWorkspace, 0);

            textureName = mTextureNameWorkspace[0];
            gl.glBindTexture(GL10.GL_TEXTURE_2D, textureName);

            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

            gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_REPLACE);

            InputStream is = context.getResources().openRawResource(resourceId);
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(is, null, sBitmapOptions);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    // Ignore.
                }
            }

            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

            mCropWorkspace[0] = 0;
            mCropWorkspace[1] = bitmap.getHeight();
            mCropWorkspace[2] = bitmap.getWidth();
            mCropWorkspace[3] = -bitmap.getHeight();

            bitmap.recycle();

            ((GL11) gl).glTexParameteriv(GL10.GL_TEXTURE_2D,
                    GL11Ext.GL_TEXTURE_CROP_RECT_OES, mCropWorkspace, 0);


            int error = gl.glGetError();
            if (error != GL10.GL_NO_ERROR) {
                Log.e("SpriteMethodTest", "Texture Load GLError: " + error);
            }

        }

        return textureName;
    }
public static Renderable bounds;
}