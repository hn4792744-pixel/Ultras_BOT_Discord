package com.ultras.bans.config;
import com.ultras.bans.ULTRASBansPlugin; import org.bukkit.configuration.file.YamlConfiguration; import java.io.File; import java.util.*;
public final class ConfigManager {
 private final ULTRASBansPlugin plugin; private final Map<String,YamlConfiguration> cfg=new HashMap<>();
 public ConfigManager(ULTRASBansPlugin p){plugin=p;}
 public void load(){ plugin.saveDefaultConfig(); plugin.reloadConfig(); String[] files={"database.yml","permissions.yml","security.yml","punishment.yml","proxy.yml"}; for(String f:files) loadFile(f); loadFile("lang/"+plugin.getConfig().getString("language","en")+"/messages.yml"); }
 private void loadFile(String path){ File f=new File(plugin.getDataFolder(),path); if(!f.exists()){plugin.saveResource(path,false);} File actual=f.exists()?f:new File(plugin.getDataFolder(),path); if(!actual.exists()){plugin.getLogger().warning("Missing config resource: "+path); return;} cfg.put(path,YamlConfiguration.loadConfiguration(actual)); }
 public YamlConfiguration get(String path){ return cfg.get(path); }
 public YamlConfiguration gui(String name){ String path="gui/"+name+".yml"; File f=new File(plugin.getDataFolder(),path); if(!f.exists()) plugin.saveResource(path,false); return YamlConfiguration.loadConfiguration(f); }
 public YamlConfiguration discord(String name){ String path="discord/"+name+".yml"; File f=new File(plugin.getDataFolder(),path); if(!f.exists()) plugin.saveResource(path,false); return YamlConfiguration.loadConfiguration(f); }
 public void reload(){ load(); }
}
