import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoffeeMachineTest {
    private DrinkMaker maker;
    private DrinkMakerInterpreter interpreter;
    private CoffeeMachine coffeeMachine;

    @BeforeEach
    void setUp() {
        maker = mock(DrinkMaker.class);
        interpreter = mock(DrinkMakerInterpreter.class);
        coffeeMachine = new CoffeeMachine(maker, interpreter);
    }

    // - The drink maker should receive the correct instructions for my coffee / tea / chocolate order
    // - I want to be able to send instructions to the drink maker to add one or two sugars
    // - When my order contains sugar the drink maker should add a stick (touillette) with it

    //@Test
    void drink_maker_brews_just_chocolate() {
        coffeeMachine.setDrinkType(CoffeeMachine.DrinkType.CHOCOLATE);
        coffeeMachine.setSugarCount(CoffeeMachine.SugarCount.ZERO);
        coffeeMachine.execute();

        verify(maker).execute("H::");
    }

    @Test
    void drink_maker_brews_just_chocolate_1() {
        coffeeMachine.setDrinkType(CoffeeMachine.DrinkType.CHOCOLATE);
        coffeeMachine.setSugarCount(CoffeeMachine.SugarCount.ZERO);
        coffeeMachine.execute();

        verify(interpreter).interpret(CoffeeMachine.DrinkType.CHOCOLATE, CoffeeMachine.SugarCount.ZERO);
    }

    @Test
    void drink_maker_interprets_just_chocolate() {

        // unitario
        coffeeMachine.setDrinkType(CoffeeMachine.DrinkType.CHOCOLATE);
        coffeeMachine.setSugarCount(CoffeeMachine.SugarCount.ZERO);
        coffeeMachine.execute();

        verify(repository).save('antonio', 'de la torre');


        // integration  opcion1
        coffeeMachine.setDrinkType(CoffeeMachine.DrinkType.CHOCOLATE);
        coffeeMachine.setSugarCount(CoffeeMachine.SugarCount.ZERO);
        coffeeMachine.execute();

        result = repository.query();
        assertEquals(result.name , 'antonio')

        
        // integracion opcion 2

        repository.insert
        repository.query()

        result = repository.query();
        assertEquals(result.name , 'antonio')




        // opcion 2
        interpreter.interpret(CoffeeMachine.DrinkType.CHOCOLATE, CoffeeMachine.SugarCount.ZERO);

        verify(maker).execute("H::");
    }

    //@Test
    void drink_maker_brews_one_tea_with_sugar_and_a_stick() {
        // T:1:0 Drink maker makes 1 tea with 1 sugar and a stick
        coffeeMachine.setDrinkType(CoffeeMachine.DrinkType.TEA);
        coffeeMachine.setSugarCount(CoffeeMachine.SugarCount.ONE);
        coffeeMachine.execute();

        verify(maker).execute("T:1:0");
    }

    //@Test
    void drink_maker_brews_one_coffee_with_two_sugars_and_a_stick() {
        coffeeMachine.setDrinkType(CoffeeMachine.DrinkType.COFFEE);
        coffeeMachine.setSugarCount(CoffeeMachine.SugarCount.TWO);
        coffeeMachine.execute();

        verify(maker).execute("C:2:0");
    }
}
