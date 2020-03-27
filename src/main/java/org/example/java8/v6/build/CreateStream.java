package org.example.java8.v6.build;

import javax.xml.xpath.XPath;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @Author wangp
 * @Date 2020/3/26
 * @Version 1.0
 */
public class CreateStream {


    public static void main(String[] args) {
        createStreamFromCollection().forEach(System.out::println);
        createStreamFromValue().forEach(System.out::println);
        createStreamByArray().forEach(System.out::println);
        createStreamFromFile();
        createStreamFromIterator().forEach(System.out::println);
        createStreamGenerate().forEach(System.out::println);
        createObjStreamFromGenerate().forEach(System.out::println);
    }


    /**
     *  Generate the stream object from collection
     * @return
     */
    private static Stream<String> createStreamFromCollection(){
        List<String> strings = Arrays.asList("bruce", "farling", "jade", "alex");
        return strings.stream();
    }

    private static Stream<String> createStreamFromValue(){
        Stream<String> stream = Stream.of("bruce", "farling", "jade", "alex");
        return stream;
    }

    private static Stream<String> createStreamByArray(){
        String[] arr = {"bruce", "farling", "jade", "alex"};
        return Arrays.stream(arr);
    }

    private static Stream<String>  createStreamFromFile(){
        Path path = Paths.get("D:\\spring_learn\\java8\\src\\main\\java\\org\\example\\java8\\v6\\build\\CreateStream.java");
        try{
            Stream<String> lines = Files.lines(path);
            lines.filter(
                    s -> s.contains("Stream")
            ).forEach(System.out::println);
            return lines;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static Stream<Integer> createStreamFromIterator(){
        Stream<Integer> stream = Stream
                .iterate(0,n->n+2)
                .limit(10);
        return stream;
    }

    private static Stream<Double> createStreamGenerate(){
        Stream<Double> generate = Stream.generate(Math::random).limit(10);
        return generate;
    }


    static class ObjSupplier implements Supplier<Obj>{

        private int index = 0;
        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index = random.nextInt();
            return new Obj(index,"name ->"+index);
        }
    }

    static class Obj{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    private static Stream<Obj> createObjStreamFromGenerate(){
        return Stream.generate(new ObjSupplier()).limit(10);
    }
}
