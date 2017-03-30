import java.util.ArrayList;  
import java.util.LinkedHashMap;  
import java.util.Iterator;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
/** 
 * Created by ajtdnyy on 13-9-3. 
 */  
public class ChineseAddressParser {  
  
    static final String reg = "[\u4e00-\u9fa5]";  
    static final Pattern ms_Pattern_guo = Pattern.compile("中国");  
    static final Pattern ms_Pattern_jinjiao = Pattern.compile("近郊");  
    static final Pattern ms_Pattern_sheng = Pattern.compile(reg + "+?省");  
    static final Pattern ms_Pattern_shi = Pattern.compile(reg + "+?市(?!场)");  
    static final Pattern ms_Pattern_qu = Pattern.compile(reg + "+?区");
    static final Pattern ms_Pattern_zhou = Pattern.compile(reg + "?州");
    static final Pattern ms_Pattern_meng = Pattern.compile(reg + "?盟");
    static final Pattern ms_Pattern_qi = Pattern.compile(reg + "?旗");
    static final Pattern ms_Pattern_xiang = Pattern.compile(reg + "+?乡");  
    static final Pattern ms_Pattern_xian = Pattern.compile(reg + "+?县");  
    static final Pattern ms_Pattern_dao = Pattern.compile(reg + "+?道");  
    static final Pattern ms_Pattern_hutong = Pattern.compile(reg + "+?胡同");  
    static final Pattern ms_Pattern_nongtang = Pattern.compile(reg + "+?(弄堂|弄)");  
    static final Pattern ms_Pattern_jie = Pattern.compile(reg + "+?街");  
    static final Pattern ms_Pattern_xiangg = Pattern.compile(reg + "+?巷");  
    static final Pattern ms_Pattern_lu = Pattern.compile(reg + "+?路");  
    static final Pattern ms_Pattern_cun = Pattern.compile(reg + "+?村");  
    static final Pattern ms_Pattern_zhen = Pattern.compile(reg + "+?镇");  
    static final Pattern ms_Pattern_hao = Pattern.compile("[甲_乙_丙_0-9_-]+?号");  
    static final Pattern ms_Pattern_point = Pattern.compile(reg + "+?(?:广场|酒店|饭店|宾馆|中心|大厦|百货|大楼|商城)");  
    static final Pattern ms_Pattern_ditie = Pattern.compile("地铁" + reg + "+?线(?:" + reg + "+?站)?");  
    static final Pattern ms_Pattern_province = Pattern.compile(reg + "{2,10}?(?:省|特区|自治区|特别行政区)");  
    static final Pattern ms_Pattern_city = Pattern.compile(reg + "+?(?:市|地区|自治州|盟)");  
    static final Pattern ms_Pattern_district = Pattern.compile(reg + "+?(?:区|旗)");
    static final Pattern ms_Pattern_county = Pattern.compile(reg + "+?(?:乡|县)");  
    static final Pattern ms_Pattern_street = Pattern.compile(reg + "+?街道");  
    static final Pattern ms_Pattern_road = Pattern.compile(reg + "+?(?:胡同|弄堂|街|巷|路|道)");  
    static final Pattern ms_Pattern_roadnear = Pattern.compile("(?<=近)" + reg + "+?(?:胡同|弄堂|街|巷|路|道)");  
    static final Pattern ms_Pattern_ip = Pattern.compile(reg + "+?(?:开发区|科技区|园区)");  
    static final Pattern ms_Pattern_zone = Pattern.compile(reg + "+?(?:小区|社区|新村)");  
    static final Pattern ms_Pattern_village = Pattern.compile(reg + "+?村");  
    static final Pattern ms_Pattern_town = Pattern.compile(reg + "+?镇");  
    static final Pattern ms_Pattern_number = Pattern.compile("[甲_乙_丙_0-9_-]+号");  
    static final Pattern ms_Pattern_plaza = Pattern.compile(reg + "+?(?:广场|酒店|饭店|宾馆|中心|大厦|百货|大楼|商城)");  
    static final Pattern ms_Pattern_underground = Pattern.compile("地铁" + reg + "+?线(?:" + reg + "+?站)?");  
    
