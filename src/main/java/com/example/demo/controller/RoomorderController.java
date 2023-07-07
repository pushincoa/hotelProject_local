package com.example.demo.controller;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.demo.model.Orderinfo;
import com.example.demo.model.Roomorder;
import com.example.demo.model.Webmember;
import com.example.demo.repository.OrderInfoRepository;
import com.example.demo.repository.WebmemberRepository;
import com.example.demo.service.RoomorderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class RoomorderController {
	@Autowired
	private RoomorderService os;
	@Autowired
	private WebmemberRepository w;
	
	@Autowired
	private OrderInfoRepository oi;
	
	@PostMapping("/order")
	@CrossOrigin("*")
	public String createOrder(@RequestBody Roomorder orderForm) {
//		System.out.println(orderForm.getNote());
//		System.out.println(orderForm.getWebmember().getEmail());
		String odNo = os.createNo();
		
		orderForm.setOrderNo(odNo);
		
		
		
		List<Orderinfo> Gd=orderForm.getOrderinfos();
		System.out.println(Gd.toString());
		for(Orderinfo x:Gd)
		{
			x.setRoomorder(orderForm);
		}
		
		//存進去orderlist;
		oi.saveAll(Gd);		
		
//		orderForm.addOrderinfo(null);
//		System.out.println(orderForm.toString());		
//		orderForm.getOrderinfos().get(0).setRoomorder(orderForm);
		System.out.println(orderForm.toString());
		System.out.println("Roomorder orderForm = "+orderForm);
		os.saveOrder(orderForm);
		return "success";
		
	}
	
	
}

