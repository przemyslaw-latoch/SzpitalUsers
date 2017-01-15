/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.administration.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import szpital.administration.data.EquipmentType;
import szpital.administration.data.*;
import szpital.administration.services.*;

/**
 *
 * @author Przemyslaw
 */
@ManagedBean
@ViewScoped
@Getter
@Setter
public class AddEquipmentView implements Serializable {

    @Inject
    private EquipmentService equipmentService;
    
    private String msg;
    /**
     * Creates a new instance of AddEquipmentView
     */
    private final List<EquipmentType> types = Arrays.asList(EquipmentType.values());
    private final Converter typeConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
            if (s == null) {
                return null;
            }
            return EquipmentType.valueOf(s);
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
            return o != null ? ((EquipmentType) o).name() : "";
        }
    };
    private Equipment equipment = new Equipment();

    public void saveEquipment() {
        if(!equipment.getName().isEmpty() && equipment.getType() != null){
            equipmentService.save(equipment);
        }
    }
}
