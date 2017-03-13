import java.util.List;  
  
/** 
 * Created by ajtdnyy on 13-9-3. 
 */  
public class ChineseAddress {  
  
    public String source;  
    public String nation;  
    public String province;  
    public String city;  
    public String county;  
    public String district;  
    public String street;  
    public List<String> roads;  
    public String number;  
    public String plaza;  
    public String ip;  
    public String town;  
    public String village;  
    public String zone;  
    public String underground;  
    public List<String> notes;  
    public List<String> noises;  
    private static final String SEPARATOR = System.getProperty("line.separator");  
  
    public String toString() {  
        String s = "src: " + source + SEPARATOR;  
        if (nation != null) {  
            s = s + "nat: " + nation + SEPARATOR;  
        }  
        if (province != null) {  
            s = s + "pro: " + province + SEPARATOR;  
        }  
        if (city != null) {  
            s = s + "cit: " + city + SEPARATOR;  
        }  
        if (county != null) {  
            s = s + "cou: " + county + SEPARATOR;  
        }  
        if (district != null) {  
            s = s + "dis: " + district + SEPARATOR;  
        }  
        if (street != null) {  
            s = s + "str: " + street + SEPARATOR;  
        }  
        if (number != null) {  
            s = s + "num: " + number + SEPARATOR;  
        }  
        if (plaza != null) {  
            s = s + "pla: " + plaza + SEPARATOR;  
        }  
        if (ip != null) {  
            s = s + "idp: " + ip + SEPARATOR;  
        }  
        if (town != null) {  
            s = s + "twn: " + town + SEPARATOR;  
        }  
        if (village != null) {  
            s = s + "vil: " + village + SEPARATOR;  
        }  
        if (zone != null) {  
            s = s + "zon: " + zone + SEPARATOR;  
        }  
        if (underground != null) {  
            s = s + "udg: " + underground + SEPARATOR;  
        }  
        if (roads != null) {  
            s = s + "rod: ";  
            for (int i = 0; i < roads.size(); i++) {  
                String r = roads.get(i);  
                if (r == roads.get(0)) {  
                    s = s + r;  
                } else {  
                    s = s + " / " + r;  
                }  
            }  
            s = s + SEPARATOR;  
        }  
        if (notes != null) {  
            s = s + "not: ";  
            for (int i = 0; i < notes.size(); i++) {  
                String n = notes.get(i);  
                if (n == roads.get(0)) {  
                    s = s + n;  
                } else {  
                    s = s + " / " + n;  
                }  
            }  
            s = s + SEPARATOR;  
        }  
        if (noises != null) {  
            s = s + "noi: ";  
            for (int i = 0; i < noises.size(); i++) {  
                s = s + noises.get(i) + " / ";  
            }  
            s = s + SEPARATOR;  
        }  
        return s;  
    }  
}  
