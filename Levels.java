public class Levels {


    public static void tester(Map obstacles) {
        obstacles.editRow(0, 0, "#######################");
        obstacles.editCol(22, 0, "#####################");
        obstacles.editCol(0, 0, "#####################");
        obstacles.editCol(7, 0, "#####");
        obstacles.editRow(8, 4, "#########");
        obstacles.editRow(4, 7, "#######");
        obstacles.editCol(4, 8, "#####");
        obstacles.editCol(18, 0, "#############");
        obstacles.editRow(12, 8, "##########");
        obstacles.editRow(16, 4, "###############");
        obstacles.editRow(20, 0, "######################");


    }

    @SuppressWarnings("unused")
    public static void debug(Map field) {
        field.editCol(2, 0, "###");

        field.printAll();
    }

}
