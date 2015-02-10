package developmental.warlocks.GL;

/**
 * Created by Scott on 5/01/14.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;

import com.developmental.warlocks.R;

import java.util.ArrayList;

import Actors.EllipseMovingAI;
import Actors.Player;
import HUD.glButton;
import HUD.glHealthBar;
import Scene.Action;
import Scene.Mission;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.NewHeirarchy.Moveable;
import developmental.warlocks.GL.NewHeirarchy.Renderable;
import developmental.warlocks.Global;


public class OpenGLTestActivity extends Activity {
    private final static int SPRITE_WIDTH = 150;
    private final static int SPRITE_HEIGHT = 150;


    private mGLSurfaceView mGLSurfaceView;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        mGLSurfaceView = new mGLSurfaceView(this);
        SimpleGLRenderer spriteRenderer = new SimpleGLRenderer(this, Global.size);
        // Clear out any old profile results.
        ProfileRecorder.sSingleton.resetAll();

        final Intent callingIntent = getIntent();
        // Allocate our sprites and add them to an array.
        final int robotCount = callingIntent.getIntExtra("spriteCount", 10);
        final boolean animate = true;
        final boolean useVerts = true;
        final boolean useHardwareBuffers = true;

        // Allocate space for the robot sprites + one background sprite.
        Renderable[] spriteArray = new Renderable[robotCount + 13];

        // We need to know the width and height of the display pretty soon,
        // so grab the information now.
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Moveable background = new Moveable(R.drawable.level_lava);

        background.size = new Vector(Global.WORLD_BOUND_SIZE.x, Global.WORLD_BOUND_SIZE.y);
        Global.spellSpritesFire = Grid.createSingleLineGrid(new Vector(30, 30), 4);
        Global.spellSpritesMeteor = Grid.createSingleLineGrid(new Vector(140, 140), 4);
        Global.fireballSpellSprites = Grid.FramesTail(new Vector(50, 50));
        Global.EffectGrid = Grid.EffectGrid(new Vector(SPRITE_WIDTH, SPRITE_HEIGHT), 8);
        ArrayList<Grid> g = new ArrayList<Grid>();
        if (useVerts) {
            // Setup the background grid.  This is just a quad.
            Grid backgroundGrid = new Grid(2, 2, false);
            backgroundGrid.set(0, 0, 0.0f, -Global.WORLD_BOUND_SIZE.y, 0.0f, 0.0f, 1.0f, null);
            backgroundGrid.set(1, 0, Global.WORLD_BOUND_SIZE.x, -Global.WORLD_BOUND_SIZE.y, 0.0f, 1.0f, 1.0f, null);
            backgroundGrid.set(0, 1, 0.0f, 0, 0.0f, 0.0f, 0.0f, null);
            backgroundGrid.set(1, 1, Global.WORLD_BOUND_SIZE.x, 0, 0.0f, 1.0f, 0.0f, null);
            g.add(backgroundGrid);
            background.setGrid(g);

        }

        spriteArray[1] = SimpleGLRenderer.l.platform;
        spriteArray[2] = SimpleGLRenderer.l.iceplatform;


        SimpleGLRenderer.gameObjects = new ArrayList<Collideable>();
        SimpleGLRenderer.players = new ArrayList<Player>();
        Grid spriteGrid = null;
        ArrayList<ArrayList<Grid>> d = new ArrayList<ArrayList<Grid>>();
        ArrayList<ArrayList<Grid>> c = new ArrayList<ArrayList<Grid>>();
        ArrayList<ArrayList<Grid>> c2 = new ArrayList<ArrayList<Grid>>();
        d.add(Global.SpritesLeft);
        d.add(Global.SpritesLeftUp);
        d.add(Global.SpritesUp);
        d.add(Global.SpritesRightUp);
        d.add(Global.SpritesRight);
        d.add(Global.SpritesRightDown);
        d.add(Global.SpritesDown);
        d.add(Global.SpritesLeftDown);
        c.add(Global.SpritesLeftCast1);
        c.add(Global.SpritesLeftUpCast1);
        c.add(Global.SpritesUpCast1);
        c.add(Global.SpritesRightUpCast1);
        c.add(Global.SpritesRightCast1);
        c.add(Global.SpritesRightDownCast1);
        c.add(Global.SpritesDownCast1);
        c.add(Global.SpritesLeftDownCast1);
        c2.add(Global.SpritesLeftCast2);
        c2.add(Global.SpritesLeftUpCast2);
        c2.add(Global.SpritesUpCast2);
        c2.add(Global.SpritesRightUpCast2);
        c2.add(Global.SpritesRightCast2);
        c2.add(Global.SpritesRightDownCast2);
        c2.add(Global.SpritesDownCast2);
        c2.add(Global.SpritesLeftDownCast2);

        if (useVerts) {
            for (int j = 0; j < d.size(); j++) {

                for (int i = 0; i < 8; i++) {

                    spriteGrid = new Grid(2, 2, false);
                    spriteGrid.set(0, 0, 0.0f, 0.0f, 0.0f, 0.0625f * i, 0.125f + 0.125f * j, null);
                    spriteGrid.set(1, 0, SPRITE_WIDTH, 0.0f, 0.0f, 0.0625f + 0.0625f * i, 0.125f + 0.125f * j, null);
                    spriteGrid.set(0, 1, 0.0f, SPRITE_HEIGHT, 0.0f, 0.0625f * i, 0.125f * j, null);
                    spriteGrid.set(1, 1, SPRITE_WIDTH, SPRITE_HEIGHT, 0.0f, 0.0625f + 0.0625f * i, 0.125f * j, null);
                    d.get(j).add(spriteGrid);
                }
                for (int i = 8; i < 12; i++) {

                    spriteGrid = new Grid(2, 2, false);
                    spriteGrid.set(0, 0, 0.0f, 0.0f, 0.0f, 0.0625f * i, 0.125f + 0.125f * j, null);
                    spriteGrid.set(1, 0, SPRITE_WIDTH, 0.0f, 0.0f, 0.0625f + 0.0625f * i, 0.125f + 0.125f * j, null);
                    spriteGrid.set(0, 1, 0.0f, SPRITE_HEIGHT, 0.0f, 0.0625f * i, 0.125f * j, null);
                    spriteGrid.set(1, 1, SPRITE_WIDTH, SPRITE_HEIGHT, 0.0f, 0.0625f + 0.0625f * i, 0.125f * j, null);
                    c.get(j).add(spriteGrid);
                }
                for (int i = 12; i < 16; i++) {

                    spriteGrid = new Grid(2, 2, false);
                    spriteGrid.set(0, 0, 0.0f, 0.0f, 0.0f, 0.0625f * i, 0.125f + 0.125f * j, null);
                    spriteGrid.set(1, 0, SPRITE_WIDTH, 0.0f, 0.0f, 0.0625f + 0.0625f * i, 0.125f + 0.125f * j, null);
                    spriteGrid.set(0, 1, 0.0f, SPRITE_HEIGHT, 0.0f, 0.0625f * i, 0.125f * j, null);
                    spriteGrid.set(1, 1, SPRITE_WIDTH, SPRITE_HEIGHT, 0.0f, 0.0625f + 0.0625f * i, 0.125f * j, null);
                    c2.get(j).add(spriteGrid);
                }
            }


            // Setup a quad for the sprites to use.  All sprites will use the
            // same sprite grid intance.

        }

        // This list of things to move. It points to the same content as the
        // sprite list except for the background.
        Renderable[] renderableArray = new Renderable[robotCount];
        final int robotBucketSize = robotCount / 4;
        for (int x = 0; x < robotCount; x++) {
            Player robot;
            Vector v = GameObject.PositiononEllipse((float) (Math.random() * 360));
            // Our robots come in three flavors.  Split them up accordingly.
            if (x == 0) {
                robot = new Player(R.drawable.charsheet, Global.spellList, v);
            } else if (x < robotBucketSize) {
                robot = new EllipseMovingAI(R.drawable.charsheet, Global.spellList, v);
            } else if (x < robotBucketSize * 2) {
                robot = new EllipseMovingAI(R.drawable.charsheetedit, Global.spellList, v);
            } else if (x < robotBucketSize * 3) {
                robot = new EllipseMovingAI(R.drawable.charsheetedit4, Global.spellList, v);
            } else {
                robot = new EllipseMovingAI(R.drawable.charsheetedit2, Global.spellList, v);
            }

            robot.size = new Vector(SPRITE_WIDTH, SPRITE_HEIGHT);
            // Pick a random location for this sprite.


            // All sprites can reuse the same grid.  If we're running the
            // DrawTexture extension test, this is null.
            robot.setGrid(Global.SpritesLeft);

            // Add this robot to the spriteArray so it gets drawn and to the
            // renderableArray so that it gets moved.
            SimpleGLRenderer.addObject(robot);
            SimpleGLRenderer.players.add(robot);
            spriteArray[x + 13] = robot;
            renderableArray[x] = robot;
        }

        Global.ButtonSize = ((float) Global.size.x) / 10f;//*3/4;
        ArrayList<Grid> w = Grid.EffectGrid(new Vector(Global.ButtonSize * 8f, Global.ButtonSize * 2.0f), 1);

        Moveable bbar = new Moveable(R.drawable.hud_backbar);

        //TODO: STOP USING A FIXED POSITION HERE. Decide how to display the backbar/how much to display
        bbar.position.x += Global.ButtonSize * 1f / 3f;
        bbar.setGrid(w);
        spriteArray[0] = background;
        spriteArray[3] = bbar;

        ArrayList<Grid> buttonGrid = Grid.EffectGrid(new Vector(Global.ButtonSize,Global.ButtonSize),3);// new ArrayList<Grid>();
        ArrayList<Grid> equipGrid = Grid.EffectGrid(new Vector(Global.ButtonSize * 2f / 3f, Global.ButtonSize * 2f / 3f), 3);// new ArrayList<Grid>();
       Grid buttonIconGrid= Grid.EffectGrid(new Vector(Global.ButtonSize,Global.ButtonSize),1).get(0);
        Grid equipIcon = Grid.EffectGrid(new Vector(Global.ButtonSize * 2f / 3f, Global.ButtonSize * 2f / 3f), 1).get(0);
        SimpleGLRenderer.buttons.clear();

        Global.playerno = 0;
        // Now's a good time to run the GC.  Since we won't do any explicit
        // allocation during the test, the GC should stay dormant and not
        // influence our results.
        Runtime r = Runtime.getRuntime();

        r.gc();
        SimpleGLRenderer.archie = (Player) SimpleGLRenderer.gameObjects.get(0);

        ArrayList<Action> actions = new ArrayList<Action>();
        for (Player p : SimpleGLRenderer.players) {
            if (p.id != SimpleGLRenderer.archie.id)
                actions.add(new Action(Action.Type.Kill, p));

        }

        Action a = new Action(Action.Type.Kill, SimpleGLRenderer.archie);
        ArrayList<Action> actions2 = new ArrayList<Action>();
        actions2.add(a);
        SimpleGLRenderer.l.mission = new Mission(actions, actions2);
        SimpleGLRenderer.archieHealthBar = new glHealthBar(R.drawable.hud_healthbar_large, new Vector(Global.size.x - 4f * Global.ButtonSize, Global.healthBarHeight), new Vector(2.0f * Global.ButtonSize, Global.ButtonSize + Global.healthBarHeight), SimpleGLRenderer.archie, glHealthBar.type.Health);
        SimpleGLRenderer.archieManaBar = new glHealthBar(R.drawable.hud_healthbar_large, new Vector(Global.size.x - 4f * Global.ButtonSize, Global.healthBarHeight), new Vector(2.0f * Global.ButtonSize, Global.ButtonSize), SimpleGLRenderer.archie, glHealthBar.type.Mana);
        for (int i = 0; i < 6; i++) {
            glButton qe = new glButton(R.drawable.hud_buttons, SimpleGLRenderer.archie.Spells[i].texture, 2.0f * Global.ButtonSize + (i * Global.ButtonSize), Global.ButtonSize, Global.ButtonSize, Global.ButtonSize, buttonIconGrid);
            qe.setGrid(buttonGrid);

            SimpleGLRenderer.buttons.add(qe);
            spriteArray[4 + i] = qe;

        }
        SimpleGLRenderer.Equips = new ArrayList<glButton>();
        for (int i = 0; i < 3; i++) {
            glButton qe = new glButton(R.drawable.hud_buttons, SimpleGLRenderer.archie.Spells[i].texture, 8.0f * Global.ButtonSize, i * Global.ButtonSize * 2 / 3 + Global.ButtonSize * 2 / 3, Global.ButtonSize * 2 / 3, Global.ButtonSize * 2 / 3, equipIcon);
            qe.setGrid(equipGrid);
            SimpleGLRenderer.Equips.add(qe);
            spriteArray[10 + i] = qe;
        }

        spriteRenderer.setSprites(spriteArray);
        spriteRenderer.setVertMode(useVerts, useHardwareBuffers);

        mGLSurfaceView.setRenderer(spriteRenderer);

        if (animate) {
            Mover simulationRuntime = new Mover(this);
            simulationRuntime.setRenderables(renderableArray);

            simulationRuntime.setViewSize(dm.widthPixels, dm.heightPixels);
            mGLSurfaceView.setEvent(simulationRuntime);
        }

        setContentView(mGLSurfaceView);

    }

}
