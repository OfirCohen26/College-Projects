import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class AddressBookJavaFx extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage[] stages = new Stage[AddressBookPane.MAX_PANES];
		Scene[] scenes = new Scene[AddressBookPane.MAX_PANES];
		AddressBookPane[] panes = new AddressBookPane[AddressBookPane.MAX_PANES];
		for (int i = 0; i < 1 + AddressBookPane.MAX_PANES; i++) {
			if (i >= AddressBookPane.MAX_PANES)
				System.out.println("SingleTone violation. Only 3 panes were created");
			else {
				panes[i] = AddressBookPane.getInstance();
				scenes[i] = new Scene(panes[i]);
				scenes[i].getStylesheets().add("styles.css");
				stages[i] = new Stage();
				stages[i].setTitle("AddressBook");
				stages[i].setScene(scenes[i]);
				stages[i].setResizable(true);
				stages[i].show();
				stages[i].setAlwaysOnTop(true);
				// stages[i].setOnCloseRequest(event -> {
				// AddressBookPane.reduceNumberOfObjects();
				// });
			}
		}

	}
}

class AddressBookPane extends GridPane {
	private RandomAccessFile raf;
	// private FlowPane jpButton;
	// Text fields
	private TextField jtfName = new TextField();
	private TextField jtfStreet = new TextField();
	private TextField jtfCity = new TextField();
	private TextField jtfState = new TextField();
	private TextField jtfZip = new TextField();
	// Buttons
	private AddButton jbtAdd;
	private FirstButton jbtFirst;
	private NextButton jbtNext;
	private PreviousButton jbtPrevious;
	private LastButton jbtLast;
	private UndoButton jbtUndo;
	private RedoButton jbtRedo;
	private FlowPane jpButton = new FlowPane();
	private CareTaker careTaker = new CareTaker();
	private Originator originator = new Originator();
	private ArrayList<CommandButton> cba = new ArrayList<CommandButton>();
	private static int numberOfPanes = 0;
	public static final int MAX_PANES = 3;
	public static final int UPDATE_FILE_PANES = 1;

