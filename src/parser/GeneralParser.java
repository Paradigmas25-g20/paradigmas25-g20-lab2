package parser;
import subscription.Subscription;

/*Esta clase modela los atributos y metodos comunes a todos los distintos tipos de parser existentes en la aplicacion*/
public abstract class GeneralParser {

  public abstract  Subscription parseSubscriptions(String obj);


}