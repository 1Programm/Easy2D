package com.programm.projects.easy2d.ui.wave.elements;

import com.programm.libraries.reactiveproperties.core.BoolProperty;
import com.programm.libraries.reactiveproperties.core.IntObservable;
import com.programm.libraries.reactiveproperties.core.IntProperty;
import com.programm.libraries.reactiveproperties.core.ObjectProperty;
import com.programm.projects.easy2d.engine.api.IMouse;
import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.GlobalComponentUtils;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.ui.wave.core.IWaveComponent;
import com.programm.projects.easy2d.ui.wave.core.IWaveComponentRenderer;
import com.programm.projects.easy2d.ui.wave.core.bounds.IBounds;
import com.programm.projects.easy2d.ui.wave.core.bounds.IEditableBounds;
import com.programm.projects.easy2d.ui.wave.core.bounds.ValueBounds;
import com.programm.projects.easy2d.ui.wave.core.reactive.UIBoolProperty;
import com.programm.projects.easy2d.ui.wave.core.reactive.UIIntProperty;
import com.programm.projects.easy2d.ui.wave.core.reactive.UIObjectProperty;
import com.programm.projects.easy2d.ui.wave.core.utils.GFXUtils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Combobox<T> extends AbstractTextComponent {

    private static class DefaultComboboxRenderer implements IWaveComponentRenderer<Combobox<?>> {

        @Override
        public void render(IBounds bounds, IPencil pen, Combobox<?> c) {
            Color secondary = c.secondary().get();
            if(secondary != null){
                pen.setColor(secondary);
                IWaveComponent.fillBounds(pen, bounds);
            }

            Color color = GFXUtils.primaryOrDisabled(c);
            if(color != null){
                pen.setColor(color);
                IWaveComponent.drawBounds(pen, bounds);
            }


            ITextComponent.renderText(bounds, pen, c);


            ITextComponent renderer = c.itemRenderer().get();
            if(c.itemsExpanded.get() && renderer != null){
                float curY = bounds.y() + bounds.height();
                float itemHeight = GlobalComponentUtils.getHeightOrMinHeight(pen, c);//c.minHeight(pen);
                float itemX = bounds.x();
                float itemWidth = bounds.width();

                for(int i=0;i<c.items.size();i++){
                    Object item = c.items.get(i);
                    String _item = Objects.toString(item);
                    IEditableBounds renderBounds = c.itemRenderBounds.get(i);

                    renderBounds.bounds(itemX, curY, itemWidth, itemHeight);

                    renderer.text().set(_item);
                    renderer.render(renderBounds, pen, true);

                    curY += itemHeight;
                }
            }
        }
    }

    private static class DefaultItemRenderer extends AbstractTextComponent {
        static {
            GlobalWaveDefaults.setBaseDefault(DefaultItemRenderer.class, "renderer", new Label.DefaultLabelRenderer());
            GlobalWaveDefaults.setBaseDefault(DefaultItemRenderer.class, "primary", Color.BLACK);
            GlobalWaveDefaults.setBaseDefault(DefaultItemRenderer.class, "secondary", Color.WHITE);
            GlobalWaveDefaults.setBaseDefault(DefaultItemRenderer.class, "disabledColor", Color.LIGHT_GRAY);
        }
    }

    static {
        GlobalWaveDefaults.setBaseDefault(Combobox.class, "renderer", new DefaultComboboxRenderer());
        GlobalWaveDefaults.setBaseDefault(Combobox.class, "primary", Color.BLACK);
        GlobalWaveDefaults.setBaseDefault(Combobox.class, "secondary", Color.WHITE);
        GlobalWaveDefaults.setBaseDefault(Combobox.class, "disabledColor", Color.LIGHT_GRAY);
        GlobalWaveDefaults.setBaseDefault(Combobox.class, "itemRenderer", new DefaultItemRenderer());
        GlobalWaveDefaults.setBaseDefault(Combobox.class, "itemsExpanded", false);
    }

    protected final List<T> items;
    protected List<IEditableBounds> itemRenderBounds;

    private final UIIntProperty selectedIndex = new UIIntProperty(getClass(), "selectedIndex");
    private final UIIntProperty hoveredIndex = new UIIntProperty(getClass(), "hoveredIndex");
    private final UIObjectProperty<ITextComponent> itemRenderer = new UIObjectProperty<>(getClass(), "itemRenderer");
    private final UIBoolProperty itemsExpanded = new UIBoolProperty(getClass(), "itemsExpanded");

    public Combobox(List<T> items, int selectedIndex) {
        this.items = items;
        this.itemRenderBounds = initRenderBounds(items.size());

        this.selectedIndex.redraw(this);
        this.hoveredIndex.redraw(this);
        this.itemRenderer.redraw(this);//TODO: maybe let the renderer control some size stuff
        this.itemsExpanded.recalculate(this);

        this.selectedIndex.listen(this::updateTextForSelectedItem);

        this.selectedIndex.set(selectedIndex);
    }

    public Combobox(T[] items, int selectedIndex) {
        this(new ArrayList<>(List.of(items)), selectedIndex);
    }

    public Combobox(T[] items) {
        this(items, -1);
    }

    public Combobox() {
        this(new ArrayList<>(), -1);
    }

    private void updateTextForSelectedItem(){
        T item = selectedItem();
        String _item;
        if(item == null){
            _item = "";
        }
        else {
            _item = Objects.toString(item);
        }
        text().set(_item);
    }

    private List<IEditableBounds> initRenderBounds(int size){
        List<IEditableBounds> renderBounds = new ArrayList<>();

        for(int i=0;i<size;i++){
            renderBounds.add(new ValueBounds());
        }

        return renderBounds;
    }

    @Override
    public Float minWidth(IPencil pen) {
        if(minWidth == null){
            for(int i=0;i<items.size();i++) {
                String _item = items.get(i).toString();
                float itemMinWidth = pen.stringWidth(_item);
                if(minWidth == null){
                    minWidth = itemMinWidth;
                }
                else {
                    minWidth = Math.max(minWidth, itemMinWidth);
                }
            }

            if(minWidth != null){
                minWidth += 4;
            }
        }

        return minWidth;
    }

    @Override
    public Float minHeight(IPencil pen) {
        if(minHeight == null){
            minHeight = pen.stringHeight();
        }

        return minHeight;
    }

    @Override
    public void onMousePressed(IBounds bounds, IMouse mouse, int button) {
        if(disabled().get()) return;

        if(itemsExpanded.get()){
            for(int i=0;i<items.size();i++){
                IBounds renderBounds = itemRenderBounds.get(i);

                if(renderBounds.inside(mouse.x(), mouse.y())){
                    selectedIndex.set(i);
                    itemsExpanded.set(false);
                    requestRedraw();
                    return;
                }
            }
        }

        if(button == MouseEvent.BUTTON1) {
            if(itemsExpanded.get()){
                itemsExpanded.set(false);
                hoveredIndex.set(-1);
                requestRedraw();
            }
            else {
                if(bounds.inside(mouse.x(), mouse.y())) {
                    itemsExpanded.set(true);
                    hoveredIndex.set(selectedIndex.get());
                    requestRedraw();
                }
            }
        }
    }

    @Override
    public void onMouseMoved(IBounds bounds, IMouse mouse) {
        if(disabled().get()) return;

        if(itemsExpanded.get()){
            hoveredIndex.set(-1);
            for(int i=0;i<items.size();i++){
                IBounds renderBounds = itemRenderBounds.get(i);
                if(renderBounds.inside(mouse.x(), mouse.y())){
                    hoveredIndex.set(i);
                    requestRedraw();
                }
            }
        }
    }

    public void addItem(T item){
        items.add(item);
        itemRenderBounds.add(new ValueBounds());
        minWidth = null;
        minHeight = null;
        requestRedraw();
    }

    public List<T> items(){
        return items;
    }

    public List<IEditableBounds> itemRenderBounds(){
        return itemRenderBounds;
    }

    public T getItem(int index){
        return items.get(index);
    }

    public T selectedItem(){
        return selectedIndex.get() == -1 ? null : getItem(selectedIndex.get());
    }

    public IntProperty selectedIndex(){
        return selectedIndex;
    }

    public IntObservable hoveredIndex(){
        return hoveredIndex;
    }

    public ObjectProperty<ITextComponent> itemRenderer(){
        return itemRenderer;
    }

    public BoolProperty itemsExpanded(){
        return itemsExpanded;
    }

}
