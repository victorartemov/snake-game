package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Виктор on 23.10.2016.
 */
public class Controller {
    @FXML
    private AnchorPane gamePart;

    @FXML
    private AnchorPane controlPart;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    public AnchorPane getGamePart() {
        return gamePart;
    }

    public AnchorPane getControlPart() {
        return controlPart;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    @FXML
    protected void startButtonClicked(){

    }

    @FXML
    protected void stopButtonClicked(){

    }

}
