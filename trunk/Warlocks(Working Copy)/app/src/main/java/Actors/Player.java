package Actors;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import Game.ObjectType;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;


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
