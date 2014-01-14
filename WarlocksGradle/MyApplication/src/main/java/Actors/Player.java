package Actors;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import Game.GameObject;
import Game.SpellEffect;
import Spells.Spell;
import Spells.SpellInfo;
import Spells.SpellType;
import Tools.Vector;
import Tools.iVector;

import com.developmental.myapplication.Global;

public class Player extends GameObject {
    int frame = 0;
    List<Bitmap> left, right, up, down, downleft, downright, upright, upleft;
    int timer = 0;
    double angleInDegrees = 0;
    public GameObject Target = null;
    private final int FramesShown = 1;

    public Player(ArrayList<Bitmap> _spriteSheet, Vector _pos, SpellInfo s[]) {
        super(1);

//this.objectObjectType = Game.ObjectType.Player;
        super.owner = this;
        this.position = _pos;
        this.size = new Vector(100, 100);
        //this.spriteSheet = _spriteSheet;
        this.feet = new Vector(this.position.x + this.size.x / 2,
                this.position.y + this.size.y );
        GetSprites(_spriteSheet);
        this.rect = new RectF(0, 0, 100, 100);

        this.paint.setTextSize(30);
        this.paint.setColor(Color.BLACK);

        this.shadowPaint = new Paint();

        this.shadowPaint.setMaskFilter(new BlurMaskFilter(30,
                BlurMaskFilter.Blur.INNER));
        this.paint.setAntiAlias(true);
        this.maxVelocity = 30;
        this.Spells = new Spell[10];


        for (int x = 0; x < 10; x++) {


            this.Spells[x] = new Spell(this,s[x]);
        }
//        if(false)
//        try {
//            Class c = LightningSpell.class;
//            Spells[0]=(Spell)c.getDeclaredConstructor(GameObject.class).newInstance(this);
//
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void GetSprites(ArrayList<Bitmap> spriteSheet) {

        this.right = new ArrayList<Bitmap>();
        this.left = new ArrayList<Bitmap>();
        this.down = new ArrayList<Bitmap>();
        this.up = new ArrayList<Bitmap>();
        this.downleft = new ArrayList<Bitmap>();
        this.downright = new ArrayList<Bitmap>();
        this.upright = new ArrayList<Bitmap>();
        this.upleft = new ArrayList<Bitmap>();
        for (int x = 0; x < 16; x++)
            this.left.add(spriteSheet.get(x));
        for (int x = 16; x < 32; x++)
            this.upleft.add(spriteSheet.get(x));
        for (int x = 32; x < 48; x++)
            this.up.add(spriteSheet.get(x));
        for (int x = 48; x < 64; x++)
            this.upright.add(spriteSheet.get(x));
        for (int x = 64; x < 80; x++)
            this.right.add(spriteSheet.get(x));
        for (int x = 80; x < 96; x++)
            this.downright.add(spriteSheet.get(x));
        for (int x = 96; x < 112; x++)
            this.down.add(spriteSheet.get(x));
        for (int x = 112; x < 128; x++)
            this.downleft.add(spriteSheet.get(x));
        this.curr = spriteSheet.get(0);
    }
    public boolean Shielded = false;
    @Override
    public void Draw(Canvas canvas, float playerx, float playery) {
        Shielded= false;
        super.Draw(canvas, playerx, playery);
        if (this.curr != null)
            canvas.drawBitmap(this.curr, this.position.x - playerx, this.position.y - playery,
                    this.paint);

        if (destination != null) {
            if (Marker != null)
                Marker.Draw(canvas, playerx, playery);
        }

        for (int i = 0; i < Debuffs.size(); i++) {

            SpellEffect e = Debuffs.get(i);

            if (e.Duration > 0) {
                if (e.effectType == SpellEffect.EffectType.Reflect)
                    Shielded = true;
                e.Draw(canvas, new Vector(this.position.x - playerx, this.position.y - playery));
            } else {
                Debuffs.remove(i);
            }
        }
        if (Global.DEBUG_MODE) {
            this.paint.setColor(Color.WHITE);
            canvas.drawText("" + (int) feet.x + "," + (int) feet.y, dRect.left, dRect.top, paint);
            DrawHealthBar(canvas, 0, 0);
        }

        if(this.displayhealth>0)
        {
            this.DrawHealthBar(canvas,0,0);
            this.DrawManaBar(canvas,new Vector(position.x-playerx,position.y+11-playery),new iVector((int)size.x,12));
        }
        int counter = 0;
        for(SpellEffect s : Debuffs)
        {
            if(s.effectType== SpellEffect.EffectType.Burn)
                counter++;
        }
        canvas.drawText(counter+"",this.bounds.Center.x-playerx,this.bounds.Center.y-playery,paint);
    }

    @Override
    public void Update() {
        super.Update();
        this.rect = new RectF(this.position.x, this.position.y, this.position.x
                + this.size.x, this.position.y + this.size.y);
        bounds.Center = feet;
        if (!this.casting)

            Animate(this.destination);
    }

    // based on angle to the destination point the players frame is chosen. it
    // then cycles through until the angle changes
    public void Animate(Vector dest) {
        if (dest != null) {
            float deltaY = Math.abs(dest.y) - Math.abs(this.feet.y);
            float deltaX = Math.abs(dest.x) - Math.abs(this.feet.x);
            this.angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI
                    + 180;

            if (this.timer < FramesShown) {
                if (this.angleInDegrees >= 157.5 && this.angleInDegrees < 202.5) {
                    if (this.frame < this.right.size())
                        this.curr = this.right.get(this.frame);
                    else if (this.right.size() > 0) {
                        this.curr = this.right.get(0);
                        this.frame = 0;// reset to 0
                    }
                } else if (this.angleInDegrees >= 112.5
                        && this.angleInDegrees < 157.5) {
                    if (this.frame < this.upright.size())
                        this.curr = this.upright.get(this.frame);

                    else if (this.upright.size() > 0) {
                        this.curr = this.upright.get(0);
                        this.frame = 0;// reset to 0
                    }
                } else if (this.angleInDegrees >= 202.5
                        && this.angleInDegrees < 247.5) {
                    if (this.frame < this.downright.size())
                        this.curr = this.downright.get(this.frame);

                    else if (this.downright.size() > 0) {
                        this.curr = this.downright.get(0);
                        this.frame = 0;// reset to 0
                    }
                } else if (this.angleInDegrees >= 247.5
                        && this.angleInDegrees < 292.5) {
                    if (this.frame < this.down.size())
                        this.curr = this.down.get(this.frame);

                    else if (this.down.size() > 0) {
                        this.curr = this.down.get(0);
                        this.frame = 0;// reset to 0
                    }
                } else if (this.angleInDegrees >= 292.5
                        && this.angleInDegrees < 337.5) {
                    if (this.frame < this.downleft.size())
                        this.curr = this.downleft.get(this.frame);

                    else if (this.downleft.size() > 0) {
                        this.curr = this.downleft.get(0);
                        this.frame = 0;// reset to 0
                    }
                } else if (this.angleInDegrees < 22.5
                        || this.angleInDegrees >= 337.5) {
                    if (this.frame < this.left.size())
                        this.curr = this.left.get(this.frame);

                    else if (this.left.size() > 0) {
                        this.curr = this.left.get(0);
                        this.frame = 0;// reset to 0
                    }
                } else if (this.angleInDegrees >= 22.5
                        && this.angleInDegrees < 67.5) {
                    if (this.frame < this.upleft.size())
                        this.curr = this.upleft.get(this.frame);

                    else if (this.upleft.size() > 0) {
                        this.curr = this.upleft.get(0);
                        this.frame = 0;// reset to 0
                    }
                } else if (this.angleInDegrees >= 67.5
                        && this.angleInDegrees < 112.5)
                    if (this.frame < this.up.size())
                        this.curr = this.up.get(this.frame);

                    else if (this.up.size() > 0) {
                        this.curr = this.up.get(0);
                        this.frame = 0;// reset to 0
                    }
                this.timer++;
            } else {
                this.timer = 0;
                this.frame++;// next frame
            }
        }
    }
}
