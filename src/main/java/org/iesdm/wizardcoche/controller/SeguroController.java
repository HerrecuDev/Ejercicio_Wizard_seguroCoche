package org.iesdm.wizardcoche.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.iesdm.wizardcoche.model.CotizacionSeguro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller

@RequestMapping("/seguros")
public class SeguroController {

    @GetMapping("/calculos/cotizacion/paso1")
    public String paso1(Model model , HttpSession httpSession){

         var cotizacionSegurohttpSession = (CotizacionSeguro) httpSession.getAttribute("cotizacionSeguro");

         if ((cotizacionSegurohttpSession != null)){

             model.addAttribute("cotizacionSeguro" , cotizacionSegurohttpSession);

         }else{

             model.addAttribute("cotizacionSeguro" , new CotizacionSeguro());


         }

        return "paso1";

    }


    //Genero el GET del paso2:

    @GetMapping("/calculos/cotizacion/paso2")
    public String anteriorDesdePaso3Post(Model model , HttpSession httpSession){

        /// STring || Object
        var cotizacionSeguroHttpSession = (CotizacionSeguro) httpSession.getAttribute("cotizacionSeguro");

        model.addAttribute("cotizacionSeguro" , cotizacionSeguroHttpSession);

        return "paso2";
    }



    @PostMapping("/calculos/cotizacion/paso2")
    public String paso1Post(Model model, @ModelAttribute CotizacionSeguro cotizacionSeguro, HttpSession httpSession){


        //Recuerda que podrias utilizar un mecanismo mas abstracto mediante @SessionAttributes :

        httpSession.setAttribute("cotizacionSeguro" , cotizacionSeguro);

        //Entre peticiones consecutivas se mantiene el mapa de httpSession<String,Object>

        model.addAttribute("cotizacionSeguro" , cotizacionSeguro);

        return "paso2";
    }



    @PostMapping("/calculos/cotizacion/paso3")
    public String paso2Post(Model model, @ModelAttribute CotizacionSeguro cotizacionSeguro, HttpSession httpSession){

       var cotizacionSeguroHttpSession =  (CotizacionSeguro)httpSession.getAttribute("cotizacionSeguro");

       if (cotizacionSeguro.getTipoCobertura() == null){
           //La 1era vez que aterrizo en el FORMULARIO
           cotizacionSeguroHttpSession.setMarca(cotizacionSeguro.getMarca());
           cotizacionSeguroHttpSession.setModelo(cotizacionSeguro.getModelo());
           cotizacionSeguroHttpSession.setAnioMat(cotizacionSeguro.getAnioMat());
           cotizacionSeguroHttpSession.setUso(cotizacionSeguro.getUso());


       }
        //Entre peticiones consecutivas se mantiene el mapa de httpSession<String,Object>

        model.addAttribute("cotizacionSeguro" , cotizacionSeguroHttpSession);

        model.addAttribute("datosConductor" , cotizacionSeguro.getNombre()
                + " | " + cotizacionSeguro.getEdad()
                + " | " + cotizacionSeguro.getAnioCarnet()
        );


        model.addAttribute("datosVehiculo " , cotizacionSeguro.getMarca()
                + " | " + cotizacionSeguro.getModelo()
                + " | " + cotizacionSeguro.getAnioMat()
                + " | " + cotizacionSeguro.getUso()

        );

        if (cotizacionSeguro.getTipoCobertura() !=null){

            //Vengo del propio  POST :

            cotizacionSeguroHttpSession.setTipoCobertura(cotizacionSeguro.getTipoCobertura());
            cotizacionSeguroHttpSession.setAsistencia(cotizacionSeguro.isAsistencia());
            cotizacionSeguroHttpSession.setVehsustitucion(cotizacionSeguro.isVehsustitucion());


            model.addAttribute("datosCobertura" + cotizacionSeguroHttpSession.getTipoCobertura()
            + " | " + cotizacionSeguroHttpSession.isAsistencia()
            + " | " + cotizacionSeguroHttpSession.isVehsustitucion()
            );
        }




        return "paso3";
    }
}
