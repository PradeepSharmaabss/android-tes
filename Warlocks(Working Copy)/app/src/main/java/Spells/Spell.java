package Spells;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.developmental.warlocks.R;

import Game.ObjectType;
import Game.SpellEffect;
import HUD.PopupText;
import SpellProjectiles.AbsorptionProjectile;
import SpellProjectiles.BoomerangProjectile;
import SpellProjectiles.BounceProjectile;
import SpellProjectiles.DrainProjectile;
import SpellProjectiles.ExplosionProjectile;
import SpellProjectiles.FireballProjectile;
import SpellProjectiles.FiresprayProjectile;
import SpellProjectiles.GravityProjectile;
import SpellProjectiles.IceProjectile;
import SpellProjectiles.IcesplosionProjectile;
import SpellProjectiles.LightningProjectile;
import SpellProjectiles.LinkProjectile;
import SpellProjectiles.MeteorProjectile;
import SpellProjectiles.SplitterProjectile;
import SpellProjectiles.SwapProjectile;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;


public class Spell {
    public enum CastType{Projectile,Explosion,Passive,Spray,ActivateBuff}

    CastType castType;
    public int Cooldown = 50;
    int CastTime = 5;
    public int Current = 0;
    GameObject parent;
    Paint p;
    Bitmap curr;
    int castphase;
    boolean fired = false;
    int sz = 40;
   protected iVector targetLocation;
    SpellType spellType;
    public int Rank;
    float damage= 5;
    int radius = 15;
    int range;
    public int texture;
    void setValues(int casttime, int cooldown, float damage,int radius,int rng)
    {
        this.CastTime = casttime;
        this.Cooldown = cooldown;
        this.damage = damage;
        this.radius = radius;
        this.range = radius;
    }
    public void loadResouce()
    {
        this.texture = Global.resources.get(R.drawable.button_fireball);
        switch (spellType)
        {

            case Fireball:
                this.texture = Global.resources.get(R.drawable.button_fireball);
                break;
            case Lightning:
                this.texture = Global.resources.get(R.drawable.button_lightning);
                break;
            case Homing:

                this.texture = Global.resources.get(R.drawable.button_fireball);
                break;
            case Boomerang:

                this.texture = Global.resources.get(R.drawable.button_boomerang);
                break;
            case Link:

                this.texture = Global.resources.get(R.drawable.button_lightning);
                break;
            case Ice:

                this.texture = Global.resources.get(R.drawable.button_ice);
                break;
            case Gravity:

                this.texture = Global.resources.get(R.drawable.button_gravity);
                break;
            case Meteor:
                this.texture = Global.resources.get(R.drawable.button_meteor);
                break;
            case Drain:
                this.texture = Global.resources.get(R.drawable.button_fireball);

                break;
            case Absorb:
                this.texture = Global.resources.get(R.drawable.button_fireball);

                break;
            case Splitter:
                this.texture = Global.resources.get(R.drawable.button_fireball);

                break;
            case FireSpray:

                this.texture = Global.resources.get(R.drawable.button_firespray);
                break;
            case IceSpray:
                break;
            case Bounce:
                this.texture = Global.resources.get(R.drawable.button_fireball);
                break;
            case Teleport:
                break;
            case Swap:
                break;
            case Thrust:
                break;
            case Reflect:
                this.texture = Global.resources.get(R.drawable.button_shield);

                break;
            case Orbitals:
                break;
            case Root:
                break;
            case JuggerNaught:
                break;
            case WindWalk:
                break;
            case Phase:
                break;
            case BurnAura:
                break;
            case HealAura:
                break;
            case Bezerk:
                break;
            case Fervour:
                break;
            case Boots:
                break;
            case HealthStone:
                break;
            case Shield:
                break;
            case FreezeAura:
                break;
            case FireExplode:

                this.texture = Global.resources.get(R.drawable.button_explosion);
                break;
            case IceExplode:
                break;
            case BurnExplode:
                break;
            case DrainExplode:
                break;
        }
    }
    public Spell(GameObject _parent,SpellInfo s) {
        this.parent = _parent;
        p = new Paint();
        castphase = CastTime;
        Rank = s.Rank;
        spellType = s.spellType;

        p.setColor(Color.RED);
        switch (spellType) {
            case Fireball:
            case Lightning:
            case Homing:
            case Boomerang:
            case Link:
            case Ice:
            case Gravity:
            case Meteor:
            case Drain:
            case Absorb:
            case Splitter:
            case Bounce:
            case Teleport:
            case Swap:
            case Thrust:
                this.castType =CastType.Projectile;
                break;
            case FireSpray:
            case IceSpray:
                this.castType = CastType.Spray;
                break;

            case Reflect:
            case Orbitals:
            case Root:
            case JuggerNaught:
            case WindWalk:
            case Phase:
                this.castType = CastType.ActivateBuff;
                break;
            case BurnAura:
            case HealAura:
            case Bezerk:
            case Fervour:
            case Boots:
            case HealthStone:
            case Shield:
            case FreezeAura:
                this.castType = CastType.Passive;
                break;
            case FireExplode:
            case IceExplode:
            case BurnExplode:
            case DrainExplode:
                this.castType = CastType.Explosion;
                break;

        }
        setAttributes(spellType,s.Rank);
        // owner = parent.id;
    }
private void setAttributes(SpellType s, int rank)
{
    switch (s)
    {

        case Fireball:
            switch (rank)
            {
                case 1:
                this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Lightning:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Homing:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Boomerang:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Link:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Ice:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Gravity:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Meteor:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Drain:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Absorb:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Splitter:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case FireSpray:
            switch (rank)
            {
                case 1:
                    this.setValues(20,20,5,20,30);
                    break;
                case 2:
                    this.setValues(20,20,5,20,30);
                    break;
                case 3:
                    this.setValues(20,20,5,20,30);
                    break;
                case 4:
                    this.setValues(20,20,5,20,30);
                    break;
                case 5:
                    this.setValues(20,20,5,20,30);
                    break;
                case 6:
                    this.setValues(20,20,5,20,30);
                    break;
                case 7:
                    this.setValues(20,20,5,20,30);
                    break;
            }
            break;
        case IceSpray:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Bounce:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Teleport:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Swap:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Thrust:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Reflect:
            switch (rank)
            {
                case 1:
                    this.setValues(50,20,5,20,30);
                    break;
                case 2:
                    this.setValues(50,20,5,20,30);
                    break;
                case 3:
                    this.setValues(50,20,5,20,30);
                    break;
                case 4:
                    this.setValues(50,20,5,20,30);
                    break;
                case 5:
                    this.setValues(50,20,5,20,30);
                    break;
                case 6:
                    this.setValues(50,20,5,20,30);
                    break;
                case 7:
                    this.setValues(50,20,5,20,30);
                    break;
            }
            break;
        case Orbitals:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Root:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case JuggerNaught:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case WindWalk:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Phase:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case BurnAura:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case HealAura:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Bezerk:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Fervour:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Boots:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case HealthStone:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case Shield:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case FreezeAura:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case FireExplode:
            switch (rank)
            {
                case 1:
                    this.setValues(8,20,5,20,30);
                    break;
                case 2:
                    this.setValues(8,20,5,20,30);
                    break;
                case 3:
                    this.setValues(8,20,5,20,30);
                    break;
                case 4:
                    this.setValues(8,20,5,20,30);
                    break;
                case 5:
                    this.setValues(8,20,5,20,30);
                    break;
                case 6:
                    this.setValues(8,20,5,20,30);
                    break;
                case 7:
                    this.setValues(8,20,5,20,30);
                    break;
            }
            break;
        case IceExplode:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case BurnExplode:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
        case DrainExplode:
            switch (rank)
            {
                case 1:
                    this.setValues(5,20,5,20,30);
                    break;
                case 2:
                    this.setValues(5,20,5,20,30);
                    break;
                case 3:
                    this.setValues(5,20,5,20,30);
                    break;
                case 4:
                    this.setValues(5,20,5,20,30);
                    break;
                case 5:
                    this.setValues(5,20,5,20,30);
                    break;
                case 6:
                    this.setValues(5,20,5,20,30);
                    break;
                case 7:
                    this.setValues(5,20,5,20,30);
                    break;
            }
            break;
    }
}



    public void glDraw()
    {

    }

    public boolean Cast(iVector[] dest) {
        Log.e("SHOOT!","SHOT SPELL!!!!!!!");
        if(!parent.frozen&&!parent.dead)
            switch (castType)
            {

                case Spray:
                case Projectile:
                    if (dest.length > 0)
                        for (int x = 0; x < dest.length; x++)

                            if (this.Current == 0) {
                                this.targetLocation = parent.position.subtract(dest[x]);
                                castphase= 0;
                                fired=  true;
                                this.Current = this.Cooldown;

                                this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Cast,  this.parent,R.drawable.effect_shield));
                                if (this.parent.objectObjectType == ObjectType.Enemy || this.parent.objectObjectType == ObjectType.Player) {
                                    (this.parent).Animate(new Vector(dest[x].x, dest[x].y));
                                }
                                return true;
                            }
                    return false;
                case Explosion:
                    if(!parent.frozen&&!parent.dead)
                        if (this.Current == 0) {
                            this.Current = this.Cooldown;
                            this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Explode, this.parent,R.drawable.effect_explode));
                            this.targetLocation =new iVector(0,0);
                            castphase= 0;
                            fired=  true;
                            return true;
                        }
                    return false;
                case ActivateBuff:
                       if(!parent.frozen&&!parent.dead)
                        if (this.Current == 0) {
                            this.Current = this.Cooldown;

                            this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Reflect, this.parent,R.drawable.effect_shield));
                            return true;
                        }
                    return false;
            }
        return false;

    }
    public void Cast(iVector dest) {

        if(!parent.frozen&&!parent.dead&&!parent.casting)

                if (this.Current == 0) {
                    this.targetLocation = parent.position.subtract(dest);
                    castphase= 0;
                    fired=  true;
                    this.Current = this.Cooldown;

                    this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Cast, this.parent,R.drawable.effect_shield));
                    if (this.parent.objectObjectType == ObjectType.Enemy || this.parent.objectObjectType == ObjectType.Player) {
                        ( this.parent).Animate(new Vector(dest.x, dest.y));
                    }
                    return;
                }
    }

    public void Update() {
        if(fired)
        {
            castphase+=1;
            if(castType == CastType.Spray)
            {

                if(castphase%3==2)
                {
                    Shoot((parent.position.subtract(this.targetLocation)));
                }

            }
            if(castphase==CastTime)
            {
                Shoot((parent.position.subtract(this.targetLocation)));
                fired= false;
            }
        }
        else
        {
        if (this.Current > 0)
            this.Current -= 1;
        }
    }

    void Shoot(iVector Dest) {
        switch (spellType) {
            case Fireball:
                SimpleGLRenderer.addObject(new FireballProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
                break;
            case Lightning:
                SimpleGLRenderer.addObject(new LightningProjectile(this.parent.bounds.Center,new Vector(Dest.x, Dest.y), this.parent));
                break;
            case Homing:
                SimpleGLRenderer.addObject(new FireballProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
                break;
            case Boomerang:
                SimpleGLRenderer.addObject(new BoomerangProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
                break;
            case Link:
                SimpleGLRenderer.addObject(new LinkProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
                break;
            case Ice:
                SimpleGLRenderer.addObject(new IceProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case Gravity:
                SimpleGLRenderer.addObject(new GravityProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case Meteor:
                SimpleGLRenderer.addObject(new MeteorProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case Drain:
                SimpleGLRenderer.addObject(new DrainProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case Absorb:
                SimpleGLRenderer.addObject(new AbsorptionProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
                break;
            case Splitter:
                SimpleGLRenderer.addObject(new SplitterProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case FireSpray:
                SimpleGLRenderer.addObject(new FiresprayProjectile(this.parent.bounds.Center, Dest.add(new Vector(Global.GetRandomNumer.nextInt(50),Global.GetRandomNumer.nextInt(50))), this.parent));

                break;
            case IceSpray:
                break;
            case Bounce:
                SimpleGLRenderer.addObject(new BounceProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case Teleport:

                this.parent.position = new Vector(Dest.x - parent.size.x / 2, Dest.y - parent.size.y);
                break;
            case Swap:
                SimpleGLRenderer.addObject(new SwapProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case Thrust:
                break;
            case Reflect:
                break;
            case Orbitals:
                break;
            case Root:
                break;
            case JuggerNaught:
                break;
            case WindWalk:
                break;
            case Phase:
                break;
            case BurnAura:
                break;
            case HealAura:
                break;
            case Bezerk:
                break;
            case Fervour:
                break;
            case Boots:
                break;
            case HealthStone:
                break;
            case Shield:
                break;
            case FreezeAura:
                break;
            case FireExplode:

                SimpleGLRenderer.addObject(new ExplosionProjectile(parent.bounds.Center.get(), new Vector(500, 500), parent));
                break;
            case IceExplode:

                SimpleGLRenderer.addObject(new IcesplosionProjectile(parent.bounds.Center.get(), new Vector(500, 500), parent));
                break;
            case BurnExplode:
                break;
            case DrainExplode:
                break;
        }
    }
    @Override
         public String toString() {
        return "Fireball";
    }
}
