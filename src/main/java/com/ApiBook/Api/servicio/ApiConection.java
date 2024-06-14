
package com.ApiBook.Api.servicio;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

    
public class ApiConection {
    
    private HttpClient httpClient;
    
    public ApiConection(){
        this.httpClient = HttpClient.newHttpClient();
    }
    
    public String obtenerJson (String url){
        HttpRequest request  = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
