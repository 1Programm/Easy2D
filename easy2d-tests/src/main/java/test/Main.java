package test;

import com.programm.projects.easy2d.wave.ui.elements.*;
import com.programm.projects.easy2d.wave.ui.elements.layout.HorizontalLayout;
import com.programm.projects.easy2d.wave.ui.elements.layout.VerticalLayout;

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
//
//        create(view -> {
//            Combobox<String> combobox = new Combobox<>();
//            combobox.addItem("A");
//            combobox.addItem("Some long named item!");
//            combobox.addItem("bla bla bla");
//            combobox.addItem("stuff");
//            combobox.selectedIndex().listenChange(index -> System.out.println("Combo: " + combobox.getItem(index)));
//            view.add(combobox);
//        });
//
//        create(view -> {
//            Label label = new Label("Some label in the window...");
//            view.add(label);
//        });

        create(view -> {
            TabView tabs = new TabView();
            View view1 = new View(new VerticalLayout());
            view1.add(new Button("Button 1"));
            view1.add(new Checkbox());
            view1.add(new Button("Button 2"));
            tabs.add(view1, "View 1");

            View view2 = new View(new HorizontalLayout());
            view2.add(new Button("Button 1"));
            view2.add(new Checkbox());
            view2.add(new Button("Button 2"));
            tabs.add(view2, "Second Tab");

            view.add(tabs);
        });
    }

    private static void create(Consumer<View> initFn){
        UITestEngine engine = new UITestEngine();
        initFn.accept(engine.contentView());
        engine.start();
    }

}
