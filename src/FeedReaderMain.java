
import httpRequest.httpRequester;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import parser.SubscriptionParser;
import subscription.SingleSubscription;
import subscription.Subscription;

public class FeedReaderMain {

    private static void printHelp() {
        System.out.println("Please, call this program in correct way: FeedReader [-ne]");
    }

    public static void main(String[] args) {
        System.out.println("************* FeedReader version 1.0 *************");
        if (args.length == 0) {
//            processSubscription("paradigmas25-g20-lab2/config/subscriptions.json");
            /*
			Llamar al Parser especifico para extrar los datos necesarios por la aplicacion
			Llamar al constructor de Feed
			LLamar al prettyPrint del Feed para ver los articulos del feed en forma legible y amigable para el usuario
             */

        } else if (args.length == 1) {

//            processSubscription("paradigmas25-g20-lab2/config/subscriptions.json");
            /*
			Llamar al Parser especifico para extrar los datos necesarios por la aplicacion
			Llamar al constructor de Feed
			Llamar a la heuristica para que compute las entidades nombradas de cada articulos del feed
			LLamar al prettyPrint de la tabla de entidades nombradas del feed.
             */

        } else {
            printHelp();
        }
    }

//    private static void processSubscription(String filepath) {
//        try {
//            // Leer el contenido del archivo JSON
//            String jsonContent = new String(Files.readAllBytes(Paths.get(filepath)));
//            SubscriptionParser subParse = new SubscriptionParser();
//
//            //Aca creo un objeto de la clase subcrition y el cual posee una lista de Singlesubcripsiones,cheto. El parse hace sus cosas y lo larga
//            Subscription sub = subParse.parseSubscriptions(jsonContent);
//
//            httpRequester http = new httpRequester();
//            int sizeSubList = sub.getSubscriptionsList().size();
//
//
//            //recorremos la lista de Singlesubcripciones
//            for (int currentListSub = 0; currentListSub < sizeSubList; currentListSub++) {
//
//
//                /*nos paramos en cada elemento que es una singleSub tonce
//
//                tenemos
//	                     url;
//		                  List<String> ulrParams; aca  donde de despues iteramos
//		                 urlType;
//                */
//                SingleSubscription currentSingle = sub.getSingleSubscription(currentListSub);
//                int sizeCurretn = sub.getSubscriptionsList().size();
//                //dentro de las singleSub tenemos un array nuevo que son los parametros entonces ahora rrecorremos ese arreglo
//                for (int currentListParams = 0; currentListParams < sizeCurretn; currentListParams++) {
//                    /*
//
//                    "url": "https://rss.nytimes.com/services/xml/rss/nyt/%s.xml", aca donde dice %s van los parametros => necesito recorrer la lista de paramtetros por ejemploi
//                      "urlParams": ["Business", "Technology"], para que cada para cada parametro en vez de la %S este el mismo digamo
//                      para eso es esta linea :
//                       String formattedUrl = String.format(currentSingle.getUrl(), currentSingle.getUlrParamsid(currentListParams));
//                       el currentSingle es de la lista de afuera (lista de single archivo Singlesubcription ) y el otro es de la lista de parametros
//                       (dentro de singlesubcrition pero el la parte que es una lista de string  ) . Entonces le clavamos el parametro donde dice %s (supuestamente)
//
//                    */
//                    String formattedUrl = String.format(currentSingle.getUrl(), currentSingle.getUlrParamsid(currentListParams));
//                    String type = currentSingle.getUlrParamsid(currentListParams);
//
//
//                    /*
//
//                        aca le mandamos la url al archivo httprequest para que haga la solicitud get al la pag en cuestion (esto creo que anda)
//
//
//                    */
//                    if (type.equals("RSS")) {
//                        http.getFeedRss(formattedUrl);
//                    } else if (type.equals("Reddit")) {
//                        http.getFeedReedit(formattedUrl);
//                    }
//                    /*
//
//                        aca si todo sale bien recibimos un Html eso hay que parsearlo y sacar topicos
//                        saber que tipo es te sirve para parsearlo mas que nada creo,
//
//
//                    */
//
//                }
//
//
//                /*
//            Llamar al Parser específico para extraer los datos necesarios por la aplicación
//            Llamar al constructor de Feed
//            Llamar al prettyPrint del Feed para ver los artículos del feed en forma legible y amigable para el usuario
//                 */
//            }
//        } catch (IOException e) {
//            System.out.println("Error al procesar el archivo de suscripción: " + e.getMessage());
//        }
//    }

}