
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ShowInstrumentsGrid extends GridPane {
	private Label type;
	private Label brand;
	private Label price;
	private TextField priceTextField;
	private TextField typeTextField;
	private TextField brandTextField;

	public ShowInstrumentsGrid() {
		init();
		this.setAlignment(Pos.CENTER);
		this.setHgap(AddInstrumentWindow.SPACE10);
		this.setVgap(AddInstrumentWindow.SPACE10);
		this.setPadding(new Insets(AddInstrumentWindow.SPACE10, AddInstrumentWindow.SPACE10,
				AddInstrumentWindow.SPACE10, AddInstrumentWindow.SPACE10));
		this.add(getType(), 0, 1);
		this.add(getTypeTextField(), 1, 1);
		this.add(getBrand(), 0, 2);
		this.add(getBrandTextField(), 1, 2);
		this.add(getPrice(), 0, 3);
		this.add(getPriceTextField(), 1, 3);
		setEditable();
	}

	private void init() {
		type = new Label("Type:");
		brand = new Label("Brand:");
		price = new Label("Price:");
		priceTextField = new TextField();
		typeTextField = new TextField();
		brandTextField = new TextField();
	}

	private void setEditable() {
		getTypeTextField().setEditable(false);
		getBrandTextField().setEditable(false);
		getPriceTextField().setEditable(false);
	}

	public void setTextFields(MusicalInstrument inst) {
		getTypeTextField().setText(inst.getClass().getCanonicalName());
		getBrandTextField().setText(inst.getBrand());
		getPriceTextField().setText(inst.getPrice().toString());
	}

	public void clearTextFields() {
		getPriceTextField().setText("");
		getPriceTextField().setPromptText("No Items");
		getBrandTextField().setText("");
		getBrandTextField().setPromptText("No Items");
		getTypeTextField().setText("");
		getTypeTextField().setPromptText("No Items");
	}

	public Label getType() {
		return type;
	}

	public void setType(Label type) {
		this.type = type;
	}

	public Label getBrand() {
		return brand;
	}

	public void setBrand(Label brand) {
		this.brand = brand;
	}

	public Label getPrice() {
		return price;
	}

	public void setPrice(Label price) {
		this.price = price;
	}

	public TextField getPriceTextField() {
		return priceTextField;
	}

	public void setPriceTextField(TextField priceTextField) {
		this.priceTextField = priceTextField;
	}

	public TextField getTypeTextField() {
		return typeTextField;
	}

	public void setTypeTextField(TextField typeTextField) {
		this.typeTextField = typeTextField;
	}

	public TextField getBrandTextField() {
		return brandTextField;
	}

	public void setBrandTextField(TextField brandTextField) {
		this.brandTextField = brandTextField;
	}
}
