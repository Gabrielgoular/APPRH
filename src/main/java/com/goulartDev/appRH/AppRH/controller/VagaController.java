package com.goulartDev.appRH.AppRH.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.goulartDev.appRH.AppRH.model.Candidato;
import com.goulartDev.appRH.AppRH.model.Vaga;
import com.goulartDev.appRH.AppRH.repository.CandidatoRepository;
import com.goulartDev.appRH.AppRH.repository.VagaRepository;



@Controller
public class VagaController {
	
	@Autowired
	private VagaRepository vagaRepository;
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@RequestMapping(value="/cadastrarVaga", method = RequestMethod.GET)
	public String form() {
		
		return "vaga/formVaga";
	}
	//logica de cadastrar vaga
	@RequestMapping(value="/cadastrarVaga", method= RequestMethod.POST ) 
	public String form( @Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {	
		attributes.addFlashAttribute("mensagem","Verifique os campos....");
		return "redirect:/cadastrarVaga";
		}
		vagaRepository.save(vaga);
			attributes.addFlashAttribute("mensagem","Vaga cadastrada com sucesso!");
		return "redirect:/cadastrarVaga";
	}
	//Lista vagas
	@RequestMapping("/vagas")
	public ModelAndView listaVagas(){
		ModelAndView modelAndView = new ModelAndView("vaga/listaVaga");
		Iterable<Vaga> vagas = vagaRepository.findAll();
		modelAndView.addObject("vagas", vagas);
		return modelAndView;
	}
	// Detalhes vaga candidato
	@RequestMapping(value="/vaga/{codigo}")
	public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo) {
		Vaga vaga = vagaRepository.findByCodigo(codigo);
		ModelAndView modelAndView = new ModelAndView("vaga/detalhesVaga");
		modelAndView.addObject("vaga", vaga);
		
		Iterable<Candidato>candidatos = candidatoRepository.findByVaga(vaga);
		modelAndView.addObject("candidatos",candidatos);
		return modelAndView;
	}
	//adicionar candidato
	@RequestMapping(value= "/vaga/{codigo}", method = RequestMethod.POST )
	public String detalhesVagaPost(@PathVariable("codigo") long codigo,
			@Valid Candidato candidato, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos....");
			return "redirect:/vaga/{codigo}";
		}
		//rg duplicado
		if(candidatoRepository.findByRg(candidato.getRg()) != null) {
			attributes.addFlashAttribute("mensagem_erro", " RG duplicado");
			return "redirect:/vaga/{codigo}";
		}
		Vaga vaga = vagaRepository.findByCodigo(codigo);
			candidato.setVaga(vaga);
			candidatoRepository.save(candidato);
			attributes.addFlashAttribute("mensagem ", "Candidato adicionado com sucesso!");
			return "redirect:/vaga/{codigo}";
	}
	
	//Deletar vaga
	@RequestMapping("/deletarVaga")
	public String deletarVaga(long codigo) {
		Vaga vaga= vagaRepository.findByCodigo(codigo);
		vagaRepository.delete(vaga);
		return "redirect:/vagas";
	}
	@RequestMapping("/deletarCandidato")
	public String deletarCandidato(String rg) {
		Candidato candidato= candidatoRepository.findByRg(rg);
		Vaga vaga = candidato.getVaga();
		String codigo = "" + vaga.getCodigo();
		candidatoRepository.delete(candidato);
		return "redirect:/vaga/" + codigo;
		
	}
	//Metodos que atualizam vaga
	//formulario edição de vaga
	@RequestMapping(value="/editar-Vaga", method = RequestMethod.GET)
	public ModelAndView editarVaga(long codigo) {
		Vaga vaga = vagaRepository.findByCodigo(codigo);
		ModelAndView modelAndView = new ModelAndView("vaga/update-vaga");
		modelAndView.addObject("vaga", vaga);
		return modelAndView;
	}
	//updatevaga
	@RequestMapping(value="/editar-vaga", method = RequestMethod.POST)
	public String updateVaga(@Valid Vaga vaga, 
		BindingResult result,RedirectAttributes attributes) {
		vagaRepository.save(vaga);
		attributes.addFlashAttribute("sucesso "," Vaga alterada com sucesso!!" );
		
		long codigoLong = vaga.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/vaga/"+ codigo;
		}
}
