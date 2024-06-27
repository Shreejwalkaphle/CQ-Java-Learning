package com.example.Integration2.services;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

//input stream network connection ma bhayeko byte data read garna use huncha
//read garna lai network connection ma byte data huna paryo ni ta

//network connection ma byte data write garna lai output stream use huncha.
//network connection ma byte data write garna lai outputstream le, java side ma byte data prepared bha huna parcha.tyo prepared data lai write gardiney ho.

//yo HttpUrlConnection use garda actual server call chai esko getOutputStream() method call huda huni raicha.

@Service
public class FileServices {
    public void createAndSendFile() throws IOException {
//        emergency.txt naam ko file declare bhayo. yo java object huncha physical file bhaisaeko hudaina
        File file = new File("emergency.txt");
//        createnewfile method execute bhaisaekey pachi physical file bancha.
        file.createNewFile();

//        file ma data write gareko:
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("my name is shreejwal");
            fileWriter.flush();


//        client preepare gareko.
        String filePathauneyUrl = "http://localhost:9191/receiveFile";
        HttpURLConnection connection = (HttpURLConnection) new URL(filePathauneyUrl).openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
//        content type ma application/octet-stream bhaneko maile byte type ko data pathaudai chu hai bhaneko
        connection.setRequestProperty("Content-Type", "application/octet-stream");
//        byte type ko data pathauna lai byte type ko data huna paryo ni ta hamisanga. tyo data preapare gareyko.
//        data preapare garna lai hamisanga data file bhitra cha. file bata read garera teslai byte ma convert garna parcha.
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        byte[] contentoffileasbytearray = null;
        while ((line = bufferedReader.readLine()) != null) {
            contentoffileasbytearray = line.getBytes();
            System.out.println(line);
        }
//        connection object ma outputstream huncha. tyo outstream lai byte data pathayem bhaye connection ma hamro byte data rakhincha.
        connection.getOutputStream().write(contentoffileasbytearray);

        System.out.println("response from server is : "+connection.getResponseCode());
    }
}
