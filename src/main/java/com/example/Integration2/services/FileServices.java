package com.example.Integration2.services;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

//input stream network connection ma bhayeko byte data read garna use huncha
//read garna lai network connection ma byte data huna paryo ni ta

//network connection ma byte data write garna lai output stream use huncha.
//network connection ma byte data write garna lai outputstream le, java side ma byte data prepared bha huna parcha.tyo prepared data lai write gardiney ho.

//

@Service
public class FileServices {
    public void createAndSendFile() throws IOException {
        String res="";
//        emergency.txt naam ko file declare bhayo. yo java object huncha physical file bhaisaeko hudaina
        File file = new File("emergency.txt");
//        createnewfile method execute bhaisaekey pachi physical file bancha.
        file.createNewFile();

//        file ma data write gareko:
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("my name is shreejwal");
            fileWriter.flush();

//            http client use garera client prepare gareko
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            String serverUrl = "http://localhost:8080/receiveFile";
            HttpPost httpPost = new HttpPost(serverUrl);
            FileEntity entity = new FileEntity(file, ContentType.APPLICATION_OCTET_STREAM);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println("Server responded with status code: " + response.getStatusLine().getStatusCode());
            res = res + response.getStatusLine().getStatusCode() + response.getEntity().toString()+new String(response.getEntity().getContent().readAllBytes());
        }
        catch (Exception e){}

        System.out.println("Server responded with status code: " + res);
    }
}
