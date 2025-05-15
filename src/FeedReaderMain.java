import httpRequest.httpRequester;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import parser.SubscriptionParser;

public class FeedReaderMain {

	private static void printHelp(){
		System.out.println("Please, call this program in correct way: FeedReader [-ne]");
	}

	public static void main(String[] args) {
		System.out.println("************* FeedReader version 1.0 *************");
		if (args.length == 0) {

	try{



		// Ya implemente el Caso 1: Leer el archivo de suscription por defecto;
		// Especificar la ruta completa del archivo JSON
			String filepath = "paradigmas25-g20-lab2/config/subscriptions.json";
			// Leer el contenido del archivo JSON
			String jsonContent = new String(Files.readAllBytes(Paths.get(filepath)));
			SubscriptionParser sub = new SubscriptionParser();
			String result = sub.initialize(jsonContent);
			System.out.println("Contenido del JSON:");
			System.out.println(result);
			List<String> params = sub.getParams(jsonContent);
		  System.out.println("Parámetros  del JSON:");




			if (params != null && !params.isEmpty()) {
				for (String param : params) {
						System.out.println("- " + param);
				}
		} else {
				System.out.println("No se encontraron parámetros o hubo un error al extraerlos.");
		}



			// Leer el archivo de suscription por defecto;





			// Llamar al httpRequester para obtenr el feed del servidor (hice esto)
			String baseUrl = sub.getUrl();
			String type = sub.getUrlType();
			httpRequester http = new httpRequester();
			if(type.equals("RSS")){
				System.out.println("El tipo de feed es RSS");
				http.getFeedRss(baseUrl);
			}else if(type.equals("REDDIT")){
				System.out.println("El tipo de feed es REDDIT");
				http.getFeedReedit(baseUrl);
			}
			/*
			Llamar al Parser especifico para extrar los datos necesarios por la aplicacion
			Llamar al constructor de Feed
			LLamar al prettyPrint del Feed para ver los articulos del feed en forma legible y amigable para el usuario
			*/
		}
		catch (Exception e) {
				System.out.println("Error al procesar el archivo de suscripción: " + e.getMessage());
		}

		} else if (args.length == 1){

			/*
			Leer el archivo de suscription por defecto;
			Llamar al httpRequester para obtenr el feed del servidor
			Llamar al Parser especifico para extrar los datos necesarios por la aplicacion
			Llamar al constructor de Feed
			Llamar a la heuristica para que compute las entidades nombradas de cada articulos del feed
			LLamar al prettyPrint de la tabla de entidades nombradas del feed.
			 */

		}else {
			printHelp();
		}
	}

}
