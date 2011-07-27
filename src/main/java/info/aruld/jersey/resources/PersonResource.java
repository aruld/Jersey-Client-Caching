package info.aruld.jersey.resources;

import com.sun.jersey.spi.resource.Singleton;
import info.aruld.jersey.model.Person;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class PersonResource {

  public static List<Person> getSampleData() {
    List<Person> people = new ArrayList<Person>();
    for (int i = 1; i <= 10; i++) {
      people.add(new Person("John Doe" + i, 30));
      people.add(new Person("Jane Doe" + i, 28));
      people.add(new Person("Baby Doe" + i, 5));
    }
    return people;
  }

}
