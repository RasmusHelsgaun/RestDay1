/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAControl;

import javax.persistence.Persistence;

/**
 *
 * @author Rasmus
 */
public class Structure {
    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
    }
}
