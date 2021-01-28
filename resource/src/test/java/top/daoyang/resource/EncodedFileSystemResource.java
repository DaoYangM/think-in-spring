package top.daoyang.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Encoded的FileSystemResource
 *
 * @see EncodedResource
 * @see FileSystemResource
 */
public class EncodedFileSystemResource {

    @Test
    public void encodedFileSystemResourceTest() throws IOException {
        FileSystemResource fileSystemResource = new FileSystemResource(
                "/Users/deyangye/IdeaProjects/think-in-spring/resource/src/test/java/top/daoyang/resource" +
                        "/EncodedFileSystemResource.java");

        Assertions.assertTrue(fileSystemResource.isFile());

        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");
        Reader reader = encodedResource.getReader();

        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
