package com.react.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
public class FileUtil {
    public String getFileDataIfFile(String filePath, boolean trimRequired) throws IOException {
        Path path = Paths.get(filePath);
        if (Files.exists(path) && Files.isRegularFile(path)) {
            String data = Files.readString(path);
            return trimRequired ? StringUtils.trim(data) : data;
        }
        return null;
    }

}
