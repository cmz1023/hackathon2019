import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Vector;

public class GUI extends Application
{
    double driveability;
    double playScore;
    Vector<String> weatherData = new Vector<>();
    Label location = new Label();
    TextField location_entry = new TextField();
    Button analyze_button = new Button("Analyze");

    // Weather condition labels
    Label weather_label = new Label();
    //Label weather_conditions = new Label();
    //add weather image
    Image weatherImage;
    ImageView weatherImageView = new ImageView(weatherImage);

    Label timeLabel = new Label("time");
    Label tempLabel = new Label("temperature");
    Label weatherLabel = new Label("weather");
    Label playScoreLabel = new Label();
    Label driveAbilityLabel = new Label();

    //Driveability labels
    Label driveability_label = new Label();
    Label drive_score = new Label();

    //Outside labels
    Label outside_label = new Label();
    Label outside_score = new Label();

    GridPane weatherImageGrid = new GridPane();
    GridPane grid = new GridPane();
    //Weather alerts label, text field, and button
    Label alert_label = new Label();
    TextField enter_phone_number = new TextField();
    Button phone_number_data = new Button("Receive weather updates");

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // ------------------ Weather Labels and Boxes --------------------
        weather_label = new Label("Current weather conditions: ");
        weather_label.setFont(new Font("Arial",15));

        driveability_label = new Label("Driveability score based on weather conditions: ");
        driveability_label.setFont(new Font("Arial",15));

        outside_label = new Label("Score for outdoor activities based on weather conditions: ");
        outside_label.setFont(new Font("Arial",15));

        analyze_button.setOnAction(new analyze_button_handler());
        phone_number_data.setOnAction(new Phone_Number_Data_Handler());
        //create gridpane that includes ONLY the weather image for display

        weatherImageView.setFitWidth( 125 );
        weatherImageView.setPreserveRatio(true);
        weatherImageGrid.add(weatherImageView, 0, 0 );
        VBox vBox = new VBox(weather_label,weatherLabel,timeLabel,tempLabel);
        HBox hBox = new HBox(vBox, weatherImageGrid);


        VBox driveability = new VBox(10, driveability_label, drive_score);
        driveability.setAlignment(Pos.TOP_CENTER);

        VBox outdoors = new VBox(10, outside_label, outside_score);
        VBox outdoorsTotal = new VBox(outdoors,playScoreLabel);
        outdoorsTotal.setAlignment(Pos.TOP_CENTER);
        // --------------------- Weather Updates ---------------------

        alert_label = new Label("If you would like to receive weather updates via text, " +
                "\nenter your phone number and then click the button below.");
        alert_label.setFont(new Font("Arial",15));
        enter_phone_number = new TextField("");

        //Button to send user input to Twilio API
        //phone_number_data = new Button("Receive Weather Updates");
        //phone_number_data.setOnAction( new Phone_Number_Data_Handler() );

        //HBox emails = new HBox(10, email, email_checked, enter_email);
        HBox phone = new HBox(10, enter_phone_number);
        VBox alerts = new VBox(10, alert_label, phone, phone_number_data);

        // -------------- Enter Location label and VBox ----------------

        location = new Label("Show weather analytics in: ");
        location.setFont(new Font("Arial",15));
        HBox location_field =  new HBox(10, location, location_entry, analyze_button);
        location_field.setAlignment(Pos.CENTER);

        // --------------- Grid Declaration ------------------------
        grid.add(location_field, 0,0,1,1);
        grid.add(hBox, 0,1,1,1);
        grid.add(driveability,1,1,1,1);
        grid.add(outdoorsTotal,0,2,1,1);
        grid.add(alerts,1,2,1,1);
        grid.setAlignment(Pos.CENTER);

        // ------------------ Add borders to each cell --------------
        grid.setStyle("-fx-padding: 10;" +
                "-fx-background-color: #dde8eb");

        hBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;" +
                "-fx-background-color: #ceeaf2;");

        driveability.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;" +
                "-fx-background-color: #ceeaf2;");

        outdoorsTotal.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;" +
                "-fx-background-color: #ceeaf2;");

        alerts.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;" +
                "-fx-background-color: #ceeaf2");

        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        Scene scene = new Scene( grid );
        primaryStage.setScene( scene );
        primaryStage.setTitle("Weather Analytics");
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch ( args );
    }

    class Phone_Number_Data_Handler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {//        VBox vBox = new VBox(weather_label,weatherLabel,timeLabel,tempLabel);

            String s = weather_label.getText() + "\n";
            s += weatherLabel.getText() + "\n";
            s += timeLabel.getText() + "\n";
            s += tempLabel.getText();
            if (enter_phone_number.getText().isEmpty()){
                GeicoWeatherAPI.sendTextMessage("+14129999653",s);
            }else {
                GeicoWeatherAPI.sendTextMessage("+1" + enter_phone_number.getText(), s.toString());
            }
        }
    }

    class analyze_button_handler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            weatherImageGrid.getChildren().clear();
            if (location_entry.getText().equals("")){
                weatherData = GeicoWeatherAPI.collectWeatherData("State College");
            }else {
                weatherData = GeicoWeatherAPI.collectWeatherData(location_entry.getText());
            }
            System.out.println(weatherData.elementAt(0).substring(0,10) + weatherData.elementAt(0).substring(12,17));
            if (new Boolean(weatherData.elementAt(4))){
                timeLabel.setText(weatherData.elementAt(0).substring(0,10) + " " +  weatherData.elementAt(0).substring(12,16) + "PM");
            }else{
                timeLabel.setText(weatherData.elementAt(0).substring(0,10) + " " +  weatherData.elementAt(0).substring(12,16) + "AM");
            }
            tempLabel.setText(weatherData.elementAt(2) + " F");
            weatherLabel.setText(weatherData.elementAt(1));

            int num = new Integer(weatherData.elementAt(3));
            Image image;
            if (num < 10){
                image = new Image("https://developer.accuweather.com/sites/default/files/0" + num + "-s.png");
            }else{
                image = new Image("https://developer.accuweather.com/sites/default/files/" + num + "-s.png");
            }
            weatherImageView = new ImageView(image);
            weatherImageView.setFitWidth(100);
            weatherImageView.setPreserveRatio(true);
            weatherImageGrid.add(weatherImageView, 0, 0 );
            //weatherImageGrid.setPadding(Insets(15));
            //actual temp, int weather
            playScore = GeicoWeatherAPI.calculatePlayability(new Double(weatherData.elementAt(2)),new Integer(weatherData.elementAt(3))-3);
            playScoreLabel.setText(new Double(playScore).toString());
            //actual temp, int weather
            driveability = GeicoWeatherAPI.calculateDrivability(new Double(weatherData.elementAt(2)),new Integer(weatherData.elementAt(3))-3);
            drive_score.setText(new Double(driveability).toString());
        }
    }
}