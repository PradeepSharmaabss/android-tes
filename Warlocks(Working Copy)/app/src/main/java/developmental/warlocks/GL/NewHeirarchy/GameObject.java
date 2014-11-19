package developmental.warlocks.GL.NewHeirarchy;

import com.developmental.warlocks.R;

import javax.microedition.khronos.opengles.GL10;

import Actors.Player;
import Game.DamageType;
import Game.Destination;
import HUD.PopupText;
import HUD.glHealthBar;
import Spells.Archetype.ArchetypeManager;
import Spells.Archetype.ArchetypePower;
import Spells.Spell;
import Spells.SpellEffect;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

public class GameObject extends Collideable {



   glHealthBar healthbar;

    public Spell[] Spells;
    public boolean dead = false;
    private boolean rooted = false;


    public GameObject(int resourceId,Vector _pos, Vector _feet, Vector _size,SpellInfo[] spellList) {
        this(resourceId,_pos,_size);
        this.Spells = new Spell[7];
        shadowed = true;
        healthbar = new glHealthBar(R.drawable.hud_healthbar_small,new Vector(100,20), new Vector(0,-120),this, glHealthBar.type.Health);
        this.Spells = Spell.GenerateSpellList((Player)this,spellList);
    }

@Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {
    if (!casting)
       super.draw(gl,offsetX,offsetY, dontDrawInRelationToWorld);
        for(SpellEffect e : Debuffs) {
          e.draw(gl,offsetX-position.x,offsetY+position.y,false);
        }
        if(this.Marker!=null)
        {
            this.Marker.draw(gl,offsetX,offsetY,dontDrawInRelationToWorld);
        }
        if(displayhealth>0)
            this.healthbar.draw(gl,offsetX-position.x,offsetY+position.y,dontDrawInRelationToWorld);
    }
    public void Animate(Vector dest) {
        if (dest != null) {
            float deltaY = Math.abs(dest.y) - Math.abs(this.feet.y);
            float deltaX = Math.abs(dest.x) - Math.abs(this.feet.x);
            float angleInDegrees =(float) (Math.atan2(deltaY, deltaX) * 180 / Math.PI
                    + 180);



            if (angleInDegrees >= 157.5 && angleInDegrees < 202.5) {
                mGrid= Global.SpritesRight;
            } else if (angleInDegrees >= 112.5
                    && angleInDegrees < 157.5) {
                mGrid=Global.SpritesRightUp;
            } else if (angleInDegrees >= 202.5
                    && angleInDegrees < 247.5) {
                mGrid= Global.SpritesRightDown;
            } else if (angleInDegrees >= 247.5
                    && angleInDegrees < 292.5) {
                mGrid=Global.SpritesDown;
            } else if (angleInDegrees >= 292.5
                    && angleInDegrees < 337.5) {
                mGrid=Global.SpritesLeftDown;
            } else if (angleInDegrees < 22.5
                    || angleInDegrees >= 337.5) {
                mGrid=Global.SpritesLeft;
            } else if (angleInDegrees >= 22.5
                    && angleInDegrees < 67.5) {
                mGrid=Global.SpritesLeftUp;
            } else if (angleInDegrees >= 67.5
                    && angleInDegrees < 112.5)
                mGrid=Global.SpritesUp;

            if(lifePhase%this.frameRate==1)
            frame++;
            if(frame>=mGrid.size())
            {
                frame = 0;
            }
        }

    }

