package Spells;

import android.util.Log;
import android.widget.ResourceCursorAdapter;

import com.developmental.warlocks.R;

import java.io.Serializable;

/**
 * Created by Scott on 3/01/14.
 */
public class SpellInfo implements Serializable {
    public SpellType spellType;
    public int Rank;
    public int Resource;
    public SpellInfo(SpellType _s, int _r)
    {
        spellType= _s;
        Rank= _r;
        Resource= setResource(_s);
    }
   public static int setResource(SpellType spellType)
    {
        int Resource= 0;
        switch (spellType) {
            case Fireball:
                Resource = R.drawable.button_fireball;
                break;
            case Lightning:
                Resource = R.drawable.button_lightning;
                break;
            case Illusion:
                Resource = R.drawable.button_illusion;
                break;
            case Homing:
                Resource = R.drawable.button_homing;
                break;
            case Boomerang:
                Resource = R.drawable.button_boomerang;
                break;
            case Link:
                Resource = R.drawable.button_link;
                break;
            case Ice:
                Resource = R.drawable.button_ice;
                break;
            case Gravity:
                Resource = R.drawable.button_gravity;
                break;
            case Meteor:
                Resource = R.drawable.button_meteor;
                break;
            case Drain:
                Resource = R.drawable.button_eyeball;
                break;
            case IllusionBall:
                Resource = R.drawable.button_fireball;
                break;
            case Absorb:
                Resource = R.drawable.button_fireball;
                break;
            case Splitter:
                Resource = R.drawable.button_fireball;
                break;
            case FireSpray:
                Resource = R.drawable.button_firespray;
                break;
            case IceSpray:
                Resource = R.drawable.button_fireball;
                break;
            case Bounce:
                Resource = R.drawable.button_boomerang;
                break;
            case Teleport:
                Resource = R.drawable.button_teleport;
                break;
            case Swap:
                Resource = R.drawable.button_fireball;
                break;
            case Thrust:
                Resource = R.drawable.button_fireball;
                break;
            case Reflect:
                Resource = R.drawable.button_shield;
                break;
            case Orbitals:
                Resource = R.drawable.button_fireball;
                break;
            case Root:
                Resource = R.drawable.button_fireball;
                break;
            case JuggerNaught:
                Resource = R.drawable.button_fireball;
                break;
            case WindWalk:
                Resource = R.drawable.button_fireball;
                break;
            case Phase:
                Resource = R.drawable.button_fireball;
                break;
            case BurnAura:
                Resource = R.drawable.button_fireball;
                break;
            case HealAura:
                Resource = R.drawable.button_fireball;
                break;
            case Bezerk:
                Resource = R.drawable.button_fireball;
                break;
            case Fervour:
                Resource = R.drawable.button_fireball;
                break;
            case Boots:
                Resource = R.drawable.button_fireball;
                break;
            case HealthStone:
                Resource = R.drawable.button_fireball;
                break;
            case Shield:
                Resource = R.drawable.button_fireball;
                break;
            case FreezeAura:
                Resource = R.drawable.button_fireball;
                break;
            case FireExplode:
                Resource = R.drawable.button_explosion;
                break;
            case IceExplode:
                Resource = R.drawable.button_icesplosion;
                break;
            case BurnExplode:
                Resource = R.drawable.button_explosion;
                break;
            case Grenade:
                Resource = R.drawable.button_grenade;
                break;
            case Piercing:
                break;
            case Powerball:
                break;
            case TrapMines:
                break;
            case SonicWave:
                break;
            case MagnetExplode:
                break;
            case DrainExplode:
                Resource = R.drawable.button_explosion;
                break;
        }
        return Resource;
    }

    public void SetOrIncrement(SpellType s)
    {
        if(spellType ==s)
        {
            if(Rank<7)
               Rank+=1;
        }
        else
        {
            spellType = s;
           Resource =setResource(s);
            Rank= 1;
        }
    }
    @Override
    public String toString() {

        Log.d("Rank",Rank + ". " + spellType.toString());
        return Rank+ ". " + spellType.toString();
    }
}
