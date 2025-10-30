import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.service.QrCodeService;
import java.io.File;
import java.io.IOException;

@Route("tasks")
@UIScope
public class view extends VerticalLayout {

    private final QrCodeService qrCodeService;

    @Autowired
    public view(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;

       
        Long id = 1L;
        String title = "Comprar pão";
        String description = "Ir à padaria às 9h";

        Button qrButton = new Button("Gerar QR Code", event -> {
            try {
                File qrFile = qrCodeService.generateTaskQr(id, title, description);

                Image qrImage = new Image(qrFile.toURI().toString(), "QR da tarefa");
                qrImage.setWidth("200px");
                qrImage.setHeight("200px");

                add(qrImage);
                Notification.show("QR Code gerado com sucesso!");
            } catch (IOException e) {
                Notification.show("Erro ao gerar QR Code: " + e.getMessage());
                e.printStackTrace();
            }
        });

        add(qrButton);
    }
}
