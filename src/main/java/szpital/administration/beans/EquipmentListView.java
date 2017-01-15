/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.administration.beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import szpital.administration.data.Equipment;
import szpital.administration.services.EquipmentService;

/**
 *
 * @author Przemyslaw
 */

@ManagedBean
@ViewScoped
@Getter
@Setter
public class EquipmentListView implements Serializable {

    @Inject
    private EquipmentService equipmentService;

    private String msg;
    private Long eq_id;

    /**
     * Creates a new instance of EquipmentListView
     *
     * @return
     */
    public List<Equipment> getEquipmentList() {
        List<Equipment> equipment = equipmentService.getAllEquipment();
        return equipment;
    }

    public void equipmentOnSelect(Long equipment_id) {
        eq_id=equipment_id;
    }
}
