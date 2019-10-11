package br.com.grupoabril.teste.spring.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.grupoabril.teste.spring.models.Assinatura;

public class AssinaturaValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return Assinatura.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente.codigoCliente", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "produto.codigoProduto", "field.required");
	}
}
