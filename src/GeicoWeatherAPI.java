import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.TwiMLException;

public class GeicoWeatherAPI {
    public static void main(String[] args) throws Exception{
        //sendTextMessage("+14129999653","hi mom i sent this from my hackpsu project don't respond to this number lol");

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
        vec.add(response.getBody().getArray().getJSONObject(0).get("WeatherIcon").toString());
        int num = new Integer(response.getBody().getArray().getJSONObject(0).get("WeatherIcon").toString());
        Image image;
        if (num < 10){
            image = new Image("https://developer.accuweather.com/sites/default/files/0" + num + "-s.png");
        }else{
            image = new Image("https://developer.accuweather.com/sites/default/files/" + num + "-s.png");
        }
        ImageView imageView = new ImageView(image);
        System.out.println("6");
        for (String x : vec){
            System.out.println(x);
        }
        return vec;
    }

    public static double calculateDrivability(double actualTemp, int weather){
        double tempVal;
        Vector<Double> weatherVal = new Vector<>();
        weatherVal.add(20.0);
        weatherVal.add(30.0);
        weatherVal.add(30.0);
        weatherVal.add(40.0);
        weatherVal.add(25.0);
        weatherVal.add(35.0);
        weatherVal.add(40.0);
        weatherVal.add(40.0);
        weatherVal.add(0.0);
        weatherVal.add(0.0);
        weatherVal.add(40.0);
        weatherVal.add(40.0);
        weatherVal.add(10.0);
        weatherVal.add(15.0);
        weatherVal.add(15.0);
        weatherVal.add(10.0);
        weatherVal.add(25.0);
        weatherVal.add(40.0);
        weatherVal.add(40.0);
        weatherVal.add(40.0);
        weatherVal.add(15.0);
        weatherVal.add(15.0);
        weatherVal.add(0.0);
        weatherVal.add(0.0);
        weatherVal.add(40.0);
        weatherVal.add(25.0);
        weatherVal.add(40.0);
        weatherVal.add(20.0);
        weatherVal.add(5.0);
        weatherVal.add(5.0);
        weatherVal.add(10.0);
        weatherVal.add(10.0);

        tempVal = ((68 - actualTemp)/68);
        tempVal *= 20;
        return (tempVal + weatherVal.elementAt(weather));
    }

    public static double calculatePlayability(double actualTemp, int weather){
        double tempVal;
        Vector<Double> weatherVal = new Vector<>();
        tempVal = ((68 - actualTemp)/68);
        tempVal *= 40;
        weatherVal.add(40.0);
        weatherVal.add(40.0);
        weatherVal.add(40.0);
        weatherVal.add(30.0);
        weatherVal.add(30.0);
        weatherVal.add(25.0);
        weatherVal.add(20.0);
        weatherVal.add(20.0);
        weatherVal.add(0.0);
        weatherVal.add(0.0);
        weatherVal.add(10.0);
        weatherVal.add(0.0);
        weatherVal.add(20.0);
        weatherVal.add(5.0);
        weatherVal.add(0.0);
        weatherVal.add(10.0);
        weatherVal.add(30.0);
        weatherVal.add(40.0);
        weatherVal.add(40.0);
        weatherVal.add(40.0);
        weatherVal.add(5.0);
        weatherVal.add(5.0);
        weatherVal.add(0.0);
        weatherVal.add(0.0);
        weatherVal.add(30.0);
        weatherVal.add(5.0);
        weatherVal.add(20.0);
        weatherVal.add(5.0);
        weatherVal.add(0.0);
        weatherVal.add(0.0);
        weatherVal.add(5.0);
        weatherVal.add(5.0);

        return weatherVal.elementAt(weather) + tempVal;
    }

    public static void sendTextMessage(String phoneNumber, String text){
        String account_sid = "AC365c1ffc42023d5e0c12aad4332a7e03";
        String account_auth = "5a6e893472adfb128416ab51838d6eb2";
        Twilio.init(account_sid,account_auth);
        Message message = Message.creator(new com.twilio.type.PhoneNumber(phoneNumber), new com.twilio.type.PhoneNumber("+12014821986"), text).create();
        System.out.println(message.getSid());
    }

    public static void displayInformation(){

    }
}
