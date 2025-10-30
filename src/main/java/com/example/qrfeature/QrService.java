package com.example.qrfeature;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QrService {

    /**
     * Gera um código QR para o texto fornecido e devolve-o como um array de bytes PNG.
     * @param text O conteúdo a ser codificado no QR Code.
     * @return Um array de bytes contendo a imagem PNG do QR Code.
     */
    public byte[] generateQrCode(String text) {
        // Usa o try-with-resources para garantir que o stream é fechado
        try (ByteArrayOutputStream stream = QRCode
                .from(text)
                .withSize(300, 300)
                .to(ImageType.PNG)
                .stream()) {

            return stream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar QR Code", e);
        }
    }
}
