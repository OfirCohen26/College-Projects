
import java.util.InputMismatchException;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddInstrumentWindow extends Stage {
	public static final int SPACE50 = 50, NONOFFSET = 0, SCENCESPACE = 300, SPACE100 = 100, SPACE10 = 10,
			SPACE150 = 150, SPACE20 = 20;
	private String[] typesOfInstrumets = { "Guitar", "Bass", "Flute", "Saxophone" };
	private String[] typesOfGuitars = { "Classic", "Acoustic", "Electric" };
	private String[] typesOfFlutes = { "Flute", "Recorder", "Bass" };
	private String[] materialOfFlutes = { "Wood", "Metal", "Plastic" };
	private ComboBox<String> cboForInstruments = new ComboBox<>();
	private ComboBox<String> cboForGuitars = new ComboBox<>();
	private ComboBox<String> cboForFlutesType = new ComboBox<>();
	private ComboBox<String> cboForFlutesMaterial = new ComboBox<>();
	private Label numberOfStringsLabel, brandLabel, priceLabel, frettlesLabel, TypeLabel;
	private TextField numberOfStringsTextFieldGuitar, brandTextField, priceTextField;
	private TextField numberOfStringsTextFieldBass;
	private CheckBox fretless;
	private GridPane BaseGrid, guitarGrid, fluteGrid, bassGrid;
	private VBox centerBox;
	private HBox paneForComboBox, buttonBox;
	private BorderPane mainPane;
	private Button addButton;
	private AfekaInstrumentGUI parent;

	public AddInstrumentWindow(AfekaInstrumentGUI parent) {
		this.parent = parent;
		this.setTitle("Afeka Instruments - Add new instrument");
		mainPane = new BorderPane();
		paneForComboBox = new HBox();
		paneForComboBox.setPadding(new Insets(SPACE100, SPACE50, SPACE50, SPACE50));
		ObservableList<String> items = FXCollections.observableArrayList(typesOfInstrumets);
		cboForInstruments.getItems().addAll(items);
		cboForInstruments.setPromptText("Choose Instrument Type Here");
		paneForComboBox.setAlignment(Pos.CENTER);
		mainPane.setTop(paneForComboBox);
		paneForComboBox.getChildren().add(cboForInstruments);
		cboForInstruments.setOnAction(e -> {
			setOnActionCombobox();
		});
		Scene scene = new Scene(mainPane, SCENCESPACE, SCENCESPACE);
		this.setScene(scene);
		initialization();
	}

	private void initialization() {
		InitPriceAndBrand();
		initGuitar();
		initBass();
		initFlute();
		initCenerBox();
		initAddButton();
	}

	private void setOnActionCombobox() {
		mainPane.setBottom(buttonBox);
		mainPane.setCenter(centerBox);
		mainPane.setTop(paneForComboBox);
		String selectedOption = cboForInstruments.getValue();
		if (centerBox.getChildren().size() > 1) {
			centerBox.getChildren().remove(1);
		}
		switch (selectedOption) {
		case "Guitar":
			centerBox.getChildren().add(guitarGrid);
			addWindowDesignForGuitarFluteBass();
			brandTextField.setPromptText("Ex: Gibson");
			priceTextField.setPromptText("Ex: 7500");
			break;
		case "Bass":
			centerBox.getChildren().add(bassGrid);
			addWindowDesignForGuitarFluteBass();
			brandTextField.setPromptText("Ex: Ferender Jazz");
			priceTextField.setPromptText("Ex: 7500");
			break;
		case "Flute":
			centerBox.getChildren().add(fluteGrid);
			addWindowDesignForGuitarFluteBass();
			brandTextField.setPromptText("Ex: Levit");
			priceTextField.setPromptText("Ex: 300");
			break;
		case "Saxophone":
			brandTextField.setPromptText("");
			priceTextField.setPromptText("");
			paneForComboBox.setPadding(new Insets(SPACE50, SPACE50, NONOFFSET, SPACE50));
			brandLabel.setPrefWidth(40);
			BaseGrid.setPadding(new Insets(NONOFFSET, SPACE10, SPACE10, NONOFFSET));
			buttonBox.setPadding(new Insets(NONOFFSET, SPACE50, SPACE100, SPACE50));
			break;
		}
	}

	private MusicalInstrument createInstrument() {
		MusicalInstrument newInstrument = null;
		try {
			switch (cboForInstruments.getValue()) {
			case "Guitar":
				newInstrument = new Guitar(brandTextField.getText(), convertStringToNumber(priceTextField.getText()),
						Integer.parseInt(numberOfStringsTextFieldGuitar.getText()), cboForGuitars.getValue());
				break;
			case "Bass":
				newInstrument = new Bass(brandTextField.getText(), convertStringToNumber(priceTextField.getText()),
						Integer.parseInt(numberOfStringsTextFieldBass.getText()), fretless.isSelected());
				break;
			case "Flute":
				newInstrument = new Flute(brandTextField.getText(), convertStringToNumber(priceTextField.getText()),
						cboForFlutesMaterial.getValue(), cboForFlutesType.getValue());
				break;
			case "Saxophone":
				newInstrument = new Saxophone(brandTextField.getText(),
						convertStringToNumber(priceTextField.getText()));
				break;
			}
		} catch (InputMismatchException | IllegalArgumentException | NullPointerException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
		return newInstrument;
	}

	private void initAddButton() {
		addButton = new Button("Add");
		buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(SPACE20, SPACE50, SPACE10, SPACE50));
		buttonBox.getChildren().add(addButton);
		addButton.setCenterShape(true);
		addButton.setOnMouseClicked(e -> {
			MusicalInstrument instrument = createInstrument();
			if (instrument != null) {
				parent.addInstrument(instrument);
				this.close();
			}
		});
	}

	private void addWindowDesignForGuitarFluteBass() {
		paneForComboBox.setPadding(new Insets(SPACE20, SPACE50, NONOFFSET, SPACE50));
		brandLabel.setPrefWidth(SPACE100);
		BaseGrid.setPadding(new Insets(NONOFFSET, SPACE10, NONOFFSET, NONOFFSET));
		buttonBox.setPadding(new Insets(SPACE20, SPACE50, SPACE50, SPACE50));
	}

	private void InitPriceAndBrand() {
		BaseGrid = new GridPane();
		BaseGrid.setAlignment(Pos.CENTER);
		BaseGrid.setHgap(SPACE10);
		BaseGrid.setVgap(SPACE10);
		brandLabel = new Label("Brand:");
		brandTextField = new TextField();
		priceLabel = new Label("Price:");
		priceTextField = new TextField();
		brandLabel.setPrefWidth(SPACE100);
		BaseGrid.setPadding(new Insets(NONOFFSET, SPACE10, NONOFFSET, NONOFFSET));
		BaseGrid.add(brandLabel, 1, 4);
		BaseGrid.add(brandTextField, 2, 4);
		BaseGrid.add(priceLabel, 1, 5);
		BaseGrid.add(priceTextField, 2, 5);
	}

	private void initCenerBox() {
		centerBox = new VBox();
		centerBox.getChildren().addAll(BaseGrid);
	}

	private void initGuitar() {
		guitarGrid = new GridPane();
		guitarGrid.setAlignment(Pos.CENTER);
		guitarGrid.setHgap(SPACE10);
		guitarGrid.setVgap(SPACE10);
		guitarGrid.setPadding(new Insets(NONOFFSET, SPACE10, NONOFFSET, SPACE10));
		numberOfStringsLabel = new Label("Number Of Strings:");
		numberOfStringsTextFieldGuitar = new TextField();
		numberOfStringsTextFieldGuitar.setPromptText("Ex: 6");
		TypeLabel = new Label("Type:");
		TypeLabel.setPrefWidth(SPACE100);
		guitarGrid.add(numberOfStringsLabel, 0, 1);
		guitarGrid.add(numberOfStringsTextFieldGuitar, 1, 1);
		guitarGrid.add(TypeLabel, 0, 2);
		guitarGrid.add(typeOfGuitarField(), 1, 2);
	}

	private ComboBox<String> typeOfGuitarField() {
		HBox paneForComboBoxTypeOfGuitars = new HBox();
		paneForComboBoxTypeOfGuitars.setPadding(new Insets(NONOFFSET, SPACE50, NONOFFSET, SPACE50));
		cboForGuitars.setPrefWidth(SPACE100);
		ObservableList<String> items = FXCollections.observableArrayList(typesOfGuitars);
		cboForGuitars.getItems().addAll(items);
		cboForGuitars.setPromptText("Type");
		return cboForGuitars;
	}

	private void initBass() {
		bassGrid = new GridPane();
		bassGrid.setAlignment(Pos.CENTER);
		bassGrid.setHgap(SPACE10);
		bassGrid.setVgap(SPACE10);
		bassGrid.setPadding(new Insets(NONOFFSET, SPACE10, NONOFFSET, SPACE10));
		numberOfStringsLabel = new Label("Number Of Strings:");
		numberOfStringsTextFieldBass = new TextField();
		numberOfStringsTextFieldBass.setPromptText("Ex: 4");
		frettlesLabel = new Label("Fretless:");
		fretless = new CheckBox();
		bassGrid.add(numberOfStringsLabel, 0, 1);
		bassGrid.add(numberOfStringsTextFieldBass, 1, 1);
		bassGrid.add(frettlesLabel, 0, 2);
		bassGrid.add(fretless, 1, 2);
	}

	private void initFlute() {
		fluteGrid = new GridPane();
		fluteGrid.setAlignment(Pos.CENTER);
		fluteGrid.setHgap(SPACE10);
		fluteGrid.setVgap(SPACE10);
		fluteGrid.setPadding(new Insets(NONOFFSET, 65, NONOFFSET, SPACE10));
		Label MaterialLabel = new Label("Material");
		MaterialLabel.setPrefWidth(SPACE100);
		Label FluteTypeLabel = new Label("Flute Type:");
		fluteGrid.add(MaterialLabel, 0, 1);
		fluteGrid.add(materialOfFluteField(), 1, 1);
		fluteGrid.add(FluteTypeLabel, 0, 2);
		fluteGrid.add(typeOfFluteField(), 1, 2);
	}

	private ComboBox<String> materialOfFluteField() {
		HBox paneForComboBoxFluetMaterial = new HBox();
		paneForComboBoxFluetMaterial.setPadding(new Insets(NONOFFSET, SPACE150, NONOFFSET, NONOFFSET));
		ObservableList<String> items = FXCollections.observableArrayList(materialOfFlutes);
		cboForFlutesMaterial.getItems().addAll(items);
		cboForFlutesMaterial.setPromptText("Material");
		return cboForFlutesMaterial;
	}

	private ComboBox<String> typeOfFluteField() {
		HBox paneForComboBoxFluetTypes = new HBox();
		paneForComboBoxFluetTypes.setPadding(new Insets(NONOFFSET, SPACE150, NONOFFSET, NONOFFSET));
		ObservableList<String> items2 = FXCollections.observableArrayList(typesOfFlutes);
		cboForFlutesType.getItems().addAll(items2);
		cboForFlutesType.setPromptText("Type");
		return cboForFlutesType;
	}

	private Number convertStringToNumber(String priceSring) {
		String arr[] = priceSring.split("\\.");
		Number price;
		if (arr.length > 1) {
			price = Double.parseDouble(priceSring);
		} else {
			price = Integer.parseInt(priceSring);
		}
		return price;
	}
}