import java.util.ArrayList;

class HistorialNavegacion {

    ArrayList<String> historial = new ArrayList<>();

    public void visitarPagina(String url) {

        historial.add(url);

        if (historial.size() > 10) {
            historial.remove(0);
        }
    }

    public void mostrarHistorial() {

        for (String url : historial) {
            System.out.println(url);
        }
    }
}

public class Main {

    public static void main(String[] args) {

        HistorialNavegacion historial = new HistorialNavegacion();

        historial.visitarPagina("google.com");
        historial.visitarPagina("youtube.com");
        historial.visitarPagina("facebook.com");
        historial.visitarPagina("github.com");
        historial.visitarPagina("openai.com");
        historial.visitarPagina("amazon.com");
        historial.visitarPagina("wikipedia.org");
        historial.visitarPagina("oracle.com");
        historial.visitarPagina("microsoft.com");
        historial.visitarPagina("instagram.com");
        historial.visitarPagina("tiktok.com");

        historial.mostrarHistorial();
    }
}