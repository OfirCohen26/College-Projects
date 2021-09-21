import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SearchField extends HBox {
	private TextField srearchTextField;
	private Button searchButton;

	public SearchField() {
		searchButton = new Button("Go!");
		srearchTextField = new TextField();
		this.setPadding(new Insets(AddInstrumentWindow.SPACE10, AddInstrumentWindow.SPACE10,
				AddInstrumentWindow.SPACE10, AddInstrumentWindow.SPACE10));
		this.setSpacing(AddInstrumentWindow.SPACE10);
		getSrearchTextField().setPrefWidth(570);
		this.getChildren().addAll(getSrearchTextField(), getGoButton());
		setPromotText();
		setFocusTraversable();
	}

	private void setPromotText() {
		srearchTextField.setPromptText("Sreach...");
	}

	private void setFocusTraversable() {
		srearchTextField.setFocusTraversable(false);
	}

	public TextField getSrearchTextField() {
		return srearchTextField;
	}

	public Button getGoButton() {
		return searchButton;
	}

	public void setGoButton(Button go) {
		this.searchButton = go;
	}

	public ArrayList<MusicalInstrument> searchInstrument(ArrayList<MusicalInstrument> allInstruments,
			ArrayList<MusicalInstrument> selectedInstruments) {
		selectedInstruments.removeAll(selectedInstruments);
		for (int i = 0; i < allInstruments.size(); i++) {
			if (allInstruments.get(i).toString().contains(getSrearchTextField().getText()))
				selectedInstruments.add(allInstruments.get(i));
		}
		return selectedInstruments;
	}

}