    public GameObject(int resourceId,Vector _pos, Vector _size) {
        super(resourceId,_pos, _size,500,0);

        this.objectObjectType = ObjectType.GameObject;


        this.velocity = new Vector(0, 0);
        //this.Spells = new Spell[10];

        this.shadowed=true;
    this.shadowGrid=Grid.shadowGridGenerateObject(new Vector(100, 100));

    }


@Override
    protected void Heal(float HealAmount)
    {
        if (HealAmount+health > this.maxhealth) {

            HealAmount=maxhealth-health;
            this.health = maxhealth;
        } else {
            this.health += HealAmount;
        }
        if(HealAmount>0)
                    SimpleGLRenderer.popupTexts.add(new PopupText(PopupText.TextType.Poison,HealAmount+"",this.bounds.Center.get(),12));



    }
    @Override
    public void Damage(float dmgDealt, DamageType d) {
        if (dmgDealt > this.health) {
            this.health = 0;
            dmgDealt=0;
            if (!Global.DEBUG_MODE) {
                this.dead = true;
                SimpleGLRenderer.delObject(this.id);
            }
        } else {
          this.health -= dmgDealt;
        }
        this.mana += dmgDealt;
        this.displayhealth = 20;
        if(dmgDealt>0)
        switch (d) {
            case Spell:

                SimpleGLRenderer.popupTexts.add(new PopupText(PopupText.TextType.Spell,dmgDealt+"",this.bounds.Center.get(),12));
                break;
            case Lava:

                SimpleGLRenderer.popupTexts.add(new PopupText(PopupText.TextType.Lava,dmgDealt+"",this.bounds.Center.get(),12));
               archetypeManager.AddStacks(new ArchetypePower(0,0,0,0,0,0,(int)dmgDealt),null);
                break;
        }
    }

    protected int displayhealth = 0;



public ArchetypeManager archetypeManager = new ArchetypeManager(this);

    public boolean casting = false, frozen = false, stunned = false;

    @Override
    protected void Movement() {
        if (!this.rooted)
            super.Movement();
        else
            velocity = new Vector(0, 0);
    }

    @Override
    public void Update() {
        invisible = false;
        casting = false;
        frozen = false;
        stunned = false;
        shielded=false;
        thrusting= false;
        this.rooted = false;
        int slowcounter = 0;
        for (int i = 0; i < Debuffs.size(); i++) {

            SpellEffect e = Debuffs.get(i);
            e.Update();
            if (e.Duration > 0) {


                if (e.effectType == SpellEffect.EffectType.Cast)
                    casting = true;

                if (e.effectType == SpellEffect.EffectType.Freeze)
                    frozen = true;
                if (e.effectType == SpellEffect.EffectType.Stun)
                    stunned = true;
                if (e.effectType == SpellEffect.EffectType.Slow)
                    slowcounter++;
                if(e.effectType== SpellEffect.EffectType.Reflect)
                    shielded = true;
                if(e.effectType== SpellEffect.EffectType.Invisible)
                    invisible=true;
                if(e.effectType==SpellEffect.EffectType.Thrust)
                    this.thrusting =true;
                if (e.effectType == SpellEffect.EffectType.Root)
                    this.rooted = true;
            } else {

                e.FinalUpdate();
                Debuffs.remove(i);
            }


        }
        super.Update();
        if (Marker != null)
            Marker.Update();
        if (lifePhase % 150 == 149)
            Heal(this.HealthRegenPer150Updates);
        if (displayhealth > 0)
            this.displayhealth -= 1;

        switch (objectObjectType) {
            case GameObject:
            case Player:
            case Enemy:
                if ((!SimpleGLRenderer.l.platform.Within(this.bounds.Center)) && !SimpleGLRenderer.l.iceplatform.Within(this.bounds.Center)) {
                    //    Log.e("LAVA","I AM ON ZEE LAVA!!!");
                    Damage(3, DamageType.Lava);
                } else {
//                            if(displayhealth==0)
//                            velocity = Vector.multiply(velocity, 0.99f);
                }
                break;

        }
        archetypeManager.Update();


        if (!casting && !frozen)
            if (this.destination != null)
                MoveTowards(this.destination, maxVelocity * (float) Math.pow(0.5, slowcounter), acceleration * (float) Math.pow(0.5, slowcounter)-(acceleration*0.8f*(SimpleGLRenderer.l.iceplatform.Within(this.bounds.Center)?1:0)));

        this.feet = new Vector(this.position.x + this.size.x / 2,
                this.position.y/*+size.y*7/10*/);
        bounds.Center = feet;


        CollideMap();

        if (Spells != null)
            for (int j = 0; j < Spells.length; j++) {

                Spells[j].Update();
            }
       switch (objectObjectType)
       {

           case GameObject:
           case Player:
           case Enemy:
               if (destination != null) {

                   Animate(destination);

               } else {
                   if (casting) {
                       AnimateCasting(destination);
                   }
               }
                   
               break;

       }

    }

