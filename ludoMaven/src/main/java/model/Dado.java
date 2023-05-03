package model;

import java.io.Serializable;

public class Dado implements Serializable {

    private int dado = 0;

    /**
     *
     * @return
     */
    public int getDado() {
        return dado;
    }

    /**
     *
     * @param dado
     */
    public void setDado(int dado) {
        this.dado = dado;
    }

}
