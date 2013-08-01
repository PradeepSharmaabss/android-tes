package com.example.warlockgame;

/**
 * Created by Scott on 30/07/13.
 */
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import android.os.NetworkOnMainThreadException;
import android.util.Log;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

import Tools.*;

public class ServerThread extends Thread {
    List<String> Peers = new ArrayList<String>();
    int id = 0;
    protected DatagramSocket socket = null;
    public enum ActionType {
      AcceptConnections, AcceptInfomation
    }
    private ActionType a;
    public ServerThread(ActionType _a) throws IOException {
        this("QuoteServerThread");
        a=_a;
    }

    public ServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);

        Log.d("INET","STARTED THREAD!");

    }
    public static void Send(String hostname, Tools.Vector pos) throws IOException {

//        if (args.length() != 1) {
//            System.out.println("Usage: java QuoteClient <hostname>");
//            return;
//        }

        // get a datagram socket
        DatagramSocket socket = new DatagramSocket();

        // send request
        byte[] buf = new byte[256];
        try {

            InetAddress address = InetAddress.getByName(hostname);
            ByteBuffer b = ByteBuffer.allocate(64);
//b.order(ByteOrder.BIG_ENDIAN); // optional, the initial order of a byte buffer is always BIG_ENDIAN.
            b.putFloat(pos.x);
            b.putFloat(pos.y);

            buf = b.array();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);

            Log.d("INET",hostname);
            socket.send(packet);

            // get response
            packet = new DatagramPacket(buf, buf.length);
            // socket.receive(packet);

            // display response
            String received = new String(packet.getData(), 0, packet.getLength());
            // Log.d("INET","Quote of the Moment: " + received);

            socket.close();
        }
        catch (Exception e)
        {
            Log.d("INET","UNABLE TO RESOLVE HOSTNAME " );

            return;
        }

    }
    public static void printRemoteAddress (String name){

        try {
            Log.d("INET","Looking up " + name + "..." + "\n");
            InetAddress machine = InetAddress.getByName (name);
            Log.d("INET","Host name : " + machine.getHostName ()+ "\n");
            Log.d("INET","Host IP : " +  (machine.getAddress ()).toString()+ "\n");
        }
        catch (UnknownHostException e){
            Log.d("INET","Failed to lookup " + name + "\n");
            e.printStackTrace ();
        }
        catch (SecurityException e){
            Log.d("INET","Failed to find host other than host web server:");
            e.printStackTrace ();
        }
    }
    public void run() {
if(a==ActionType.AcceptInfomation)
{
        while (true) {
            try {
                byte[] buf = new byte[64];
                Log.d("INET","Host IP : " +  ServerThread.getLocalIpAddress());
                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);

                socket.receive(packet);
                Log.d("INET","Server Recieved: " );
               ByteBuffer b =  ByteBuffer.wrap(buf);
                float x =b.getFloat();

                float y = b.getFloat();
                Tools.Vector vector = new Tools.Vector(x,y);
                RenderThread.archie2.StartTo(vector);
                Log.d("INET","x=" +x+" , y="+ y);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
        else
{
    Peers = new ArrayList<String>();

        for(int x = 1; x<players;x++)
        {

            Log.d("INET","Awaiting Connections: " );
            Log.d("INET","Players Remaining: "+(players-x) );
            byte[] buf = new byte[64];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                Log.d("INET","Somethings gone to shit "+(players-x) );
            }

            InetAddress address = packet.getAddress();
            Log.d("INET","Recieved Connection from: "+address.getHostAddress());

            ByteBuffer b =  ByteBuffer.wrap(buf);
            b.putInt(id++);

            Peers.add(address.getHostAddress());
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
}
        //socket.close();
    }
    public static void Connect(String hostname)
    {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            return;

        }

        // send request
        byte[] buf = new byte[64];
        try {

            InetAddress address = InetAddress.getByName(hostname);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);

            Log.d("INET",hostname);
            socket.send(packet);

            // get response
            packet = new DatagramPacket(buf, buf.length);
            // socket.receive(packet);
            socket.receive(packet);
            // display response
            String received = new String(packet.getData(), 0, packet.getLength());
            // Log.d("INET","Quote of the Moment: " + received);

            socket.close();
        }
        catch (Exception e)
        {
            Log.d("INET","UNABLE TO RESOLVE HOSTNAME " );

            return;
        }
    }

    int players = 2;
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip =(inetAddress.getHostName());//.getHostAddress());
                        Log.i("INET", "***** IP="+ ip);
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("INET", ex.toString());
        }
        return null;
    }

}
