package SpellProjectiles;


import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

public class SonicWaveProjectile extends Projectile {

    public SonicWaveProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(R.drawable.spell_gravity,_from, _to, _shooter, 200, 15f, new Vector(300, 300), 1);


        this.objectObjectType = ObjectType.GravityField;

        this.velocity = GetVel(_from.get(), _to.get());
        SetVelocity(this.maxVelocity);

        this.pull = -1;

//        this.damagevalue=1;
    }

    @Override
    protected void setFrames() {
      FramesNoTail();
    }

    @Override
    public void Update() {
        super.Update();

        //Animate();
        this.bounds.Center = position;

    }



    public void Animate() {
     super.Animate();
    }



}