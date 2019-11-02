import jdk.nashorn.internal.parser.JSONParser;
import kong.unirest.*;
import kong.unirest.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
//07vMDheK0m6yvGnpXEpnqvkVZhyCFxo2
//^api key
//text search: http://dataservice.accuweather.com/locations/v1/search
//current conditions (GET): http://dataservice.accuweather.com/currentconditions/v1/07vMDheK0m6yvGnpXEpnqvkVZhyCFxo2


public class GeicoWeatherAPI {
    public static void main(String[] args) throws Exception{
        String city = "State College";
        String s2;
        final HttpResponse<JsonNode> stresponse = Unirest.get("http://dataservice.accuweather.com/locations/v1/search")
                .queryString("apikey","07vMDheK0m6yvGnpXEpnqvkVZhyCFxo2")
                .queryString("q",city)
                .asJson();
        String v = stresponse.getBody().getArray().getJSONObject(0).get("Key").toString();
        System.out.println(v);
        final HttpResponse<String> response = Unirest.get("http://dataservice.accuweather.com/currentconditions/v1/" + v)
                .queryString("apikey","07vMDheK0m6yvGnpXEpnqvkVZhyCFxo2")
                .asString();

        System.out.println(response.getBody());







        /**
        String city = "State College";
        HttpResponse<JsonNode> response = Unirest.get("http://dataservice.accuweather.com/locations/v1/cities/search")
                .queryString("apikey","07vMDheK0m6yvGnpXEpnqvkVZhyCFxo2")
                .queryString(city,"q")
                .asJson();
        JSONObject obj = response.getBody().getObject();
        System.out.println(obj.toString());
        //String ans = obj.getString("");
        //System.out.println(ans);
        **/



    }
}

