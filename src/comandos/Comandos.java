package comandos;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.util.Random;

import javax.swing.text.AttributeSet.ColorAttribute;

import org.jetbrains.annotations.NotNull;

import controlador.JugadorControlador;
import modelo.DbConnection;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Comandos extends ListenerAdapter {

	public String prefix="!"; 
	// Generamos un número aleatorio entre 1 y 100 al iniciar el juego
    private int targetNumber = new Random().nextInt(100) + 1;

	 @Override
	    public void onMessageReceived(MessageReceivedEvent event) {
		 
		 // PRUEBA COMANDOS BASICOS
		 
	        System.out.println("He recibido un mensaje de " +
	                event.getAuthor().getName() + ": " +
	                event.getMessage().getContentDisplay()
	        );

	        if (event.getMessage().getContentRaw().equals(prefix+"texto")) {
	            event.getChannel().sendMessage("Escribo texto").queue();
	        }
	        
	        if (event.getMessage().getContentRaw().equals(prefix+"respondeme")) {
	            event.getMessage().reply("no quiero responder").queue();
	        }
	        
	        if (event.getMessage().getContentRaw().equals(prefix+"arrobaUsuario")) {
	            event.getMessage().reply("<@!ID>").queue();
	        }
	        
	        if (event.getMessage().getContentRaw().equals(prefix+"bloque")) {
	        	EmbedBuilder embed = new EmbedBuilder();
	        	embed.setTitle("Titulo","https://www.youtube.com");
	        	embed.setDescription("Texto muy largo");
	        	embed.addField("Campo 1","contenido de prueba",false);
	        	embed.addField("Campo 2","contenido de prueba",false);
	        	embed.setFooter("Bot creado por Placido");
	        	event.getChannel().sendMessageEmbeds(embed.build()).queue();
	        	embed.clear();
	        } 
	        
	      //  PRUEBA CONEXION MYSQL
	        
	        if (event.getMessage().getContentRaw().equals(prefix+"conexion")) {
	        	DbConnection dbc=new DbConnection();
				Connection cn=dbc.getConnection();
				dbc.disconnect();
				dbc=null;
	        }
	       	        
	        
	    }
	 @Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
		 if (event.getName().equals("ayuda")) {
			 event.reply("texto con ayuda").queue();
		 }
	}
	 
	 @Override
		public void onReady(@NotNull ReadyEvent event) {
			 // comandos global prueba
		}
	
	 
	 
}


    
