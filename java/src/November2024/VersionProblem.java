package November2024;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VersionProblem {

    static Map<String,Integer> qMap = new HashMap<>();

    static {
        qMap.put("alpha",1);
        qMap.put("beta",2);
        qMap.put("milestone",3);
        qMap.put("rc",4);
        qMap.put("snapshot",5);
        qMap.put("",Integer.MAX_VALUE);

    }

    public void sortVersions(String[] versions){
        Arrays.sort(versions, this::compareVersion);
    }

    public int compareVersion(String version1, String version2){

        int m = version1.length();
        int n = version2.length();
        int i = 0,j = 0;

        while( i < m || j < n){


            int num1 = getNextNumber(version1, i);
            int num2 = getNextNumber(version2,i);


            if (num1 != num2)
                return num1 > num2 ? -1 : 1;

            i = getIndex(version1, i);
            j = getIndex(version2, j);

            if ((i < m && !Character.isDigit(version1.charAt(i))) || (j < n &&!Character.isDigit(version2.charAt(j)))) {
                break;
            }


        }

        String qualifiedOne = i < m && version1.charAt(i) == '-' ? version1.substring(i+1) : "";
        String qualifiedTwo = i < n && version2.charAt(i) == '-' ? version2.substring(j+1) : "";

        return compareQualifiers(qualifiedOne,qualifiedTwo);

    }

    public int getNextNumber(String data, int index){

        if(index >= data.length())
            return 0;

        int num = 0;

        while(index < data.length() && Character.isDigit(data.charAt(index))) {
            num = num * 10 + (data.charAt(index) - '0');
            index++;
        }

        return num;
    }

    public int getIndex(String data, int index){

        while (index < data.length() && Character.isDigit(data.charAt(index))) {
            index++;
        }
        if (index < data.length() && data.charAt(index) == '.') {
            index++;
        }
        return index;
    }

    public int compareQualifiers(String q1, String q2){

        Integer priority = qMap.get(q1);
        Integer priority2 = qMap.get(q2);

        if(priority != priority2)
            return Integer.compare(priority, priority2);

        return q1.compareTo(q2);
    }


    public static void main(String[] args){

        VersionProblem v1 = new VersionProblem();

        String[] versions = {
                "1.0", "1.0-alpha", "1.0-beta", "1.0-rc", "1.0.1", "2.0.1-beta", "2.0.1"
        };

        v1.sortVersions(versions);
        System.out.println("Sorted Versions: " + Arrays.toString(versions));
    }
}
