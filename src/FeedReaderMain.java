import feed.Article;
import feed.Feed;
import httpRequest.HttpRequester;
import parser.GeneralParser;
import parser.RedditParser;
import parser.RssParser;
import parser.SubscriptionParser;
import subscription.SingleSubscription;
import subscription.Subscription;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FeedReaderMain {

    private static void printHelp() {
        System.out.println("Please, call this program in correct way: FeedReader [-ne]");
    }

    public static void main(String[] args) {
        System.out.println("************* FeedReader version 1.0 *************");
        if (args.length == 0) {
         processSubscription("paradigmas25-g20-lab2/config/subscriptions.json");
            /*
			Llamar al Parser especifico para extrar los datos necesarios por la aplicacion
			Llamar al constructor de Feed
			LLamar al prettyPrint del Feed para ver los articulos del feed en forma legible y amigable para el usuario
             */

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
            // Leer el contenido del archivo JSON
            String jsonContent = new String(Files.readAllBytes(Paths.get(filepath)));
            SubscriptionParser subParse = new SubscriptionParser();

            //Aca creo un objeto de la clase subcrition y el cual posee una lista de Singlesubcripsiones,cheto. El parse hace sus cosas y lo larga
            GeneralParser<Subscription> subParser = new SubscriptionParser();
            Subscription subscriptionContainer = subParser.parse(jsonContent);
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
                }else {
                    for (String params : urlParams) {
                    /*
                    "url": "https://rss.nytimes.com/services/xml/rss/nyt/%s.xml", aca donde dice %s van los parametros => necesito recorrer la lista de paramtetros por ejemploi
                      "urlParams": ["Business", "Technology"], para que cada para cada parametro en vez de la %S este el mismo digamo
                      para eso es esta linea :
                       String formattedUrl = String.format(currentSingle.getUrl(), currentSingle.getUlrParamsid(currentListParams));
                       el currentSingle es de la lista de afuera (lista de single archivo Singlesubcription ) y el otro es de la lista de parametros
                       (dentro de singlesubcrition pero el la parte que es una lista de string  ) . Entonces le clavamos el parametro donde dice %s (supuestamente)

                    */
                        String formattedUrl = String.format(urlBa, params);
                        processFeed(formattedUrl, feedType, http);


                        //  aca si todo sale bien recibimos un Html eso hay que parsearlo y sacar topicos
                        // saber que tipo es te sirve para parsearlo mas que nada creo,


                    }


                }
                /*
            Llamar al Parser específico para extraer los datos necesarios por la aplicación
            Llamar al constructor de Feed
            Llamar al prettyPrint del Feed para ver los artículos del feed en forma legible y amigable para el usuario
                 */
            }
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo de suscripción: " + e.getMessage());
        } catch (Exception e) { // Captura general para otros errores inesperados
            System.err.println("Un Error ha ocurrido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processFeed(String url, String urltype, HttpRequester httpRequester) {


        String content = null;
        Feed feed = null;
        if (urltype.equals("rss")) {
            content = httpRequester.getFeedRss(url);
            if (content != null && !content.isEmpty()) {
                GeneralParser<Feed> rssParser = new RssParser(); // ¡Usa RedditParser aquí!
                feed = rssParser.parse(content);
            } else {
                System.err.println("No content received for Reddit feed: " + url);
            }


        } else if (urltype.equals("Reddit")) {
            content = httpRequester.getFeedReedit(url);

            if (content != null && !content.isEmpty()) {
                GeneralParser<Feed> redditParser = new RedditParser(); // ¡Usa RedditParser aquí!
                feed = redditParser.parse(content);
            } else {
                System.err.println("No content received for Reddit feed: " + url);
            }


            }
        if(feed!=null){
          if ( !feed.getArticleList().isEmpty() && feed.getArticleList() != null){

                for (Article article : feed.getArticleList()) {
                    System.out.println("  -> Article: " + article.getTitle());
                    System.out.println("     Link: " + article.getLink());
                    System.out.println("     Date: " + article.getPublicationDate());
                    System.out.println("     Desc: " + article.getText());
                }

            }else {
              System.err.println("No hay artculos para prosesar en : " + url);
          }


        }   else {
            String urlTypeError = urltype;
            System.err.println("Fallo en parser el feed : " + url + " (Tipo: " + urlTypeError + ")");

        }
    }


}