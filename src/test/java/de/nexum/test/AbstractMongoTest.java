package de.nexum.test;

import java.net.UnknownHostException;

import org.junit.Before;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class AbstractMongoTest {

	private MongoClient mongoClient;
	private DB db;
	
	public AbstractMongoTest() {
		
		super();
		
		try {
			this.mongoClient = new MongoClient("localhost", 27017);
			this.db = this.mongoClient.getDB( "world");	
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Before
	public void setup() {
		
		db.dropDatabase();
		echo("Dropped test database!");
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public DB getDb() {
		return db;
	}
	
	public void echo(String msg) {
		
		System.out.println(msg);
	}
	
}
