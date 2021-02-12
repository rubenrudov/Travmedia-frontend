package com.travmedia.communication;

import android.os.AsyncTask;
import android.text.style.TabStopSpan;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SocketTask extends AsyncTask<JSONObject, Void, JSONObject> {
    private final static String IP_ADDRESS = "XXX.XXX.XXX.XXX";    // Host's IP address in the LAN
    private static final int PORT = 8080;       // HTTP port
    private static final int PACKET_SIZE = 1024;    // standard 1kb packet size

    // Properties
    private InputStreamReader streamReader;
    private Socket socket;
    private JSONObject sendingJson, readingJson;

    public SocketTask(JSONObject object){
        // Constructor for SocketTask
        this.sendingJson = object;
        this.readingJson = new JSONObject();
    }

    @Override
    protected JSONObject doInBackground(JSONObject... jsonObjects) {
        try{
            // Socket setting
            this.socket = new Socket(IP_ADDRESS, PORT);
            send(this.sendingJson);
            receive();
            this.socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Exception", e.toString());
        }

        return this.readingJson;
    }

    private void receive()  {
        // Function that reads the data that comes from the server
        try {
            this.streamReader = new InputStreamReader(this.socket.getInputStream(), StandardCharsets.UTF_8);
            char[] charBuffer = new char[PACKET_SIZE];
            StringBuilder stringBuilder = new StringBuilder();
            while (this.streamReader.read(charBuffer) != -1) {
                stringBuilder.append(charBuffer);
            }

            this.streamReader.close();
            this.readingJson = new JSONObject(stringBuilder.toString());
        } catch (IOException | JSONException e) {
            Log.d("Exception", e.toString());
        }
    }

    private void send(JSONObject dataJson) {
        // Function for sending data to the server
        String data = dataJson.toString();
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.socket.getOutputStream(), StandardCharsets.UTF_8);
            outputStreamWriter.write(data);
            outputStreamWriter.flush();
        } catch(IOException e) {
            Log.d("Exception", e.toString());
        }
        Log.d("Send function", "Finished process");
    }
}


