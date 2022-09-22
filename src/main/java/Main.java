import java.io.IOException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private static final String filePath = "src/main/resources/city_ru.csv";
    public static void main(String[] args) throws IOException {    
        var path = Paths.get(Main.filePath);
        var parser = new Parser();
        List<City> cities = parser.getCities(path);
    }
    //Сортировка списка городов по наименованию в алфавитном порядке по убыванию без учета регистра;
    public static void sortByCityName(List<City> cities){
        cities.sort(Comparator.comparing(City::getName, String.CASE_INSENSITIVE_ORDER));
    }
    //Сортировка списка городов по федеральному округу и наименованию города внутри каждого федерального округа
    //в алфавитном порядке по убыванию с учетом регистра;
    public static void sortByDistrictAndName(List<City> cities){
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }
    
    public static void findMaxPopulationAndIndex(List<City> cities){
        int[] ints = cities.stream().mapToInt(City::getPopulation).toArray();
        var max = 0;
        var maxIndex = 0;
        for (int i = 0; i < ints.length; i++) {
            if(ints[i] > max){
                max = ints[i];
                maxIndex = i;
            }
        }
        System.out.printf("[%s] - %s%n", maxIndex, max);
    }
    public static Map<String, Long> groupingByRegion(List<City> cities){
        return cities.stream().collect(Collectors.groupingBy(City::getRegion, Collectors.counting()));
    }
}
