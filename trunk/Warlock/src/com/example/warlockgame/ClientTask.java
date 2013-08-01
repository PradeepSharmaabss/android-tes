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

import android.util.Log;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

import Tools.Vector;

public class ClientTask {
    public static void Send(String args, Vector pos) throws IOException {

//        if (args.length() != 1) {
//            System.out.println("Usage: java QuoteClient <hostname>");
//            return;
//        }

        // get a datagram socket
        DatagramSocket socket = new DatagramSocket();

        // send request
        byte[] buf = new byte[256];
        try {

            InetAddress address = InetAddress.getByName(args);
            ByteBuffer b = ByteBuffer.allocate(64);
//b.order(ByteOrder.BIG_ENDIAN); // optional, the initial order of a byte buffer is always BIG_ENDIAN.
            b.putFloat(pos.x);
            b.putFloat(pos.y);

          buf = b.array();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);

            Log.d("INET",args);
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
}
