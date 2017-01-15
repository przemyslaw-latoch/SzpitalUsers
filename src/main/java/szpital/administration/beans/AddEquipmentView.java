/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.administration.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Przemyslaw
 */
@ManagedBean
@ViewScoped
@Getter
@Setter
public class AddEquipmentView implements Serializable {

    private String msg;
    /**
     * Creates a new instance of AddEquipmentView
     */
    public AddEquipmentView() {
    }

}
