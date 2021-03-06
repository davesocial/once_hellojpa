package com.alfonsotienda.holaspring.controller;

import com.alfonsotienda.holaspring.model.Cliente;
import com.alfonsotienda.holaspring.model.ClienteRepository;
import com.alfonsotienda.holaspring.model.Factura;
import com.alfonsotienda.holaspring.model.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * MainController
 */

@Controller
public class MainController {

    @GetMapping("/")
    @ResponseBody
    public String holaMundo(@RequestParam("nombre") String name, @RequestParam("edad") Integer edad) {
        return "Hola " + name + " tienes " + edad + "años";
    }

    @Autowired
    FacturaRepository facturaRepository;

    @GetMapping("creafactura")
    @ResponseBody
    public String creafactura(
        @RequestParam("fecha") String fecha,
        @RequestParam("id") Integer id,
        @RequestParam("total") Double total
        ) {
            Factura factura = new Factura();
            factura.setFecha(fecha);
            factura.setId(id);
            factura.setTotal(total);
            facturaRepository.save(factura); 

        return null;

        }

        @Autowired
        ClienteRepository clienteRepository;
    
        @GetMapping("/creacliente")
        @ResponseBody
        public String creacliente(
            @RequestParam("id") Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("telefono") String telefono
            ) {
                Cliente cliente = new Cliente();
                cliente.setId(id);
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                clienteRepository.save(cliente); 
    
            return null;
    
            }
    

    @GetMapping("/ingles")
    @ResponseBody
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/calculadora")
    public ModelAndView calculadoraHTML() {
        ModelAndView modelAndView = new ModelAndView("hello"); // Aquí va en html entre paréntesis
        modelAndView.addObject("mensaje", "");
        return modelAndView;
    }

    @PostMapping("/calculadora")
    public ModelAndView calculadoraHTMLPost(@RequestParam("operando1") Integer operando1,
            @RequestParam(value = "operando2", required = false) Integer operando2,
            @RequestParam("operacion") String operacion) {

        ModelAndView modelAndView = new ModelAndView("hello");

        String resultado = calcula(operando1, operacion, operando2);

        modelAndView.addObject("mensaje", "El resultado es " + resultado);

        return modelAndView;
    }

    private String calcula(Integer operando1, String operacion, Integer operando2) {
        double resultado = 0.0;
        switch (operacion) {
        case "suma":
            resultado = operando1 + operando2;
            break;
        case "resta":
            resultado = operando1 - operando2;
            break;
        case "multiplicacion":
            resultado = operando1 * operando2;
            break;
        case "division":
            resultado = operando1 / operando2;
            break;
        case "^":
            resultado = (int) Math.pow(operando1, operando2);
            break;
        case "mod":
            resultado = operando1 % operando2;
            break;
        case "raiz":
            resultado = Math.sqrt(operando1);
            break;
        default:
            return "No coinciden el operador";
        }
        return operando1 + " " + operacion + " " + operando2 + " = " + resultado;
    }

