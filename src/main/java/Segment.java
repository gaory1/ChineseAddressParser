import java.util.regex.Pattern;  
  
/** 
 * Created by ajtdnyy on 13-9-3. 
 */  
public class Segment {  
  
    String value;  
    Pattern pattern;  
  
    public Segment(String value, Pattern pattern) {  
        this.value = value;  
        this.pattern = pattern;  
    }  
}  
