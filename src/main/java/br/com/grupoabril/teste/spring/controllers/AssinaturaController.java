package br.com.grupoabril.teste.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.grupoabril.teste.spring.dao.AssinaturaDAO;
import br.com.grupoabril.teste.spring.dao.ClienteDAO;
import br.com.grupoabril.teste.spring.dao.ProdutoDAO;
import br.com.grupoabril.teste.spring.models.Assinatura;
import br.com.grupoabril.teste.spring.validators.AssinaturaValidator;


@Controller
@Transactional
@RequestMapping("/assinaturas")
public class AssinaturaController {

	@Autowired
	private AssinaturaDAO assinaturaDAO;
	
	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder){
	    binder.addValidators(new AssinaturaValidator());
	}
	
	
	@RequestMapping("/form")
	public ModelAndView form(@ModelAttribute Assinatura assinatura) {
		ModelAndView modelAndView = new ModelAndView("assinaturas/form");
		modelAndView.addObject("clientes", clienteDAO.list());
		modelAndView.addObject("produtos", produtoDAO.list());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("assinatura") @Valid Assinatura assinatura, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
			return form(assinatura);
		}
		assinatura.setAtivo(true);
		assinaturaDAO.save(assinatura);
		redirectAttributes.addFlashAttribute("success", "Assinatura realizada com sucesso");
		return new ModelAndView("redirect:/assinaturas");
	}
	
	@RequestMapping(method=RequestMethod.GET)	
	public ModelAndView listAll(){
		ModelAndView modelAndView = new ModelAndView("assinaturas/listAll");
		modelAndView.addObject("assinaturas", assinaturaDAO.listAll());
		return modelAndView;
	}
	
//	@RequestMapping(value = "/{codigoCliente}", method = RequestMethod.GET)	
//	public ModelAndView listById(@PathVariable("codigoCliente") Integer codigoCliente){
//		ModelAndView modelAndView = new ModelAndView("assinaturas/listById");
//		modelAndView.addObject("assinaturas", assinaturaDAO.list(codigoCliente));
//		return modelAndView;
//	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ModelAndView cancelarAssintura(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
		assinaturaDAO.cancel(id);
		redirectAttributes.addFlashAttribute("success", "Assinatura cancelada com sucesso");
		return new ModelAndView("redirect:/assinaturas");
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView handleDataIntegrityViolation(HttpServletRequest req, Exception ex) {

		ModelAndView model = new ModelAndView();
		model.setViewName("assinaturas/form");
		model.addObject("msg", "Erro ao incluir assinatura");
		model.addObject("assinatura", new Assinatura());
		return model;

	}
}
