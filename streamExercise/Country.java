package streamExercise;

public class Country {
	    private int countryId;
	    private String countryName;
	    
	    Country(int countryId, String countryName){
	        this.setCountryId(countryId);
	        this.setCountryName(countryName);
	    }
	    public int getCountryId() {
	        return countryId;
	    }
	    public void setCountryId(int countryId) {
	        this.countryId = countryId;
	    }
	    public String getCountryName() {
	        return countryName;
	    }
	    public void setCountryName(String countryName) {
	        this.countryName = countryName;
	    }
}
