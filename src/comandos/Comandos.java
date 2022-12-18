package comandos;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.text.AttributeSet.ColorAttribute;

import org.jetbrains.annotations.NotNull;

import controlador.JugadorControlador;
import modelo.DbConnection;
import modelo.Jugador;
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
	        	try {
					DbConnection dbc=new DbConnection();
					Connection cn=dbc.getConnection();
		            event.getMessage().reply("Conexion exitosa").queue();
					dbc.disconnect();
					dbc=null;
				} catch (Exception e) {
		            event.getMessage().reply("error").queue();
				}
		        	
	        }
	     
	     // COMANDOS IMPORTANTES 
	        
	        // COMPRUEBA SI EXISTE JUGADOR
	        if (event.getMessage().getContentRaw().equals(prefix+"jugador")) {
	        	try {
	        		DbConnection dbc = new DbConnection();
					Connection cn = dbc.getConnection();
					JugadorControlador jugadorControlador = new JugadorControlador(cn);
		            
					String idDiscord = String.valueOf(event.getAuthor().getIdLong());
					Jugador jugador = jugadorControlador.getJugador(idDiscord);
					
					if(jugador!=null) {
			            event.getMessage().reply("El jugador "+jugador.getUsuario()+" existe").queue();
					} else {
			            event.getMessage().reply("No existe ningún jugador, puedes crear uno utilizando el comando !crear").queue();
					}
					
				} catch (SQLException e) {
		            event.getMessage().reply("error").queue();
				}
	        }
	        
	        // CREAR JUGADOR
	        if (event.getMessage().getContentRaw().equals(prefix+"crear")) {
	        	event.getChannel().sendTyping().queue(); // aparece bot escribiendo
	        	
	            String idDiscord = String.valueOf(event.getAuthor().getIdLong());
	            String nombre = String.valueOf(event.getAuthor().getName());
	            
	            Jugador nuevoJugador = new Jugador();
				nuevoJugador.setDiscid(idDiscord);
				nuevoJugador.setUsuario(nombre);
	            
				try {
					DbConnection dbc = new DbConnection();
					Connection cn = dbc.getConnection();
					JugadorControlador jugadorControlador=new JugadorControlador(cn);
				
					Jugador jugador = jugadorControlador.getJugador(idDiscord);
					if(jugador==null) {
						if(jugadorControlador.agregarJugador(nuevoJugador)) {
							event.getMessage().reply("Jugador añadido").queue();
						} else {
							event.getMessage().reply("No se puedo añadido").queue();
						}					
					} else {
			            event.getMessage().reply("El jugador ya existe").queue();
					}
					
				} catch (SQLException e) {
		            event.getMessage().reply("error").queue();
				}
				
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


    
