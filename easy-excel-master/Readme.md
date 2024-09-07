# Easy Excel

基于 `Apache POI` 的注解驱动的 Excel 解析工具。

### 使用之前的准备

在使用读取或导出功能之前需要先创建实体类，创建的方式相当简单，只要在实体类上添加类注解 `@ExcelMapper` 和字段注解 `ExcelColumn` 即可。

```java
@ExcelMapper("Student")
public class Student {
    @ExcelColumn("id")
    private Integer id;
    @ExcelColumn("name")
    private String name;
    @ExcelColumn("gander")
    private Gender gender;
    @ExcelColumn("birth")
    private OffsetDateTime birth;
}
```

```java
public enum Gender {
    OTHER("Other", 0),
    MALE("Male", 1),
    FEMALE("Female", 2);

    private String name;
    private Integer value;
}
```

### 导出数据到 Excel：

```java
Collection<Student> instances = new List<>();
File exportFile = new File("/path/to/excel");
exportFile.createNewFile();
ExcelWriter.write(instances, Files.newOutputStream(exportFile.toPath()));
```

### 从 Excel 读取数据到程序中：

```java
File file = new File(ClassLoader.getSystemResource("/path/to/excel").getFile());
List<Student> students = ExcelReader.read(file, Student.class);
```

### 自定义类型解析

EasyExcel 目前只支持基本数据类型，如果需要解析为其他 Java 类可以自定义解析类。

1. 实现 `com.orkva.utils.easy.excel.parser.ExcelClassParser` 接口
```java
public class OffsetDateTimeExcelClassParser implements ExcelClassParser<OffsetDateTime> {

    @Override
    public OffsetDateTime parse(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("y/M/d").withZone(ZoneId.of("Asia/Shanghai")))
                .atTime(LocalTime.MIDNIGHT)
                .atZone(ZoneId.of("Asia/Shanghai")).toOffsetDateTime();
    }

}
```
2. 在 `ExcelClassParserRegister` 中注册
```java
ExcelClassParserRegister.register(OffsetDateTime.class, new OffsetDateTimeExcelClassParser());
```