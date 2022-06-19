
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.*;

public class Hotels {
    static neededData data = new neededData();
    static Helper helper = new Helper();
    static String placeId, country, city;

    @Test(priority = 1)
    public static void hotelsSearchValidScenario() {
        HashMap<String, String> searchData = data.getTravelInfo();
        country = searchData.get("country");
        city = searchData.get("city");
        RestAssured.baseURI = data.getUri();
        RequestSpecification request = RestAssured.given();
        request.header("token", neededData.getToken());
        request.queryParam("query", city);

        Response response = request.get("/api/enigma/autocomplete");

        placeId = response.getBody().jsonPath().getString("locations[0].placeId");
        data.setPlaceId(placeId);
        Assert.assertEquals(response.getStatusCode(), 200);
        //Validate city
        Assert.assertEquals(response.getBody().jsonPath().getString("locations[0].name"), city);
        //Validate country
        Assert.assertEquals(response.getBody().jsonPath().getString("locations[0].country"), country);


    }

    @Test(priority = 2)
    public static void asyncValidScenario() {

        RestAssured.baseURI = data.getUri();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json;charset=UTF-8");
        request.header("token", data.getToken());

        JSONObject requestParams = new JSONObject();
        requestParams.put("checkIn", helper.getCheckInDate());
        requestParams.put("checkOut", helper.getCheckOutDate());
        requestParams.put("placeId", data.getPlaceId());
        requestParams.put("roomsInfo", data.getRoomsInfo(2, 3));

        request.body(requestParams.toJSONString());
        Response response = request.post("/api/enigma/search/async");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(!response.getBody().jsonPath().getString("sId").isEmpty());

    }

    @Test(priority = 3)
    public static void hotelsSearchInvalidScenario() {
        HashMap<String, String> searchData = data.getTravelInfo();
        country = searchData.get("country");
        city = searchData.get("city");
        RestAssured.baseURI = data.getUri();
        RequestSpecification request = RestAssured.given();
        request.header("token", neededData.getToken());
        request.queryParam("query", "");

        Response response = request.get("/api/enigma/autocomplete");

        data.setPlaceId("sending wrong value");

        //Verify Status Code
        Assert.assertEquals(response.getStatusCode(), 400);
        //Validate errorType
        Assert.assertEquals(response.getBody().jsonPath().getString("errorType"),"Validation error");


    }

    //Passing Wrong value from hotelsSearchInvalidScenario()
    @Test(priority = 4)
    public static void asyncInvalidScenario() {

        RestAssured.baseURI = data.getUri();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json;charset=UTF-8");
        request.header("token", data.getToken());

        JSONObject requestParams = new JSONObject();
        requestParams.put("checkIn", helper.getCheckInDate());
        requestParams.put("checkOut", helper.getCheckOutDate());
        requestParams.put("placeId", data.getPlaceId());
        requestParams.put("roomsInfo", data.getRoomsInfo(2, 3));

        request.body(requestParams.toJSONString());
        Response response = request.post("/api/enigma/search/async");

        //Verify Status Code
        Assert.assertEquals(response.getStatusCode(), 400);
        //Validate errorType
        Assert.assertEquals(response.getBody().jsonPath().getString("errorType"),"Validation error");

    }

}