package cn.nero.community.mock;

import cn.nero.community.domain.City;
import cn.nero.community.domain.Inoculation;
import cn.nero.community.domain.Resident;
import cn.nero.community.domain.Returnees;
import cn.nero.community.utils.DateTimeUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/10
 */
public class Mock {

    /*******************************IdCard****************************************/
    public static Map<String, String> getIdCard() {
        StringBuilder sb = new StringBuilder();
        // areaId
        String areaId = getAreaId();
        // Birth
        Map<String, String> map = getBirth();
        // random tail number
        String tailNumber = getRandom();
        map.put("idCard", sb.append(areaId).append(map.get("birth")).append(tailNumber).toString());
        return map;
    }

    private static String getAreaId(){
        StringBuilder sb = new StringBuilder();
        // 六位区号
        String[] provinceId = new String[]{
                "210", "220", "230",
                "110", "120", "130",
                "140", "150", "310",
                "320", "330", "340",
                "350", "360", "370",
        };
        int len = provinceId.length;
        String areaId = String.valueOf((int) (Math.random() * 900) + 100);
        return sb.append(provinceId[new Random().nextInt(len)]).append(areaId).toString();

    }

    private static Map<String, String> getBirth(){
        String[] month = new String[]{
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"
        };
        String[] day = new String[]{
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"
        };
        Map<String, String> map = new HashMap<>();
        Integer year = (int) ((Math.random() * 60) + 1950);
        map.put("age", String.valueOf((2022 - year) + 1));
        int mLen = month.length;
        int dLen = day.length;
        int mRandom = new Random().nextInt(mLen);
        int dRandom = new Random().nextInt(dLen);
        String m = month[mRandom];
        String d = day[dRandom];
        // 如果闰年
        if (year % 4 == 0) {
            // 二月多一天
            if ("02".equals(m)) {
                if (Integer.parseInt(d) > 29) {
                    d = "29";
                }
            }
        } else {
            if ("02".equals(m)) {
                if (Integer.parseInt(d) > 28) {
                    d = "28";
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        map.put("birth", sb.append(year).append(m).append(d).toString());
        return map;
    }

    public static String getRandom(){
        char[] chars = "1234567890X".toCharArray();
        StringBuilder sb = new StringBuilder();
        int num = (int) (Math.random() * 900) + 100;
        sb.append(num).append(chars[new Random().nextInt(chars.length)]);
        return sb.toString();
    }

    /*******************************Name****************************************/
    private static String getName(String gender){
        String surname = "赵,钱,孙,李,周,吴,郑,王,冯,陈,褚,卫,蒋,沈,韩,杨," +
                "朱,秦,尤,许,何,吕,施,张,孔,曹,严,华,金,魏,陶,姜," +
                "戚,谢,邹,喻,柏,水,窦,章,云,苏,潘,葛,奚,范,彭,郎," +
                "鲁,韦,昌,马,苗,凤,花,方,俞,任,袁,柳,酆,鲍,史,唐," +
                "费,廉,岑,薛,雷,贺,倪,汤,滕,殷,罗,毕,郝,邬,安,常," +
                "乐,于,时,傅,皮,卞,齐,康,伍,余,元,卜,顾,孟,平,黄," +
                "和,穆,萧,尹,姚,邵,湛,汪,祁,毛,禹,狄,米,贝,明,臧," +
                "计,伏,成,戴,谈,宋,茅,庞,熊,纪,舒,屈,项,祝,董,梁," +
                "杜,阮,蓝,闵,席,季,麻,强,贾,路,娄,危,江,童,颜,郭," +
                "梅,盛,林,刁,钟,徐,邱,骆,高,夏,蔡,田,樊,胡,凌,霍," +
                "虞,万,支,柯,昝,管,卢,莫,经,房,裘,缪,干,解,应,宗," +
                "丁,宣,贲,邓,郁,单,杭,洪,包,诸,左,石,崔,吉,钮,龚," +
                "程,嵇,邢,滑,裴,陆,荣,翁,荀,羊,於,惠,甄,麴,家,封," +
                "芮,羿,储,靳,汲,邴,糜,松,井,段,富,巫,乌,焦,巴,弓," +
                "牧,隗,山,谷,车,侯,宓,蓬,全,郗,班,仰,秋,仲,伊,宫," +
                "宁,仇,栾,暴,甘,钭,厉,戎,祖,武,符,刘,景,詹,束,龙," +
                "叶,幸,司,韶,郜,黎,蓟,薄,印,宿,白,怀,蒲,邰,从,鄂," +
                "索,咸,籍,赖,卓,蔺,屠,蒙,池,乔,阴,欎,胥,能,苍,双," +
                "闻,莘,党,翟,谭,贡,劳,逄,姬,申,扶,堵,冉,宰,郦,雍," +
                "舄,璩,桑,桂,濮,牛,寿,通,边,扈,燕,冀,郏,浦,尚,农," +
                "温,别,庄,晏,柴,瞿,阎,充,慕,连,茹,习,宦,艾,鱼,容," +
                "向,古,易,慎,戈,廖,庾,终,暨,居,衡,步,都,耿,满,弘," +
                "匡,国,文,寇,广,禄,阙,东,殴,殳,沃,利,蔚,越,夔,隆," +
                "师,巩,厍,聂,晁,勾,敖,融,冷,訾,辛,阚,那,简,饶,空," +
                "曾,毋,沙,乜,养,鞠,须,丰,巢,关,蒯,相,查,後,荆,红," +
                "游,竺,权,逯,盖,益,桓,公,万俟,司马,上官,欧阳,夏侯," +
                "诸葛,闻人,东方,赫连,皇甫,尉迟,公羊,澹台,公冶,宗政," +
                "濮阳,淳于,单于,太叔,申屠,公孙,仲孙,轩辕,令狐,钟离," +
                "宇文,长孙,慕容,鲜于,闾丘,司徒,司空,亓官,司寇,仉,督," +
                "子车,颛孙,端木,巫马,公西,漆雕,乐正,壤驷,公良,拓跋," +
                "夹谷,宰父,谷梁,晋,楚,闫,法,汝,鄢,涂,钦,段干,百里," +
                "东郭,南门,呼延,归,海,羊舌,微生,岳,帅,缑,亢,况,后," +
                "有,琴,梁丘,左丘,东门,西门,商,牟,佘,佴,伯,赏,南宫," +
                "墨,哈,谯,笪,年,爱,阳,佟,第五,言,福,百,家,姓,终";
        String[] surnames = surname.split(",");
        String prefixNameFemale = "恨桃、依秋、依波、香巧、紫萱、涵易、忆之、幻巧、美倩、安寒、白亦、惜玉、碧春、怜雪、听南、念蕾、紫夏、凌旋、芷梦、凌寒、梦竹、千凡、丹蓉、慧贞、思菱、平卉、笑柳、雪卉、南蓉、谷梦、巧兰、绿蝶、飞荷、佳蕊、芷荷、怀瑶、慕易、若芹、紫安、曼冬、寻巧、雅昕、尔槐、以旋、初夏、依丝、怜南、傲菡、谷蕊、笑槐、飞兰、笑卉、迎荷、佳音、梦君、妙绿、觅雪、寒安、沛凝、白容、乐蓉、映安、依云、映冬、凡雁、梦秋、梦凡、秋巧、若云、元容、怀蕾、灵寒、天薇、翠安、乐琴、宛南、怀蕊、白风、访波、亦凝、易绿、夜南、曼凡、亦巧、青易、冰真、白萱、友安、海之、小蕊、又琴、天风、若松、盼菡、秋荷、香彤、语梦、惜蕊、迎彤、沛白、雁彬、易蓉、雪晴、诗珊、春冬、晴钰、冰绿、半梅、笑容、沛凝、映秋、盼烟、晓凡、涵雁、问凝、冬萱、晓山、雁蓉、梦蕊、山菡、南莲、飞双、凝丝、思萱、怀梦、雨梅、冷霜、向松、迎丝、迎梅、雅彤、香薇、以山、碧萱、寒云、向南、书雁、怀薇、思菱、忆文、翠巧、书文、若山、向秋、凡白、绮烟、从蕾、天曼、又亦、从语、绮彤、之玉、凡梅、依琴、沛槐、又槐、元绿、安珊、夏之、易槐、宛亦、白翠、丹云、问寒、易文、傲易、青旋、思真、雨珍、幻丝、代梅、盼曼、妙之、半双、若翠、初兰、惜萍、初之、宛丝、寄南、小萍、静珊、千风、天蓉、雅青、寄文、涵菱、香波、青亦、元菱、翠彤、春海、惜珊、向薇、冬灵、惜芹、凌青、谷芹、雁桃、映雁、书兰、盼香、梅致、寄风、芳荷、绮晴、映之、醉波、幻莲、晓昕、傲柔、寄容、以珊、紫雪、芷容、书琴、美伊、涵阳、怀寒、易云、代秋、惜梦、宇涵、谷槐、怀莲、英莲、芷卉、向彤、新巧、语海、灵珊、凝丹、小蕾、迎夏、慕卉、飞珍、冰夏、亦竹、飞莲、秋月、元蝶、春蕾、怀绿、尔容、小玉、幼南、凡梦、碧菡、初晴、宛秋、傲旋、新之、凡儿、夏真、静枫、芝萱、恨蕊、乐双、念薇、靖雁、菊颂、丹蝶、元瑶、冰蝶、念波、迎翠、海瑶、乐萱、凌兰、曼岚、若枫、傲薇、雅芝、乐蕊、秋灵、凤娇、觅云、依伊、恨山、从寒、忆香、香菱、静曼、青寒、笑天、涵蕾、元柏、代萱、紫真、千青、雪珍、寄琴、绿蕊、荷柳、诗翠、念瑶、兰楠、曼彤、怀曼、香巧、采蓝、芷天、尔曼、巧蕊";
        String prefixNameMale = "德泽、海超、海阳、海荣、海逸、海昌、瀚钰、瀚文、涵亮、涵煦、明宇、涵衍、浩皛、浩波、浩博、浩初、浩宕、浩歌、浩广、浩邈、浩气、浩思、浩言、鸿宝、鸿波、鸿博、鸿才、鸿畅、鸿畴、鸿达、鸿德、鸿飞、鸿风、鸿福、鸿光、鸿晖、鸿朗、鸿文、鸿轩、鸿煊、鸿骞、鸿远、鸿云、鸿哲、鸿祯、鸿志、鸿卓、嘉澍、光济、澎湃、彭泽、鹏池、鹏海、浦和、浦泽、瑞渊、越泽、博耘、德运、辰宇、辰皓、辰钊、辰铭、辰锟、辰阳、辰韦、辰良、辰沛、晨轩、晨涛、晨濡、晨潍、鸿振、吉星、铭晨、起运、运凡、运凯、运鹏、运浩、运诚、运良、运鸿、运锋、运盛、运升、运杰、运珧、运骏、运凯、运乾、维运、运晟、运莱、运华、耘豪、星爵、星腾、星睿、星泽、星鹏、星然、震轩、震博、康震、震博、振强、振博、振华、振锐、振凯、振海、振国、振平、昂然、昂雄、昂杰、昂熙、昌勋、昌盛、昌淼、昌茂、昌黎、昌燎、昌翰、晨朗、德明、德昌、德曜、范明、飞昂、高旻、晗日、昊然、昊天、昊苍、昊英、昊宇、昊嘉、昊明、昊伟、昊硕、昊磊、昊东、鸿晖、鸿朗、华晖、金鹏、晋鹏、敬曦、景明、景天、景浩、俊晖、君昊、昆琦、昆鹏、昆纬、昆宇、昆锐、昆卉、昆峰、昆颉、昆谊、昆皓、昆鹏、昆明、昆杰、昆雄、昆纶、鹏涛、鹏煊、曦晨、曦之、新曦、旭彬、旭尧、旭鹏、旭东、旭炎、炫明、宣朗、学智、轩昂、彦昌、曜坤、曜栋、曜文、曜曦、曜灿、曜瑞、智伟、智杰、智刚、智阳、昌勋、昌盛、昌茂、昌黎、昌燎、昌翰、晨朗、昂然、昂雄、昂杰、昂熙、范明、飞昂、高朗、高旻、德明、德昌、德曜、智伟、智杰、智刚、智阳、瀚彭、旭炎、宣朗、学智、昊然、昊天、昊苍、昊英、昊宇、昊嘉、昊明、昊伟、鸿朗、华晖、金鹏、晋鹏、敬曦、景明、景天、景浩、景行、景中、景逸、景彰、昆鹏、昆明、昆杰、昆雄、昆纶、鹏涛、鹏煊、景平、俊晖、君昊、昆琦、昆鹏、昆纬、昆宇、昆锐、昆卉、昆峰、昆颉、昆谊、轩昂、彦昌、曜坤、曜文、曜曦、曜灿、曜瑞、曦晨、曦之、新曦、鑫鹏、旭彬、旭尧、旭鹏、旭东、浩轩、浩瀚、浩慨、浩阔、鸿熙、鸿羲、鸿禧、鸿信、泽洋、泽雨、哲瀚、胤运、佑运、允晨、运恒、运发、云天、耘志、耘涛、振荣、振翱、中震、子辰、晗昱、瀚玥、瀚昂、瀚彭、景行、景中、景逸、景彰、绍晖、文景、曦哲、永昌、子昂、智宇、智晖、晗日、晗昱、瀚昂、昊硕、昊磊、昊东、鸿晖、绍晖、文昂、文景、曦哲、永昌、子昂、智宇、智晖、浩然、鸿运、辰龙、运珹、振宇、高朗、景平、鑫鹏、昌淼、炫明、昆皓、曜栋、文昂、治汇、澄邈";
        String[] prefixFemaleNames = prefixNameFemale.split("、");
        String[] prefixMaleNames = prefixNameMale.split("、");
        StringBuilder sb = new StringBuilder();
        if ("男".equals(gender)) {
            sb.append(surnames[new Random().nextInt(surnames.length)]).append(prefixMaleNames[new Random().nextInt(prefixMaleNames.length)]);
        } else {
            sb.append(surnames[new Random().nextInt(surnames.length)]).append(prefixFemaleNames[new Random().nextInt(prefixFemaleNames.length)]);
        }
        return sb.toString();
    }

    /********************************gender****************************************/
    public static String getGender() {
        String[] genders = new String[]{"男", "女"};
        return genders[new Random().nextInt(2)];
    }
    /********************************phone****************************************/
    public static String getPhone() {
        String headCode = "133,142,144,146,148,149,153,180,181,189," +
                "130,131,132,141,143,145,155,156,185,186," +
                "134,135,136,137,138,139,140,147,150,151," +
                "152,157,158,159,182,183,187,188";
        String[] headCodes = headCode.split(",");
        int len = headCodes.length;
        char[] chars = "1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append(headCodes[new Random().nextInt(len)]);
        for (int i = 0; i < 8; i++) {
            sb.append(chars[new Random().nextInt(chars.length)]);
        }
        return sb.toString();
    }
    /********************************address****************************************/
    public static String getAddress(){
        char[] buildingCode = "ABCD".toCharArray();
        int len = buildingCode.length;
        StringBuilder sb = new StringBuilder();
        sb.append("世纪新村")
                .append(buildingCode[new Random().nextInt(len)])
                .append(new Random().nextInt(14) + 1)
                .append("-")
                .append(new Random().nextInt(14) + 1)
                .append("楼")
                .append(new Random().nextInt(6) + 1)
                .append("单元")
                .append(new Random().nextInt(6) + 1)
                .append("0")
                .append(new Random().nextInt(4) + 1);
        return sb.toString();
    }
    /********************************resident****************************************/
    public static Resident getResident() {
        Resident resident = new Resident();
        Map<String, String> map = getIdCard();
        resident.setAge(map.get("age")).setIdCard(map.get("idCard"));
        String gender = getGender();
        resident.setGender(gender);
        String name = getName(gender);
        resident.setName(name);
        String address = getAddress();
        resident.setAddress(address);
        String phone = getPhone();
        resident.setPhone(phone);
        resident.setState("5");
        return resident;
    }

    public static List<Resident> getManyResident(int count){
        List<Resident> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(getResident());
        }
        return list;
    }

    public static String getRandomTime() {
        String[] years = {"2021", "2022"};
        String[] months = {
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"
        };
        String[] days = new String[]{
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"
        };
        StringBuilder sb = new StringBuilder();
        String month = months[new Random().nextInt(12)];
        String day = days[new Random().nextInt(30)];
        if ("02".equals(month)) {
            if ("28".compareTo(day) < 0) {
                day = "28";
            }
        }
        String year = years[new Random().nextInt(2)];
        sb.append(year).append("-").append(month).append("-").append(day);
        return sb.toString();
    }

    public static List<Inoculation> getManyInoculation(int startId, int count){
        List<Inoculation> list = new ArrayList<>();
        int endId = startId + count;
        for (int i = startId; i < endId; i++) {
            Inoculation inoculation = new Inoculation();
            inoculation.setResident_id(String.valueOf(i));
            int times = new Random().nextInt(4);
            if (times == 0){
                inoculation.setTimes(String.valueOf(times)).setLastTime(null);
            } else {
                inoculation.setTimes(String.valueOf(times)).setLastTime(getRandomTime());
            }
            list.add(inoculation);
        }
        return list;
    }

    public static List<Map<String, Object>> getAllCities() throws IOException {
        String filePath = "D:\\data\\source\\java\\community-staff\\src\\main\\resources\\cities.json";
        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        FileChannel channel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int read = channel.read(buf);
        StringBuilder sb = new StringBuilder();
        while(read != -1){
            buf.flip();
            sb.append(StandardCharsets.UTF_8.decode(buf));
            buf.clear();
            read = channel.read(buf);
        }
        channel.close();
        file.close();
        String jsonString = sb.toString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonString);
        Iterator<JsonNode> elements = jsonNode.elements();
        List<Map<String, Object>> list = new ArrayList<>();
        while(elements.hasNext()){
            // 一个省份的封装
            Map<String, Object> map = new HashMap<>();
            JsonNode next = elements.next();
            String label = next.get("label").asText();
            // 封装省份名称
            map.put("province", label);
            Iterator<JsonNode> children = next.get("children").elements();
            List<String> childCities = new ArrayList<>();
            while(children.hasNext()){
                JsonNode childCity = children.next();
                String childLabel = childCity.get("label").asText();
                childCities.add(childLabel);
            }
            map.put("childCities", childCities);
            list.add(map);
        }
        return list;
    }

    public static String getRandomProvince() throws IOException {
        StringBuilder sb = new StringBuilder();
        List<Map<String, Object>> list = getAllCities();
        int size = list.size();
        Map<String, Object> provinceMap = list.get(new Random().nextInt(size));
        String pName = (String) provinceMap.get("province");
        List<String> childCities = (List<String>) provinceMap.get("childCities");
        int childLen = childCities.size();
        // 清空sb
        if (childLen == 1) {
            sb.append(pName).append("市");
        } else {
            sb.append(pName).append("省").append(childCities.get(new Random().nextInt(childLen))).append("市");
        }
        return sb.toString();
    }

    /*随机生成一个返乡人员*/
    public static Returnees getReturnees(){
        String[] genders = {"男", "女"};
        String gender = genders[new Random().nextInt(2)];
        String name = getName(gender);
        Map<String, String> map = getIdCard();
        String idCard = map.get("idCard");
        String address = getAddress();
        String applyTime = DateTimeUtil.getTime();
        //String from = getRandomProvince();
        String phone = getPhone();
        return new Returnees()
                .setName(name)
                .setIdCard(idCard)
                .setAddress(address)
                .setApplyTime(applyTime)
                .setFrom(String.valueOf(new Random().nextInt(150)))
                .setPhone(phone)
                .setGender(gender)
                .setInoculationTimes(String.valueOf(3));
    }

    public static List<Returnees> getManyReturnees(int count){
        List<Returnees> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(getReturnees());
        }
        return list;
    }

    public static List<City> getCityList() throws IOException {
        List<Map<String, Object>> allCities = getAllCities();
        List<City> list = new ArrayList<>();
        for (Map<String, Object> allCity : allCities) {
            String province = (String) allCity.get("province");
            List<String> childCities = (List<String>) allCity.get("childCities");
            for (int i = 0; i < childCities.size(); i++) {
                String cityName = childCities.get(i);
                City city = new City();
                city.setProvince(province).setCity(cityName).setLevel("低风险");
                list.add(city);
            }
        }
        return list;
    }


    /********************Main*********************/
    public static void main(String[] args) throws IOException {
        Calendar instance = Calendar.getInstance();
        Date date = new Date( 2022, Calendar.MAY, 13);
        instance.setTime(date);
        instance.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(instance.getTime());
        System.out.println(format);
    }

}
