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
    static boolean isDayTime;
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
        vec.add(response.getBody().getArray().getJSONObject(0).get("IsDayTime").toString());
        if (new Boolean(response.getBody().getArray().getJSONObject(0).get("IsDayTime").toString())){
            isDayTime = true;
        }else{
            isDayTime = false;
        }
        return vec;
    }

    public static double calculateDrivability(double actualTemp, int weather){
        double tempVal;
        Vector<Double> weatherVal = new Vector<>();

        weatherVal.add(50.0);
        weatherVal.add(75.0);
        weatherVal.add(75.0);
        weatherVal.add(100.0);
        weatherVal.add(87.5);
        weatherVal.add(87.5);
        weatherVal.add(100.0);
        weatherVal.add(100.0);
        weatherVal.add(0.0);
        weatherVal.add(0.0);
        weatherVal.add(100.0);
        weatherVal.add(100.0);
        weatherVal.add(25.0);
        weatherVal.add(37.5);
        weatherVal.add(37.5);
        weatherVal.add(25.0);
        weatherVal.add(62.5);
        weatherVal.add(100.0);
        weatherVal.add(100.0);
        weatherVal.add(100.0);
        weatherVal.add(37.5);
        weatherVal.add(37.5);
        weatherVal.add(0.0);
        weatherVal.add(0.0);
        weatherVal.add(100.0);
        weatherVal.add(62.5);
        weatherVal.add(100.0);
        weatherVal.add(50.0);
        weatherVal.add(12.5);
        weatherVal.add(12.5);
        weatherVal.add(25.0);
        weatherVal.add(25.0);

        System.out.println(new Double(weatherVal.elementAt(weather)).toString());
        double tot = new Double(weatherVal.elementAt(weather).toString());
        if (!isDayTime){
            tot /= 1.5;
        }
        return (tot);
    }

    public static double calculatePlayability(double actualTemp, int weather){
        double tempVal;
        Vector<Double> weatherVal = new Vector<>();
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
        double x = new Double(weatherVal.elementAt(weather));
        tempVal = actualTemp-60;
        if (tempVal < 0){
            tempVal = -tempVal;
        }
        tempVal = 60 - tempVal;
        x *= .25;
        if (tempVal < 0){
            tempVal = -tempVal;
        }
        int tot = 0;
        if (isDayTime){
            tot = (int) ((tempVal) + (x))-15;
        }else{
            tot = (int) ((tempVal) + (x))-15;
            tot/=2;
        }
        return (tot);
    }

    public static void sendTextMessage(String phoneNumber, String text){
        String account_sid = "AC365c1ffc42023d5e0c12aad4332a7e03";
        String account_auth = "5a6e893472adfb128416ab51838d6eb2";
        Twilio.init(account_sid,account_auth);
        Message message = Message.creator(new com.twilio.type.PhoneNumber(phoneNumber), new com.twilio.type.PhoneNumber("+12014821986"), text).create();
        System.out.println(message.getSid());
    }

}
