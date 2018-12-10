import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class PersonTableController {
	
	@FXML
	private TextField filterField;
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, Integer> idColumn;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> secondNameColumn;
	@FXML
	private TableColumn<Person, String> timeColumn;

	@FXML
	private TableColumn<Person, String> dateColumn;

	@FXML
	private TableColumn<Person, String> durationColumn;

	private ObservableList<Person> masterData = FXCollections.observableArrayList();
	public PersonTableController() {
		masterData.add(new Person(1,"Èâàí", "Àíäðåé","15:54","15.04.2018","01:25:00"));
		masterData.add(new Person(2,"Àíäðåé", "Èâàí","20:43","14.04.2018","01:02:00"));
		masterData.add(new Person(3,"Îëåã", "Ñåìåí","23:36","13.05.2018","00:04:01"));
		masterData.add(new Person(4,"Âëàä", "Îëåã","22:06","12.06.2018","00:12:25"));
		masterData.add(new Person(5,"Àðòåì", "Àðêàäèé","16:11","03.02.2018","03:23:24"));
		masterData.add(new Person(6,"Ñåðãåé", "Âëàä","14:14","10.01.2018","04:13:13"));
		masterData.add(new Person(7,"Âèêòîð", "Âèêòîð","13:59","10.12.2018","00:00:31"));
		masterData.add(new Person(8,"Ñåìåí", "Ñåðãåé","08:13","10.08.2018","00:00:53"));
		masterData.add(new Person(9,"Àðêàäèé", "Àðòåì","14:55","10.12.2018","00:00:33"));
	}
	private void initRecord() throws FileNotFoundException {
		Scanner in = new Scanner(new File("files/records.txt"));
		String s;
		int k = 9;
		while (in.hasNextLine()) {
			k++;
			s = in.nextLine();
			String w[] = s.split(" ");
			for (int i = 0; i < w.length - 4; i++)
				masterData.add(new Person(k, w[i], w[i + 1], w[i + 2], w[i + 3], (w[i + 4])));
		}
		in.close();
	}
	@FXML
	private void initialize() throws FileNotFoundException {
		initRecord();
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		secondNameColumn.setCellValueFactory(cellData -> cellData.getValue().secondNameProperty());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		durationColumn.setCellValueFactory(cellData -> cellData.getValue().durationProperty());
		filteredData = new FilteredList<>(masterData, p -> true);                
		filterField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        filteredData.setPredicate(person -> {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }
                            String lowerCaseFilter = newValue.toLowerCase();
                            
                            if (person.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                return true;
                            } else if (person.getSecondName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                return true;
                            } else if (person.getTime().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                return true;
                            } else if (person.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                return true;
                            } else if (person.getDuration().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                return true;
                            }
                            
                            return false;
                        });
                    }
                });
		sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(personTable.comparatorProperty());
		personTable.setItems(sortedData);
                buttonFind.disableProperty().bind(textFieldWithDuration.textProperty().isEmpty().or(textFieldByDuration.textProperty().isEmpty()));
	}
        
        @FXML
        private void onlyDigit(KeyEvent evt){            
            if(!Character.isDigit(evt.getCharacter().charAt(0))){
                evt.consume();
            }
        }
        
        @FXML
        private void buttonFindAction(){
            int with = Integer.valueOf(textFieldWithDuration.getText());
            int by = Integer.valueOf(textFieldByDuration.getText());
            durationData = personTable.getItems();
            ObservableList<Person> durationList = FXCollections.observableArrayList();

            
            
            
            for (Person dd : durationData) {
                LocalTime time = LocalTime.parse(dd.getDuration(), DateTimeFormatter.ofPattern("HH:mm:ss"));
                int second = time.getHour()*3600 + time.getMinute()*60 + time.getSecond();
                if(second >= with && second <= by){
                    durationList.add(dd);
                }
            }
            personTable.setItems(durationList);
            filterField.setEditable(false);
            buttonCancel.setDisable(false);
            
            
        }
        
        @FXML
        private void buttonCancelAction(){
            buttonCancel.setDisable(true);
            personTable.setItems(sortedData);           
            filterField.setEditable(true);
            textFieldWithDuration.clear();
            textFieldByDuration.clear();
        }
}
