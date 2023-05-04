package model;

import java.io.Serializable;
/**
 *
 * @author Bruno Augusto Furquim
 * @author Gabriel Ribeiro Ferreira
 * @author Karolyne Domiciano Marques
 * @author Willian Yoshio Murayama 
 */
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
