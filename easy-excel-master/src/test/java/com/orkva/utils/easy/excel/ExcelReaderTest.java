package com.orkva.utils.easy.excel;

import com.orkva.utils.easy.excel.entity.Student;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * ExcelWriterTest
 *
 * @author Shepherd Xie
 * @version 2022/11/15
 */
public class ExcelReaderTest {

    @Test
    public void testRead() {

        File sourceFile = new File(ClassLoader.getSystemResource("test_student.xlsx").getFile());
        List<Student> students = ExcelReader.read(sourceFile, Student.class);
        for (Student student : students) {
            System.out.println(student);
        }
        Assert.assertNotNull(students);
    }

}
