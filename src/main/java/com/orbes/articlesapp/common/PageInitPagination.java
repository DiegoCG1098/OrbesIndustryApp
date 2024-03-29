package com.orbes.articlesapp.common;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.orbes.articlesapp.model.Article;
import com.orbes.articlesapp.model.Request;
import com.orbes.articlesapp.model.Supplier;
import com.orbes.articlesapp.service.ArticleService;
import com.orbes.articlesapp.service.RequestService;
import com.orbes.articlesapp.service.SupplierService;

@Component
public class PageInitPagination {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private SupplierService supplierService;
	
	/*@Autowired
	private BookService bookService;*/
	

	// pagination
	private static final int BUTTONS_TO_SHOW = 3;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10 };

	public  ModelAndView initPagination(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
		// If pageSize == null, return initial page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		/*
		 * If page == null || page < 0 (to prevent exception), return initial size Else,
		 * return value of param. decreased by 1
		 */
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Request> requestsList = requestService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(requestsList.getTotalPages(), requestsList.getNumber(), BUTTONS_TO_SHOW);
		initModelView.addObject("requestsList", requestsList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);
	
		/*
		
		Page<Request> requestsList = requestService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager2 = new PagerModel(requestsList.getTotalPages(), requestsList.getNumber(), BUTTONS_TO_SHOW);
		initModelView.addObject("requestsList", requestsList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager2);
		
		Page<Supplier> suppliersList = supplierService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager3 = new PagerModel(suppliersList.getTotalPages(), suppliersList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("suppliersList", suppliersList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager3);
		
		
		*/
	
		return initModelView;
	}

}
