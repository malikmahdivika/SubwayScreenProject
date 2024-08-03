package ca.ucalgary.ensf380;

import java.sql.SQLException; //Handles SQL exceptions
import java.util.List; //List data structure
import java.util.Timer; //Timer to schedule tasks
import java.util.TimerTask; //TimerTask defines tasks

public class AdvertisementManager {
	private DatabaseConnection dbConnection; //Connection to db
	private List<Advertisement> ads; //Stores ads fetched from db
	private int currentAdIndex; //Keeps track of current ad being played
	private Timer timer; //Timer schedules ad rotation tasks
	
	//Constructor Initializes AdvertisementManager
	public AdvertisementManager() {
		dbConnection = new DatabaseConnection("jdbc:mysql://localhost:3306/subway", "root", "Waterfall@17"); //Initializes db connection
		try {
			ads = dbConnection.fetchAdvertisements(); //Fetch ads from db and store into list
		} catch (SQLEception e) {
			e.printStackTrace(); //Print the stack trace if there's SQL exception
		}
		currentAdIndex = 0; //Initialize index to 0
		timer = new Timer(); //Initialize timer		
	}
	
	//Method starts rotating advertisements
	public void startAdRotation() {
		//Schedules tasks to rotate ads every 10 secs
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				displayNextAd(); //Call method to display next ad
			}
		}, 0, 10000); //Schedules task w/ initial delay of 0 ms and repeats every 1K ms (10 secs)
	}
	
	//Method displays next advertisement
	private void displayNextAd() {
		Advertisement Ad = ads.get(currentAdIndex); 
	}
}
