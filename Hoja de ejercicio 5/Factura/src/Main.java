import java.util.ArrayList;
import java.util.HashMap;

class Factura {

    int idFactura;
    String cliente;
    double monto;

    public Factura(int idFactura, String cliente, double monto) {
        this.idFactura = idFactura;
        this.cliente = cliente;
        this.monto = monto;
    }
}

public class Main {

    public static void generarResumen(ArrayList<Factura> facturas) {

        HashMap<String, Double> resumen = new HashMap<>();

        for (Factura factura : facturas) {

            if (resumen.containsKey(factura.cliente)) {

                double totalActual = resumen.get(factura.cliente);

                resumen.put(
                        factura.cliente,
                        totalActual + factura.monto
                );

            } else {

                resumen.put(factura.cliente, factura.monto);
            }
        }

        for (String cliente : resumen.keySet()) {

            System.out.println(
                    cliente + " - Total facturado: Q" +
                    resumen.get(cliente)
            );
        }
    }

    public static void main(String[] args) {

        ArrayList<Factura> facturas = new ArrayList<>();

        facturas.add(new Factura(1, "Carlos", 1000));
        facturas.add(new Factura(2, "Ana", 1500));
        facturas.add(new Factura(3, "Carlos", 2000));
        facturas.add(new Factura(4, "Ana", 500));
        facturas.add(new Factura(5, "Luis", 3000));

        generarResumen(facturas);
    }
}