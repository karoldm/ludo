package utils;

import java.io.Serializable;
import java.util.HashMap;
/**
 *
 * @author Bruno Augusto Furquim
 * @author Gabriel Ribeiro Ferreira
 * @author Karolyne Domiciano Marques
 * @author Willian Yoshio Murayama 
 */
public abstract class PosicaoPeaoMap implements Serializable {

    /**
     *
     */
    public HashMap<Integer, int[]> posicao = new HashMap<>();
}
