package com.paimapi.messaging;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.net.Socket;
import java.io.PrintWriter;

public class MessageTask extends AsyncTask<void,void,void> {
    Socket socket;
    DataOutputStream dataOutputStream;
    PrintWriter printWriter;

    @Override
    protected void doInBackground(void... voids) {
        socket = new Socket()
    }
}
