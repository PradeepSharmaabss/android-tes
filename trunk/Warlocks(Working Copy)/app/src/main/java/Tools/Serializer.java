package Tools;

/**
 * Created by Scott on 7/06/13.
 */

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import Input.Finger;
import Input.NetworkFinger;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

public class Serializer {
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }

    public static byte[] SerializetoBytes(List<GameObject> gameObjects) {

        try {

            ByteArrayOutputStream fileOut =
                    new ByteArrayOutputStream();
            ObjectOutputStream out =
                    new ObjectOutputStream(fileOut);

            out.writeObject(gameObjects);
            return fileOut.toByteArray();

        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;
    }

    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            //  Log.d("INET", "SERIALISATION SUCCESSFUL");
            oos.writeObject(obj);
            //  Log.d("INET", "SERIALISATION SUCCESSFUL");
            oos.flush();
            oos.close();
            bos.close();

            bytes = bos.toByteArray();
            //Log.d("INET", "SERIALISATION SUCCESSFUL:"+bytes.length);
        } catch (Exception ex) {
            //TODO: Handle the exception
            ex.printStackTrace();

            Log.d("INET", "SERIALISATION FUCKED UP");
        }
        return bytes;
    }

    public static Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (IOException ex) {
            //TODO: Handle the exception
        } catch (ClassNotFoundException ex) {
            //TODO: Handle the exception
        }
        return obj;
    }

    public static byte[] SerializetoBytes(NetworkFinger gameObjects) {

        try {

            ByteArrayOutputStream fileOut =
                    new ByteArrayOutputStream();
            ObjectOutputStream out =
                    new ObjectOutputStream(fileOut);
            out.writeObject(gameObjects);

            return fileOut.toByteArray();

        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;
    }

    public static byte[] SerializetoBytes(Vector gameObjects) {

        try {

            ByteArrayOutputStream fileOut =
                    new ByteArrayOutputStream();

            ObjectOutputStream out =
                    new ObjectOutputStream(fileOut);

            out.writeObject(gameObjects);

            return fileOut.toByteArray();

        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;
    }

    public static Finger DeserializefromFile(byte[] gameObjects) {

        Finger f = null;
        try {
            ByteArrayInputStream fileIn =
                    new ByteArrayInputStream(gameObjects);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            f = (Finger) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();

        }
        System.out.println("Deserialized Employee...");
        return f;
    }

    public static NetworkFinger DeserializefromFiletoVector(byte[] gameObjects) {

        NetworkFinger f = null;
        try {
            ByteArrayInputStream fileIn =
                    new ByteArrayInputStream(gameObjects);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            f = (NetworkFinger) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {

            c.printStackTrace();

        }
        System.out.println("Deserialized Employee...");
        return f;
    }
}