package httpRequest;

import parser.SubscriptionParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/* Esta clase se encarga de realizar efectivamente el pedido de feed al servidor de noticias
 * Leer sobre como hacer una http request en java
 * https://www.baeldung.com/java-http-request
 * */

public class HttpRequester {

	private String sendRequest(String urlFeed){

		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
							.uri(new URI(urlFeed))
							.header("User-Agent", "MiLectorDeFeeds/1.0")
							.GET() // Especifica el método GET
							.build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == 200) {
				return response.body();
			}
		} catch (URISyntaxException e) {
					System.err.println("Error: La URL proporcionada no es válida - " + urlFeed + " - " + e.getMessage());
		} catch (IOException e) {
		System.err.println("Error: Ocurrió un problema al enviar la solicitud - " + e.getMessage());
		} catch (InterruptedException e) {
		System.err.println("Error: La solicitud fue interrumpida - " + e.getMessage());
		Thread.currentThread().interrupt(); // Restablecer el estado de interrupción del hilo
		}
	  return null;
	}

	public String getFeedRss(String fullurlFeed) {
		System.out.println("Solicitando RSS desde: " + fullurlFeed);
			return sendRequest(fullurlFeed);

	}

	public String getFeedReedit(String fullurlFeed) {
		 System.out.println("Solicitando Reddit desde: " + fullurlFeed);
		return sendRequest(fullurlFeed);
	}

	public static void main(String[] args) {
		// Corroboramos correctitud del httpRequester.
		SubscriptionParser subsToReq = new SubscriptionParser();
		String rssUrlToReq = subsToReq.parseSubscriptions().getSingleSubscription(0).getFeedToRequest(0);
		HttpRequester request = new HttpRequester();
		System.out.println(request.getFeedRss(rssUrlToReq));

	}
}


