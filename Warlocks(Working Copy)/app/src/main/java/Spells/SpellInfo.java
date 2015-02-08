package Spells;

import android.util.Log;

import com.developmental.warlocks.R;

import java.io.Serializable;

/**
 * Created by Scott on 3/01/14.
 */
public class SpellInfo implements Serializable {
    public SpellType spellType;
    public int Rank;
    public int Resource;

    public SpellInfo(SpellType _s, int _r) {
        spellType = _s;
        Rank = _r;
        Resource = setResource(_s);
    }

    public static int setDescription(SpellType spellType) {
        int i = R.string.spell_fireball_description;
        switch (spellType) {
            case Fireball:
                i = R.string.spell_fireball_description;
                break;
            case Lightning:
                i = R.string.spell_lightning_description;
                break;
            case Illusion:
                i = R.string.spell_illusion_description;
                break;
            case Homing:
                i = R.string.spell_homing_description;
                break;
            case Boomerang:
                i = R.string.spell_boomerang_description;
                break;
            case Link:
                i = R.string.spell_link_description;
                break;
            case Ice:
                i = R.string.spell_ice_description;
                break;
            case Gravity:
                i = R.string.spell_gravity_description;
                break;
            case Meteor:
                i = R.string.spell_meteor_description;
                break;
            case Drain:
                i = R.string.spell_drain_description;
                break;
            case IllusionBall:
                i = R.string.spell_illusionball_description;
                break;
            case Absorb:
                i = R.string.spell_absorb_description;
                break;
            case Splitter:
                i = R.string.spell_splitter_description;
                break;
            case FireSpray:
                i = R.string.spell_firespray_description;
                break;
            case IceSpray:
                i = R.string.spell_icespray_description;
                break;
            case Bounce:
                i = R.string.spell_bounce_description;
                break;
            case Teleport:
                i = R.string.spell_teleport_description;
                break;
            case Swap:
                i = R.string.spell_swap_description;
                break;
            case Thrust:
                i = R.string.spell_thrust_description;
                break;
            case Reflect:
                i = R.string.spell_reflect_description;
                break;
            case Orbitals:
                i = R.string.spell_orbitals_description;
                break;
            case Root:
                i = R.string.spell_thrust_description;
                break;
            case JuggerNaught:
                i = R.string.spell_juggernaut_description;
                break;
            case WindWalk:
                i = R.string.spell_windwalk_description;
                break;
            case Phase:
                i = R.string.spell_phase_description;
                break;
            case MiddleOfAction:
                i = R.string.spell_phase_description;
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
            case Grenade:
                i = R.string.spell_grenade_description;
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
                break;
        }
        return i;
    }

    public static int setResource(SpellType spellType) {
        int Resource = R.drawable.spell_fireball;
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
                Resource = R.drawable.button_orbital;
                break;
            case Root:
                Resource = R.drawable.button_fireball;
                break;
            case JuggerNaught:
                Resource = R.drawable.button_fireball;
                break;
            case WindWalk:
                Resource = R.drawable.button_windwalk;
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
                Resource = R.drawable.button_boots;
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
                Resource = R.drawable.button_ice;
                break;
            case Powerball:
                Resource = R.drawable.button_ice;
                break;
            case TrapMines:
                Resource = R.drawable.button_grenade;
                break;
            case SonicWave:
                Resource = R.drawable.button_grenade;
                break;
            case MagnetExplode:
                Resource = R.drawable.button_explosion;
                break;
            case DrainExplode:
                Resource = R.drawable.button_explosion;
                break;
            case MiddleOfAction:

                Resource = R.drawable.button_explosion;
                break;
        }
        return Resource;
    }

    public void SetOrIncrement(SpellType s) {
        if (spellType == s) {
            if (Rank < 7)
                Rank += 1;
        } else {
            spellType = s;
            Resource = setResource(s);
            Rank = 1;
        }
    }

    @Override
    public String toString() {

        Log.d("Rank", Rank + ". " + spellType.toString());
        return Rank + ". " + spellType.toString();
    }
}
