import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Helper {
    //Return current date
    public String getCheckInDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        String output = sdf.format(c.getTime());
        return output;
    }

//Return current date + 7 days
public String getCheckOutDate(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.DATE, 7); // Adding 5 days
    String output = sdf.format(c.getTime());
return output;
}

}
