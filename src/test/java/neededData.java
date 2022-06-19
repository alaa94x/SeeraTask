import java.util.*;

public class neededData {
    private String placeId;

    public void setPlaceId(String placeId){
       this.placeId=placeId;
    }

    public String getPlaceId(){
        return placeId;
    }

    public static String getUri(){
        return "https://www.almosafer.com";
    }

    public static String getToken(){
        return "skdjfh73273$7268u2j89s";
    }


    public static HashMap<String, String> getTravelInfo() {
        int randomNum = new Random().nextInt(6);

        HashMap<String, String> cities = new HashMap<>();
        HashMap<String, String> desiredData = new HashMap<>();
        cities.put("United Arab Emirates", "Abu Dhabi");
        cities.put("Jordan", "Amman");
        cities.put("France", "Paris");
        cities.put("Germany", "Munich");
        cities.put("USA", "Los Angeles");
        cities.put("Saudi Arabia", "Riyadh");
        cities.put("Kuwait", "Kuwait");

        desiredData.put("country", cities.keySet().toArray()[randomNum].toString());
        desiredData.put("city", cities.values().toArray()[randomNum].toString());
        return desiredData;
    }


     /*This method returns roomsInfo if adult number less than or equal 0 will return 0
       kids stand for the number of kids, regarding this will generate randoms ages for them
       if the kids equal 0 will return empty list*/

    public static Object getRoomsInfo(int adults, int kids) {

        Map<String, Object> roomsInfo = new HashMap<String, Object>();
        List<String> emptyList = Collections.<String>emptyList();
        Map<String, Object> details = new HashMap<>();
        List<Integer> kidsList = new ArrayList<>(kids);
        if (adults <= 0) {
            details.put("adultsCount", 1);
        } else {
            details.put("adultsCount", adults);
        }

        if (kids <= 0) {
            details.put("kidsAges", emptyList);
        } else {
            for (int i = 0; i < kids; i++)
                kidsList.add(new Random().nextInt(10) + 1);
            details.put("kidsAges", kidsList);
        }
        roomsInfo.put("roomsInfo", Arrays.asList(details));

        return roomsInfo.get("roomsInfo");

    }
}
