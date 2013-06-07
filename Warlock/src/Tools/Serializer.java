package Tools;

/**
 * Created by Scott on 7/06/13.
 */
import java.io.*;
import java.util.List;

import Actors.EllipseMovingAI;
import Game.GameObject;

public class Serializer
{
    public static void SerializetoFile(List<GameObject> gameObjects)
    {

        try
        {
            FileOutputStream fileOut =
                    new FileOutputStream("employee.ser");
            ObjectOutputStream out =
                    new ObjectOutputStream(fileOut);
            out.writeObject(gameObjects);
            out.close();
            fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    public static void DeserializefromFile(List<GameObject> gameObjects )
    {
       gameObjects = null;
        try
        {
            FileInputStream fileIn =
                    new FileInputStream("employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
           gameObjects = (List<GameObject>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserialized Employee...");
    }
}