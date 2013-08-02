package com.example.warlockgame;

import android.os.AsyncTask;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by Scott on 2/08/13.
 */
public class NetTask extends AsyncTask<String, Integer, String>
{
    @Override
    protected String doInBackground(String...params)
    {
        String addre = null;
        Enumeration<NetworkInterface> n = null;
        try {
            n = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while ( n.hasMoreElements())
        {
            NetworkInterface e = n.nextElement();
            System.out.println("Interface: " + e.getName());
            Enumeration<InetAddress> a = e.getInetAddresses();
            while ( a.hasMoreElements())
            {
                InetAddress addr = a.nextElement();
               addre+= addr.getHostName()+"\n";
            }
        }

        return addre;
    }
}
