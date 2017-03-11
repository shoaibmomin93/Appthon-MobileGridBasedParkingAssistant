package com.example.serversocket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;

public class MyServer extends Activity {
	Thread m_objThread;
	ServerSocket m_server;
	String m_strMessage;
	DataDisplay m_dataDisplay;
	Object m_connected;
	String msg;
    String servermsg;
    static boolean a,b,c;
    MainActivity m=new MainActivity();
    EntryTable et = new EntryTable(this);

	public MyServer() {

		
	}

	public void setEventListener(DataDisplay dataDisplay) {
		m_dataDisplay = dataDisplay;
		
	}
	

	public void startListening() {
		m_objThread = new Thread(new Runnable() {
			public void run() {

				try {
					
					while (true) {
						m_server = new ServerSocket(2001);
						Socket connectedSocket = m_server.accept();
						Message clientmessage = Message.obtain();
						ObjectInputStream ois = new ObjectInputStream(
								connectedSocket.getInputStream());
						String strMessage = (String) ois.readObject();
						clientmessage.obj = strMessage;
						mHandler.sendMessage(clientmessage);
						ObjectOutputStream oos = new ObjectOutputStream(
								connectedSocket.getOutputStream());
						//Thread.;
						//ShowParking();
						/*m.getSlotF1();
						m.getSlotF2();
						m.getSlotF3();*/
						if(a==true)
						{
							oos.writeObject("Go To Floor No.1");
							
						}
						
						else if(b==true)
						{
							oos.writeObject("Go To Floor No.2");
						}
						
						else if(c==true)
						{
							oos.writeObject("Go To Floor No.3");
						}
						
						//oos.writeObject(servermsg);
						//Thread.sleep(1500);
						ois.close();
						oos.close();
						m_server.close();
					}
					
				} catch (Exception e) {
					Message msg3 = Message.obtain();
					msg3.obj = e.getMessage();
					mHandler.sendMessage(msg3);
				}
			}
		});

		m_objThread.start();

	}

	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message status) {
			
			m_dataDisplay.Display(status.obj.toString());
			msg=status.obj.toString();
		}
	};
	
	private void ShowParking() {
		
		
		/*MainActivity m=new MainActivity();
	    String count=m.getSlot("F1");*/
		Debugg.msg(this ,"odiudhvdu");
		String count=et.getSlotData("F1");
	    int cnt=Integer.parseInt(count);
	    if(cnt<30)
	    {
	       	servermsg="F1";
	    	Debugg.msg(this , msg);
	    }
	
   }
		

}
