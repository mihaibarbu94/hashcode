import java.util.List;

/**
 * Created by Mihai on 23.03.2016.
 */
class LocationAndTimes {
    Location location;
    List<Times> times;

    public LocationAndTimes(Location location, List<Times> times) {
        this.location = location;
        this.times = times;
    }

    public Location getLocation() {
        return location;
    }

    public List<Times> getTimes() {
        return times;
    }

    @Override
    public String toString() {
        return "LocationAndTimes{" +
                "location=" + location.toString() +
                ", times=" + times.toString() +
                '}';
    }
}
