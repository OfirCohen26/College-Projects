import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AfekaInstrumentGUI extends Application {
	public final int RIGHT = 1;
	public final int LEFT = -1;
	public final int STANDSTILL = 0;
	private Stage window;
	private Scene mainScene;
	private BorderPane mainLayout;
	private SearchField searchBar;
	private BottomFields BottomFields;
	private ShowInstrumentsGrid ShowInstrumentsGrid;
	private ArrayList<MusicalInstrument> allInstruments = new ArrayList<MusicalInstrument>();
	private ArrayList<MusicalInstrument> selectedInstruments = new ArrayList<MusicalInstrument>();
	private int index;

	public AfekaInstrumentGUI() {
		AfekaInstruments.loadInstrumentsFromFile(getInstrumentsFileFromUser(), allInstruments);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void init() {
		BottomFields = new BottomFields();
		searchBar = new SearchField();
		mainLayout = new BorderPane();
		ShowInstrumentsGrid = new ShowInstrumentsGrid();
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		init();
		window.setTitle("Afeka Instruments Music Store");
		mainLayout.setTop(searchBar);
		mainLayout.setCenter(ShowInstrumentsGrid);
		mainLayout.setBottom(BottomFields);
		mainLayout.setLeft(paneForLeftButton());
		mainLayout.setRight(paneForRightButton());
		mainLayout.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				HandleSearch();
			}
			if (e.getCode() == KeyCode.DELETE) {
				HandleDelete();
			}
			if (e.getCode() == KeyCode.A) {
				new AddInstrumentWindow(this).show();
			}
		});
		searchBar.getGoButton().setOnMouseClicked(e -> {
			HandleSearch();
		});
		BottomFields.getClearButton().setOnMouseClicked(e -> {
			HandlClear();
		});
		BottomFields.getDeleteButton().setOnMouseClicked(e -> {
			HandleDelete();
		});
		BottomFields.getAddButton().setOnMouseClicked(e -> {
			new AddInstrumentWindow(this).show();
		});
		selectedInstruments = new ArrayList<MusicalInstrument>(allInstruments);
		this.changeItem(0);
		mainScene = new Scene(mainLayout, 650, 300);
		window.setScene(mainScene);
		window.show();
	}

	public void addInstrument(MusicalInstrument inst) {
		allInstruments.add(inst);
		searchBar.searchInstrument(allInstruments, selectedInstruments);
		int foundIndex = selectedInstruments.indexOf(inst);
		if (foundIndex != -1) {
			changeItem(foundIndex);
		}
	}

	private void HandleDelete() {
		if (selectedInstruments.isEmpty())
			return;
		MusicalInstrument foundInst = selectedInstruments.get(index);
		selectedInstruments.remove(index);
		allInstruments.remove(foundInst);
		if (selectedInstruments.isEmpty()) {
			ShowInstrumentsGrid.clearTextFields();
			index = 0;
		} else {
			this.setLocation(STANDSTILL);
		}
	}

	private void HandleSearch() {
		searchBar.searchInstrument(allInstruments, selectedInstruments);
		if (selectedInstruments.isEmpty()) {
			ShowInstrumentsGrid.clearTextFields();
		} else {
			this.changeItem(0);
		}
	}

	private void HandlClear() {
		ShowInstrumentsGrid.clearTextFields();
		allInstruments.removeAll(allInstruments);
		selectedInstruments.removeAll(selectedInstruments);
	}

	private void changeItem(int i) {
		this.index = i;
		if (!selectedInstruments.isEmpty()) {
			ShowInstrumentsGrid.setTextFields(selectedInstruments.get(i));
		}
	}

	private void setLocation(int direction) {
		if (!selectedInstruments.isEmpty()) {
			int index = (this.index + direction + selectedInstruments.size()) % selectedInstruments.size();
			changeItem(index);
		}
	}

	private GridPane paneForLeftButton() {
		GridPane gridleft = new GridPane();
		Button leftButton = new Button("<");
		gridleft.setPadding(new Insets(AddInstrumentWindow.SPACE10, AddInstrumentWindow.SPACE10,
				AddInstrumentWindow.SPACE10, AddInstrumentWindow.SPACE10));
		gridleft.setAlignment(Pos.CENTER_LEFT);
		gridleft.setHgap(AddInstrumentWindow.SPACE10);
		gridleft.setVgap(AddInstrumentWindow.SPACE10);
		gridleft.add(leftButton, 0, 2);
		leftButton.setOnMouseClicked(e -> {
			setLocation(LEFT);
		});
		return gridleft;
	}

	public GridPane paneForRightButton() {
		Button rightButton = new Button(">");
		GridPane gridright = new GridPane();
		gridright.setPadding(new Insets(AddInstrumentWindow.SPACE10, AddInstrumentWindow.SPACE10,
				AddInstrumentWindow.SPACE10, AddInstrumentWindow.SPACE10));
		gridright.setAlignment(Pos.CENTER_RIGHT);
		gridright.setHgap(AddInstrumentWindow.SPACE10);
		gridright.setVgap(AddInstrumentWindow.SPACE10);
		gridright.add(rightButton, 0, 2);
		rightButton.setOnMouseClicked(e -> {
			setLocation(RIGHT);
		});
		return gridright;
	}

	public File getInstrumentsFileFromUser() {
		boolean stopLoop = true;
		File file;
		do {
			TextInputDialog dialog = new TextInputDialog("");
			dialog.setTitle("Confirmation");
			dialog.setHeaderText("Load Instruments From File");
			dialog.setContentText("Please enter file name:");
			Optional<String> result = dialog.showAndWait();
			if (!result.isPresent()) {
				System.exit(2);
			}
			file = new File(result.get());
			stopLoop = file.exists() && file.canRead();
			if (!stopLoop) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("File Error!");
				alert.setContentText("Cannot read from file, please try again");
				alert.showAndWait();
			}

		} while (!stopLoop);
		return file;
	}

}
