package utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.assertj.core.util.Arrays;

import java.io.File;

@Log4j2
public class FileUtil {

    public static File findFile(String name, String extension) {
        var userDir = System.getProperty("user.dir");
        var fullName = name.concat(".").concat(extension);
        log.debug("trying to find {}.{} file recursively in {}", name, extension, userDir);
        var searchedFile = FileUtils
                .listFiles(new File(userDir), Arrays.array(extension), true)
                .stream()
                .filter(file -> file.getPath().endsWith(fullName))
                .findFirst();
        searchedFile.ifPresent(file ->
                log.debug("environment.conf file found: {}", file.getAbsolutePath()));
        return searchedFile.orElseThrow();
    }

}
