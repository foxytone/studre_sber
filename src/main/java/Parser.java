import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {
    public List<City> getCities(Path path) throws IOException {
        List<City> cities = new ArrayList<>();
        var scanner = new Scanner(path).useDelimiter("\\s*\n\\s*");
        while (scanner.hasNext()) {
            cities.add(getCityModel(scanner.next()));
        }
        scanner.close();
        return cities;
    }
    
    private City getCityModel(String line){
        var scanner = new Scanner(line);
        scanner.useDelimiter("\\s*;\\s*");
        //skip id
        scanner.nextInt();

        var name = scanner.next();
        var region = scanner.next();
        var district = scanner.next();
        var population = scanner.nextInt();
        var foundation = "";
        try{
            foundation = scanner.next();
        }
        catch (NoSuchElementException ignored){
            //empty on purpose
        }
        return new City(name, region, district, population, foundation);
    }
}
