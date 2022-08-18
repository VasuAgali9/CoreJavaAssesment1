package streamExercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class StreamOperationsExercise {
	public static void main(String[] args) {
	List<Player> player = Arrays.asList(new Player("Gayle",301,10480,215,"westIndies"),
			new Player("smith",132,4500,98,"Australia"),
			new Player("virat",350,10150,225,"India"),                            
			new Player("Polad",78,1030,85,"westIndies"),
			new Player("Dhoni",350,10703,183,"India"));
	
	displayPlayers(player);
	displayPlayerForCountry(player,"India");
	getPlayerNames(player);
	getAverageRunsByCountry(player,"westIndies");
	getPlayerNamesSorted(player);
	getPlayerCountry(player);
	findPlayer( player,"Polad");
	getMaxRunsPlayer(player);
	checkHighScorerByCountry(player ,"smith");
	}
	
	public static void displayPlayers(List<Player> player) {
		 List<String> playerName = player.stream()
				 .map(pn->pn.getPlayerName())
				 .collect(Collectors.toList());
		 System.out.println("All players name: "+playerName);
	}
	
	static void displayPlayerForCountry(List<Player> p,String country){
		List<String> playerNameByCountry =  p.stream()
				.filter(player ->player.getCountry()
				.equals(country) && player
				.getHighestScore() > 100)
				.map(u->u.getPlayerName())
				.collect(Collectors.toList());
		System.out.println("Highest Score by "+country+": "+playerNameByCountry);
	}
	
	static void getPlayerNames(List<Player> p) {
		LinkedList<String> PN = p.stream()
				.filter(player -> player.getRuns() > 5000)
				.sorted((p1, p2) -> p2.getPlayerName().compareTo(p1.getPlayerName()))
				.map(player -> player.getPlayerName())
				.collect(Collectors.toCollection(LinkedList::new));
		System.out.println("Players scored more than 5,000: "+PN);
		}
	
	public static void getAverageRunsByCountry(List<Player> p,String country) {
		  List<Integer> getRuns = p.stream().filter(player -> {
			  return player.getCountry().equals(country);})
				  .map(p1->p1.getRuns())
				  .collect(Collectors.toList());
		  OptionalDouble average = getRuns.stream()
				  .mapToInt(x1-> x1.intValue())
				  .average();
		  System.out.println("Average Runs of "+country+":"+average);
	}
	public static void getPlayerNamesSorted(List<Player> p){
		List<String> PNS =p.stream()
				.sorted((p1, p2) -> p1.getCountry().compareTo(p2.getCountry()))
				.map(p1->p1.getPlayerName()).collect(Collectors.toList());
		System.out.println("players name sorted by country: "+PNS);
		List<String> PNS1 = p.stream()
				.sorted(Comparator.comparingInt(Player::getMatchesPlayed))
				.map(p1->p1.getPlayerName()).collect(Collectors.toList());
		System.out.println("players name sorted by match played: "+ PNS1);
	} 
	public static void getPlayerCountry(List<Player> p){
		Map<Object, Object> countryNameWithPlayerName = p.stream()
				.filter(player -> player.getMatchesPlayed() > 200)
				.collect(Collectors.toMap(p1->p1.getPlayerName(),p1->p1.getCountry()));
		System.out.println("country Name With PlayerName: "+countryNameWithPlayerName);
	}
	public static void getMaxRunsPlayer(List<Player> p) {
        Player MaxRun = p.stream()
        		.max((p1,p2)->p1.getRuns() > p2.getRuns() ? 1:-1).get();
        System.out.println("Maximum runs of"+p+MaxRun);
	} 
	public static void findPlayer(List<Player> p,String name) {
		String FP = p.stream()
				  .filter(customer -> name.equals(customer.getPlayerName()))
				  .map(p1->p1.getCountry())
				  .findAny()
				  .orElse(null);
		System.out.println(name+" Player of: "+FP);
		} 
	public static void checkHighScorerByCountry(List<Player> p,String name) {
		boolean HighScore = p.stream()
				.anyMatch(s -> s.getRuns()>10000 && s.getCountry().equals(name));
		System.out.println(name +" is highest scorer of the country? "+HighScore);
		
	}
}