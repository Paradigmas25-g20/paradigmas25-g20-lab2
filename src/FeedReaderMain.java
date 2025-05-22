import feed.Article;
import feed.Feed;

import httpRequest.HttpRequester;

import namedEntity.NamedEntity;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;
import namedEntity.heuristic.RandomHeuristic;

import parser.RssParser;
import parser.SubscriptionParser;

import subscription.SingleSubscription;
import subscription.Subscription;

import java.util.List;
import java.util.Scanner;

public class FeedReaderMain {

    private static void printHelp() {
        System.out.println("The correct way to use this program is: FeedReader [-ne]");
    }

    public static void main(String[] args) {
        System.out.println("************* FeedReader version 1.0 *************");
        if (args.length == 0) {
            processSubscription(false, null);
        } else if (args.length == 1) {
            processSubscription(true, heuristicChooser());
            NamedEntity.prettyPrint();
        } else {
            printHelp();
        }
    }

    private static void processSubscription(boolean args, Heuristic h) {
        try {
            // Creamos el parser para las suscripciones
            SubscriptionParser subParse = new SubscriptionParser();
            Subscription subscriptionContainer = subParse.parse("config/subscriptions.json");
            // Creamos el httpRequeser
            HttpRequester http = new HttpRequester();
            //recorremos la lista de Singlesubcripciones
            for (SingleSubscription singleSub : subscriptionContainer.getSubscriptionsList()) {
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
                    processFeed(urlBa, feedType, http, args, h);
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
                        processFeed(urlToReq, feedType, http, args, h);
                        //  aca si todo sale bien recibimos un Html eso hay que parsearlo y sacar topicos
                        // saber que tipo es te sirve para parsearlo mas que nada creo,


                    }
                }
            }
        } catch (Exception e) { // Captura general para otros errores inesperados
            System.err.println("An error has occurred: " + e.getMessage());
//            e.printStackTrace();
        }
    }

    private static void processFeed(String url, String urltype, HttpRequester httpRequester, boolean args, Heuristic h) {
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
                if (args) {
                    for (Article a : feed.getArticleList()) {
                        a.computeNamedEntities(h);
                    }
                }
            } else {
                System.out.println("Error while parsing the rss feed:" + url);
            }
        }
        // Aquí iría el manejo de los feeds de reddit, si hubieramos llegado a implementarlo :(

    }

    private static Heuristic heuristicChooser() {
        Scanner hInput = new Scanner(System.in);
        String heuristicChosen;
        System.out.println("""
                Choose the wanted heuristic for looking up NamedEntities
                Press Q for Quick Heuristic
                Press R for Random Heuristic""");
        while (true) {
            heuristicChosen = hInput.nextLine();
            if (heuristicChosen.equalsIgnoreCase("Q")) {
                hInput.close();
                return new QuickHeuristic();
            } else if (heuristicChosen.equalsIgnoreCase("R")) {
                hInput.close();
                return new RandomHeuristic();
            } else {
                System.out.println("""
                        Invalid entry.
                        Press Q for Quick Heuristic
                        Press R for Random Heuristic""");
            }
        }
    }
}
