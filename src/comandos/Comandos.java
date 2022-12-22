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
	
	private Connection cn;
	
	public Comandos(Connection cn) {
		this.cn=cn;
	}

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
        
     
      // COMANDOS IMPORTANTES 
        
        // DEVUELVE FICHA DE JUGADOR
        if (event.getMessage().getContentRaw().equals(prefix+"jugador")) {
        	try {
				JugadorControlador jugadorControlador = new JugadorControlador(cn);
	            
				String idDiscord = String.valueOf(event.getAuthor().getIdLong());
				Jugador jugador = jugadorControlador.getJugador(idDiscord);
				
				if(jugador!=null) {
					EmbedBuilder embed = new EmbedBuilder();
		        	embed.setTitle("FICHA  "+jugador.getUsuario().toUpperCase());
		        	embed.addField("Estadísticas: ", ":moneybag: Dinero: " + String.valueOf(jugador.getDinero())+"\n"+
		        					":pencil: Nivel: " + String.valueOf(jugador.getLvl())+"\n"+
		        					":test_tube: EXP: " + String.valueOf(jugador.getExp())+"\n"+
		        					":drop_of_blood: Crítico: " + String.valueOf(jugador.getPorcentaje())+" % \n"+
		        					":crossed_swords: Daño: " + String.valueOf(jugador.getAtt())+"\n"+
		        					":shield: Defensa: " + String.valueOf(jugador.getDef())+"\n"+
		        					":heart: Vida: " + String.valueOf(jugador.getMaxhp())+"\n",false);
		        	event.getChannel().sendMessageEmbeds(embed.build()).queue();
		        	embed.clear();
				} else {
		            event.getMessage().reply("No existe ningún jugador, puedes crear uno utilizando el comando `!crear`").queue();
				}
				
			} catch (SQLException e) {
	            event.getMessage().reply("error").queue();
			}
        }
        
        // DEVUELVE INVENTARIO DE JUGADOR
        if (event.getMessage().getContentRaw().equals(prefix+"inventario")) {
        	try {
				JugadorControlador jugadorControlador = new JugadorControlador(cn);
	            
				String idDiscord = String.valueOf(event.getAuthor().getIdLong());
				Jugador jugador = jugadorControlador.getJugador(idDiscord);
				
				if(jugador!=null) {
					EmbedBuilder embed = new EmbedBuilder();
		        	embed.setTitle("Inventario "+jugador.getUsuario());
		        	embed.addField("Item 1", String.valueOf(jugador.getItem1()),false);
		        	embed.addField("Item 2", String.valueOf(jugador.getItem2()),false);
		        	embed.addField("Item 3", String.valueOf(jugador.getItem3()),false);
		        	embed.addField("Item 4", String.valueOf(jugador.getItem4()),false);
		        	embed.addField("Item 5", String.valueOf(jugador.getItem5()),false);
		        	event.getChannel().sendMessageEmbeds(embed.build()).queue();
		        	embed.clear();
					
				} else {
		            event.getMessage().reply("No existe ningún jugador, puedes crear uno utilizando el comando `!crear`").queue();
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
        
        // AÑADIR EXPERIENCIA (aumenta nivel)
        if (event.getMessage().getContentRaw().startsWith(prefix+"addEXP")) {
        	event.getChannel().sendTyping().queue(); // aparece bot escribiendo
        	try {
	            String mensaje = event.getMessage().getContentRaw();
	            mensaje = mensaje.replaceAll(" ", "");
	            String exp = mensaje.replaceAll("!addEXP", "");
            	int expINT = Integer.parseInt(exp);
            	
            	JugadorControlador jugadorControlador = new JugadorControlador(cn);
				String idDiscord = String.valueOf(event.getAuthor().getIdLong());
				Jugador jugador = jugadorControlador.getJugador(idDiscord);		
				
				if(jugador!=null) {
					jugadorControlador.ganaEXPJugador(expINT, jugador);
					event.getMessage().reply("Experiencia añadida correctamente").queue();	
				} else {
		            event.getMessage().reply("No existe ningún jugador, puedes crear uno utilizando el comando `!crear`").queue();
				}
            	
			} catch (Exception e) {
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


    
