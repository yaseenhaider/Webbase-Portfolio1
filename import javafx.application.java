import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class BikeGame extends Application {

    private static final double BIKE_SPEED = 2.0;
    private static final double BIKE_ROTATION_SPEED = 5.0;

    private Box bike;
    private Group root;
    private PerspectiveCamera camera;
    private Translate cameraTranslate = new Translate(0, -50, -200);
    private Rotate cameraRotateX = new Rotate(-20, Rotate.X_AXIS);
    private Rotate cameraRotateY = new Rotate(0, Rotate.Y_AXIS);

    /**
     * Initializes and sets up the JavaFX application window and scene.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Create the root group
        root = new Group();

        // Create the bike (represented by a box for simplicity)
        bike = new Box(20, 10, 40);
        bike.setMaterial(new PhongMaterial(Color.RED));
        bike.setTranslateY(-10); // Position the bike slightly above the ground

        // Add the bike to the scene
        root.getChildren().add(bike);

        // Set up the camera
        camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll(cameraTranslate, cameraRotateX, cameraRotateY);

        // Create the scene
        Scene scene = new Scene(root, 800, 600, true);
        scene.setCamera(camera);
        scene.setFill(Color.LIGHTBLUE);

        // Handle keyboard input
        scene.setOnKeyPressed(this::handleKeyPress);

        // Set up the stage
        primaryStage.setTitle("3D Bike Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                bike.setTranslateZ(bike.getTranslateZ() + BIKE_SPEED);
                break;
            case DOWN:
                bike.setTranslateZ(bike.getTranslateZ() - BIKE_SPEED);
                break;
            case LEFT:
                bike.setRotate(bike.getRotate() - BIKE_ROTATION_SPEED);
                break;
            case RIGHT:
                bike.setRotate(bike.getRotate() + BIKE_ROTATION_SPEED);
                break;
            case W:
                cameraTranslate.setZ(cameraTranslate.getZ() + BIKE_SPEED);
                break;
            case S:
                cameraTranslate.setZ(cameraTranslate.getZ() - BIKE_SPEED);
                break;
            case A:
                cameraRotateY.setAngle(cameraRotateY.getAngle() - BIKE_ROTATION_SPEED);
                break;
            case D:
                cameraRotateY.setAngle(cameraRotateY.getAngle() + BIKE_ROTATION_SPEED);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}