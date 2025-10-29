package pdffeature;

import com.example.base.ui.MainLayout;
import com.example.base.ui.component.ViewToolbar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;

@Route(value = "pdf", layout = MainLayout.class)
public class PdfView extends VerticalLayout {

    private final PdfService pdfService = new PdfService();

    public PdfView() {
        // Toolbar consistente com o resto da aplicação
        add(new ViewToolbar("PDF Generator"));

        // Botão para gerar PDF
        Button generateButton = new Button("Gerar PDF");
        add(generateButton);

        // Link de download inicial vazio
        Anchor downloadLink = new Anchor();
        downloadLink.setText("Download PDF");
        add(downloadLink);

        generateButton.addClickListener(event -> {
            byte[] pdfBytes = pdfService.generateSamplePdf("Olá, PDF!");
            ByteArrayInputStream pdfStream = new ByteArrayInputStream(pdfBytes);

            StreamResource resource = new StreamResource("sample.pdf", () -> pdfStream);
            downloadLink.setHref(resource);
        });
    }
}