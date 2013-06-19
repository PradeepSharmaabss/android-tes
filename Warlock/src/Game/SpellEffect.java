package Game;

/**
 * Created by Scott on 6/19/13.
 */
public class SpellEffect {
    public int Duration;
   public enum EffectType{Stun, Poison, Reflect, Magnetise,Cast}
   public EffectType effectType;
public  SpellEffect(int _d, EffectType _e)
{
    Duration= _d;
    effectType=_e;
}
}
