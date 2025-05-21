//import feed.Article;
import feed.Feed;
import httpRequest.HttpRequester;
//import parser.GeneralParser;
//import parser.RedditParser;
import parser.RssParser;
import parser.SubscriptionParser;
import subscription.SingleSubscription;
import subscription.Subscription;

//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.List;

public class FeedReaderMain {

    private static void printHelp() {
        System.out.println("Please, call this program in correct way: FeedReader [-ne]");
    }

    public static void main(String[] args) {
        System.out.println("************* FeedReader version 1.0 *************");
        if (args.length == 0) {
         processSubscription("config/subscriptions.json");

        } else if (args.length == 1) {
            /*
			Llamar al Parser especifico para extrar los datos necesarios por la aplicacion
			Llamar al constructor de Feed
			Llamar a la heuristica para que compute las entidades nombradas de cada articulos del feed
			LLamar al prettyPrint de la tabla de entidades nombradas del feed.
             */
            processSubscription(args[0]);
        } else {
            printHelp();
        }
    }

    private static void processSubscription(String filepath) {
        try {
            // Creamos el parser para las suscripciones
            SubscriptionParser subParse = new SubscriptionParser();
            Subscription subscriptionContainer = subParse.parse(filepath);
            // Creamos el httpRequeser
            HttpRequester http = new HttpRequester();
            //recorremos la lista de Singlesubcripciones
            for(SingleSubscription singleSub : subscriptionContainer.getSubscriptionsList()) {
                //nos paramos en cada elemento que es una singleSub tonce
                /*   tenemos
	                     url;
		                  List<String> ulrParams; aca  donde de despues iteramos
		                 urlType;
               */
                String urlBa = singleSub.getUrl();
                List<String> urlParams = singleSub.getUlrParams(); // Asumiendo que este método devuelve List<String>
                String feedType = singleSub.getUrlType();
                //dentro de las singleSub tenemos un array nuevo que son los parametros entonces ahora rrecorremos ese arreglo
                if (urlParams.isEmpty()) { // Si no hay parámetros, usar la URL base directamente
                    processFeed(urlBa, feedType, http);
                } else {
                    for (int i = 0; i < urlParams.size(); i++) {
                    /*
                    "url": "https://rss.nytimes.com/services/xml/rss/nyt/%s.xml", aca donde dice %s van los parametros => necesito recorrer la lista de paramtetros por ejemploi
                      "urlParams": ["Business", "Technology"], para que cada para cada parametro en vez de la %S este el mismo digamo
                      para eso es esta linea :
                       String formattedUrl = String.format(currentSingle.getUrl(), currentSingle.getUlrParamsid(currentListParams));
                       el currentSingle es de la lista de afuera (lista de single archivo Singlesubcription ) y el otro es de la lista de parametros
                       (dentro de singlesubcrition pero el la parte que es una lista de string  ) . Entonces le clavamos el parametro donde dice %s (supuestamente)

                    */
                        String urlToReq = singleSub.getFeedToRequest(i);
                        processFeed(urlToReq, feedType, http);
                        //  aca si todo sale bien recibimos un Html eso hay que parsearlo y sacar topicos
                        // saber que tipo es te sirve para parsearlo mas que nada creo,


                    }
                }
            }
        } catch (Exception e) { // Captura general para otros errores inesperados
            System.err.println("Un Error ha ocurrido: " + e.getMessage());
//            e.printStackTrace();
        }
    }

    private static void processFeed(String url, String urltype, HttpRequester httpRequester) {
//        String content = null;
        Feed feed = null;
        if (urltype.equals("rss")) {
            String content = httpRequester.getFeedRss(url);
            if (content != null && !content.isEmpty()) {
                RssParser rssParser = new RssParser();
                feed = rssParser.parse(content);
            } else {
                System.err.println("No content received for rss feed: " + url);
            }
            if (feed != null) {
                feed.prettyPrint();
            } else {
                System.out.println("Error while parsing the rss feed:" + url);
            }

// TODAVÍA NO ESTÁ IMPLEMENTADO!!!!!!!!!!!!
//        } else if (urltype.equals("Reddit")) {
//            content = httpRequester.getFeedReedit(url);
//
//            if (content != null && !content.isEmpty()) {
//                GeneralParser<Feed> redditParser = new RedditParser(); // ¡Usa RedditParser aquí!
//                feed = redditParser.parse(content);
//            } else {
//                System.err.println("No content received for Reddit feed: " + url);
//            }
        }   else {
            // Por el momento, se cambia si llegamos a implementar el parser de reddit
            System.err.println("Reddit feeds are not available yet: " + url);

        }
    }


}