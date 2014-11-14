package SpellProjectiles;

import com.developmental.warlocks.R;

import HUD.PopupText;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 14/11/2014.
 */
public class PowerBallProjectile extends Projectile{
    public int stacks;

    @Override
    protected void setFrames() {
        FramesNoTail();
    }

    public PowerBallProjectile(Vector _from, Vector _to, Collideable shooter) {
        super(R.drawable.spell_grenade, _from, _to, shooter, 6000, 4, new Vector(80, 80), 3);
        this.objectObjectType = ObjectType.PowerBall;
        stacks = 3;
        SimpleGLRenderer.popupTexts.add(new PopupText( PopupText.TextType.Burn,stacks+"",this.position.get(),15));
    }


    public void dropStacks() {

        this.stacks-=1;

        SimpleGLRenderer.popupTexts.add(new PopupText( PopupText.TextType.Burn,stacks+"",this.position.get(),15));
        if(this.stacks==0)
            SimpleGLRenderer.delObject(this.id);

    }
}
