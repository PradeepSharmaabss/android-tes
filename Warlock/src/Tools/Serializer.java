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
    public static byte[] SerializetoBytes(List<GameObject> gameObjects)
    {

        try
        {
          
            ByteArrayOutputStream fileOut =
                    new ByteArrayOutputStream();
            ObjectOutputStream out =
                    new ObjectOutputStream(fileOut);

            out.writeObject(gameObjects);
             return fileOut.toByteArray();

        }catch(IOException i)
        {
            i.printStackTrace();
        }
        return null;
    }
    public static byte[] SerializetoBytes(GameObject gameObjects)
{

    try
    {

        ByteArrayOutputStream fileOut =
                new ByteArrayOutputStream();
        ObjectOutputStream out =
                new ObjectOutputStream(fileOut);

        out.writeObject(gameObjects);
        return fileOut.toByteArray();

    }catch(IOException i)
    {
        i.printStackTrace();
    }
    return null;
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