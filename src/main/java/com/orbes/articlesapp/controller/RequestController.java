package com.orbes.articlesapp.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.orbes.articlesapp.common.PageInitPagination;
import com.orbes.articlesapp.model.Request;
import com.orbes.articlesapp.service.RequestService;

@Controller
@RequestMapping("/requests")
public class RequestController {
	protected static final String REQUEST_VIEW = "requests/showRequest"; // view template for single article
	protected static final String REQUEST_ADD_FORM_VIEW = "requests/newRequest"; // form for new article
	protected static final String REQUEST_EDIT_FORM_VIEW = "requests/editRequest"; // form for editing an article

	protected static final String REQUEST_PAGE_VIEW = "requests/allRequests"; // list with pagination

	protected static final String INDEX_VIEW = "index"; // articles with pagination

	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private RequestService requestService;

	@GetMapping("/{id}")
	public String getRequestyId(@PathVariable(value = "id") Long requestId, Model model) {
		model.addAttribute("request", requestService.findById(requestId));
		return REQUEST_VIEW;
	}

	
	@GetMapping
	public ModelAndView getAllRequests(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPagination(pageSize, page, REQUEST_PAGE_VIEW);
		return modelAndView;
	}
	
	@GetMapping("/new")
	public String newRequest(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("request")) {
			model.addAttribute("request", new Request());
		}
		return REQUEST_ADD_FORM_VIEW;
	}

	@PostMapping("/create")
	public String createRequest(@Valid Request request, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() ) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.request", result);
			attr.addFlashAttribute("request", request);

			/*attr.addFlashAttribute("error", "Error a la hora de crear la solicitud");*/

			return "redirect:/requests/new";
		}
		Request newRequest = requestService.createRequest(request);
		model.addAttribute("request", newRequest);

		return "redirect:/requests/" + newRequest.getId();
	}

	@GetMapping("{id}/edit")
	public String editRequest(@PathVariable(value = "id") Long requestId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("request")) {
			model.addAttribute("request", requestService.findById(requestId));
		}
		return REQUEST_EDIT_FORM_VIEW;
	}

	@PostMapping(path = "/{id}/update")
	public String updateRequest(@PathVariable(value = "id") Long requestId, @Valid Request requestDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() ) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.request", result);
			attr.addFlashAttribute("request", requestDetails);

			attr.addFlashAttribute("error", "Error a la hora de actualizar la solicitud");

			return "redirect:/requests/" + requestDetails.getId() + "/edit";
		}

		requestService.updateRequest(requestId, requestDetails);
		model.addAttribute("request", requestService.findById(requestId));
		return "redirect:/requests/" + requestId;
	}

	@GetMapping(value = "/{id}/delete")
	public String deleteRequest(@PathVariable("id") Long requestId) {
		//Article article = articleService.findById(articleId);
		// String title = article.getTitle();
		requestService.deleteRequest(requestId);
		return "redirect:/requests";
	}
}
