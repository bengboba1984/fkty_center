package ra.com.common;

import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.Message.MessageEncodings;
import org.smslib.modem.SerialModemGateway;

public class SendMsgWebchinese {
	private static Service srv;

	static {

		srv = Service.getInstance();

		SerialModemGateway gateway = new SerialModemGateway("modem.com1",
				"COM1", 9600, "wavecom", ""); // ���ö˿��벨����
		gateway.setInbound(true);
		gateway.setOutbound(true);
		gateway.setSimPin("0000");

		srv.setOutboundMessageNotification(
				new IOutboundMessageNotification() {
					@Override
					public void process(AGateway arg0, OutboundMessage arg1) {
						System.out.println("@@@ send success@@");
						
						for (OutboundMessage.MessageStatuses c : arg1.getMessageStatus().values()){  
							System.out.println("===="+arg1.getRecipient()+"===="+arg1.getText()+"===="+c);
						}
					}
				});
		try {
			srv.addGateway(gateway);
			srv.startService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendMsg(String port, String telephone, String content)
			throws Exception {
		try {
			System.out.println("start send==========");
			OutboundMessage msg;
			msg = new OutboundMessage(telephone, content);
			//msg.setEncoding(MessageEncodings.ENCUCS2);
			System.out.println(srv.queueMessage(msg));
			//srv.sendMessage(msg);
		
//			OutboundMessage msg1;
//			msg1 = new OutboundMessage("18016260153", telephone+"----"+port+"----"+content);
//			msg1.setEncoding(MessageEncodings.ENCUCS2);
//			srv.sendMessage(msg1);
			System.out.println("end send==========");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//srv.stopService();
		}
	}
}
