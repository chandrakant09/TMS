package com.lei.security.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lei.dto.user.UserDTO;
import com.lei.dto.user.UserStatusDto;



public class TestWS {   private static final String USER_AGENT = "Mozilla/5.0";

//private static final String GET_URL = "http://localhost:8080/CdiOnDemand/usermaintenance/login";



private static final String GET_URL = "http://localhost:8080/CdiOnDemand/reportsService/getReports";
//private static final String GET_URL = "http://localhost:8080/CdiOnDemand/reportsService/getReportsData";


//private static final String GET_URL = "http://localhost:8080/CdiOnDemand/projectservice/getProjectConfig";

private static final String POST_URL = "http://localhost:8080/CdiOnDemand/usermaintenance/login";

//private static final String POST_URL = "http://localhost:8080/CdiOnDemand/userproject/getProjects";

public static void main(String[] args) throws IOException {
    sendGET();
    System.out.println("GET DONE");
	
	
 //   sendPOST();
//    System.out.println("POST DONE");
}








private static void sendGET() throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    
    
    
    
    
   
    
    
    
    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    nvps.add(new BasicNameValuePair("projectId", "109"));
    
    HttpGet httpGet = new HttpGet(GET_URL);
    httpGet.addHeader("User-Agent", USER_AGENT);
    
    
   // httpGet.setURI(uri)
    //;setEntity(new UrlEncodedFormEntity(nvps));
    
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
    
    UserDTO userDto = new UserDTO();
    UserStatusDto status = new UserStatusDto();
    
    status.setPassword("d9adfsdf6p9");
    
    userDto.setEmail("shriikant.kushwaha@xoriant.com");
    //userDto.setPassword("d9a6p9");
    userDto.setStatus(status);
    
    String jsonInString = mapper.writeValueAsString(userDto);

    System.out.println(jsonInString);
    
  // List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
   // urlParameters.add(new BasicNameValuePair("userId", "01"));

   // httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
//    httpPost.setEntity(new StringEntity("{\"userId\":\"00000000\"}"));
    
    
    httpPost.setEntity(new StringEntity(jsonInString));

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