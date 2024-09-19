package test;

public class Test {
    public static void main(String[] args) {
        String x = "lamidu.rathnayake";
        int dotIndex = x.lastIndexOf('.');
        String sub = x.substring(dotIndex);
        System.out.println(sub);
    }
}
