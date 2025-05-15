package parser;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
/*
 * Esta clase implementa el parser del archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */

public class SubscriptionParser extends GeneralParser {

  private  String parserurl;
	private List<String> parserulrParams;
	private  String parserurlType;

  public String initialize(String url) {
    JSONObject obj = new JSONObject(url);
    this.parserurl = obj.getString("url");
    this.parserurlType = obj.getString("urlType");
    return obj.toString();
  }

  @Override
  public  List<String>  getParams(String jsonContent) {


    this.parserulrParams= new ArrayList<>();
try{
    JSONObject obj = new JSONObject(jsonContent);
    JSONArray urlParamsArray = obj.getJSONArray("urlParams");


    for (int i = 0; i < urlParamsArray.length(); i++) {
      this.parserulrParams.add(urlParamsArray.getString(i));
    }
}catch (JSONException e){
  System.err.println("Error al parsear JSON para obtener parámetros: " + e.getMessage());
}
    return this.parserulrParams;// Guardar los parámetros en el atributo de la clase


  }
  @Override
  public String getUrl() {
    return parserurl;
}
@Override
public String getUrlType() {
    return parserurlType;
}
@Override
public List<String> getUlrParams() {
    return parserulrParams;
}



}
