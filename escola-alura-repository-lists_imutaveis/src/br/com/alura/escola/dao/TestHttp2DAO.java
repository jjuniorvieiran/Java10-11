package br.com.alura.escola.dao;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TestHttp2DAO {

    public void testeConnexaoHTTP() throws IOException, URISyntaxException, InterruptedException {

        URI url = new URI("http://www.google.com.br");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(url).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

    }


}
