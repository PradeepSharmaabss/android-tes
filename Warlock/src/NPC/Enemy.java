package NPC;
import Game.GameObject;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.RectF;



public class Enemy extends GameObject{

	Bitmap bitmap;
	public Enemy(Bitmap bmp)
	{
		bitmap = bmp;
		rect = new RectF(0,0,100,100);
		position = new Vector(0,0);
		size = new Vector(100, 100);
	}
	
	
	public void Update()
	{
		
	}
}
