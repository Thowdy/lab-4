import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Person {
	private IntegerProperty id;
	private final StringProperty firstName;
	private final StringProperty secondName;
	private final StringProperty time;
	private final StringProperty date;
	private final StringProperty duration;


	public Person(int id,String firstName, String secondName, String time, String date,String duration) {

		this.id = new SimpleIntegerProperty(id);
		this.firstName = new SimpleStringProperty(firstName);
		this.secondName = new SimpleStringProperty(secondName);
		this.date = new SimpleStringProperty(date);
		this.duration = new SimpleStringProperty(duration);
		this.time = new SimpleStringProperty(time);
	}
	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id.set(id);
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getSecondName() {
		return secondName.get();
	}

	public void setSecondName(String lastName) {
		this.secondName.set(lastName);
	}

	public StringProperty secondNameProperty() {
		return secondName;
	}


	public String getTime() {
		return time.get();
	}

	public void settime(String time) {
		this.time.set(time);
	}
	
	public StringProperty timeProperty() {
		return time;
	}

	public String getDate() {
		return date.get();
	}

	public void setDate(String date) {
		this.date.set(date);
	}

	public StringProperty dateProperty() {
		return date;
	}

	public String getDuration() {
		return duration.get();
	}

	public void setDuration(String duration) {
		this.duration.set(duration);
	}

	public StringProperty durationProperty() {
		return duration;
	}
}