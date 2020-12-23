package Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WeatherByGPSApplication {

   JSONParser jsonParser;
   JSONObject jsonObj;
   JSONObject obj;
   JSONObject mainArray;
   double ktemp;
   double temp;

   public static double retemp; //
   public static String reobj = "";
   public static String retemp2="";

   public WeatherByGPSApplication(){

      try{
         //안양의 위도와 경도
         String lon = "126.919717";  //경도
         String lat = "37.392046";   //위도

         //OpenAPI call하는 URL
         String urlstr = "http://api.openweathermap.org/data/2.5/weather?"
               + "lat="+lat+"&lon="+lon
               +"&appid=88300c2695fa92ea989771d082eeea0a";
         URL url = new URL(urlstr);
         BufferedReader bf;
         String line;
         String result="";

         //날씨 정보를 받아온다.
         bf = new BufferedReader(new InputStreamReader(url.openStream()));

         //버퍼에 있는 정보를 문자열로 변환.
         while((line=bf.readLine())!=null){
            result=result.concat(line);
            //System.out.println(line);
         }

         //문자열을 JSON으로 파싱
         jsonParser = new JSONParser();
         jsonObj = (JSONObject) jsonParser.parse(result);

         //지역 출력
         System.out.println("지역 : " + jsonObj.get("name"));

         //날씨 출력
         JSONArray weatherArray = (JSONArray) jsonObj.get("weather");
         obj = (JSONObject) weatherArray.get(0);
         System.out.println("날씨 : "+obj.get("main"));
         reobj = (String) obj.get("main");
         System.out.println(reobj);

         //온도 출력(절대온도라서 변환 필요)
         mainArray = (JSONObject) jsonObj.get("main");
         ktemp = Double.parseDouble(mainArray.get("temp").toString());
         temp = ktemp-273.15;
         int a = (int)Math.round(temp);
         System.out.printf("온도 : %.2f\n",temp);
         
         retemp2 = String.valueOf(a);
         System.out.println(retemp2); //결과 : 3.14


         bf.close();
      }catch(Exception e){
         System.out.println(e.getMessage());
      }

   }

   public static void main(String[] args) {
      new WeatherByGPSApplication();
   }

}

