package SpellProjectiles;


import android.graphics.RectF;
import android.util.Log;

import com.developmental.warlocks.R;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11Ext;

import Tools.Vector;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;


/**
 * Created by Scott on 27/07/13.
 */
public class LinkProjectile extends Projectile {
    public Vector Start, Dest;
    public Collideable linked = null;
    float Range=10;
    public LinkProjectile(Vector _start, Vector _dest, GameObject _parent,int Rank) {
        super(R.drawable.spell_link,_start, _dest, _parent,Rank);






        Start = _start.get();
        this.Dest = _dest;
        float dx = this.Start.x - this.Dest.x;
        float dy = this.Start.y - this.Dest.y;
        float ToteDist = Math.abs(dx) + Math.abs(dy);
        this.objectObjectType = ObjectType.LinkSpell;
        this.Dest =Start;
        // Dest=new Vector(dx/ToteDist*maxVelocity,dy/ToteDist*maxVelocity);
        //this.health = 3;
        // shadowPaint = new Paint();
        // this.damagevalue=15;

        this.pull = 0.5f;

        // SimpleGLRenderer.addParticle(new glParticle(Start, Dest, this.velocity, 7,  R.drawable.fireball));
        //this.paint.setAlpha(125);
    }

    protected void Stats(int rank)
    {
        this.maxVelocity = 15;

        switch (rank)
        {
            case 1:
                this.health = 100;
                this.knockback =0.5;
                this.size = new Vector(50,50);
                this.damagevalue =0.05f;

                break;
            case 2:
                this.health = 110;
                this.knockback =0.5;
                this.size = new Vector(50,50);
                this.damagevalue =0.1f;
                break;
            case 3:
                this.health = 120;
                this.knockback =0.5;
                this.size = new Vector(50,50);
                this.damagevalue =0.15f;
                break;
            case 4:
                this.health = 130;
                this.knockback =0.5;
                this.size = new Vector(50,50);
                this.damagevalue =0.20f;
                break;
            case 5:
                this.health = 140;
                this.knockback =0.5;
                this.size = new Vector(60,60);
                this.damagevalue =0.25f;
                break;
            case 6:
                this.health = 150;
                this.knockback =0.5;
                this.size = new Vector(60,60);
                this.damagevalue =0.3f;
                break;
            case 7:
                this.health = 160;
                this.knockback =0.5;
                this.size = new Vector(60,60);
                this.damagevalue =0.35f;
                break;
        }


    }
    @Override
    public boolean Within(RectF Bounds) {
        return true;
    }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {

        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureName);

        if (mGrid == null) {
            // Draw using the DrawTexture extension.
            ((GL11Ext) gl).glDrawTexfOES(position.x, position.y, z, size.x, size.y);
        } else {
            // Draw using verts or VBO verts.
            gl.glPushMatrix();
            gl.glLoadIdentity();

            gl.glTranslatef(
                    bounds.Center.x-offsetX,
                    -bounds.Center.y-offsetY+15,
                    z);
            float angle=0;




//                Range= Vector.DistanceBetween(Start,Dest);
//                mGrid=  Grid.LightningLineGrid(Range);


                angle= (float) Math.toDegrees((float) Math.atan2((this.Start.y-this.Dest.y), -(this.Start.x-this.Dest.x)) - Math.atan2(0, 0));

            gl.glRotatef( (angle),0,0,1.0f);
            mGrid.get(this.frame).draw(gl, true, false);
//            if(!boundsz)
//            OpenGLTestActivity.boundingCircle.draw(gl,0,0);
            gl.glPopMatrix();

            //
        }
    }
    @Override
    public void Update() {

if(linked!=null) {

    if (linked.health <= 0 || linked.position == null) {
        linked = null;
        //Start = this.linked.bounds.Center;
    }

}
        if(owner.health<=0)
        {

        }
        if (this.health > 0) {
            if (linked == null||linked.health <= 0) {
                super.Update();
                Dest= owner.bounds.Center;
                //   Log.e("LINK MADE", this.position.x+","+this.position.y + "AND "+owner.position.x+","+owner.position.y+"AND "+owner.velocity.x+","+owner.velocity.y);
                Start = this.bounds.Center;
            } else {
                lifePhase++;
                owner.velocity = owner.velocity.add(linked
                        .DirectionalPull(owner.bounds.Center, this.knockback));
                linked.velocity = linked.velocity.add(owner
                        .DirectionalPull(linked.bounds.Center, this.knockback));


                switch (linked.objectObjectType) {
                    case Player:
                    case Enemy:
                    case GameObject:

                        DealDamageTo(linked);

                        break;
                }
                health -= 1;
            }
            Range= Vector.DistanceBetween(Start,Dest);
          mGrid=  Grid.LinkLineGrid(Range, lifePhase);
        } else {

            SimpleGLRenderer.delObject(this.id);
        }

    }



    public void Link(Collideable g) {
        if(linked==null)
        this.linked = g;
        this.bounds=linked.bounds;
        Start = linked.bounds.Center;
        Dest = this.owner.bounds.Center;
//        paint.setColor(Color.WHITE);
//        paint.setStrokeWidth(4);
    }

}
