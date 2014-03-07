package Spells;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import Actors.Player;
import com.developmental.myapplication.GL.NewHeirachy.GameObject;
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

import com.developmental.myapplication.Global;
import com.developmental.myapplication.R;
import com.developmental.myapplication.RenderThread;

public class Spell {
    public enum CastType{Projectile,Explosion,Passive,Spray,ActivateBuff};
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
        this.texture = Global.resources.get(R.drawable.fireball);
        switch (spellType)
        {

            case Fireball:
                this.texture = Global.resources.get(R.drawable.fireball);
                break;
            case Lightning:
                this.texture = Global.resources.get(R.drawable.lightningspell);
                break;
            case Homing:

                this.texture = Global.resources.get(R.drawable.fireball);
                break;
            case Boomerang:

                this.texture = Global.resources.get(R.drawable.bomerang);
                break;
            case Link:

                this.texture = Global.resources.get(R.drawable.lightningspell);
                break;
            case Ice:

                this.texture = Global.resources.get(R.drawable.icespell);
                break;
            case Gravity:

                this.texture = Global.resources.get(R.drawable.gravityspell);
                break;
            case Meteor:
                this.texture = Global.resources.get(R.drawable.meteorspell);
                break;
            case Drain:
                this.texture = Global.resources.get(R.drawable.fireball);

                break;
            case Absorb:
                this.texture = Global.resources.get(R.drawable.fireball);

                break;
            case Splitter:
                this.texture = Global.resources.get(R.drawable.fireball);

                break;
            case FireSpray:

                this.texture = Global.resources.get(R.drawable.firesprayspell);
                break;
            case IceSpray:
                break;
            case Bounce:
                this.texture = Global.resources.get(R.drawable.bomerang);
                break;
            case Teleport:
                break;
            case Swap:
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

    public void DrawButton(Canvas c, int x, int y, float w, float h) {
        switch (spellType)
        {

            case Fireball:
                c.drawCircle(x+30,y+30,30,Global.PaintOrange);
                c.drawCircle(x+60,y+60,20,Global.PaintOrange);
                c.drawCircle(x+90,y+90,10,Global.PaintOrange);

//                c.drawBitmap( Global.ButtonImages.get(4), x, y , p);
                break;
            case Lightning:
                c.drawLine(x,y,x+w/3,y+h/2,Global.PaintBlue);
                c.drawLine(x+w/3,y+h/2,x+2*w/3,y+h/2,Global.PaintBlue);
                c.drawLine(x+2*w/3,y+h/2,x+w,y+h,Global.PaintBlue);
                break;
            case Homing:
                break;
            case Boomerang:
                for(int d=0; d<5; d++)
                {

                    DrawBlade1(c,x+w/2,y+h/2,d*72);
                }
                break;
            case Link:
                c.drawCircle(x+5, y+5, 5, p);
                c.drawLine(x+5,y+5, x+w-5,y+h-5, p);
                c.drawCircle(x+w-5, y+h-5, 5, p);
                    break;
            case Ice:

                c.drawCircle(x+w/2,y+h/2,w/4,Global.PaintBlue);
                break;
            case Gravity:

                c.drawBitmap( Global.ButtonImages.get(3), x, y , p);
                break;
            case Meteor:

                c.drawBitmap( Global.ButtonImages.get(2), x, y , p);
                break;
            case Drain:
                c.drawRect(x,y,x+w,y+h,Global.PaintGreen);
                c.drawText("MISSING DRAW",x,y,Global.PaintOutline);
                break;
            case Absorb:
                c.drawRect(x,y,x+w,y+h,Global.PaintGreen);
                c.drawText("MISSING DRAW",x,y,Global.PaintOutline);
                break;
            case Splitter:
                c.drawRect(x,y,x+w,y+h,Global.PaintGreen);
                c.drawText("MISSING DRAW",x,y,Global.PaintOutline);
                break;
            case FireSpray:

                c.drawCircle(x+30,y+30,30,Global.PaintYellow);
                c.drawCircle(x+60,y+60,20,Global.PaintYellow);
                c.drawCircle(x+90,y+90,10,Global.PaintYellow);
                c.drawCircle(x+30,y+45,30,Global.PaintYellow);
                c.drawCircle(x+60,y+75,20,Global.PaintYellow);
                c.drawCircle(x+90,y+105,10,Global.PaintYellow);
                c.drawCircle(x+45,y+30,30,Global.PaintYellow);
                c.drawCircle(x+75,y+60,20,Global.PaintYellow);
                c.drawCircle(x+105,y+90,10,Global.PaintYellow);
                break;
            case IceSpray:
                c.drawRect(x,y,x+w,y+h,Global.PaintGreen);
                c.drawText("MISSING DRAW",x,y,Global.PaintOutline);
                break;
            case Bounce:
//                c.drawCircle(x+w/2,y+h/2,w/2,p);
//
//
//                c.drawCircle(x+w/2,y+h/2,w/2,Global.PaintOutline);
                for(int d=0; d<6; d++)
                {

                    DrawBlade2(c,x+w/2,y+h/2,(60*d)%360);
                }
                break;
            case Teleport:
                break;
            case Swap:
                c.drawCircle(x+w/2,y+h/2,w/4,Global.PaintGreen);
                break;
            case Thrust:
                break;
            case Reflect:

                c.drawBitmap( Global.ButtonImages.get(0), x, y , p);
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
                c.drawCircle(x+20,y+20,20,Global.PaintRed);
                c.drawCircle(x+20,y+20,20,Global.PaintRed);
                c.drawCircle(x+20,y+45,20,Global.PaintRed);
                c.drawCircle(x+20,y+32,20,Global.PaintRed);
                c.drawCircle(x+20,y+20,20,Global.PaintRed);
                c.drawCircle(x+45,y+20,20,Global.PaintYellow);
                c.drawCircle(x+34,y+65,20,Global.PaintYellow);
                c.drawCircle(x+20,y+87,20,Global.PaintYellow);
                c.drawCircle(x+42,y+21,20,Global.PaintYellow);
                c.drawCircle(x+62,y+76,20,Global.PaintYellow);
                break;
            case IceExplode:
                break;
            case BurnExplode:
                break;
            case DrainExplode:
                break;
            default:
                c.drawRect(x,y,x+w,y+h,Global.PaintGreen);
                c.drawText("MISSING DRAW",x,y,Global.PaintOutline);
                break;
        }
    }
    protected void DrawBlade1(Canvas canvas , float playerx,float playery,float angle)
    {


        float t2 =30;
        canvas.drawArc(new RectF(playerx-30,playery-30,30+playerx,30+playery),angle,t2,true,p);
        canvas.drawArc(new RectF(playerx-30,playery-30,30+playerx,30+playery),angle,t2,true,Global.PaintOutline);
    }
    protected void DrawBlade2(Canvas canvas, float playerx, float playery, float angle) {

        float centerx = (float) (playerx+30*Math.cos(Math.toRadians(angle)));
        float centery = (float) (playery + 30*Math.sin(Math.toRadians(angle)));
        float t2 =30;
        //canvas.drawRect(new RectF(centerx-bounds.Radius/2,centery-bounds.Radius/2,bounds.Radius/2+centerx,bounds.Radius/2+centery),Global.PaintCyan);
        canvas.drawArc(new RectF(centerx-30,centery-30,30+centerx,30+centery),(angle+180)%360,t2,true,p);
        canvas.drawArc(new RectF(centerx-30,centery-30,30+centerx,30+centery),(angle+180)%360,t2,true,Global.PaintOutline);
      //  canvas.drawArc(new RectF(centerx-15,centery-15,15+centerx,15+centery),(angle+180)%360,t2,true, Global.PaintOutline);

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

                                this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Cast, Global.Sprites.get(2), this.parent));
                                if (this.parent.objectObjectType == ObjectType.Enemy || this.parent.objectObjectType == ObjectType.Player) {
                                    ((Player) this.parent).Animate(new Vector(dest[x].x, dest[x].y));
                                }
                                return true;
                            }
                    return false;
                case Explosion:
                    if(!parent.frozen&&!parent.dead)
                        if (this.Current == 0) {
                            this.Current = this.Cooldown;
                            this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Cast, Global.Sprites.get(2), this.parent));
                            this.targetLocation =new iVector(0,0);
                            castphase= 0;
                            fired=  true;
                            return true;
                        }
                    return false;
                case ActivateBuff:
                    RenderThread.popupTexts.add(new PopupText(PopupText.TextType.Poison,"SPAWNED",RenderThread.archie.position.get(),100));
                    if(!parent.frozen&&!parent.dead)
                        if (this.Current == 0) {
                            this.Current = this.Cooldown;

                            this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Reflect, Global.Sprites.get(2), this.parent));
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

                    this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Cast, Global.Sprites.get(2), this.parent));
                    if (this.parent.objectObjectType == ObjectType.Enemy || this.parent.objectObjectType == ObjectType.Player) {
                        ((Player) this.parent).Animate(new Vector(dest.x, dest.y));
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
                RenderThread.addObject(new FireballProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
                break;
            case Lightning:
                RenderThread.addObject(new LightningProjectile(this.parent.bounds.Center,new Vector(Dest.x, Dest.y), this.parent));
                break;
            case Homing:
                RenderThread.addObject(new FireballProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
                break;
            case Boomerang:
                RenderThread.addObject(new BoomerangProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
                break;
            case Link:
                RenderThread.addObject(new LinkProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
                break;
            case Ice:
                RenderThread.addObject(new IceProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case Gravity:
                RenderThread.addObject(new GravityProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case Meteor:
                RenderThread.addObject(new MeteorProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case Drain:
                RenderThread.addObject(new DrainProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case Absorb:
                RenderThread.addObject(new AbsorptionProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
                break;
            case Splitter:
                RenderThread.addObject(new SplitterProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case FireSpray:
                RenderThread.addObject(new FiresprayProjectile(this.parent.bounds.Center, Dest.add(new Vector(Global.GetRandomNumer.nextInt(50),Global.GetRandomNumer.nextInt(50))), this.parent));

                break;
            case IceSpray:
                break;
            case Bounce:
                RenderThread.addObject(new BounceProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

                break;
            case Teleport:

                this.parent.position = new Vector(Dest.x - parent.size.x / 2, Dest.y - parent.size.y);
                break;
            case Swap:
                RenderThread.addObject(new SwapProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));

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

                RenderThread.addObject(new ExplosionProjectile(parent.bounds.Center.get(), new Vector(500, 500), parent));
                break;
            case IceExplode:

                RenderThread.addObject(new IcesplosionProjectile(parent.bounds.Center.get(), new Vector(500, 500), parent));
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