	public EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent arg0) {
			((Command) arg0.getSource()).Execute();
		}
	};

	public static AddressBookPane getInstance() {
		AddressBookPane adPane = new AddressBookPane();
		numberOfPanes++;
		if (numberOfPanes <= UPDATE_FILE_PANES) {
			AddressBookJavaFxDecoretor.decoretor(adPane.jpButton, true, adPane.cba);
			return adPane;
		} else if (numberOfPanes <= MAX_PANES) {
			AddressBookJavaFxDecoretor.decoretor(adPane.jpButton, false, adPane.cba);
			return adPane;
		} else
			return null;

	}

	private AddressBookPane() { // Open or create a random access file
		try {
			raf = new RandomAccessFile("address.dat", "rw");
		} catch (IOException ex) {
			System.out.print("Error: " + ex);
			System.exit(0);
		}

		jtfState.setAlignment(Pos.CENTER_LEFT);
		jtfState.setPrefWidth(90);
		jtfZip.setPrefWidth(60);

		cba.add(jbtFirst = new FirstButton(this, raf, false, "First"));
		cba.add(jbtNext = new NextButton(this, raf, false, "Next"));
		cba.add(jbtPrevious = new PreviousButton(this, raf, false, "Previous"));
		cba.add(jbtLast = new LastButton(this, raf, false, "Last"));
		cba.add(jbtAdd = new AddButton(this, raf, true, "Add", careTaker, originator));
		cba.add(jbtRedo = new RedoButton(this, raf, true, "Redo", careTaker, originator));
		cba.add(jbtUndo = new UndoButton(this, raf, true, "Undo", careTaker, originator));

		Label state = new Label("State");
		state.setPadding(new Insets(0, 4, 0, 4));
		Label zp = new Label("Zip");
		zp.setPadding(new Insets(0, 4, 0, 4));
		Label name = new Label("Name");
		Label street = new Label("Street");
		Label city = new Label("City");
		// Panel p1 for holding labels Name, Street, and City
		GridPane p1 = new GridPane();
		p1.add(name, 0, 0);
		p1.add(street, 0, 1);
		p1.add(city, 0, 2);
		p1.setAlignment(Pos.CENTER_LEFT);
		p1.setVgap(8);
		p1.setPadding(new Insets(0, 2, 0, 2));
		GridPane.setVgrow(name, Priority.ALWAYS);
		GridPane.setVgrow(street, Priority.ALWAYS);
		GridPane.setVgrow(city, Priority.ALWAYS);
		// City Row
		GridPane adP = new GridPane();
		adP.add(jtfCity, 0, 0);
		adP.add(state, 1, 0);
		adP.add(jtfState, 2, 0);
		adP.add(zp, 3, 0);
		adP.add(jtfZip, 4, 0);
		adP.setAlignment(Pos.CENTER_LEFT);
		GridPane.setHgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfState, Priority.ALWAYS);
		GridPane.setVgrow(jtfZip, Priority.ALWAYS);
		GridPane.setVgrow(state, Priority.ALWAYS);
		GridPane.setVgrow(zp, Priority.ALWAYS);
		// Panel p4 for holding jtfName, jtfStreet, and p3
		GridPane p4 = new GridPane();
		p4.add(jtfName, 0, 0);
		p4.add(jtfStreet, 0, 1);
		p4.add(adP, 0, 2);
		p4.setVgap(1);
		GridPane.setHgrow(jtfName, Priority.ALWAYS);
		GridPane.setHgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setHgrow(adP, Priority.ALWAYS);
		GridPane.setVgrow(jtfName, Priority.ALWAYS);
		GridPane.setVgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setVgrow(adP, Priority.ALWAYS);
		// Place p1 and p4 into jpAddress
		GridPane jpAddress = new GridPane();
		jpAddress.add(p1, 0, 0);
		jpAddress.add(p4, 1, 0);
		GridPane.setHgrow(p1, Priority.NEVER);
		GridPane.setHgrow(p4, Priority.ALWAYS);
		GridPane.setVgrow(p1, Priority.ALWAYS);
		GridPane.setVgrow(p4, Priority.ALWAYS);
		// Set the panel with line border
		jpAddress.setStyle("-fx-border-color: grey;" + " -fx-border-width: 1;" + " -fx-border-style: solid outside ;");
		// Add buttons to a panel
		jpButton.setHgap(5);
		jpButton.setAlignment(Pos.CENTER);
		GridPane.setVgrow(jpButton, Priority.NEVER);
		GridPane.setVgrow(jpAddress, Priority.ALWAYS);
		GridPane.setHgrow(jpButton, Priority.ALWAYS);
		GridPane.setHgrow(jpAddress, Priority.ALWAYS);
		// Add jpAddress and jpButton to the stage
		this.setVgap(5);
		this.add(jpAddress, 0, 0);
		this.add(jpButton, 0, 1);
		jbtAdd.setOnAction(ae);
		jbtFirst.setOnAction(ae);
		jbtNext.setOnAction(ae);
		jbtPrevious.setOnAction(ae);
		jbtLast.setOnAction(ae);
		jbtUndo.setOnAction(ae);
		jbtRedo.setOnAction(ae);
		jbtFirst.Execute();
	}

	public void actionHandled(ActionEvent e) {
		((Command) e.getSource()).Execute();
	}

	public void SetName(String text) {
		jtfName.setText(text);
	}

	public void SetStreet(String text) {
		jtfStreet.setText(text);
	}

	public void SetCity(String text) {
		jtfCity.setText(text);
	}

	public void SetState(String text) {
		jtfState.setText(text);
	}

	public void SetZip(String text) {
		jtfZip.setText(text);
	}

	public String GetName() {
		return jtfName.getText();
	}

	public String GetStreet() {
		return jtfStreet.getText();
	}

	public String getPane() {
		return jtfCity.getText();
	}

	public String GetState() {
		return jtfState.getText();
	}

	public String GetZip() {
		return jtfZip.getText();
	}

	public String GetCity() {
		return jtfCity.getText();
	}

	public static void reduceNumberOfObjects() {
		numberOfPanes--;
	}

	public static void resetNumberOfObjects() {
		numberOfPanes = 0;
	}

	public void clearTextFields() {
		jtfName.setText("");
		jtfStreet.setText("");
		jtfCity.setText("");
		jtfState.setText("");
		jtfZip.setText("");
	}

}

