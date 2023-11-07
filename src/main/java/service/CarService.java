package service;

import model.Car;
import org.primefaces.model.FilterMeta;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.*;

/**
 * Simulate server-side processing logic for dataGrid
 */
@ManagedBean
@ApplicationScoped
public class CarService {
    //Simulate server data
    private List<Car> cars;
    private final static String[] colors;

    private final static String[] brands;

    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";

        brands = new String[10];
        brands[0] = "BMW";
        brands[1] = "Mercedes";
        brands[2] = "Volvo";
        brands[3] = "Audi";
        brands[4] = "Renault";
        brands[5] = "Fiat";
        brands[6] = "Volkswagen";
        brands[7] = "Honda";
        brands[8] = "Jaguar";
        brands[9] = "Ford";
    }


    public CarService() {
        //Initialize the simulated car collection data ，The simulation server has 200 pieces of data.
        cars = createCars(200);
    }

    public List<Car> createCars(int size) {
        List<Car> list = new ArrayList<Car>();
        for (int i = 0; i < size; i++) {
            list.add(new Car(getRandomId(), getRandomBrand(), getRandomYear(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
        }

        return list;
    }

    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }

    private String getRandomColor() {
        return colors[(int) (Math.random() * 10)];
    }

    private String getRandomBrand() {
        return brands[(int) (Math.random() * 10)];
    }

    private int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }

    private boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true : false;
    }

    public List<String> getColors() {
        return Arrays.asList(colors);
    }

    public List<String> getBrands() {
        return Arrays.asList(brands);
    }

    public int countCar(Map<String, FilterMeta> filters) {
        return cars.size();
    }

    /**
     * Simulate obtaining a specified number of rows of data from the server
     *
     * @param first
     * @param rows
     * @return
     */
    public List<Car> findPage(int first, int rows) {
        int endIndex = first + rows;
        if (endIndex > cars.size()) {
            endIndex = cars.size();
        }
        return cars.subList(first, endIndex);
    }

    public Car findCarById(String carId) {
        return cars.stream().filter(x->x.getId().equals(carId)).findFirst().get();
    }
}
