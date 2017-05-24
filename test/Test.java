import math.comparison.ComparisonBuilder;
import math.operation.Operation;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import math.operation.OperationDefaultFactory;
import math.operation.OperationBuilder;
import math.operation.entities.Constant;
import math.operation.entities.Function;
import math.Operand;
import math.operation.entities.Operator;

import javax.security.auth.login.LoginException;

/**
 * A simple discord bot for testing Mathagician
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class Test
{
    public static void main(String[] args)
    {
        JDA jda;
        try {
            jda = new JDABuilder(AccountType.BOT)
                    .setToken(PrivateConstant.BOT_TOKEN)
                    .addEventListener(new MathListener())
                    .setAutoReconnect(true)
                    .setEnableShutdownHook(false)
                    .setMaxReconnectDelay(300)
                    .setEnableShutdownHook(true)
                    .buildBlocking();

            jda.getPresence().setGame(Game.of("Math Magicians - ,howtomath"));

        } catch (LoginException | IllegalArgumentException | InterruptedException | RateLimitedException e) {
            e.printStackTrace();
        }
    }

    public static class MathListener extends ListenerAdapter {
        @Override
        public void onMessageReceived(MessageReceivedEvent event) {
            if(event.getAuthor().isFake() || event.getAuthor().isBot())
                return;

            String message = event.getMessage().getContent();

            if(message.startsWith(",")) {
                String expression = message.replaceFirst(",", "");

                if (expression.startsWith("howtomath")) {

                    event.getChannel().sendMessage(howToMath().build()).queue();

                } else if (expression.startsWith("compare ")) {

                    String compare = expression.replaceFirst("compare ", "");
                    System.out.println(compare);
                    ComparisonBuilder comparison = new ComparisonBuilder(new Operation("1+1"), ">", new Operation("1+1"));
                    event.getChannel().sendMessage(String.valueOf(comparison.eval())).queue();

                } else {

                    EmbedBuilder embed = new EmbedBuilder();
                    try {
                        OperationBuilder builder = new OperationBuilder(expression).parse();

                        double operation = builder.build().eval();

                        embed.setTitle("Expression: " + expression, null)
                                .setDescription("Reverse Polish: " + builder.toString())
                                .addField("Answer", operation + "", true);

                        System.out.println("Expression: " + expression + "\nReverse Polish: " + builder.toString() + "\nAnswer: " + operation);

                    } catch (Exception ex) {
                        embed.setTitle("Oops!", null).setDescription("```\n\n" + ex.getMessage() + "```");
                    }
                    event.getChannel().sendMessage(embed.build()).queue();

                }
            }

        }

        public EmbedBuilder howToMath() {
            OperationDefaultFactory defaults = new OperationDefaultFactory();
            String operators = "", constants = "", functions = "";
            for(Operator op : defaults.getDefaultOperator()) {
                operators += op.getSection()+", ";
            }
            for(Constant con : defaults.getDefaultConstant()) {
                constants += con.getInitialKey()+", ";
            }
            for(Function func : defaults.getDefaultFunction()) {
                functions += func.getSection()+", ";
            }

            EmbedBuilder embed = new EmbedBuilder()
                .addField("Operators", operators, false)
                .addField("Constants", constants, false)
                .addField("Functions", functions, false)
                .addField("Example", "Prefix: **,**\n,sin(30)*2 gives 0.499999", false);
            return embed;
        }
    }
}