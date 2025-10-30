package pt.isel.leic.todoapp.utils;

import net.glxn.qrgen.javase.QRCode;
import net.glxn.qrgen.core.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class QrCodeGenerator {

    public static File generateQrToFile(String text, String outputPath) throws IOException {
        ByteArrayOutputStream stream = QRCode
                .from(text)
                .withSize(300, 300)
                .to(ImageType.PNG)
                .stream();

        File outputFile = new File(outputPath);
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(stream.toByteArray());
        }
        return outputFile;
    }

    public static void main(String[] args) {
        try {
            String content = "{\"id\":1,\"title\":\"Comprar pão\",\"description\":\"Ir à padaria às 9h\"}";
            File qr = generateQrToFile(content, "task-1.png");
            System.out.println("✅ QR Code gerado em: " + qr.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
