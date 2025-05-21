package parser;

import feed.Article;
import feed.Feed;
import httpRequest.HttpRequester;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/*Una vez conseguidos la lista de artículos de un feed, van a extraer sólo aquellos atributos
del artículo (item) que nos interesa (title, description,pubDate,link )
que luego serán mostrados por pantalla en forma legible para el usuario.*/
/* Esta clase implementa el parser de feed de tipo rss (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
 * */

public class RssParser extends GeneralParser {

    SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    @Override
    public Feed parse (String rssToParse) {
        Feed listFeed = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            // Parseamos el rss
            ByteArrayInputStream input = new ByteArrayInputStream(rssToParse.getBytes(StandardCharsets.UTF_8));
            Document xmldoc = docBuilder.parse(input);
            // Recorremos el rss
            Element rootelement = xmldoc.getDocumentElement();
            NodeList rootChildren = rootelement.getChildNodes();


            System.out.println("El elemento Root es: " + rootelement.getTagName());
            Element channelElement = null;
            // El root es rss y nosotros queremos encotrar el primer hijo channel para poder recorrer adentro de el
            for (int i = 0; i < rootChildren.getLength(); i++) {
                Node node = rootChildren.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equalsIgnoreCase("channel")) {
                    channelElement = (Element) node;
                    break;
                }
            }
            if (channelElement != null) {
                String siteNameFromChannel = getTitle(channelElement);
                listFeed = new Feed(siteNameFromChannel);

                NodeList nList = channelElement.getChildNodes();
                NodeList channelItems = channelElement.getElementsByTagName("item");
                for (int currentItem = 0; currentItem < nList.getLength(); currentItem++) {
                    Node itemNode = channelItems.item(currentItem);
                    if (itemNode.getNodeType() == Node.ELEMENT_NODE ) {

                        Element eElement = (Element) itemNode;
                        String title = getTitle(eElement);
                        String link = getLink(eElement);
                        String descrip = getDescription(eElement);
                        String pubDateStr = getPubDate(eElement);
                        Date data = null;
                        
                        try {
                            if (pubDateStr != null && !pubDateStr.isEmpty()) {
                                data = formatter.parse(pubDateStr);
                            }
                            Article new_art = new Article(title, descrip, data, link);
                            listFeed.addArticle(new_art);
                        } catch (java.text.ParseException pe) { // Atrapar específicamente ParseException
                            System.err.println("Error al parsear fecha '" + pubDateStr + "' para el artículo con título: '" + title + "'. Omitiendo fecha para este artículo.");
                            // publicationDate permanece null, el artículo se creará sin fecha
                        }
                    }
                }
            } else {
                System.out.println("Elemento <channel> no encontrado!");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error durante el parseo del XML: " + e.getMessage());
        }
        return listFeed;
    }

    private String getLink(Element element) {
        String link = ""; // Valor por defecto
        NodeList linkNodes = element.getElementsByTagName("link");
        if (linkNodes.getLength() > 0 && linkNodes.item(0) != null) {
            link = linkNodes.item(0).getTextContent();
        }
        return link;
    }

    private String getDescription(Element element) {
        String description = ""; // Valor por defecto
        NodeList descriptionNodes = element.getElementsByTagName("description");
        if (descriptionNodes.getLength() > 0 && descriptionNodes.item(0) != null) {
            description = descriptionNodes.item(0).getTextContent();
        }
        return description;
    }

    private String getPubDate(Element element) {
        String pubDate = ""; // Valor por defecto
        NodeList pubDateNodes = element.getElementsByTagName("pubDate");
        if (pubDateNodes.getLength() > 0 && pubDateNodes.item(0) != null) {
            pubDate = pubDateNodes.item(0).getTextContent();
        }
        return pubDate;
    }

    private String getTitle(Element element) {

        NodeList titleNodes = element.getElementsByTagName("title");
        String title = "";
        if (titleNodes.getLength() > 0 && titleNodes.item(0) != null) {
            title = titleNodes.item(0).getTextContent();
        }
        return title;
    }
/*
    public static void main(String[] args) {
        // Coroboramos correctitud acumulativa de la clase.
        RssParser rssToParse = new RssParser();
        SubscriptionParser subsToParse = new SubscriptionParser();
        String urlToReq = subsToParse.parseSubscriptions().getSingleSubscription(0).getFeedToRequest(0);
        HttpRequester httpReq = new HttpRequester();
        rssToParse.parseRss(httpReq.getFeedRss(urlToReq)).prettyPrint();
    }

 */

}


