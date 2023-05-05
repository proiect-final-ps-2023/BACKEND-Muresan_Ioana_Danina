package com.proiectps.shopping.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proiectps.shopping.dto.PerfumeDTO;
import com.proiectps.shopping.model.Ad;
import com.proiectps.shopping.service.impl.ComandaServiceImpl;
import com.proiectps.shopping.service.impl.PerfumeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    @Autowired
    private PerfumeServiceImpl perfumeService;

    @Autowired
    private ComandaServiceImpl comandaService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        List<PerfumeDTO> list = perfumeService.findAll();
        for (int i = 0; i < 100; i++)
        {
            for (PerfumeDTO perfumeDTO : list)
            {
                if (perfumeDTO.getNew_price() != null)
                {
                    String description = "Pretul produsului  '" + perfumeDTO.getTitle() + "' a fost modificat de la " + perfumeDTO.getPrice() + " la " + perfumeDTO.getNew_price() + " lei !!!";
                    String name;
                    if (perfumeDTO.getNew_price() > perfumeDTO.getPrice()) {
                       name= "Crestere pret";
                    }
                    else {
                        name= "Reducere pret";
                    }
                    Ad ad = new Ad(name, description);
                    if (name.equals("Crestere pret")) {
                        ad.setIncreased(true);
                    }
                    else {
                        ad.setIncreased(false);
                    }
                    //Sending StockPrice
                    TextMessage message = new TextMessage(objectMapper.writeValueAsString(ad));
                    session.sendMessage(message);
                    Thread.sleep(3000);
                }
            }

            if(comandaService.getOrderById(2102L).get().getTransport()) {
                Ad ad = new Ad("Transport gratuit", "In aceasta saptamana beneficiati de transport gratuit la orice comanda !!!");
                ad.setIncreased(true);
                TextMessage message = new TextMessage(objectMapper.writeValueAsString(ad));
                session.sendMessage(message);
                Thread.sleep(3000);
            }
            else {
                Ad ad = new Ad("Transport 20 lei", "In aceasta saptamana orice transport este 20 lei!!!");
                ad.setIncreased(false);
                TextMessage message = new TextMessage(objectMapper.writeValueAsString(ad));
                session.sendMessage(message);
                Thread.sleep(3000);
            }
        }
        sessions.add(session);
    }
}