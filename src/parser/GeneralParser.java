package parser;

import java.util.List;

/*Esta clase modela los atributos y metodos comunes a todos los distintos tipos de parser existentes en la aplicacion*/
public abstract class GeneralParser {

  public abstract String  inilizialite(String obj);
  //agregame las calses abs
  public abstract String getUrl();
  public abstract String getUrlType();
  public abstract List<String> getParams(String jsonContent);
  public abstract String getUrlParams();
  public abstract List<String> getUlrParams();


}
