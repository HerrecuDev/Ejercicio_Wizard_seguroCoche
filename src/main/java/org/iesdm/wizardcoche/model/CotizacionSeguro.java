package org.iesdm.wizardcoche.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CotizacionSeguro {

    private Integer id;
    private String nombre;
    private String nif;
    private int edad;
    private int anioCarnet;
    private String marca;
    private String modelo;
    private int anioMat;
    private String uso;
    private boolean asistencia;
    private boolean vehsustitucion;
    private BigDecimal precioTotal;
    private String tipoCobertura;

}

