package com.proiectps.shopping;

import com.proiectps.shopping.model.*;
import com.proiectps.shopping.repository.*;
import com.proiectps.shopping.service.ComandaService;
import com.proiectps.shopping.service.PerfumeService;
import com.proiectps.shopping.service.ReviewService;
import com.proiectps.shopping.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PerfumeRepository perfumeRepository, UserRepository userRepository,
						   OrderRepository orderRepository,
						   OrderItemRepository orderItemRepository,
						   ReviewRepository reviewRepository,
						   UserService userService, PerfumeService perfumeService, ComandaService comandaService, ReviewService reviewService)
	{
		return args -> {

			Perfume parfum= new Perfume();
			parfum.setTitle("La Vie Est Belle");
			parfum.setBrand("Lancome");
			parfum.setGender("Female");
			parfum.setPrice(100);
			parfum.setVolume(100);
			parfum.setFragrance_top_notes("Iris");
			parfum.setFragrance_middle_notes("Jasmine");
			parfum.setFragrance_base_notes("Vanilla");
			parfum.setStock(10);
			parfum.setDescription("La Vie Est Belle is a new fragrance by Lancome and will be available in September 2012. The scent is oriental-floral.");
			perfumeService.savePerfume(parfum);

			Perfume parfum2= new Perfume();
			parfum2.setTitle("Hugo Boss Bottled");
			parfum2.setBrand("Hugo Boss");
			parfum2.setPrice(200);
			parfum2.setVolume(50);
			parfum2.setDescription("Hugo Boss Bottled is a masculine fragrance by Hugo Boss.The bottle was designed by Serge Mansau.");
			parfum2.setFragrance_top_notes("Lavender");
			parfum2.setFragrance_middle_notes("Geranium");
			parfum2.setFragrance_base_notes("Musk");
			parfum2.setGender("Male");
			parfum2.setStock(20);
			perfumeService.savePerfume(parfum2);

			Perfume parfum3= new Perfume();
			parfum3.setTitle("Sauvage");
			parfum3.setBrand("Dior");
			parfum3.setPrice(350);
			parfum3.setVolume(100);
			parfum3.setDescription("Sauvage is a new fragrance by Dior and will be available in September 2015. The scent is oriental-spicy.");
			parfum3.setFragrance_top_notes("Calabrian bergamot");
			parfum3.setFragrance_middle_notes("Sichuan pepper");
			parfum3.setFragrance_base_notes("Ambroxan");
			parfum3.setGender("Male");
			parfum3.setStock(30);
			perfumeService.savePerfume(parfum3);


			reviewService.createReview(new Review("Martin Ramona","Parfumul este bun", LocalDate.now(),4));
			reviewService.createReview(new Review("Muresan Daniel Ionut","Parfumul este foarte bun", LocalDate.now(),5));
			System.out.println(reviewService.getReviewById(1L));
			reviewService.updateReview(1L,new Review("Martin Ramona","Parfumul este foarte bun", LocalDate.now(),5));
			//reviewService.deleteReview(1L);

			User user=new User();
			user.setName("User1");
			user.setEmail("user1@yahoo.com");
			user.setPassword("1234");
			user.setIsAdmin(false);
			userService.createUser(user);


			OrderItem orderItem=new OrderItem();
			orderItem.setPerfume(parfum);
			orderItem.setQuantity(2);
			orderItemRepository.save(orderItem);

			Comanda comanda=new Comanda();
		 	comanda.setCity("Bucuresti");
			comanda.setPhone_number("0722222222");
			comanda.setAddress("Strada 1, nr 1");
			comanda.setDate(LocalDate.now());
			comanda.setTotal_price(200);
			comanda.getOrderItems().add(orderItem);

			List<Comanda> ordersList =new ArrayList<>();
			ordersList.add(comanda);

			User user2=new User();
			user2.setName("User2");
			user2.setEmail("user2@yahoo.com");
			user2.setPassword("0000");
			user2.setIsAdmin(true);
			user2.setOrdersList(ordersList);
			userService.createUser(user2);
			comanda.setUser(user2);

			comandaService.createOrder(comanda);

			Review review2=new Review();
			review2.setAuthor("Anonim");
			review2.setRating(3);
			review2.setMessage("Parfumul nu persista");
			reviewService.createReview(review2);

			parfum.getReviews().add(review2);
			perfumeService.savePerfume(parfum);

			System.out.println("Lista de reviews:");
			System.out.println(reviewService.getAllReviews());

			System.out.println("User cu id 1:");
            System.out.println(userService.getUserById(1L));

			System.out.println("Parfum cu id 1:");
			System.out.println(perfumeService.getPerfumeById(1L));

			//perfumeService.deletePerfume(parfum3.getId());
			//perfumeService.updatePrice(parfum3.getId(), 200);

			//System.out.println("Parfumuri de femei:");
			//System.out.println(perfumeService.findByGender("Female"));

			//System.out.println("Parfumuri de brand Hugo Boss:");
			//System.out.println(perfumeService.findByBrand("Hugo Boss"));

			//System.out.println("Comanda cu id 1:");
			//System.out.println(comandaService.getOrderById(1L));

			//aici nu se sterge comanda pentru ca userul nu e admin,daca modific user2 admin=true se sterge
			comandaService.deleteOrder(comanda.getId());

			//userService.updateUserInfo("user1@yahoo.com",new User("UserNou", "1234", "emailNou@yahoo.com", false));
           // userService.deleteUserById(2L);

		//	System.out.println(comandaService.getAllOrders());

 		};
	}

}
