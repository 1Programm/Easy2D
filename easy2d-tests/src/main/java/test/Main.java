package test;

import com.programm.projects.easy2d.wave.ui.elements.Button;
import com.programm.projects.easy2d.wave.ui.elements.Checkbox;
import com.programm.projects.easy2d.wave.ui.elements.Label;
import com.programm.projects.easy2d.wave.ui.elements.*;
import com.programm.projects.easy2d.wave.ui.elements.layout.AbstractPolicyLayout;
import com.programm.projects.easy2d.wave.ui.elements.layout.GridLayout;
import com.programm.projects.easy2d.wave.ui.elements.layout.HorizontalLayout;
import com.programm.projects.easy2d.wave.ui.elements.layout.VerticalLayout;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        UITestEngine engine = new UITestEngine();

        initUI_LabelTest1(engine.contentView());
//        initUI_ButtonTest1(engine.contentView());
//        initUI_CheckboxTest1(engine.contentView());
//        initUI_ComboboxTest1(engine.contentView());
//        initUI_TabViewTest1(engine.contentView());
//        initUI_LayoutGrid(engine.contentView());
//        initUI_BasicWindowTest1(engine.contentView());

        engine.window().moveToScreen(1);
        engine.start();
    }

    private static void initUI_LabelTest1(View rootView) {
        Label label = new Label("Some label in the window...");
        rootView.add(label);
    }

    private static void initUI_ButtonTest1(View rootView) {
        Button btn = new Button("Hello World!");
        btn.disabled().set(true);
        btn.pressed().fTrue().listen(() -> System.out.println("Press!"));
        rootView.add(btn);
    }

    private static void initUI_CheckboxTest1(View rootView) {
        Checkbox checkbox = new Checkbox();
        checkbox.checked().listen(() -> System.out.println("Check!"));
        rootView.add(checkbox);
    }

    private static void initUI_ComboboxTest1(View rootView){
        Combobox<String> combobox = new Combobox<>();
        combobox.addItem("A");
        combobox.addItem("Some long named item!");
        combobox.addItem("bla bla bla");
        combobox.addItem("stuff");
        combobox.selectedIndex().listenChange(index -> System.out.println("Combo: " + combobox.getItem(index)));
        rootView.add(combobox);
    }

    private static void initUI_TabViewTest1(View rootView){
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

        rootView.add(tabs);
    }

    private static void initUI_LayoutGrid(View rootView){
        rootView.setLayout(new GridLayout(3, 3));
        rootView.secondary().set(Color.RED);
        rootView.margin(10, 10, 10, 10);

        for(int i=0;i<9;i++){
            int x = i % 3;
            int y = i / 3;

            Label label = new Label("(" + x + " / " + y + ")");
            rootView.add(label, i);
        }
    }

    private static void initUI_BasicWindowTest1(View rootView){
        rootView.setLayout(new VerticalLayout(AbstractPolicyLayout.POLICY_STRETCH, AbstractPolicyLayout.POLICY_INITIAL));

        View topControlsView = new View(
                new HorizontalLayout()
                .padding(20)
//                .verticalPolicy(HorizontalLayout.POLICY_STRETCH_FORCE)
                .horizontalAlign(HorizontalLayout.ALIGN_LEFT)
                .verticalAlign(HorizontalLayout.ALIGN_CENTER)
        );
//        topControlsView.secondary().set(Color.RED);
        topControlsView.margin(10, 10, 10, 10);
        topControlsView.add(new Button("Play").size(130, 60));
        topControlsView.add(new Button("Pause").size(50, 40));

        View gameView = new View();
//        gameView.secondary().set(Color.BLUE);

        View botControlsView = new View(new HorizontalLayout());
//        botControlsView.secondary().set(Color.YELLOW);
        botControlsView.height(100);

        rootView.add(topControlsView);
        rootView.add(gameView, AbstractPolicyLayout.POLICY_STRETCH);
        rootView.add(botControlsView);
    }

}