    @GetMapping("/insertar")
    public ModelAndView insertarHTML() {
        ModelAndView modelAndView = new ModelAndView("insertar");
        modelAndView.addObject("mensaje", "");
        return modelAndView;
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/insertar")
    public ModelAndView insertarHTMLPost(@RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos, @RequestParam("edad") String edad) {

        ModelAndView modelAndView = new ModelAndView("insertar");

        int nfilas = jdbcTemplate.update("INSERT ignore INTO Employees(first, last, age) VALUES(?,?,?)", nombre,
                apellidos, Integer.parseInt(edad));

        String resultado = "INSERT ignore INTO Employees(first, last, age) VALUES('" + nombre + "','" + apellidos + "',"
                + edad + ");";

        modelAndView.addObject("mensaje", "INSERTADAS " + nfilas + " FILAS MEDIANTE " + resultado);

        return modelAndView;
    }

    @GetMapping("/preguntas")
    public ModelAndView preguntasHTML() {
        ModelAndView modelAndView = new ModelAndView("preguntas");
        modelAndView.addObject("mensaje", "");
        return modelAndView;
    }

    @PostMapping("/preguntas")
    public ModelAndView preguntasHTMLPost(@RequestParam("respuesta1") String respuesta1,
            @RequestParam("respuesta2") String respuesta2, @RequestParam("respuesta3") String respuesta3) {

        ModelAndView modelAndView = new ModelAndView("preguntas");
        String resultado = "¿De que color es el caballo blanco de Santiago? " + respuesta1 + "\n¿Ultimo libro leido? "
                + respuesta2 + "\n¿Color favorito? " + respuesta3;

        modelAndView.addObject("mensaje", resultado);

        return modelAndView;
    }

    @GetMapping("/test")
    @ResponseBody
    public ModelAndView testlocuraHTML() {
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("mensaje", "");
        return modelAndView;

    }

    @PostMapping("/test")
    @ResponseBody

    public ModelAndView testloculraHTMLPost(@RequestParam("respuesta1") String respuesta1,
            @RequestParam("respuesta2") String respuesta2, @RequestParam("respuesta3") String respuesta3,
            @RequestParam("respuesta4") String respuesta4, @RequestParam("respuesta5") String respuesta5) {

        ModelAndView modelAndView = new ModelAndView("test");

        String resultado = calcula1(respuesta1, respuesta2, respuesta3, respuesta4, respuesta5);

        modelAndView.addObject("mensaje", resultado);

        return modelAndView;

    }

    private String calcula1(String respuesta1, 
                            String respuesta2, 
                            String respuesta3,
                            String respuesta4,
                            String respuesta5) {

        int suma1 = 0;
        int suma2 = 0, suma3 = 0, suma4= 0, suma5 = 0;

        // pregunta 1
        if (respuesta1.equals("0")) {
            suma1 = 0;
        } else if (respuesta1.equals("1")) {
            suma1 = 1;
        } else if (respuesta1.equals("2")) {
            suma1 = 2;
        }

        // pregunta 2
        if (respuesta2.equals("0")) {
            suma2 = 0;
        } else if (respuesta2.equals("1")) {
            suma2 = 1;
        } else if (respuesta2.equals("2")) {
            suma2 = 2;
        }
        // pregunta 3
        if (respuesta3.equals("0")) {
            suma3 = 0;
        } else if (respuesta3.equals("1")) {
            suma3 = 1;
        } else if (respuesta3.equals("2")) {
            suma3 = 2;
        }
        // pregunta 4
        if (respuesta4.equals("0")) {
            suma4 = 0;
        } else if (respuesta4.equals("1")) {
            suma4 = 1;
        } else if (respuesta4.equals("2")) {
            suma4 = 2;
        }
        // pregunta 5
        if (respuesta5.equals("0")) {
            suma5 = 0;
        } else if (respuesta5.equals("1")) {
            suma5 = 1;
        } else if (respuesta5.equals("2")) {
            suma5 = 2;
        }

        // calculo de la suma de puntos obtenidos en el test

        int suma;
        suma = suma1 + suma2 + suma3 + suma4 + suma5;

        System.out.println("RESULTADOS TEST 2.0 ULTIMA GENRACION - SUPER FIVE QUESTIONS?");

        System.out.println();

        System.out.println("\nHas obtenido " + suma + " puntos.");

        System.out.println();

        // cerramos la funcion con el return de los resulatdos obtenidos segun nuestra
        // clasificacion
        if (suma >= 0 && suma < 4) {
            return "estas al principio de la escala, completamente cuerdo pero MUY ABURRIDO";
        } else if (suma >= 4 && suma < 9) {
            return "estas en la parte media de la escala, vas en CAMINO HACIA LA LOCURA, BIEN!!!";
        } else  {
            return "enhorabuena ERES DIOS, eres el nuevo NORMAN BATES!!!";
        }

    }
}