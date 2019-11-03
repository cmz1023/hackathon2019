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

public class GUI extends Application
{
    Label location = new Label();
    TextField location_entry = new TextField();
    Button analyze = new Button();

    // Weather condition labels
    Label weather_label = new Label();
    Label weather_conditions = new Label();
    //add weather image
    Image weatherImage = new Image("http://s3.amazonaws.com/Summitsoft-Digital-Assets/websiteanimator/sun_469.png");
    ImageView weatherImageView = new ImageView(weatherImage);


    //Driveability labels
    Label driveability_label = new Label();
    Label drive_score = new Label();

    //Outside labels
    Label outside_label = new Label();
    Label outside_score = new Label();

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

        //create gridpane that includes ONLY the weather image for display
        GridPane weatherImageGrid = new GridPane();
        weatherImageView.setFitWidth( 125 );
        weatherImageView.setPreserveRatio(true);
        weatherImageGrid.add(weatherImageView, 0, 0 );

        HBox hWeather = new HBox(100, weather_label, weatherImageGrid);
        hWeather.setAlignment(Pos.TOP_RIGHT);
        VBox vWeather = new VBox(100, hWeather, weather_conditions );

        VBox driveability = new VBox(100, driveability_label, drive_score);

        VBox outdoors = new VBox(10, outside_label, outside_score);


        // --------------------- Weather Updates ---------------------

        alert_label = new Label("If you would like to receive weather updates via text, " +
                                    "\nenter your phone number and then click the button below.");
        alert_label.setFont(new Font("Arial",15));
        enter_phone_number = new TextField("");

        //Button to send user input to Twilio API
        phone_number_data = new Button("Receive Weather Updates");
        phone_number_data.setOnAction( new Phone_Number_Data_Handler() );

        //HBox emails = new HBox(10, email, email_checked, enter_email);
        HBox phone = new HBox(10, enter_phone_number);
        VBox alerts = new VBox(10, alert_label, phone, phone_number_data);

        // -------------- Enter Location label and VBox ----------------

        location = new Label("Show weather analytics in: ");
        location.setFont(new Font("Arial",15));
        analyze = new Button("Analyze");
        HBox location_field =  new HBox(10, location, location_entry, analyze);
        location_field.setAlignment(Pos.CENTER);

        // --------------- Grid Declaration ------------------------
        GridPane grid = new GridPane();
        grid.add(location_field, 0,0,1,1);
        grid.add(vWeather, 0,1,1,1);
        grid.add(driveability,1,1,1,1);
        grid.add(outdoors,0,2,1,1);
        grid.add(alerts,1,2,1,1);
        grid.setAlignment(Pos.CENTER);

        // ------------------ Add borders to each cell --------------
        grid.setStyle("-fx-padding: 10;" +
                      "-fx-background-color: #dde8eb");

        vWeather.setStyle("-fx-padding: 10;" +
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

        outdoors.setStyle("-fx-padding: 10;" +
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

    private class Phone_Number_Data_Handler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {

        }
    }
}
