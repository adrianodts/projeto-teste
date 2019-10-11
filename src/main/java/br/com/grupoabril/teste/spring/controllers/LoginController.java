package br.com.grupoabril.teste.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.grupoabril.teste.spring.dao.AssinaturaDAO;
import br.com.grupoabril.teste.spring.dao.UserDAO;
import br.com.grupoabril.teste.spring.models.Cliente;
import br.com.grupoabril.teste.spring.validators.UserValidator;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final String LOGIN_PAGE = "auth/loginPage";
	private static final String CLIENTE_MODEL = "cliente";
	
	@Autowired 
	UserDAO userDAO;

	@Autowired 
	AssinaturaDAO assinaturaDAO;

	@InitBinder
	public void InitBinder(WebDataBinder binder){
	    binder.addValidators(new UserValidator());
	}

	@GetMapping(value = "/loginPage")
	public ModelAndView loginPage() {
		ModelAndView modelAndView = new ModelAndView(LOGIN_PAGE);
		modelAndView.addObject(CLIENTE_MODEL, new Cliente());
		return modelAndView;
	}
	
	@GetMapping(value = "/processLogout")
	public ModelAndView processLogout(HttpServletRequest request) {	
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView("/index");
	}
	
	@PostMapping(value = "/processLogin")
	public ModelAndView processLogin(@ModelAttribute("cliente") @Valid Cliente cliente, 
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) {	
		
		ModelAndView modelAndView = new ModelAndView(LOGIN_PAGE);
		modelAndView.addObject(CLIENTE_MODEL, cliente);
		
		if(bindingResult.hasErrors())			
			return modelAndView;
		
		Cliente usuario = userDAO.loadUserByUsername(cliente);
		if(usuario == null) {
			redirectAttributes.addFlashAttribute("error", "O e-mail ou a senha digitada esta incorreta. Por favor, tente novamente.");
			return modelAndView;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("usuarioLogado", usuario);
		
		return new ModelAndView("redirect:/clientes/listarAssinaturas");		
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleDataIntegrityViolation(HttpServletRequest req, Exception ex) {
		ModelAndView model = new ModelAndView(LOGIN_PAGE);
		model.addObject("msg", "O e-mail ou a senha digitada esta incorreta. Por favor, tente novamente.");
		model.addObject(CLIENTE_MODEL, new Cliente());
		return model;
	}

}