package com.lei.configuration.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.ObjectMapper;



public class TestWS {   private static final String USER_AGENT = "Mozilla/5.0";

//private static final String GET_URL = "http://localhost:8080/CdiOnDemand/usermaintenance/login";

private static final String GET_URL = "http://localhost:8080/CdiOnDemand/userproject/getProjects";

//private static final String POST_URL = "http://localhost:8080/CdiOnDemand/usermaintenance/login";

private static final String POST_URL = "http://localhost:8080/CdiOnDemand/userproject/getProjects";

public static void main(String[] args) throws IOException {
    sendGET();
    System.out.println("GET DONE");
	
	
  //  sendPOST();
  //  System.out.println("POST DONE");
}

private static void sendGET() throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    
    
    
    
    
   
    
    
    
    /*List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    nvps.add(new BasicNameValuePair("userId", "0"));*/
    
    HttpGet httpGet = new HttpGet(GET_URL);
    httpGet.addHeader("User-Agent", USER_AGENT);
    
    
 //   httpGet.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
    
    CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

    System.out.println("GET Response Status:: "
            + httpResponse.getStatusLine().getStatusCode());

    BufferedReader reader = new BufferedReader(new InputStreamReader(
            httpResponse.getEntity().getContent()));

    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = reader.readLine()) != null) {
        response.append(inputLine);
    }
    reader.close();

    // print result
    System.out.println(response.toString());
    httpClient.close();
}






private static void sendPOST() throws IOException {

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(POST_URL);
   // httpPost.addHeader("User-Agent", USER_AGENT);
    
    httpPost.addHeader("Content-Type", "application/json");
    
    ObjectMapper mapper = new ObjectMapper();
    
   /* UserDTO userDto = new UserDTO();
    
    userDto.setEmail("cdfsdfaust1@xyz.com");
    userDto.setPassword("cust");
    
    String jsonInString = mapper.writeValueAsString(userDto);

    System.out.println(jsonInString);*/
    
  // List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
   // urlParameters.add(new BasicNameValuePair("userId", "01"));

   // httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
    
    
    httpPost.setEntity(new StringEntity("{\"userId\":\"00000000\"}"));
    //httpPost.setEntity(new StringEntity(jsonInString));

    CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

    System.out.println("POST Response Status:: "
            + httpResponse.getStatusLine().getStatusCode());

    BufferedReader reader = new BufferedReader(new InputStreamReader(
            httpResponse.getEntity().getContent()));

    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = reader.readLine()) != null) {
        response.append(inputLine);
    }
    reader.close();

    // print result
    System.out.println(response.toString());
    httpClient.close();

}}