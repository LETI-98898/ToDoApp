package com.example.qrfeature;

import com.example.base.ui.MainLayout;
import com.example.base.ui.component.ViewToolbar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;

@Route(value = "qr", layout = MainLayout.class)
@PageTitle("QR Code Generator")
@Menu(order = 2, icon = "vaadin:qrcode", title = "QR Code")
public class QrView extends VerticalLayout {

    // O Spring/Vaadin injetará automaticamente o QrService
    private final QrService qrService;
    private final Image qrCodeImage = new Image();
    private final TextField contentField = new TextField("Conteúdo do QR Code");

    public QrView(QrService qrService) {
        this.qrService = qrService;

        // 1. Configuração da UI
        add(new ViewToolbar("QR Code Generator"));
        setSizeFull();

        contentField.setValue("Exemplo: " + LocalDateTime.now());
        contentField.setWidth("300px");

        Button generateButton = new Button("Gerar QR Code");

        qrCodeImage.setAlt("QR Code gerado");
        qrCodeImage.setWidth("300px");
        qrCodeImage.setVisible(false); // Esconde a imagem até ser gerada

        // 2. Lógica de Geração e Exibição
        generateButton.addClickListener(event -> {
            String content = contentField.getValue();

            if (content != null && !content.isEmpty()) {
                // 1. Gerar os bytes da imagem
                byte[] qrBytes = qrService.generateQrCode(content);

                // 2. Criar um recurso de Stream para o Vaadin
                StreamResource resource = new StreamResource("qrcode.png",
                        () -> new ByteArrayInputStream(qrBytes)
                );

                // 3. Aplicar o recurso ao componente Image e mostrar
                qrCodeImage.setSrc(resource);
                qrCodeImage.setVisible(true);
            }
        });

        add(contentField, generateButton, qrCodeImage);
    }
}
