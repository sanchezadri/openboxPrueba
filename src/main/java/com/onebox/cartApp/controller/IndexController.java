package com.onebox.cartApp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onebox.cartApp.pojos.Cart;
import com.onebox.cartApp.pojos.Product;

@Controller
public class IndexController {

	Logger logger = LoggerFactory.getLogger(IndexController.class);

	private static long id = 1 + (long) (Math.random() * (9999 - 1));
	private static List<Product> cart = new ArrayList<>();
	private static Cart carrito = new Cart(id, cart, 0, new Date());
	private static List<Product> listaProductos = Arrays.asList(
			new Product(1, "Antoine Griezmann", "Starz (Signed)", "10/10", 200, 0),
			new Product(2, "Cristiano Ronaldo", "Starz", "7/10", 100, 0),
			new Product(3, "Lionel Messi", "Classic", "5/10", 70, 0),
			new Product(4, "Paolo Maldini", "Leyenda", "8/10", 300, 0));

	
	/**
	 * Método que devuelve la pantalla principal
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String Index(Model model) {		
		
		model.addAttribute("InfoCarrito", carrito);
		model.addAttribute("listaJugadores", listaProductos);
		return "index.html";
	}

	/**
	 * Método que añade un producto al carrito
	 * @param query
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carrito", method = RequestMethod.GET)
	public String anadirACarrito(@RequestParam String query, Model model) {

		for (Product jugador : listaProductos) {
			if (Long.parseLong(query) == jugador.productId) {
				
				
				if(Collections.frequency(carrito.products, jugador) == 0) {
					carrito.products.add(jugador);
				}
				
				jugador.amount += Collections.frequency(carrito.products, jugador);
				carrito.total += jugador.price;
			}
		}

		model.addAttribute("InfoCarrito", carrito);
		return "carrito.html";
	}

	/**
	 * Método que elimina un producto por completo del carrito
	 * @param query
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carrito/remove", method = RequestMethod.GET)
	public String eliminarDeCarrito(@RequestParam String query, Model model) {

		for (Product jugador : listaProductos) {
			if (Long.parseLong(query) == jugador.productId) {
				carrito.total -= jugador.price*jugador.amount;
				jugador.amount = Collections.frequency(carrito.products, jugador)-1;
				carrito.products.remove(jugador);
			}
		}

		model.addAttribute("InfoCarrito", carrito);
		return "carrito.html";
	}
	
	/**
	 * Método que elimina una unidad de producto del carrito
	 * @param query
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carrito/removeOne", method = RequestMethod.GET)
	public String eliminarUnoDeCarrito(@RequestParam String query, Model model) {

		for (Product jugador : listaProductos) {
			if (Long.parseLong(query) == jugador.productId) {
				carrito.total -= jugador.price;
				jugador.amount -= 1;
				if(jugador.amount==0) {
					carrito.products.remove(jugador);
				}
			}
		}

		model.addAttribute("InfoCarrito", carrito);
		return "carrito.html";
	}

	/**
	 * Método que reinicia el carrito al completo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/restartCarrito", method = RequestMethod.GET)
	public String reiniciarCarrito(Model model) {

		carrito.id = 1 + (long) (Math.random() * (9999 - 1));
		carrito.products.removeAll(carrito.products);
		carrito.total = 0;
		carrito.timestamp = new Date();

		model.addAttribute("InfoCarrito", carrito);
		model.addAttribute("listaJugadores", listaProductos);
		
		return "index.html";
	}
	
	/**
	 * Método que lleva al carrito
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/toCarrito")
	public String alCarrito(Model model) {

		model.addAttribute("InfoCarrito", carrito);
		return "carrito.html";
	}
	
}