package me.thesquidhq.recording;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Recording implements CommandExecutor {
	
	public Recording() {}
	
	private final Set<UUID> recording = new HashSet<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String args, String[] label) {
		
		if (!(sender instanceof Player))return false;
		
		Player player = (Player) sender;
		
		if (recording.add(player.getUniqueId())){
			
			Bukkit.getOnlinePlayers().forEach(p -> p.sendTitle(ChatColor.BLUE.toString() + ChatColor.BOLD + "[Broadcast]", ChatColor.RED.toString() + ChatColor.BOLD + player.getName() + ChatColor.BOLD + " is now recording!"));
			
			Bukkit.broadcastMessage(
					ChatColor.translateAlternateColorCodes('&', "&l" + player.getName() + " &c&lis now recording!"));
			
			player.setPlayerListName(
					ChatColor.translateAlternateColorCodes('&', "&c&lRECORD " + "&r" + player.getName()));
			
			player.setDisplayName(
					ChatColor.translateAlternateColorCodes('&', "&c&lRECORD " + "&r" + player.getName()));
			
		} else {
			
			recording.remove(player.getUniqueId());
			
			Bukkit.getOnlinePlayers().forEach(p -> p.sendTitle(ChatColor.BLUE.toString() + ChatColor.BOLD + "[Broadcast]", ChatColor.RED.toString() + ChatColor.BOLD + player.getName() + ChatColor.BOLD + " is no longer recording!"));
			
			Bukkit.broadcastMessage(
					ChatColor.translateAlternateColorCodes('&', "&l" + player.getName() + " &c&lis no longer recording!"));
			
			player.setDisplayName(player.getName());
			
			if (player.isOp()){
				
				player.setPlayerListName(
						ChatColor.translateAlternateColorCodes('&', "&c&lOP " + "&r" + player.getName()));
				
			}else player.setPlayerListName(player.getName());
			
		}
		return true;
	}
	
}
