package streamExercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StreamCollectorsExercise {
	public static void main(String[] args) {
		List<Player> player = Arrays.asList(new Player("Ravindra",345,5000,100,"India"),
                new Player("smith",232,6500,120,"Australia"),
                new Player("virat",350,1150,225,"India"),
                new Player("Polad",185,1230,185,"westIndies"),
                new Player("Dhoni",350,1073,183,"India"));
		getPlayersByCountry(player);
		getPlayerNamesByCountry(player);
		getTotalPlayersByCountry(player);
		getTotalRunsByCountry(player);
		getMaxScoreByCountry(player);
		getPlayerNamesStringByCountry(player);
	}
	public static void getPlayersByCountry(List<Player> p){
		Map<Object, Object> countryNameWithPlayerName = p.stream()
				.collect(Collectors.toMap(p1->p1.getPlayerName(),p1->p1.getCountry()));
		System.out.println("Players with country: "+countryNameWithPlayerName);
		}
	
	public static void getPlayerNamesByCountry(List<Player> p){
		Map<Object, Object> countryNameWithPlayerName1 = p.stream()
				.filter(player -> player.getMatchesPlayed() > 100)
				.collect(Collectors.toMap(p1->p1.getPlayerName(),p1->p1.getCountry()));
		System.out.println("Players who have played more than 100 matches: "+countryNameWithPlayerName1);
		}
	
	public static void getTotalPlayersByCountry(List<Player> p) {
		 Map<String, Long>  n2 = p.stream()
				 .collect(Collectors.groupingBy(k -> k.getCountry(), Collectors.counting()));
		 System.out.println("Number of players in each country: "+n2);
	}
	
	public static void getTotalRunsByCountry(List<Player> p) {
		Map<String, Long>  n3 = p.stream()
				.collect(Collectors.groupingBy(k -> k.getCountry(), Collectors.summingLong(p5->p5.getRuns())));
		 System.out.println("Total score of each country: "+n3);
	}
	public static void getMaxScoreByCountry(List<Player> p) {
		Map<Object, Object> highest = p.stream()
        .collect(Collectors.groupingBy(Player::getCountry, Collectors.groupingBy(Player::getRuns,TreeMap::new, Collectors.toList())))
        .entrySet().stream()
        .collect(Collectors.toMap(e -> e.getKey(),
                e -> e.getValue().lastEntry().getKey()));
		System.out.println(""+highest);
	}
	public static void getPlayerNamesStringByCountry(List<Player> p){
		Map<Object, Object> countryNameWithPlayerName = p.stream()
				.collect(Collectors.toMap(p1->p1.getCountry(),p1->p1.getPlayerName(), (s, a) -> s + ", " + a, LinkedHashMap::new));
		System.out.println("Players with respective to country: "+countryNameWithPlayerName);
		}
}



