package ca.ucalgary.ensf380;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class WeatherFetcher {

// Declaring And Initializing The Base URL Used For The Weather Data:
	
	private static final String BASE_URL = "http://wttr.in/";
	
// Public Function/Method Used To Gain Access To The Website API Information Through URL Connection:
	
	public static String fetchWeather(String cityName, String countryCode) throws Exception {
		
		// If-Statement To Check For And Implement Default Value For Country Code If That Argument Is Null Or Empty (Edge-Case To Make Sure The URL Works If User Leaves The "countryCode" Empty):
		
		if (countryCode == null || countryCode.isEmpty()) {
			
			countryCode = ""; //If The Country Code Is Null Or If There Is No Country Code Then Keep Position Empty.
			
		} else {
			countryCode = "/" + countryCode; // If There Is A Country Code Then Add A Forward Slash To Continue Path Of URL Then Add The Country Code To The Right Of The Forward Slash.
		}
		
		String completeUrl = BASE_URL + cityName + countryCode + "?format=%C+%t+%w+%p";
		
		URL urlObject = new URL(completeUrl);
		HttpURLConnection urlConnection = (HttpURLConnection) urlObject.openConnection();
		urlConnection.setRequestMethod("GET");
		
		int responseCode = urlConnection.getResponseCode();
		
		if (responseCode == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			
			String inputLine;
			
		}
	}
	
}
