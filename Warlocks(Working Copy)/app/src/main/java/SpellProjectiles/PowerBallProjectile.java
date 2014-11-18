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

    public PowerBallProjectile(Vector _from, Vector _to, Collideable shooter,int Rank) {
        super(R.drawable.spell_grenade, _from, _to, shooter,Rank);
        this.objectObjectType = ObjectType.PowerBall;
        stacks = 3;
        SimpleGLRenderer.popupTexts.add(new PopupText( PopupText.TextType.Burn,stacks+"",this.position.get(),15));
    }

    @Override
    protected void Stats(int rank)
    {
        this.maxVelocity = 15;

        switch (rank)
        {
            case 1:
                this.health = 100;
                this.knockback =7;
                this.size = new Vector(50,50);
                this.damagevalue = 6;

                break;
            case 2:
                this.health = 110;
                this.knockback =8.5;
                this.size = new Vector(50,50);
                this.damagevalue = 7;
                break;
            case 3:
                this.health = 120;
                this.knockback =10;
                this.size = new Vector(50,50);
                this.damagevalue = 8;
                break;
            case 4:
                this.health = 130;
                this.knockback =11.5;
                this.size = new Vector(50,50);
                this.damagevalue = 9;
                break;
            case 5:
                this.health = 140;
                this.knockback =13;
                this.size = new Vector(60,60);
                this.damagevalue = 10;
                break;
            case 6:
                this.health = 150;
                this.knockback =14.5;
                this.size = new Vector(60,60);
                this.damagevalue = 11;
                break;
            case 7:
                this.health = 160;
                this.knockback =16;
                this.size = new Vector(60,60);
                this.damagevalue = 12;
                break;
        }


    }

    public void dropStacks() {

        this.stacks-=1;

        SimpleGLRenderer.popupTexts.add(new PopupText( PopupText.TextType.Burn,stacks+"",this.position.get(),15));
        if(this.stacks==0)
            SimpleGLRenderer.delObject(this.id);

    }
}
