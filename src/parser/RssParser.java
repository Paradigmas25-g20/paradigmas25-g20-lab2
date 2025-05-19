package parser;


/*
 * Esta clase implementa el parser de feed de tipo reddit (json)
 * pero no es necesario su implemntacion
 * */


package parser;

import feed.Article;
import feed.Feed;
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
import java.util.List;
import java.util.Locale;
/*Una vez conseguidos la lista de artículos de un feed, van a extraer sólo aquellos atributos
del artículo (item) que nos interesa (title, description,pubDate,link )
que luego serán mostrados por pantalla en forma legible para el usuario.*/
/* Esta clase implementa el parser de feed de tipo rss (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
 * */

public class RssParser extends GeneralParser {

    SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    public static void main(String[] args) {
        RssParser parser = new RssParser();

        // Ejemplo de un String XML para un feed RSS
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                "<rss version=\"2.0\">" +
                "<channel>" +
                "  <title>Ejemplo de Feed RSS</title>" +
                "  <link>http://www.ejemplo.com</link>" +
                "  <description>Este es un feed RSS de ejemplo.</description>" +
                "  <item>" +
                "    <title>Artículo de Ejemplo 1</title>" +
                "    <link>http://www.ejemplo.com/articulo1</link>" +
                "    <description>Descripción del primer artículo.</description>" +
                "    <pubDate>Mon, 01 Jan 2024 10:00:00 +0000</pubDate>" +
                "  </item>" +
                "  <item>" +
                "    <title>Artículo de Ejemplo 2</title>" +
                "    <link>http://www.ejemplo.com/articulo2</link>" +
                "    <description>Descripción del segundo artículo con fecha mala.</description>" +
                "    <pubDate>Fecha Incorrecta</pubDate>" +
                "  </item>" +
                "  <item>" +
                "    <title>Artículo de Ejemplo 3 Sin Fecha</title>" +
                "    <link>http://www.ejemplo.com/articulo3</link>" +
                "    <description>Descripción del tercer artículo.</description>" +
                "  </item>" +
                "  <item>" +
                "    <title></title>" + // Artículo sin título
                "    <link>http://www.ejemplo.com/articulosinTitulo</link>" +
                "    <description>Este artículo no tiene título.</description>" +
                "    <pubDate>Tue, 02 Jan 2024 12:00:00 +0000</pubDate>" +
                "  </item>" +
                "</channel>" +
                "</rss>";

        System.out.println("--- Probando con XML válido ---");
        List<Article> articles = parser.genparser(xmlData);

        if (articles.isEmpty()) {
            System.out.println("No se parsearon artículos o el feed estaba vacío/con errores.");
        } else {
            System.out.println("\nArtículos Parseados (" + articles.size() + "):");
            for (Article article : articles) {
                // Asumiendo que tu clase Article tiene un método prettyPrint() o toString() útil
                article.prettyPrint(); // O System.out.println(article);
                System.out.println("---");
            }
        }

        System.out.println("\n--- Probando con XML inválido (canal faltante) ---");
        String invalidXmlChannelMissing = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                "<rss version=\"2.0\">" +
                // No hay <channel>
                "</rss>";
        List<Article> articlesFromInvalid = parser.genparser(invalidXmlChannelMissing);
        if (articlesFromInvalid.isEmpty()) {
            System.out.println("Correcto: No se parsearon artículos del XML inválido (canal faltante).");
        } else {
            System.out.println("Error: Se parsearon artículos de un XML sin canal.");
        }

        System.out.println("\n--- Probando con XML completamente malformado ---");
        String malformedXml = "<rss><channel<title>Test</title></rss>"; // XML malformado
        List<Article> articlesFromMalformed = parser.genparser(malformedXml);
        if (articlesFromMalformed.isEmpty()) {
            System.out.println("Correcto: No se parsearon artículos del XML malformado.");
        } else {
            System.out.println("Error: Se parsearon artículos de un XML malformado.");
        }

        System.out.println("\n--- Fin de las pruebas ---");
    }

    public List<Article> genparser(String new_parser) {
        Feed listFeed = null;
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            //PARSEAMOS EL XML
            ByteArrayInputStream input = new ByteArrayInputStream(new_parser.getBytes(StandardCharsets.UTF_8));
            Document xmldoc = docBuilder.parse(input);
            //creamos la lista de feed para devolver

            //creamos la clase para formatear data

            //RECORREMOS EL ARRAY IMPUT
            Element rootelement = xmldoc.getDocumentElement();
            NodeList rootChildren = rootelement.getChildNodes(); //
            System.out.println("Root element name is " + rootelement.getTagName());
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

                    for (int currentItem = 0; currentItem < nList.getLength(); currentItem++) {
                        Node itemNode = nList.item(currentItem);
                        if (itemNode.getNodeType() == Node.ELEMENT_NODE && itemNode.getNodeName().equalsIgnoreCase("item")) {

                            Element eElement = (Element) itemNode;
                            String title = getTitle(eElement);
                            String link = getLink(eElement);
                            String descip = getDescription(eElement);
                            String pubDateStr = getPubDate(eElement);
                            Date data = null;




                            try {
                                if (pubDateStr != null && !pubDateStr.isEmpty()) {
                                   data = formatter.parse(pubDateStr);
                                } else {
                                    // Opcional: loguear que la fecha estaba vacía
                                    // System.out.println("Fecha vacía para el artículo: " + title);
                                }
                                Article new_art = new Article(title, descip, data, link);
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
            e.printStackTrace();
        }

        return listFeed.getArticleList();


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
}



}
