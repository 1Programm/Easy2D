package test;

import com.programm.projects.easy2d.wave.ui.elements.Button;
import com.programm.projects.easy2d.wave.ui.elements.Checkbox;
import com.programm.projects.easy2d.wave.ui.elements.Combobox;
import com.programm.projects.easy2d.wave.ui.elements.View;

import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
//        create(view -> {
//            Button btn = new Button("Hello World!");
//            btn.pressed().fTrue().listen(() -> System.out.println("Press!"));
//            view.add(btn);
//        });
//
//        create(view -> {
//            Checkbox checkbox = new Checkbox();
//            checkbox.checked().listen(() -> System.out.println("Check!"));
//            view.add(checkbox);
//        });

        create(view -> {
            Combobox<String> combobox = new Combobox<>();
            combobox.addItem("A");
            combobox.addItem("Some long named item!");
            combobox.addItem("bla bla bla");
            combobox.addItem("stuff");
            combobox.selectedIndex().listenChange(index -> System.out.println("Combo: " + combobox.getItem(index)));
            view.add(combobox);
        });
    }

    private static void create(Consumer<View> initFn){
        UITestEngine engine = new UITestEngine();
        initFn.accept(engine.contentView());
        engine.start();
    }

}
