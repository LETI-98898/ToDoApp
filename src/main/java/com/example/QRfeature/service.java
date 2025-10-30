package com.example.examplefeature.service;

import com.example.examplefeature.utils.QrCodeGenerator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
public class QrCodeService {

    public File generateTaskQr(Long id, String title, String description) throws IOException {
        String json = String.format("{\"id\":%d,\"title\":\"%s\",\"description\":\"%s\"}", id, title, description);
        String outputPath = "target/task-" + id + ".png";
        return QrCodeGenerator.generateQrToFile(json, outputPath);
    }
}
