import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.util.Duration;

public class BottomFields extends VBox {
	public static final int SPACE2 = 2;
	private Button addButton;
	private Button deleteButton;
	private Button clearButton;
	private String textPromt = "Afeka Instrument Music Store $$$ ON SALE!!! $$$ Guitars, Basses, Flutes, Saxophones, and more!";
	private Text movingPromt = new Text(LocalDate.now() + " " + LocalTime.now() + " " + textPromt);
	private PathTransition pathTransition = new PathTransition();
	private Timeline timeline;

	public BottomFields() {
		addButton = new Button("Add");
		deleteButton = new Button("Delete");
		clearButton = new Button("Clear");
		this.setPadding(new Insets(AddInstrumentWindow.NONOFFSET, SPACE2, AddInstrumentWindow.SPACE50, SPACE2));
		this.setSpacing(AddInstrumentWindow.SPACE20);
		painButtonFields();
		setupMovingText();
	}

	private void painButtonFields() {
		HBox hbox = new HBox();
		hbox.setSpacing(15);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(getAddButton(), getDeleteButton(), getClearButton());
		this.getChildren().add(hbox);
	}

	private void setupMovingText() {
		final int X_START = 100, X_END = 400, DURATION = 10;
		movingPromt.setTextOrigin(VPos.CENTER);
		movingPromt.setTextAlignment(TextAlignment.CENTER);
		Line line = new Line(X_START, getHeight() / 2, X_END, getHeight() / 2);
		this.getChildren().add(movingPromt);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setDuration(Duration.seconds(DURATION));
		pathTransition.setCycleCount(Animation.INDEFINITE);
		pathTransition.setNode(movingPromt);
		pathTransition.setPath(line);
		pathTransition.setAutoReverse(true);
		pathTransition.play();
		movingPromt.setOnMouseEntered(e -> {
			pathTransition.pause();
		});
		movingPromt.setOnMouseExited(e -> {
			pathTransition.play();
		});
		movingPromt.setTextAlignment(TextAlignment.CENTER);
		movingPromt.setFill(Color.BLACK);
		movingPromt.setStroke(Color.RED);
		setupTimeUpDating();

	}

	private void setupTimeUpDating() {
		timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> nextSecond()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	private void nextSecond() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		movingPromt.setText(LocalDate.now() + " " + LocalTime.now().format(dateTimeFormatter) + " " + textPromt);
	}

	public Button getAddButton() {
		return addButton;
	}

	public void setAddButton(Button add) {
		this.addButton = add;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button delete) {
		this.deleteButton = delete;
	}

	public Button getClearButton() {
		return clearButton;
	}

	public void setClearButton(Button clear) {
		this.clearButton = clear;
	}
}
