/**
 *
 * @Authors: Connor Zold, Collin Tice, Sarah Lengel
 */
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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.awt.*;
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
    //add weather image
    Image weatherImage;
    ImageView weatherImageView = new ImageView(weatherImage);

    Label timeLabel = new Label("");
    Label tempLabel = new Label("");
    Label weatherLabel = new Label("");
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

        driveability_label = new Label("Driving Safety Score based on weather conditions: ");
        driveability_label.setFont(new Font("Arial",15));

        outside_label = new Label("Outdoor Comfort Score based on weather conditions: ");
        outside_label.setFont(new Font("Arial",15));

        analyze_button.setOnAction(new analyze_button_handler());
        phone_number_data.setOnAction(new Phone_Number_Data_Handler());
        //create gridpane that includes ONLY the weather image for display

        weatherImageView.setFitWidth( 125 );
        weatherImageView.setPreserveRatio(true);
        weatherImageGrid.add(weatherImageView, 0, 0 );
        VBox vBox = new VBox(weather_label,timeLabel,weatherLabel,tempLabel);
        HBox hBox = new HBox(vBox, weatherImageGrid);


        VBox driveability = new VBox(10, driveability_label, drive_score);
        driveability.setAlignment(Pos.TOP_CENTER);

        VBox outdoors = new VBox(outside_label, outside_score);
        outdoors.setAlignment(Pos.TOP_CENTER);
        VBox outdoorsTotal = new VBox(-6,outdoors,playScoreLabel);
        playScoreLabel.setAlignment(Pos.TOP_CENTER);
        outdoorsTotal.setAlignment(Pos.TOP_CENTER);
        // --------------------- Weather Updates ---------------------

        alert_label = new Label("If you would like to receive weather updates via text, " +
                "\nenter your phone number and then click the button below.");
        alert_label.setFont(new Font("Arial",15));
        enter_phone_number = new TextField("");
        //HBox emails = new HBox(10, email, email_checked, enter_email);
        HBox phone = new HBox(10, enter_phone_number, phone_number_data);
        phone.setAlignment(Pos.CENTER);
        VBox alerts = new VBox(10, alert_label, phone);

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
        {
            String s;
            try {
                s = location_entry.getText() + "\n";
                s += weather_label.getText() + "on \n";
                s += timeLabel.getText().substring(0, 11) + "at " + timeLabel.getText().substring(11, timeLabel.getText().length()) + ":\n";
                s += weatherLabel.getText() + "\n";
                s += tempLabel.getText() + "\n";
                s += "Driving Safety Score: " + drive_score.getText() + "\n";
                s += "Outdoor Comfort Score: " + playScoreLabel.getText();
            }catch(java.lang.StringIndexOutOfBoundsException e){

                s = "State College" + "\n";
                s += weather_label.getText() + "on \n";
                s += timeLabel.getText().substring(0, 11) + "at " + timeLabel.getText().substring(11, timeLabel.getText().length()) + ":\n";
                s += weatherLabel.getText() + "\n";
                s += tempLabel.getText() + "\n";
                s += "Driving Safety Score: " + drive_score.getText() + "\n";
                s += "Outdoor Comfort Score: " + playScoreLabel.getText();
            }
            String phoneNumber = enter_phone_number.getText();
            try {
                if (phoneNumber.substring(0, 2).equals("+1")) {
                    phoneNumber = phoneNumber.substring(2);
                }
                if (phoneNumber.substring(2, 3).equals("-")) {
                    for (int i = 1; i < phoneNumber.length(); i++) {
                        if (!Character.isLetter(phoneNumber.charAt(i))) {
                            phoneNumber = phoneNumber.substring(0, i) + phoneNumber.substring(i, phoneNumber.length());
                        }
                    }
                }
            }catch(java.lang.StringIndexOutOfBoundsException e){
                //
            }
            if (enter_phone_number.getText().isEmpty()){
                GeicoWeatherAPI.sendTextMessage("+14129999653",s);
            }else {
                GeicoWeatherAPI.sendTextMessage("+1" + phoneNumber, s.toString());
            }
        }
    }

    class analyze_button_handler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            weatherData = new Vector<String>();
            weatherImageGrid.getChildren().clear();
            if (location_entry.getText().equals("")){
                weatherData = GeicoWeatherAPI.collectWeatherData("State College");
            }else {
                weatherData = GeicoWeatherAPI.collectWeatherData(location_entry.getText());
            }
            if (new Boolean(weatherData.elementAt(4))){
                timeLabel.setText(weatherData.elementAt(0).substring(0,10) + " " +  weatherData.elementAt(0).substring(12,16) + "PM");
            }else{
                timeLabel.setText(weatherData.elementAt(0).substring(0,10) + " " +  weatherData.elementAt(0).substring(12,16) + "AM");
            }
            tempLabel.setText(weatherData.elementAt(2) + " F");
            weatherLabel.setText(weatherData.elementAt(1));
            int zx = new Integer(weatherData.elementAt(3));
            if (zx > 8){
                zx += 3;
            }
            if (zx > 26){
                zx += 3;
            }
            int num = new Integer(zx);
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
            playScore = GeicoWeatherAPI.calculatePlayability(new Double(weatherData.elementAt(2)),zx);
            playScoreLabel.setText(new Integer((int) playScore).toString());
            driveability = GeicoWeatherAPI.calculateDrivability(new Double(weatherData.elementAt(2)),zx);
            drive_score.setText(new Integer((int) driveability).toString());
            drive_score.setFont(new Font("Arial Bold", 25));
            playScoreLabel.setFont(new Font("Arial Bold", 25));
            if (new Integer(drive_score.getText()) <= 33){
                drive_score.setTextFill(Color.web("#b50c00"));
            }else if (new Integer(drive_score.getText()) <= 66){
                drive_score.setTextFill(Color.web("#c4b616"));
            }else{
                drive_score.setTextFill(Color.web("#1d7d0e"));
            }
            if (new Integer(playScoreLabel.getText()) <= 33){
                playScoreLabel.setTextFill(Color.web("#b50c00"));
            }else if (new Integer(playScoreLabel.getText()) <= 66){
                playScoreLabel.setTextFill(Color.web("#c4b616"));
            }else{
                playScoreLabel.setTextFill(Color.web("#1d7d0e"));
            }
        }
    }
}