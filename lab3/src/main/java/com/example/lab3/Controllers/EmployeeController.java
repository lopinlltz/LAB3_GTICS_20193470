package com.example.lab3.Controllers;

import com.example.lab3.entity.Employees;
import com.example.lab3.repository.EmployeeRepository;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    final EmployeeRepository employeeRepository;
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/lista")
    public String listarTransportistas(Model model) {

        List<Employees> lista = employeeRepository.findAll();
        model.addAttribute("listaempleados", lista);

        return "employee/lista";
    }

    @PostMapping("/buscarPorNombre")
    public String buscarPorNombre(@RequestParam("searchField") String searchField, Model model) {

        List<Employees> lista1 = employeeRepository.buscarPorNombre(searchField);
        model.addAttribute("listaempleados", lista1);
        model.addAttribute("textoBuscado1", searchField);
        return "employee/buscarPorNombre";
    }

    public String buscarPorApellido(@RequestParam("searchField") String searchField, Model model) {

        List<Employees> lista2 = employeeRepository.buscarPorApellido(searchField);
        model.addAttribute("listaempleados", lista2);
        model.addAttribute("textoBuscado2", searchField);
        return "employee/buscarPorApellido";
    }

    @GetMapping("/editar")
    public String editarEmployee(Model model, @RequestParam("id") int id) {

        Optional<Employees> optionalEmployees = employeeRepository.findById(id);

        if (optionalEmployees.isPresent()) {
            Employees employees = optionalEmployees.get();
            model.addAttribute("employees", employees);
            return "employee/edit";
        } else {
            return "redirect:/employee/lista";
        }
    }

    @PostMapping("/updateOnlyPhone")
    public String actualizarTelefono (Employees employees) {
        employeeRepository.actualizarTelefono(employees.getPhonenumber(),employees.getEmployeeid());

        return "redirect:/employee/lista";
    }


}
