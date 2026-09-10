package web;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class TemplateRenderer {

    public String returnTemplate(String templateHtml) throws IOException {
        String percorsoCompleto = "templates/" + templateHtml;

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(percorsoCompleto)) {
            if (inputStream == null) {
                throw new IOException("File non trovato: " + percorsoCompleto);
            }

            String contenutoHtml = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            return contenutoHtml;

        }

        
    }

}