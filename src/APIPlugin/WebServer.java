package APIPlugin;

import java.io.*;
import java.util.*;

import org.bukkit.plugin.java.JavaPlugin;

import APIPlugin.NanoHTTPD.Response;

public class WebServer extends NanoHTTPD{
	
	MainPlugin Handler;
		
		public WebServer(MainPlugin m_Handler) throws IOException
		{
			super(8080, new File("."));
			Handler = m_Handler;
		}

	    public Response serve(String uri, String method, Properties header, Properties params, Properties files)
	    {
	    	String Data = Handler.handleRequest(uri, method, header, params);
			return new NanoHTTPD.Response( NanoHTTPD.HTTP_OK, "text/plain", Data );
	    }


		public void Listen()
		{
			try
			{
				new WebServer(Handler);
			}
			catch( IOException ioe )
			{
				System.err.println( "Couldn't start server:\n" + ioe );
				System.exit( -1 );
			}
			try { while(true){} } catch( Throwable t ) {};
		}
}
