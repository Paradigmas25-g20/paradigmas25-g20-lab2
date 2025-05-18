package parser;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SingleSelectionModel;
import subscription.SingleSubscription;
import subscription.Subscription;
import org.json.JSONArray;
import org.json.JSONObject;


/*
 * Esta clase implementa el parser del archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */

public class SubscriptionParser extends GeneralParser {


    @Override
     public Subscription parseSubscriptions(String json) {



        JSONArray arr = new JSONArray(json);
        Subscription subList = new Subscription();
        for(int i=0 ; i<arr.length();i++){

              JSONObject currentJsonObj = arr.getJSONObject(i);
              List<String> urlParams= new ArrayList<>();
              String url = currentJsonObj.getString("url");
              String urlType = currentJsonObj.getString("urlType");
              paramsList(currentJsonObj, urlParams);
              SingleSubscription sub = new SingleSubscription(url,urlParams ,urlType);
              subList.addSingleSubscription(sub);


        }
        return  subList;

    }



  private List<String> paramsList(JSONObject obj, List<String> urlParams ) {
    try {

            JSONArray urlParamsArray = obj.getJSONArray("urlParams");
            for (int i = 0; i < urlParamsArray.length(); i++) {
                urlParams.add(urlParamsArray.getString(i));
            }
        } catch (JSONException e) {
            System.err.println("Error al parsear JSON para obtener parámetros: " + e.getMessage());
        }
        return urlParams;

    }



}
