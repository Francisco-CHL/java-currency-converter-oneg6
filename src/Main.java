import com.fogalde.currency_converter.exceptions.CannotFindCurrencyException;
import com.fogalde.currency_converter.models.Conversion;
import com.fogalde.currency_converter.models.ConversionApiExchange;
import com.fogalde.currency_converter.utils.ExchangeApiRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        String baseCode;
        String targetCode;
        Double amount;
        Scanner sc = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        while (true) {
            try {
                //Imprimimos el menú
                printMenu();
                //Leemos la opción elegida por el usuario
                String input = sc.nextLine();
                //Creamos un array que para almacenar las dos divisas
                String[] currencies = new String[2];
                //Verificamos que el usuario desea seguir con el programa
                if (input.equalsIgnoreCase("7")) {
                    System.out.println("\n\nSaliendo del programa, gracias por utilizar nuestros servicios........");
                    break;
                }
                //Si el programa no se cierra, asignamos los códigos de divisas a las variables correspondientes
                baseCode = checkOptionSelected(input)[0];
                targetCode = checkOptionSelected(input)[1];
                //Preguntamos el monto que desea convertir y lo asignamos a una variable
                System.out.print("Ingrese el monto que desea convertir: ");
                amount = Double.parseDouble(sc.nextLine());
                //Creamos un objeto de la clase EchangeApiRequest que envia la petición con las variables asignadas arriba
                ExchangeApiRequest response = new ExchangeApiRequest(amount, baseCode, targetCode);
                //Solicitamos la respuesta en formato json al metodo de la clase encargada de la conexión
                String responseJson = response.getExchangeApiConversion();
                //Creamos una instancia de la clases Record de Conversion y populamos con Gson los atributos
                ConversionApiExchange conversionRecord = gson.fromJson(responseJson, ConversionApiExchange.class);
                //Creamos una nueva instancia de la clase conversion
                Conversion conversion = new Conversion(conversionRecord, amount);
                //Imprimimos el resultado haciendo referencia directa al objeto ya que se sobreescribió su método toString()
                System.out.println("\n\n****************** Resultado ******************");
                System.out.println(conversion);
                System.out.println("***********************************************\n\n");

            }catch (CannotFindCurrencyException e){
                System.out.println("\n\n\n\nError: " + e.getMessage());
            }catch (NumberFormatException e){
                System.out.println("\n\n\n\nError: Solo se aceptan números");
            }catch (NullPointerException e){
                System.out.println("\n\n\n\nError: hay valores nulos");
            }catch (Exception e){
                System.out.println("Error desconocido, entregue el siguiente mensaje a soporte: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                ************************ Conversor de divisas ************************
                                    
                Bienvenido(a), seleccione la opción que desea ejecutar:
                                    
                1 - Dolar (USD) a Peso Chileno (CLP).
                2 - Peso Chileno (CLP) a Dolar (USD).
                3 - Peso Argentino (ARS) a Peso Chileno (CLP).
                4 - Peso Chileno (CLP) a Peso Argentino (ARS).
                5 - Peso Colombiano (COP) a Peso Chileno (CLP).
                6 - Peso Chileno (CLP) a Peso Colombiano (COP).
                7 - Salir del programa.
                                    
                **********************************************************************
                """);
    }

    private static String[] checkOptionSelected(String input) {
        String[] s = new String[2];
        switch (input) {
            case "1":
                s[0] = "USD";
                s[1] = "CLP";
                break;
            case "2":
                s[0] = "CLP";
                s[1] = "USD";
                break;
            case "3":
                s[0] = "ARS";
                s[1] = "CLP";
                break;
            case "4":
                s[0] = "CLP";
                s[1] = "ARS";
                break;
            case "5":
                s[0] = "COP";
                s[1] = "CLP";
                break;
            case "6":
                s[0] = "CLP";
                s[1] = "COP";
                break;
            default:
                throw new CannotFindCurrencyException("No se puede procesar su solicitud, ingrese nuevamente la opción...\n\n\n\n\n\n");
        }
        return s;
    }
}