    static final Splitter ms_splitter_guo = new Splitter(ms_Pattern_guo, new Pattern[]{ms_Pattern_guo});  
    static final Splitter ms_splitter_sheng = new Splitter(ms_Pattern_sheng, new Pattern[]{ms_Pattern_province});  
    static final Splitter ms_splitter_shi = new Splitter(ms_Pattern_shi, new Pattern[]{ms_Pattern_city}, false);  
    static final Splitter ms_splitter_jinjiao = new Splitter(ms_Pattern_jinjiao, new Pattern[]{ms_Pattern_jinjiao});  
    static final Splitter ms_splitter_qu = new Splitter(ms_Pattern_qu, new Pattern[]{ms_Pattern_province, ms_Pattern_city, ms_Pattern_zone, ms_Pattern_ip, ms_Pattern_district}, false);  
    static final Splitter ms_splitter_xiang = new Splitter(ms_Pattern_xiang, new Pattern[]{ms_Pattern_county});  
    static final Splitter ms_splitter_xian = new Splitter(ms_Pattern_xian, new Pattern[]{ms_Pattern_county});  
    static final Splitter ms_splitter_dao = new Splitter(ms_Pattern_dao, new Pattern[]{ms_Pattern_street, ms_Pattern_roadnear, ms_Pattern_road}, false);  
    static final Splitter ms_splitter_hutong = new Splitter(ms_Pattern_hutong, new Pattern[]{ms_Pattern_roadnear, ms_Pattern_road}, false);  
    static final Splitter ms_splitter_nongtang = new Splitter(ms_Pattern_nongtang, new Pattern[]{ms_Pattern_roadnear, ms_Pattern_road}, false);  
    static final Splitter ms_splitter_jie = new Splitter(ms_Pattern_jie, new Pattern[]{ms_Pattern_roadnear, ms_Pattern_road}, false);  
    static final Splitter ms_splitter_lu = new Splitter(ms_Pattern_lu, new Pattern[]{ms_Pattern_roadnear, ms_Pattern_road}, false);  
    static final Splitter ms_splitter_xiangg = new Splitter(ms_Pattern_xiangg, new Pattern[]{ms_Pattern_roadnear, ms_Pattern_road}, false);  
    static final Splitter ms_splitter_cun = new Splitter(ms_Pattern_cun, new Pattern[]{ms_Pattern_zone, ms_Pattern_village});  
    static final Splitter ms_splitter_zhen = new Splitter(ms_Pattern_zhen, new Pattern[]{ms_Pattern_town});  
    static final Splitter ms_splitter_hao = new Splitter(ms_Pattern_hao, new Pattern[]{ms_Pattern_number});  
    static final Splitter ms_splitter_point = new Splitter(ms_Pattern_point, new Pattern[]{ms_Pattern_plaza});  
    static final Splitter ms_splitter_ditie = new Splitter(ms_Pattern_ditie, new Pattern[]{ms_Pattern_underground});  
    static final Splitter ms_splitter_zhou = new Splitter(ms_Pattern_zhou, new Pattern[]{ms_Pattern_city});
    static final Splitter ms_splitter_meng = new Splitter(ms_Pattern_meng, new Pattern[]{ms_Pattern_city});
    static final Splitter ms_splitter_qi = new Splitter(ms_Pattern_qi, new Pattern[]{ms_Pattern_district});
    
    static final Splitter[] ms_defaultsplitters = new Splitter[]{  
            ms_splitter_guo,  
            ms_splitter_sheng,  
            ms_splitter_shi,  
            ms_splitter_qu,  
            ms_splitter_xiang,  
            ms_splitter_xian,  
            ms_splitter_dao,  
            ms_splitter_hutong,  
            ms_splitter_nongtang,  
            ms_splitter_jie,  
            ms_splitter_xiangg,  
            ms_splitter_lu,  
            ms_splitter_cun,  
            ms_splitter_zhen,  
            ms_splitter_hao,  
            ms_splitter_point,  
            ms_splitter_ditie,  
            ms_splitter_jinjiao,
            ms_splitter_zhou,
            ms_splitter_meng,
            ms_splitter_qi
    };  
  
    private static LinkedHashMap<Integer, Splitter> split(String src, Splitter[] splitters) {  
        LinkedHashMap<Integer, Splitter> splitterdic = new LinkedHashMap<Integer, Splitter>();  
        for (Splitter s : splitters) {  
            Matcher m = s.pattern.matcher(src);  
            while (m.find()) {  
                splitterdic.put(m.start() + m.group().length(), s);  
                if (s.flag) {  
                    break;  
                }  
            }  
        }  
        return splitterdic;  
    }  
  
