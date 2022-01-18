public class Memory {

    public static void main(String[] args) {
        int i = 1;
        Object obj = new Object();
        Memory mem = new Memory();

    }

    private void foo(Object param) {
        Object reference2 = param;
        String str = reference2.toString();
        System.out.println(str);
    }
}
