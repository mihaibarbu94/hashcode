import java.util.List;

/**
 * Created by Mihai on 23.03.2016.
 */
class ImageCollection {
    int value;

    @Override
    public String toString() {
        return "ImageCollection{" +
                "value=" + value +
                ", locations=" + locations.toString() +
                ", times=" + times.toString() +
                '}';
    }

    public int getValue() {
        return value;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Times> getTimes() {
        return times;
    }

    List<Location> locations;
    List<Times> times;

    public ImageCollection(int value, List<Location> locations, List<Times>
            times) {
        this.value = value;
        this.locations = locations;
        this.times = times;
    }
}
