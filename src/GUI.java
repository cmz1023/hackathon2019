import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application
{
    Label weather_label = new Label();
    Label weather_conditions = new Label();

    Label driveability_label = new Label();
    Label drive_score = new Label();

    Label outside_label = new Label();
    Label outside_score = new Label();

    Label alert_settings = new Label();
    CheckBox email = new CheckBox();
    CheckBox text = new CheckBox();
    CheckBox call = new CheckBox();

    TextField enter_email = new TextField();
    TextField enter_phone_number = new TextField();

    Button data = new Button("Receive weather updates");

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        weather_label = new Label("Current weather conditions: ");
        weather_label.setFont(new Font("Times New Roman",15));
        //Label weather_conditions = new Label();

        driveability_label = new Label("Driveability score based on weather conditions: ");
        driveability_label.setFont(new Font("Times New Roman",15));
        //Label drive_score = new Label();

        outside_label = new Label("Score for outdoor activities based on weather conditions: ");
        outside_label.setFont(new Font("Times New Roman",15));

        VBox weather = new VBox(100, weather_label, weather_conditions);

        VBox driveability = new VBox(100, driveability_label, drive_score);

        VBox outdoors = new VBox(10, outside_label, outside_score);


        // --------------------- Weather Updates ---------------------

        alert_settings = new Label("Please select a method to receive weather updates: ");
        alert_settings.setFont(new Font("Times New Roman",15));
        email = new CheckBox("Email");
        text = new CheckBox("Texts");
        call = new CheckBox("Calls");

        enter_email = new TextField("Enter email");
        enter_phone_number = new TextField("Enter phone number");

        data = new Button("Receive weather updates");
        data.setOnAction( new dataHandler() );

        HBox emails = new HBox(10, email, enter_email);
        HBox calls = new HBox(10, call, enter_phone_number);
        VBox alerts = new VBox(10, alert_settings, emails, text, calls, data);


        GridPane grid = new GridPane();
        grid.add(weather, 0,0,1,1);
        grid.add(driveability,1,0,1,1);
        grid.add(outdoors,0,1,1,1);
        grid.add(alerts,1,1,1,1);

        //grid.setGridLinesVisible(true);
        grid.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #000000;");

        weather.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #000000;");

        driveability.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #000000;");

        outdoors.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #000000;");

        alerts.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #000000;");

        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);


        Scene scene = new Scene( grid,800,400);
        primaryStage.setScene( scene );

        primaryStage.show();
    }


    private class dataHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event)
        {

            if( email.isIndeterminate() )
            {

            }

            else if ( text.isSelected() )
            {

            }

            else if ( call.isSelected() )
            {

            }

        }
    }
}
