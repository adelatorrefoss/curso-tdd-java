public class CoffeeMachine {
    private final DrinkMaker maker;
    private final DrinkMakerInterpreter interpreter;
    private DrinkType drinkType;
    private SugarCount sugarCount;

    public CoffeeMachine(DrinkMaker maker, DrinkMakerInterpreter interpreter) {
        this.interpreter = interpreter;
        this.maker = maker;
    }

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public void setSugarCount(SugarCount count) {
        this.sugarCount = count;
    }

    public void execute() {
        String buffer = interpreter.interpret(this.drinkType, this.sugarCount);
        maker.execute(buffer);
    }

    public void sendMessage(String message) {
        maker.execute("M:" + message);
    }

    public enum DrinkType {
        COFFEE("C"),
        TEA("T"),
        CHOCOLATE("H");

        private final String value;

        DrinkType(String value) {
            this.value = value;
        }
    }

    public enum SugarCount {
        ZERO,
        ONE,
        TWO
    }
}
