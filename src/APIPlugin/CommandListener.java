package APIPlugin;

import java.util.Properties;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandListener {

    private WebServer m_Server;
    private MainPlugin m_Plugin;
	
    public CommandListener(MainPlugin Plugin)
    {
	m_Plugin = Plugin;
    }
	
 
    public void Listen()
    {
        m_Plugin.getLogger().info("Attempting to listen...");
        
        try
        {
		m_Server = new WebServer(m_Plugin);
        }
        
        catch(Exception e)
        {
            	m_Plugin.getLogger().info("Error listening - " + e);
        }

    }

    public void Stop()
    {
	m_Plugin.getLogger().info("Closing server...");

	try
	{
		// not yet implemented
	}

	catch(Exception e)
	{

	}
	
    }


}