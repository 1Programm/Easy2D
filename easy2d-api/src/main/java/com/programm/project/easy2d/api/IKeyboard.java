package com.programm.project.easy2d.api;

import java.awt.event.KeyEvent;

public interface IKeyboard {

    boolean isKeyPressed(int keyCode);

    default boolean A(){
        return isKeyPressed(KeyEvent.VK_A);
    }

    default boolean B(){
        return isKeyPressed(KeyEvent.VK_B);
    }

    default boolean C(){
        return isKeyPressed(KeyEvent.VK_C);
    }

    default boolean D(){
        return isKeyPressed(KeyEvent.VK_D);
    }

    default boolean E(){
        return isKeyPressed(KeyEvent.VK_E);
    }

    default boolean F(){
        return isKeyPressed(KeyEvent.VK_F);
    }

    default boolean G(){
        return isKeyPressed(KeyEvent.VK_G);
    }

    default boolean H(){
        return isKeyPressed(KeyEvent.VK_H);
    }

    default boolean I(){
        return isKeyPressed(KeyEvent.VK_I);
    }

    default boolean J(){
        return isKeyPressed(KeyEvent.VK_J);
    }

    default boolean K(){
        return isKeyPressed(KeyEvent.VK_K);
    }

    default boolean L(){
        return isKeyPressed(KeyEvent.VK_L);
    }

    default boolean M(){
        return isKeyPressed(KeyEvent.VK_M);
    }

    default boolean N(){
        return isKeyPressed(KeyEvent.VK_N);
    }

    default boolean O(){
        return isKeyPressed(KeyEvent.VK_O);
    }

    default boolean P(){
        return isKeyPressed(KeyEvent.VK_P);
    }

    default boolean Q(){
        return isKeyPressed(KeyEvent.VK_Q);
    }

    default boolean R(){
        return isKeyPressed(KeyEvent.VK_R);
    }

    default boolean S(){
        return isKeyPressed(KeyEvent.VK_S);
    }

    default boolean T(){
        return isKeyPressed(KeyEvent.VK_T);
    }

    default boolean U(){
        return isKeyPressed(KeyEvent.VK_U);
    }

    default boolean V(){
        return isKeyPressed(KeyEvent.VK_V);
    }

    default boolean W(){
        return isKeyPressed(KeyEvent.VK_W);
    }

    default boolean X(){
        return isKeyPressed(KeyEvent.VK_X);
    }

    default boolean Y(){
        return isKeyPressed(KeyEvent.VK_Y);
    }

    default boolean Z(){
        return isKeyPressed(KeyEvent.VK_Z);
    }

    default boolean NUM_0(){
        return isKeyPressed(KeyEvent.VK_0);
    }

    default boolean NUM_1(){
        return isKeyPressed(KeyEvent.VK_1);
    }

    default boolean NUM_2(){
        return isKeyPressed(KeyEvent.VK_2);
    }

    default boolean NUM_3(){
        return isKeyPressed(KeyEvent.VK_3);
    }

    default boolean NUM_4(){
        return isKeyPressed(KeyEvent.VK_4);
    }

    default boolean NUM_5(){
        return isKeyPressed(KeyEvent.VK_5);
    }

    default boolean NUM_6(){
        return isKeyPressed(KeyEvent.VK_6);
    }

    default boolean NUM_7(){
        return isKeyPressed(KeyEvent.VK_7);
    }

    default boolean NUM_8(){
        return isKeyPressed(KeyEvent.VK_8);
    }

    default boolean NUM_9(){
        return isKeyPressed(KeyEvent.VK_9);
    }

    default boolean ENTER(){
        return isKeyPressed(KeyEvent.VK_ENTER);
    }

    default boolean SPACE(){
        return isKeyPressed(KeyEvent.VK_SPACE);
    }

    default boolean BACK_SPACE(){
        return isKeyPressed(KeyEvent.VK_BACK_SPACE);
    }

    default boolean BACK_ESCAPE(){
        return isKeyPressed(KeyEvent.VK_ESCAPE);
    }

    default boolean LEFT(){
        return isKeyPressed(KeyEvent.VK_LEFT);
    }

    default boolean RIGHT(){
        return isKeyPressed(KeyEvent.VK_RIGHT);
    }

    default boolean UP(){
        return isKeyPressed(KeyEvent.VK_UP);
    }

    default boolean DOWN(){
        return isKeyPressed(KeyEvent.VK_DOWN);
    }

}
