import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Competition {

    public static Map<String, Integer> getTeamPlaceMap(Collection<Team> teams) {
        return teams.stream()
                .sorted(Comparator.comparing(Team::getPlace))
                .collect(Collectors.toMap(Team::getName, Team::getPlace, (first, second) -> first, LinkedHashMap::new)); // write your code here
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        Test Data:
//        Champions 2
//        Ducks 3
//        Bright 4
//        Jcoders 1
        Collection<Team> teams = Stream
                .iterate(1, i -> scanner.hasNextLine(), i -> i + 1)
                .limit(4) //just for exit the loop
                .map(i -> scanner.nextLine().split("\\s+"))
                .map(parts -> new Team(parts[0], Integer.parseInt(parts[1])))
                .collect(Collectors.toSet());

        getTeamPlaceMap(teams)
                .forEach((team, speaker) -> System.out.println(team + ": " + speaker));
    }
}

class Team {
    private final String name;
    private final int place;

    public Team(String name, int place) {
        this.name = name;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }
}