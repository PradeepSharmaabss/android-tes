package Actors;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

import com.developmental.myapplication.GL.NewHeirachy.GameObject;

import Game.ObjectType;
import Game.SpellEffect;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;

import com.developmental.myapplication.Global;

public class Player extends GameObject {
    int frame = 0;
    int timer = 0;
    double angleInDegrees = 0;
    public GameObject Target = null;
    private final int FramesShown = 1;
    public Player(int _charsheet, SpellInfo[] _spellList, Vector _position)
    {
        super(_charsheet,_spellList);
        this.position = _position;
        this.objectObjectType = ObjectType.Player;
    }

    public Player( Vector _pos, SpellInfo s[]) {
        super(1);
//this.objectObjectType = Game.ObjectType.Player;
        super.owner = this;
        this.position = _pos;
        this.size = new Vector(100, 100);
        //this.spriteSheet = _spriteSheet;
        this.feet = new Vector(this.position.x + this.size.x / 2,
                this.position.y -bounds.Radius);
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
        super.Animate(dest);

    }
}