    public void AnimateCasting(Vector dest) {
        if (dest != null) {
            float deltaY = Math.abs(dest.y) - Math.abs(this.feet.y);
            float deltaX = Math.abs(dest.x) - Math.abs(this.feet.x);
            float angleInDegrees = (float) (Math.atan2(deltaY, deltaX) * 180 / Math.PI
                    + 180);


            if (angleInDegrees >= 157.5 && angleInDegrees < 202.5) {
                mGrid = Global.SpritesRightCast1;
            } else if (angleInDegrees >= 112.5
                    && angleInDegrees < 157.5) {
                mGrid = Global.SpritesRightUpCast1;
            } else if (angleInDegrees >= 202.5
                    && angleInDegrees < 247.5) {
                mGrid = Global.SpritesRightDownCast1;
            } else if (angleInDegrees >= 247.5
                    && angleInDegrees < 292.5) {
                mGrid = Global.SpritesDownCast1;
            } else if (angleInDegrees >= 292.5
                    && angleInDegrees < 337.5) {
                mGrid = Global.SpritesLeftDownCast1;
            } else if (angleInDegrees < 22.5
                    || angleInDegrees >= 337.5) {
                mGrid = Global.SpritesLeftCast1;
            } else if (angleInDegrees >= 22.5
                    && angleInDegrees < 67.5) {
                mGrid = Global.SpritesLeftUpCast1;
            } else if (angleInDegrees >= 67.5
                    && angleInDegrees < 112.5)
                mGrid = Global.SpritesUpCast1;

            if (lifePhase % this.frameRate == 1)
                frame++;
            if (frame >= mGrid.size()) {
                frame = 0;
            }
        }

    }



    void AddtoBurnCounter(int burrns)
    {
    }

    public void FingerUpdate(iVector[] f, int SelectedSpell) {

        if (SelectedSpell == -1) {
            if (f.length > 0)
            {

                StartTo(new Vector(f[0].x, f[0].y));
            }
        } else {

            if (Spells[SelectedSpell].Current == 0)
            {

                if(Spells[SelectedSpell].Cast(f))
                    for(Spell spell:Spells)
                    {
                        if(spell.Current<Global.GlobalCooldown)
                            spell.Current=Global.GlobalCooldown;
                    }
            }
        }
    }



    public void StartTo(Vector Dest) {
        this.destination = new Vector(Dest.x, Dest.y);
        this.Marker = new Destination(destination);
    }

    public void CollideMap() {
        if (this.bounds.Center.x -bounds.Radius< 0)
            this.velocity.x = Math.abs(this.velocity.x);
        if (this.bounds.Center.x + bounds.Radius > Global.WORLD_BOUND_SIZE.x)
            this.velocity.x = -Math.abs(this.velocity.x);
        if (this.bounds.Center.y + bounds.Radius> Global.WORLD_BOUND_SIZE.y)
            this.velocity.y = -Math.abs(this.velocity.y);
        if (this.bounds.Center.y-bounds.Radius < 0)
            this.velocity.y = Math.abs(this.velocity.y);
    }





    public static Vector PositiononEllipse(float _angle) {
        float _x = (SimpleGLRenderer.l.platform.size.x / 2 - (SimpleGLRenderer.l.platform.size.x / 10))
                * (float) Math.cos(Math.toRadians(_angle))
                + SimpleGLRenderer.l.platform.position.x;
        float _y = (SimpleGLRenderer.l.platform.size.y / 2 - (SimpleGLRenderer.l.platform.size.y / 10))
                * (float) Math.sin(Math.toRadians(_angle))
                + SimpleGLRenderer.l.platform.position.y;
        return new Vector(_x, _y);
    }


    public void ProjectileHit(Vector v) {


        this.velocity = v;
        this.position.add(Vector.multiply(this.velocity, 2));

    }



}
