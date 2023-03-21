package com.srivn.works.smaster.smasterhome.utils;

import com.srivn.works.smaster.smasterhome.services.StudentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(AppFileUtil.class);
    static RetryLogic retryLogic = new RetryLogic(3, 2000);


    public static boolean saveFile(String filePath, MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                deleteFile(filePath);
                Files.write(path, bytes);
            } else {
                Files.write(path, bytes);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        try {
            Files.delete(path);
        } catch (Exception e) {
            logger.info(e.getMessage());
            retryLogic.retryImpl(() -> {
                        deleteFile(filePath);
                        return null;
                    }
            );
        }
        return true;
    }

    public static byte[] readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }
}
