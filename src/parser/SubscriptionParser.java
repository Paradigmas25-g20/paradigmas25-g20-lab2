package parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONTokener;
import subscription.SingleSubscription;
import subscription.Subscription;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Esta clase implementa el parser del archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */

public class SubscriptionParser extends GeneralParser<Subscription> {

    @Override
    public Subscription parse(String jsonFile) {
        // Creo el objeto que guardará mi lista de suscripciones.
        Subscription parsedSubs = new Subscription();
        try {
            // Leo el archivo de suscripciones.
            FileReader toParseFile = new FileReader(jsonFile);
            // Lo parseo a un arreglo de JSONs.
            JSONArray toParseSubs = new JSONArray(new JSONTokener(toParseFile));
            // Itero sobre dicho arreglo para ir armando los SingleSubscriptions.
            for (int i = 0; i < toParseSubs.length(); i++) {
                // Parseo cada elemento del arreglo a un JSON.
                JSONObject obj = toParseSubs.getJSONObject(i);
                // Creo el arreglo con los parámetros de la URL.
                JSONArray toParseUrlParams = obj.getJSONArray("urlParams");
                List<String> parsedUrlParams = new ArrayList<>();
                for (int j = 0; j < toParseUrlParams.length(); j++) {
                    parsedUrlParams.add(toParseUrlParams.getString(j));
                }
                // Instancio un SingleSubscription con la data recolectada.
                SingleSubscription singleSub = new SingleSubscription(obj.getString("url"), parsedUrlParams, obj.getString("urlType"));
                // Añado a mi ista de suscripciones la SingleSubscription recién creada.
                parsedSubs.addSingleSubscription(singleSub);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found." + e.getMessage());
        }
        // Devuelvo la lista de suscripciones como un String.
        return parsedSubs;
    }

//    public static void main(String[] args) {
//        // Main para corroborar la correctitud del parseSubscription.
//        SubscriptionParser parsedData = new SubscriptionParser();
//        System.out.println(parsedData.parse("config/subscriptions.json"));
//    }

}
