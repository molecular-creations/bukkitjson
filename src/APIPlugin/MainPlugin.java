package APIPlugin;


import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.*;
import org.json.simple.*;

import java.text.*;
import java.util.*;


public class MainPlugin extends JavaPlugin {
	
	    private CommandListener m_commandListener;
	
	    public void onEnable() {
	    	
	    	getLogger().info("APIPlugin started!");
	    	m_commandListener = new CommandListener(this);
	    	m_commandListener.Listen();
	    }
	    
	    public String handleRequest(String uri, String method, Properties header, Properties params)
	    {
	    	getLogger().info("Parsing request!");
	    	
			String msg = "";
	    	
	    	try{
	    		
		    	String api = params.getProperty("api");
		    	
		    	if(api.equals("serverinfo"))
		    	{
		    		JSONObject ServerInfo = new JSONObject();
			    	JSONArray Players = new JSONArray();
			    	
			    	int numPlayers = 0;
		    		
		    		for (Player p : Bukkit.getOnlinePlayers()) {
		    			JSONObject Player = new JSONObject();
		    			Player.put("Name", p.getName());
		    			Player.put("Online", true);
		    			Player.put("IP", p.getAddress().toString());
		    			Player.put("Admin", p.isOp());
		    			Players.add(Player);
		    			numPlayers++;
		    		}
		    		
		    		ServerInfo.put("Host", Bukkit.getIp());
		    		ServerInfo.put("Name", Bukkit.getServerName());
		    		ServerInfo.put("MOTD", Bukkit.getMotd());
		    		ServerInfo.put("Port", Bukkit.getPort());
		    		ServerInfo.put("Version", Bukkit.getVersion());
		    		
		    		
		    		ServerInfo.put("PlayerCount", numPlayers);
		    		ServerInfo.put("MaxPlayers", Bukkit.getMaxPlayers());
		    		
		    		
		    		ServerInfo.put("Players", Players);
		    		
		    		JSONArray Plugins = new JSONArray();
		    		for(Plugin p : Bukkit.getPluginManager().getPlugins())
		    		{
		    			JSONObject plugin = new JSONObject();
		    			plugin.put("Name", p.getName());
		    			plugin.put("Description", p.getDescription());
		    		}
		    		
		    		ServerInfo.put("Plugins", Plugins);
		    		
		    		msg += ServerInfo.toJSONString();
		    	}
		    	
		    	else
		    	{
		    		msg = "Method not supported";
		    	}
	    	}
	    	
	    	catch(Exception e)
	    	{
	    		
	    	}
	    	
			return msg;
	    }
	    
	    
	    @Override
	    public void onDisable() {
	    	getLogger().info("APIPlugin stopped!");
	    }

}



