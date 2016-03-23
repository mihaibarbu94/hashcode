import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = readFile("src/constellation.in");
        List<Satellite> satelliteList = new LinkedList<Satellite>();
        List<ImageCollection> collectionList = new LinkedList<ImageCollection>();
        List<LocationAndTimes> allLocations = new LinkedList<LocationAndTimes>();
        String[] tokens = input.split("\n");

        int currentTurn = 0;

        int T = Integer.parseInt(tokens[0]);
        int S = Integer.parseInt(tokens[1]);
        int i;

        for (i = 2; i < 2 + S; i++) {
            satelliteList.add(constructSatellite(tokens[i], i - 2));
        }

        int C = Integer.parseInt(tokens[i]);
        i++;
        int j = 0;
        while (j < C) {
            ImageCollection newCollection = constructImageCollection(tokens, i);
            collectionList.add(newCollection);
            i = i+ 1 + newCollection.locations.size() + newCollection.times.size();
            j++;
        }

        for (ImageCollection c : collectionList) {
            //System.out.println(c.toString());
            for (Location l : c.locations) {
                allLocations.add(new LocationAndTimes(l, c.getTimes()));
            }
        }
//        Satellite s = satelliteList.get(0);
//        System.out.println(s.toString());
//        Satellite s1 = satelliteList.get(1);
//        System.out.println(s1.toString());
//        nextTurn(satelliteList);
//        System.out.println(s.toString());
//        System.out.println(s1.toString());


        method1(satelliteList, allLocations, T);

        System.out.println(T);
        System.out.println();
        System.out.println(i);
    }

    private static void nextTurn(List<Satellite> satellites){
        for (Satellite s: satellites){
            //System.out.println(s.toString());
            updateSatellite(s);
        }

    }

    private static boolean canSee(Satellite satellite, Location location){
//        Satellite temp = new Satellite(satellite.getPsi(), satellite
//                .getLambda(), satellite.getVelocity(), satellite.getW(),
//                satellite.getD(), satellite.getDelPsi(), satellite
//                .getDelLambda(), satellite.getId());
//
//        updateSatellite(temp);

        int lambdaDif = location.getLambda() - (satellite.getLambda() +
                satellite.getDelLambda());
        int psiDif = location.getPsi() - (satellite.getPsi() + satellite.getDelPsi());

//        int newDelPsi    = temp.getDelPsi()    + psiDif;
//        int newDelLambda = temp.getDelLambda() + lambdaDif;
        boolean returnValue =
                   Math.abs(lambdaDif) <= satellite.getW() * satellite.getNumTurns()
                && Math.abs(psiDif)    <= satellite.getW() * satellite.getNumTurns()
                && Math.abs(satellite.getDelLambda() + lambdaDif) <= satellite.getD()
                && Math.abs(satellite.getDelPsi() + psiDif) <= satellite.getD();

        if (returnValue) {
            satellite.setDelLambda(satellite.getDelLambda() + lambdaDif);
            satellite.setDelPsi(satellite.getDelPsi() + psiDif);
        }
        return returnValue;
    }

    private static void updateSatellite(Satellite satellite){
        int sum =  satellite.getPsi() + satellite.getVelocity();
        if (-324000 <= sum && sum <= 324000 ) {
            //System.out.println(satellite.toString());
            satellite.setPsi(sum);
            satellite.setLambda(satellite.getLambda() - 15);
            //System.out.println(satellite.toString());
        } else if (sum > 324000) {
            //System.out.println(satellite.toString());
            satellite.setPsi(648000 - sum);
            satellite.setLambda(-648000 + (satellite.getLambda() - 15));
            satellite.setVelocity(-1 * satellite.getVelocity());
            //System.out.println(satellite.toString());
        } else if (sum < -324000) {
            //System.out.println(satellite.toString());
            satellite.setPsi(-648000 - sum);
            satellite.setLambda(-648000 + (satellite.getLambda() - 15));
            satellite.setVelocity(-1 * satellite.getVelocity());
            //System.out.println(satellite.toString());
        } else {
            System.out.println("FUCCCCCKKKKKKK/n/n/n/n");
        }
    }

    private static Satellite constructSatellite(String input, int id) {
        String[] satTokens = input.split(" ");
        int psi      = Integer.parseInt(satTokens[0]);
        int lambda   = Integer.parseInt(satTokens[1]);
        int velocity = Integer.parseInt(satTokens[2]);
        int w        = Integer.parseInt(satTokens[3]);
        int d        = Integer.parseInt(satTokens[4]);
        return new Satellite(psi, lambda, velocity, w, d, id);
    }

    public static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    private static ImageCollection constructImageCollection(String[] tokens, int i) {
        int value = -1;
        List<Location> locations = new LinkedList<Location>();
        List<Times> times        = new LinkedList<Times>();

        String[] colTokens = tokens[i].split(" ");

        value =  Integer.parseInt(colTokens[0]);
        int numLocations = Integer.parseInt(colTokens[1]);
        int numTimes = Integer.parseInt(colTokens[2]);

        for(int k = 0; k < numLocations; k++) {
            String[] locationTokens = tokens[i + 1 + k].split(" ");
            int psi    = Integer.parseInt(locationTokens[0]);
            int lambda = Integer.parseInt(locationTokens[1]);
            locations.add(new Location(psi, lambda));
        }

        for(int p = 0; p < numTimes; p++) {
            String[] timeTokens = tokens[i + 1 + p + numLocations].split(" ");
            int start    = Integer.parseInt(timeTokens[0]);
            int end      = Integer.parseInt(timeTokens[1]);
            times.add(new Times(start, end));
        }

        return new ImageCollection(value, locations, times);
    }

    private static void setCamera(int newDelPsi, int newDelLambda, Satellite
            satellite){
        satellite.setDelPsi(newDelPsi);
        satellite.setDelLambda(newDelLambda);
    }

    private static boolean timeIsValid(LocationAndTimes l, int currentTurn) {

        for (Times t : l.times) {
            if (t.getStart() <= currentTurn && currentTurn <= t.getEnd()) {
                return true;
            }
        }
        return false;
    }

    private static void method1(List<Satellite> satelliteList,
                                List<LocationAndTimes> locationList, int
                                        turns) throws FileNotFoundException, UnsupportedEncodingException {
        int picturesTaken    = 0;
        List<Result> results = new LinkedList<Result>();
        for (int i = 0; i < 1000; i++){
            for (Satellite s: satelliteList) {
                for(LocationAndTimes l: locationList){
                    if (canSee(s, l.location) && timeIsValid(l, i)){
                        System.out.println("true/n/n/n/n/n/n");
                        picturesTaken++;
                        int lambdaDif = s.getLambda() + s.getDelLambda() -
                                l.location.getLambda();
                        int psiDif    = s.getPsi() +    s.getDelPsi() -
                                l.location.getPsi();

                        int newDelPsi    = s.getDelPsi()    + psiDif;
                        int newDelLambda = s.getDelLambda() + lambdaDif;
                        setCamera(newDelPsi, newDelLambda, s);
                        results.add(new Result(l.location.getPsi(), l
                                .location.getLambda(), i, s.getId()));
                        locationList.remove(l);
                        //System.out.println(true);
                        s.setNumTurns(1);
                        break;
                    } else {
                        s.setNumTurns(s.getNumTurns() + 1);
                    }
                }
            }
            nextTurn(satelliteList);
        }

        PrintWriter writer = new PrintWriter("src/result.out", "UTF-8");
        writer.println(picturesTaken);
        for(Result r: results) {
            writer.print(r.toString());
        }
        writer.close();
    }
}

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

