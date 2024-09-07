package com.orkva.utils.easy.excel;

import com.orkva.utils.easy.excel.entity.Gender;
import com.orkva.utils.easy.excel.entity.Student;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ExcelWriterTest
 *
 * @author Shepherd Xie
 * @version 2022/11/15
 */
public class ExcelWriterTest {

    @Test
    public void testWrite() {
        List<Student> entities = new ArrayList<>();
        entities.add(new Student(1, "Larry", Gender.MALE, OffsetDateTime.parse("2002-03-14T00:00:00+08:00")));
        entities.add(new Student(2, "Anna", Gender.FEMALE, OffsetDateTime.parse("1999-12-03T00:00:00+08:00")));
        entities.add(new Student(3, "Emma", Gender.FEMALE, OffsetDateTime.parse("1998-02-26T00:00:00+08:00")));
        entities.add(new Student(4, "White", Gender.OTHER, OffsetDateTime.parse("2000-09-04T00:00:00+08:00")));
        for (Student entity : entities) {
            System.out.println(entity);
        }
        writeToFile(entities, "./src/test/resources/test_export.xlsx");
    }

    public static void writeToFile(Collection instances, String path) {
        File exportFile = new File(path);
        try {
            boolean newFile = exportFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ExcelWriter.write(instances, Files.newOutputStream(exportFile.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
