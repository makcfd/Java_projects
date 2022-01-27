
public class Main {
    public static void main(String[] args) {
        TodoList todo = new TodoList()
                .addPrimary("Sleep")
                .addPrimary("Eat")
                .addPrimary("Study")
                .addSecondary("Play game")
                .addSecondary("Watch series");

        for (String task: todo ) {
            System.out.println(task);
        }
    }
}
