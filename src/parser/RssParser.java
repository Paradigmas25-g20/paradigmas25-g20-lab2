package parser;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilder;


/* Esta clase implementa el parser de feed de tipo rss (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
 * */

public class RssParser extends GeneralParser {

    private String parserTitle;
    private String parserDescription;
    private String parserurlType;




  @Override
  public String  setParser(String http){
       //Creating a DocumentBuilder Object
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	     DocumentBuilder docBuilder = factory.newDocumentBuilder();


      Document xmldoc = docBuilder.parse(input);



  }

}
