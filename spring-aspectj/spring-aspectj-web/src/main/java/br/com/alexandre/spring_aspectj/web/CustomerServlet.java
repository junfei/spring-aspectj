package br.com.alexandre.spring_aspectj.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.alexandre.spring_aspectj.persistence.entity.Customer;
import br.com.alexandre.spring_aspectj.service.CustomerService;

public class CustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final Long id = Long.parseLong(req.getParameter("id"));
			
			final CustomerService customerService = (CustomerService) getServletContext().getAttribute("customerService");

			final Customer customer = customerService.findById(id);
			if (customer == null) {
				resp.sendError(404);
			} else {			
				final Gson gson = new Gson();
				final String json = gson.toJson(customer);
			
				resp.setContentType("application/json");
				resp.getWriter().write(json);
			}
		} catch (final NumberFormatException e) {
			resp.sendError(400);
		} catch (final RuntimeException e) {
			resp.sendError(500);
		}			
	}
	
}