interface Command {
	public void Execute();
}

class CommandButton extends Button implements Command {
	public final static int NAME_SIZE = 32;
	public final static int STREET_SIZE = 32;
	public final static int CITY_SIZE = 20;
	public final static int STATE_SIZE = 10;
	public final static int ZIP_SIZE = 5;
	public final static int RECORD_SIZE = (NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE);
	protected AddressBookPane p;
	protected RandomAccessFile raf;
	protected Comparator<String> comparator;
	protected boolean update;
	protected CareTaker careTaker;
	protected Originator originetor;

	public CommandButton(AddressBookPane pane, RandomAccessFile r, boolean update, String text) {
		super(text);
		this.p = pane;
		this.raf = r;
		this.update = update;
	}

	public void setPane(AddressBookPane pane) {
		p = pane;
	}

	public AddressBookPane getPane() {
		return p;
	}

	public void Execute() {
	}

	/** Write a record at the end of the file */
	public void writeAddress() {
		try {
			raf.seek(raf.length());
			FixedLengthStringIO.writeFixedLengthString(p.GetName(), NAME_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetStreet(), STREET_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetCity(), CITY_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetState(), STATE_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetZip(), ZIP_SIZE, raf);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/** Read a record at the specified position */
	public void readAddress(long position) throws IOException {
		raf.seek(position);
		String name = FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf);
		String street = FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf);
		String city = FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf);
		String state = FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf);
		String zip = FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf);
		p.SetName(name);
		p.SetStreet(street);
		p.SetCity(city);
		p.SetState(state);
		p.SetZip(zip);
	}

	// Read from file the last record
	public String readFullRecordForMemento(long position) throws IOException {
		raf.seek(position);
		String fullRecord = FixedLengthStringIO.readFixedLengthString(RECORD_SIZE, raf);
		return fullRecord;
	}

	// Write record from memento to file
	public void writeAddressFrM(String record) {
		try {
			raf.seek(raf.length());
			FixedLengthStringIO.writeFixedLengthString(record, RECORD_SIZE, raf);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void FromFileToMemento(Originator originator, CareTaker careTaker) {
		try {
			raf.seek(0);
			for (int i = 0; i < raf.length(); i = i + (RECORD_SIZE * 2)) {
				originator.setState(readFullRecordForMemento(i));
				careTaker.add(originator.saveStateToMemento());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class AddButton extends CommandButton {
	Originator originator;
	CareTaker careTaker;

	public AddButton(AddressBookPane pane, RandomAccessFile r, boolean update, String text, CareTaker careTaker,
			Originator orig) {
		super(pane, r, update, text);
		this.originator = orig;
		this.careTaker = careTaker;
	}

	@Override
	public void Execute() {
		try {
			long lastPosition = raf.length();
			if (lastPosition > 0 && careTaker.isEmpty())
				FromFileToMemento(originator, careTaker);
			writeAddress();
			long currentPosition = raf.getFilePointer();
			originator.setState(readFullRecordForMemento(currentPosition - RECORD_SIZE * 2));
			careTaker.add(originator.saveStateToMemento());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class NextButton extends CommandButton {
	public NextButton(AddressBookPane pane, RandomAccessFile r, boolean update, String text) {
		super(pane, r, update, text);
	}

	@Override
	public void Execute() {

		try {
			if (raf.length() == 0) {
				p.clearTextFields();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			long currentPosition = raf.getFilePointer();
			if (currentPosition < raf.length())
				readAddress(currentPosition);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class PreviousButton extends CommandButton {
	public PreviousButton(AddressBookPane pane, RandomAccessFile r, boolean update, String text) {
		super(pane, r, update, text);
	}

	@Override
	public void Execute() {
		try {
			if (raf.length() == 0) {
				p.clearTextFields();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			long currentPosition = raf.getFilePointer();
			if (currentPosition > raf.length())
				currentPosition = raf.length();
			if (currentPosition - 2 * 2 * RECORD_SIZE >= 0)
				readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
			else
				;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class LastButton extends CommandButton {
	public LastButton(AddressBookPane pane, RandomAccessFile r, boolean update, String text) {
		super(pane, r, update, text);
	}

	@Override
	public void Execute() {
		try {
			if (raf.length() == 0) {
				p.clearTextFields();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			long lastPosition = raf.length();
			if (lastPosition > 0)
				readAddress(lastPosition - 2 * RECORD_SIZE);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class FirstButton extends CommandButton {
	public FirstButton(AddressBookPane pane, RandomAccessFile r, boolean update, String text) {
		super(pane, r, update, text);
	}

	@Override
	public void Execute() {
		try {
			if (raf.length() == 0) {
				p.clearTextFields();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (raf.length() > 0)
				readAddress(0);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class UndoButton extends CommandButton {
	Originator originator;
	CareTaker careTaker;

	public UndoButton(AddressBookPane pane, RandomAccessFile r, boolean update, String text, CareTaker careTaker,
			Originator orig) {
		super(pane, r, update, text);
		this.careTaker = careTaker;
		this.originator = orig;
	}

	@Override
	public void Execute() {
		try {
			if (raf.length() > 0 && careTaker.isEmpty())
				FromFileToMemento(originator, careTaker);
			long lastPosition = raf.length();
			if (lastPosition > 0) {
				originator.getStateFromMemento(careTaker.getPrev());
				raf.setLength(lastPosition - 2 * RECORD_SIZE);
				if (raf.length() > 0) {
					readAddress(0);
				} else {
					p.clearTextFields();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class RedoButton extends CommandButton {
	Originator originator;
	CareTaker careTaker;

	public RedoButton(AddressBookPane pane, RandomAccessFile r, boolean update, String text, CareTaker careTaker,
			Originator orig) {
		super(pane, r, update, text);
		this.careTaker = careTaker;
		this.originator = orig;
	}

	@Override
	public void Execute() {
		try {
			if (!careTaker.isEmpty()) {
				if (raf.length() == 0) {
					writeAddressFrM(originator.getfullRecord());
					readAddress(raf.length() - 2 * RECORD_SIZE);
				} else {
					Memento m = careTaker.getNext();
					if (m != null) {
						originator.getStateFromMemento(m);
						writeAddressFrM(originator.getfullRecord());
						readAddress(raf.length() - 2 * RECORD_SIZE);
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}

// ******************************************///
// ***** Memento Design Pattern Classes *****///
// ******************************************///
// ***** Memento Class *****///
class Memento {
	private String fullRecord;

	public Memento(String allDtails) {
		this.fullRecord = allDtails;
	}

	public String getfullRecord() {
		return fullRecord;
	}

}

// ***** CareTaker Class *****///
class CareTaker {
	private List<Memento> mementoList = new ArrayList<Memento>();
	private int index;

	public CareTaker() {
		index = mementoList.size();
	}

	public boolean isEmpty() {
		if (mementoList.isEmpty())
			return true;
		return false;
	}

	public void add(Memento state) {
		if (state != null) {
			mementoList.add(state);
			index = mementoList.size() - 1;
		}
	}

	public Memento getPrev() {
		if (mementoList.isEmpty() || index <= 0) {
			return null;
		}
		return mementoList.get(--index);
	}

	public Memento getNext() {
		if (mementoList.isEmpty() || index >= mementoList.size() - 1) {
			return null;
		}
		return mementoList.get(++index);
	}

}

class Originator {
	private String fullRecord;

	public String getfullRecord() {
		return fullRecord;
	}

	public void setState(String allDtails) {
		this.fullRecord = allDtails;
	}

	public Memento saveStateToMemento() {
		return new Memento(fullRecord);
	}

	public void getStateFromMemento(Memento memento) {
		if (memento != null) {
			this.fullRecord = memento.getfullRecord();
		}
	}
}
