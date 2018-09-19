package healthsparq;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class App {
    private static List<Pet> pets = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length < 2) { // checking if all required params were provided
            System.out.println("Not enough data, please provide CSV file name and search params (like \"pets.csv name=Yoda\" or \"pets.csv type=dog,zipcode=90210\")");
            return;
        }

        String line;
        String cvsSplitBy = ",";
        String[] fieldNames;

        //importing data
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            line = br.readLine();
            if (line == null) {
                return; // exit if the CSV file was empty
            } else {
                fieldNames = line.split(cvsSplitBy);
            }
            while ((line = br.readLine()) != null) {
                String[] petLine = line.split(cvsSplitBy);
                if (petLine.length == fieldNames.length) { // check if some of the lines in CSV doesn't contain all required data
                    Pet pet = new Pet(petLine[0], petLine[1], petLine[2], petLine[3], petLine[4]);
                    pets.add(pet);
                } else {
                    System.out.println("Wrong pet row: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //filtering
        //We are taking the complete list and filter it step by step. Each step reduces amount of pets to be checked so it's working
        // like a multiple checks united with AND.
        //We can also build a single method that will check all the fields at once, but it will be much more complicated
        //since during writing this program we don't know which fields will be in a query.
        List<Pet> filteredPets = new ArrayList<>(pets); // creating new array so we won't affect whole data storage
        String[] filterPairs = args[1].split(",");
        for (String filterPair : filterPairs) {
            filteredPets = filteredPets.stream()
                    .filter(getFilter(filterPair.split("="))) //splitting entered search string into field name and field value
                    .collect(Collectors.toList()); // collecting for multi-param search
        }

        //Writing found records to the console
        if (filteredPets.isEmpty()) {
            System.out.println("No pets found for your search.");
        } else {
            System.out.println("Found pets:");
            for (Pet pet : filteredPets) {
                System.out.println(String.format("%s (%s, %s) - %s", pet.getName(), pet.getType(), pet.getGender(), pet.getZipCode()));
            }
        }
    }

    /**
     * Forming a filtering Predicate from a pair of strings.
     * @param filter Pair or strings, first of them is field name, second is its value to be searched for,
     * @return Predicate formed from provided params.
     */
    private static Predicate<Pet> getFilter(String[] filter) {
        return pet -> {
            switch (filter[0].toLowerCase()) {
//we don't need to search by names or id, but we are able to do it too
//                case "id":
//                    return pet.getId().equalsIgnoreCase(filter[1]);
//                case "name":
//                    return pet.getName().equalsIgnoreCase(filter[1]);
                case "type":
                    return pet.getType().equalsIgnoreCase(filter[1]);
                case "gender":
                    return pet.getGender().equalsIgnoreCase(filter[1]);
                case "zipcode":
                    return pet.getZipCode().equalsIgnoreCase(filter[1]);
                default:
                    return true;
            }
        };
    }
}