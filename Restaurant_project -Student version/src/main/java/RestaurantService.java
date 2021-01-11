import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurant=null;

        Optional<Restaurant> searchedRestaurant=  restaurants.stream().filter(p-> p.getName().equalsIgnoreCase(restaurantName)).findAny();
        if(searchedRestaurant.isPresent()) {
           restaurant = restaurants.stream().filter(p -> p.getName().equalsIgnoreCase(restaurantName)).findAny().get();
         }else {
            throw new restaurantNotFoundException("Restaurant not found");
        }

         return restaurant;

    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public double getTotalCostOfOrder(List<Item> items){

        double totalCost=0.0;
        if(items !=null) {
            for (Item item : items){
                totalCost+=item.getPrice();
            }

        }
        return totalCost;
    }

}
