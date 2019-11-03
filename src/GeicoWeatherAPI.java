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

        Vector<String> weatherData = collectWeatherData("State College");
    }
    public static Vector<String>  collectWeatherData(String city){
        final HttpResponse<JsonNode> stresponse = Unirest.get("http://dataservice.accuweather.com/locations/v1/search")
                .queryString("apikey","07vMDheK0m6yvGnpXEpnqvkVZhyCFxo2")
                .queryString("q",city)
                .asJson();
        String v = stresponse.getBody().getArray().getJSONObject(0).get("Key").toString();
        System.out.println(v);
        final HttpResponse<JsonNode> response = Unirest.get("http://dataservice.accuweather.com/currentconditions/v1/" + v)
                .queryString("apikey","07vMDheK0m6yvGnpXEpnqvkVZhyCFxo2")
                .queryString("details","true")
                .asJson();
        System.out.println(response.getBody());
        Vector<String> vec = new Vector<>();
        vec.add(response.getBody().getArray().getJSONObject(0).get("LocalObservationDateTime").toString()); //Time weather was checked
        vec.add(response.getBody().getArray().getJSONObject(0).get("WeatherText").toString()); //Weather outside (clear, raining, etc..)
        vec.add(response.getBody().getArray().getJSONObject(0).getJSONObject("Temperature").getJSONObject("Imperial").get("Value").toString());
        for (String x : vec){
            System.out.println(x);
        }
        return vec;
    }

    public static int calculateDrivability(){

        return 0;
    }

    public static int calculatePlayability(){

        return 0;
    }

    public static void sendTextMessage(String phoneNumber){

    }

    public static void sendEmail(String recipientEmail){

    }

    public static void displayInformation(){

    }
}