    private static ArrayList<Segment> recognize(String src, LinkedHashMap<Integer, Splitter> splitterdic) {  
        Segment s;  
        int index = 0;  
        ArrayList<Segment> segments = new ArrayList<Segment>();  
        if (src.length() > 0) {  
            for (Integer key : splitterdic.keySet()) {  
                Splitter value = splitterdic.get(key);  
                if (key > index && key <= src.length()) {  
                    for (Pattern r : value.patterns) {  
                        s = segmentRecognize(src.substring(index, key), r);  
                        if (s != null) {  
                            segments.add(s);  
                            break;  
                        }  
                    }  
                    index = key;  
                }  
            }  
        }  
        return segments;  
    }  
  
    private static Segment segmentRecognize(String src, Pattern r) {  
        Matcher m = r.matcher(src);  
        if (m.matches()) {  
            return new Segment(m.group(), r);  
        } else {  
            return null;  
        }  
    }  
  
    private static ArrayList<String> segmentsGetStringListForPattern(ArrayList<Segment> segments, Pattern r) {  
        ArrayList<String> ss = new ArrayList<String>();  
        for (Iterator<Segment> it = segments.iterator(); it.hasNext();) {  
            Segment s = it.next();  
            if (s.pattern == r) {  
                ss.add(s.value);  
            }  
        }  
        return ss;  
    }  
  
    private static String segmentsGetStringForPattern(ArrayList<Segment> segments, Pattern r) {  
        for (Iterator<Segment> it = segments.iterator(); it.hasNext();) {  
            Segment s = it.next();  
            if (s.pattern == r) {  
                return s.value;  
            }  
        }  
        return null;  
    }  
  
    public static void main(String[] args) {  
        System.out.println(ChineseAddressParser.parse("北京市海淀区中关村北大街37号天龙大厦3层"));  
        System.out.println(ChineseAddressParser.parse("福州市台江区群众路278号源利明珠大厦6楼"));  
        System.out.println(ChineseAddressParser.parse("北京西城区百万庄大街68号6楼"));  
        System.out.println(ChineseAddressParser.parse("新疆维吾尔自治区喀什地区"));
        System.out.println(ChineseAddressParser.parse("湖南省湘西自治州"));
        System.out.println(ChineseAddressParser.parse("内蒙古自治区阿拉善盟额济纳旗"));
    }  
  
    public static ChineseAddress parse(String source) {  
        source = source.replace(".", "").replace("，", "").replace(",", "");  
        ArrayList<Segment> segments = recognize(source, split(source, ms_defaultsplitters));  
        ChineseAddress ca = new ChineseAddress();  
        ca.source = source;  
  
        ca.nation = segmentsGetStringForPattern(segments, ms_Pattern_guo);  
        ca.province = segmentsGetStringForPattern(segments, ms_Pattern_province);  
        ca.city = segmentsGetStringForPattern(segments, ms_Pattern_city);  
        ca.district = segmentsGetStringForPattern(segments, ms_Pattern_district);  
        ca.county = segmentsGetStringForPattern(segments, ms_Pattern_county);  
        ca.street = segmentsGetStringForPattern(segments, ms_Pattern_street);  
  
        ArrayList<String> roads = segmentsGetStringListForPattern(segments, ms_Pattern_road);  
        ArrayList<String> near = segmentsGetStringListForPattern(segments, ms_Pattern_roadnear);  
        for (Iterator<String> it = near.iterator(); it.hasNext();) {  
            roads.add(it.next());  
  
        }  
        ca.roads = roads;  
  
        ca.underground = segmentsGetStringForPattern(segments, ms_Pattern_underground);  
        ca.number = segmentsGetStringForPattern(segments, ms_Pattern_number);  
        ca.plaza = segmentsGetStringForPattern(segments, ms_Pattern_plaza);  
        ca.ip = segmentsGetStringForPattern(segments, ms_Pattern_ip);  
        ca.town = segmentsGetStringForPattern(segments, ms_Pattern_town);  
        ca.village = segmentsGetStringForPattern(segments, ms_Pattern_village);  
        return ca;  
    }  
}  
