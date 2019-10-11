package br.com.grupoabril.teste.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.grupoabril.teste.spring.dao.AssinaturaDAO;
import br.com.grupoabril.teste.spring.dao.ClienteDAO;
import br.com.grupoabril.teste.spring.models.Cliente;
import br.com.grupoabril.teste.spring.validators.ClienteValidator;


@Controller
@Transactional
@RequestMapping("/clientes")
public class ClienteController {
	private static final String LOGIN_PAGE = "auth/loginPage";
	private static final String CLIENTE_MODEL = "cliente";
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Autowired
	private AssinaturaDAO assinaturaDAO;	
	
	@InitBinder
	public void InitBinder(WebDataBinder binder){
	    binder.addValidators(new ClienteValidator());
	}
	
	@RequestMapping(method=RequestMethod.GET)	
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("clientes/list");
		modelAndView.addObject("clientes", clienteDAO.list());
		return modelAndView;
	}
	
	@RequestMapping(value = "/listarAssinaturas", method = RequestMethod.GET)	
	public ModelAndView listarAssinaturas(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("assinaturas/listById");
		HttpSession session = request.getSession();
		Cliente user = (Cliente)session.getAttribute("usuarioLogado");
		modelAndView.addObject("assinaturas", assinaturaDAO.list(user.getCodigoCliente()));
		return modelAndView;
	}
	
	@RequestMapping("/form")
	public ModelAndView form(@ModelAttribute Cliente cliente) {
		ModelAndView modelAndView = new ModelAndView("clientes/form");
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable("id") Integer id){
		ModelAndView modelAndView = new ModelAndView("clientes/show");
		Cliente cliente = clienteDAO.find(id);
		modelAndView.addObject("cliente", cliente);
		return modelAndView;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
		clienteDAO.delete(id);
		redirectAttributes.addFlashAttribute("success", "Cliente excluido com sucesso");
		return new ModelAndView("redirect:/clientes");
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("cliente") @Valid Cliente cliente, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(cliente.getCodigoCliente() != null && cliente.getCodigoCliente() > 0) {
			
			if(bindingResult.hasErrors()){
				ModelAndView modelAndView = new ModelAndView("clientes/show");
				modelAndView.addObject("cliente", cliente);
				return modelAndView;
			}
			clienteDAO.update(cliente);
			
			redirectAttributes.addFlashAttribute("success", "Cliente alterado com sucesso");
			return new ModelAndView("redirect:/clientes");
		}

		if(bindingResult.hasErrors()){
			return form(cliente);
		}
		
//		long count = clienteDAO.findDuplicate(cliente);
//		if (count > 0)
//		{
//			ModelAndView modelAndView = new ModelAndView("clientes/form");
//			redirectAttributes.addFlashAttribute("error", "Cliente ja cadastrado");
//			return modelAndView;
//		}
		
		clienteDAO.save(cliente);
		redirectAttributes.addFlashAttribute("success", "Cliente cadastrado com sucesso");
		return new ModelAndView("redirect:/clientes");
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView handleDataIntegrityViolation(HttpServletRequest req, Exception ex) {

		ModelAndView model = new ModelAndView();
		model.setViewName("clientes/form");
		model.addObject("msg", "Cliente ja cadastrado");
		model.addObject("cliente", new Cliente());
		return model;

	}
}