class Satellite {
    private int psi;
    private int lambda;
    private int velocity;
    private int w;
    private int d;
    private int numTurns;

    public int getId() {
        return id;
    }

    public int getNumTurns() {
        return numTurns;
    }

    public void setNumTurns(int numTurns) {
        this.numTurns = numTurns;
    }

    private int delPsi;
    private int delLambda;
    private int id;

    public void setDelPsi(int delPsi) {
        this.delPsi = delPsi;
    }

    public void setDelLambda(int delLambda) {
        this.delLambda = delLambda;
    }

    public int getDelLambda() {
        return delLambda;
    }

    public int getDelPsi() {
        return delPsi;
    }

    public void setPsi(int psi) {
        this.psi = psi;
    }

    public void setLambda(int lambda) {
        if (lambda < -648000) {
            this.lambda = 648000 - (-lambda % 648000);
        } else {
            this.lambda = lambda % 648000;
        }
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getPsi() {
        return psi;
    }

    public int getLambda() {
        return lambda;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getW() {
        return w;
    }

    public int getD() {
        return d;
    }

    @Override
    public String toString() {
        return "Satellite{" +
                "psi=" + psi +
                ", lambda=" + lambda +
                ", velocity=" + velocity +
                ", w=" + w +
                ", d=" + d +
                ", id=" + id +
                '}';
    }

    public Satellite(int psi, int lambda, int velocity, int w, int d, int
            delPsi, int delLambda, int id) {
        this.psi = psi;
        this.lambda = lambda;
        this.velocity = velocity;
        this.w = w;
        this.d = d;
        this.delPsi = delPsi;
        this.delLambda = delLambda;
        this.id = id;
    }

    public Satellite(int psi, int lambda, int velocity, int w, int d, int id) {
        this.psi = psi;
        this.lambda = lambda;
        this.velocity = velocity;
        this.w = w;
        this.d = d;
        this.delPsi = 0;
        this.delLambda = 0;
        this.id = id;
    }
}

class Location {
    private int psi;
    private int lambda;

    public int getPsi() {
        return psi;
    }

    public int getLambda() {
        return lambda;
    }

    public void setLambda(int lambda) {
        if (lambda < -648000) {
            this.lambda = 648000 - (-lambda % 648000);
        } else {
            this.lambda = lambda % 648000;
        }
    }

    @Override
    public String toString() {
        return "Location{" +
                "psi=" + psi +
                ", lambda=" + lambda +
                '}';
    }

    public Location(int psi, int lambda) {
        this.psi = psi;
        this.lambda = lambda;
    }
}

class Result{
    private int psi;
    private int lambda;
    private int turn;
    private int satellite;

    @Override
    public String toString() {
        return  psi + " " + lambda + " " + turn + " " + satellite + '\n';
    }

    public Result(int psi, int lambda, int turn, int satellite) {
        this.psi = psi;
        this.lambda = lambda;
        this.turn = turn;
        this.satellite = satellite;
    }
}

class Times {
    private int start;
    private int end;

    @Override
    public String toString() {
        return "Times{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public Times(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

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