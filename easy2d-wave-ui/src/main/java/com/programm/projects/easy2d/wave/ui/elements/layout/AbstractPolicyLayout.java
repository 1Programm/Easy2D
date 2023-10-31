package com.programm.projects.easy2d.wave.ui.elements.layout;

public abstract class AbstractPolicyLayout implements ILayout {

    public static final int POLICY_INITIAL = 0;
    public static final int POLICY_STRETCH = 1;
    public static final int POLICY_STRETCH_FORCE = 2;
    public static final int POLICY_SPREAD = 3;

    protected int horizontalPolicy;
    protected int verticalPolicy;
    protected int horizontalAlign;
    protected int verticalAlign;

    public AbstractPolicyLayout() {
        this.horizontalPolicy = POLICY_INITIAL;
        this.verticalPolicy = POLICY_INITIAL;
        horizontalAlign = ALIGN_LEFT;
        verticalAlign = ALIGN_TOP;
    }

    public AbstractPolicyLayout(int horizontalPolicy, int verticalPolicy, int horizontalAlign, int verticalAlign) {
        this.horizontalPolicy = horizontalPolicy;
        this.verticalPolicy = verticalPolicy;
        this.horizontalAlign = horizontalAlign;
        this.verticalAlign = verticalAlign;
    }

    public AbstractPolicyLayout horizontalPolicy(int horizontalPolicy){
        this.horizontalPolicy = horizontalPolicy;
        return this;
    }

    public int horizontalPolicy(){
        return horizontalPolicy;
    }

    public AbstractPolicyLayout verticalPolicy(int verticalPolicy){
        this.verticalPolicy = verticalPolicy;
        return this;
    }

    public int verticalPolicy(){
        return verticalPolicy;
    }

    public AbstractPolicyLayout horizontalAlign(int horizontalAlign){
        this.horizontalAlign = horizontalAlign;
        return this;
    }

    public int horizontalAlign(){
        return horizontalAlign;
    }

    public AbstractPolicyLayout verticalAlign(int verticalAlign){
        this.verticalAlign = verticalAlign;
        return this;
    }

    public int verticalAlign(){
        return verticalAlign;
    }
}
