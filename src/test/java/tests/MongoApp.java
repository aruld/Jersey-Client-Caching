package tests;

import info.aruld.jersey.model.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.document.mongodb.MongoOperations;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.SimpleMongoDbFactory;

import static org.springframework.data.document.mongodb.query.Update.update;
import static org.springframework.data.document.mongodb.query.Query.query;
import static org.springframework.data.document.mongodb.query.Criteria.where;

import com.mongodb.Mongo;

import java.util.List;

public class MongoApp {

  private static final Log log = LogFactory.getLog(MongoApp.class);

  public static void main(String[] args) throws Exception {

//    MongoOperations mongoOps = new MongoTemplate(new Mongo(), "people");

    UserCredentials userCredentials = new UserCredentials("joe", "secret");
    MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "people", userCredentials));

//    mongoOps.insert(new Person("Joe", 34));

//    log.info(mongoOps.findOne(new Query(where("name").is("Joe")), Person.class));
//
//    mongoOps.dropCollection("person");

    Person p = new Person("Joe", 34);
    // Insert is used to initially store the object into the database.
    mongoOps.insert(p);
    log.info("Insert: " + p);

    // Find
    p = mongoOps.findById(p.getId(), Person.class);
    log.info("Found: " + p);

    // Update
    mongoOps.updateFirst(query(where("name").is("Joe")), update("age", 35), Person.class);
    p = mongoOps.findOne(query(where("name").is("Joe")), Person.class);
    log.info("Updated: " + p);

    // Delete
    mongoOps.remove(p);

    // Check that deletion worked
    List<Person> people =  mongoOps.findAll(Person.class);
    log.info("Number of people = : " + people.size());


    mongoOps.dropCollection(Person.class);
  }
}