package com.example.web.ws.config1;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.web.dto.CalcInput;
import com.example.web.dto.Result;

@Controller
public class WebSocketController {

	/**
	 * @see http://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html
	 * 
	 * http://host:port/myApp/myEndpoint/{server-id}/{session-id}/{transport}
	 * {server-id} - useful for routing requests in a cluster but not used otherwise.
	 * {session-id} - correlates HTTP requests belonging to a SockJS session.
	 * {transport} - indicates the transport type, e.g. "websocket", "xhr-streaming", etc.
	 * 
	 * http://localhost:8080/add/info
	 * 200 OK application/json
	 * {"entropy":-1812588026,"origins":["*:*"],"cookie_needed":true,"websocket":true}
	 * 
	 * Opening Web Socket
	 * ws://localhost:8080/addnum/174/4ft55nzs/websocket
	 * 101 Switching Protocols - 101 Web Socket Protocol Handshake
	 * 
	 * wss://parakhnextgen.appspot.com/add/300/raow_y86/websocket
	 * 400 Bad Request
	 * 
	 * 


>>> SUBSCRIBE
id:sub-0
destination:/topic/showResult
	 * 
	 * 
>>> SEND
destination:/calcApp/addnum		MessageBrokerRegistry#setApplicationDestinationPrefixes("/calcApp");
content-length:23
	 * 
	 * 
<<< MESSAGE
destination:/topic/showResult
content-type:application/json;charset=UTF-8
subscription:sub-0
message-id:4ft55nzs-0
content-length:18

{"result":"1+2=3"} 
	 * 
	 * 
	 */
	@MessageMapping("/addnum" )
    @SendTo("/topic/showResult")
    public Result addNum(CalcInput input) throws Exception {
        Thread.sleep(500);
        Result result = new Result(input.getNum1()+"+"+input.getNum2()+"="+(input.getNum1()+input.getNum2())); 
        return result;
    }

}
