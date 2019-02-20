package com.nwm.filter;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Annotation identifies REST web service
@RestController
public class GetAPI {

    // Maps HTTP requests to /api
    @RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getAPI() {

        JSONObject jsonObject = new JSONObject();

        // Iterate through 20 pages of API requested data
        for (int i = 1; i < 21; i++) {
            String url = "https://rickandmortyapi.com/api/character/?page=" + i;
            HttpResponse<JsonNode> node = null;

            // Making the GET call to the API and receiving response as JSON
            try {
                node = Unirest.get(url).asJson();
            } catch (UnirestException e) {
                e.printStackTrace();
            }

            // Storing JSON Array found within the JSON Object
            JSONArray jsonArray = node.getBody().getObject().getJSONArray("results");

            // Loop through JSON Array and append data to a JSON Object
            for (int j = 0; j < jsonArray.length(); j++) {
                jsonObject.append("results", jsonArray.get(j));
            }
        }

        // JSON Object response is sent back
        return jsonObject.toString();
    }
}