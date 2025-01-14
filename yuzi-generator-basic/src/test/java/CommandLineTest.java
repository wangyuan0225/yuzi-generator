import com.yupi.cli.example.SubCommandExample;
import org.junit.jupiter.api.*;
import picocli.CommandLine;

public class CommandLineTest {
    CommandLine commandLine = new CommandLine(new SubCommandExample())
            .addSubcommand(new SubCommandExample.AddCommand())
            .addSubcommand(new SubCommandExample.DeleteCommand())
            .addSubcommand(new SubCommandExample.QueryCommand());

    @Test
    public void testMainCommand() {
        String[] args = new String[]{};
        int exitCode = commandLine.execute(args);
        Assertions.assertEquals(0, exitCode);
    }

    @Test
    public void testHelpCommand() {
        String[] args = new String[]{"--help"};
        int exitCode = commandLine.execute(args);
        Assertions.assertEquals(0, exitCode);
    }

    @Test
    public void testAddSubCommand() {
        String[] args = new String[]{"add"};
        int exitCode = commandLine.execute(args);
        Assertions.assertEquals(0, exitCode);
    }

    @Test
    public void testDeleteSubCommand() {
        String[] args = new String[]{"delete"};
        int exitCode = commandLine.execute(args);
        Assertions.assertEquals(0, exitCode);
    }

    @Test
    public void testQuerySubCommand() {
        String[] args = new String[]{"query"};
        int exitCode = commandLine.execute(args);
        Assertions.assertEquals(0, exitCode);
    }
}
