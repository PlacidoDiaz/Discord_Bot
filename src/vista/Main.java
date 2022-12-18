package vista;

import java.io.ObjectInputFilter.Config;
import java.sql.Connection;

import javax.security.auth.login.LoginException;

import comandos.Comandos;
import controlador.JugadorControlador;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;
import modelo.DbConnection;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;


	public class Main {
				
		public static JDA jda;
		private static Dotenv config;
		
		public static void main(String[] args) throws LoginException, InterruptedException, IllegalArgumentException, DotenvException {
			config = Dotenv.configure().ignoreIfMissing().load();
			String token = config.get("TOKEN");

			// CONEXIÓN BD
			DbConnection dbc = new DbConnection();
			Connection cn = dbc.getConnection();
			
			JDA jda = JDABuilder.createDefault(token)
			.setActivity(Activity.listening(" tutoriales "))
			.setStatus(OnlineStatus.ONLINE)
			.addEventListeners(new Comandos(cn))
			.setChunkingFilter(ChunkingFilter.ALL) // Ver todos los miembros
			.setMemberCachePolicy(MemberCachePolicy.ALL)
			.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES) // Permite tener acceso a los mensajes enviados (necesita verificación en la aplicacion web)
			.build().awaitReady(); // await permite cargar los comandos con slash
			
			
			/**
			 * COMANDOS SLASH
			 *
			 * Comando Guild	->	Automáticamente compilado (max 100)
			 * Comando Global	->	Tarda 1 hora en compilar (ilimitado) 
			 *
			 */
			
			Guild guild =  jda.getGuildById("738110392747819060");
		
			if(guild != null) {
				guild.upsertCommand("ayuda", "recibes ayuda").queue();

			}
				

		}
		
	

	}


