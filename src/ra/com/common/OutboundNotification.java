package ra.com.common;

import org.smslib.AGateway;  
import org.smslib.IOutboundMessageNotification;  
import org.smslib.OutboundMessage;  
  
/** 
 * ��װ���Ͷ����� 
 */  
public class OutboundNotification implements IOutboundMessageNotification {  
      
    public void process(AGateway gateway, OutboundMessage msg) {  
       System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());  
    }  
} 
